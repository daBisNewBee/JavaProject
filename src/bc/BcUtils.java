package bc;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.*;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;

import java.io.*;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.util.*;

public class BcUtils {

    public static final int LEVEL_NONE = 0;
    public static final int LEVEL_ONE = 1;
    public static final int LEVEL_ALL = 2;

    static String signAlgorithm = "SHA1WITHRSA";
//    static String signAlgorithm = "SM3WITHSM2";
    static String pfxPath = "res/sm2.pfx";
    static String pfxPin = "123456";
    static List<Certificate> mCertChain;
    // 仅校验一级证书，即不考虑自签名证书为信任证书
    static Certificate mCertificate;

    static{
        Provider provider = new BouncyCastleProvider();
        Security.addProvider(provider);
    }

    public static byte[] signMessage(byte[] plainText){
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12", "BC");
            keyStore.load(new FileInputStream(pfxPath), pfxPin.toCharArray());

            String alias = keyStore.aliases().nextElement();
            PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, "123456".toCharArray());
            X509Certificate x509Certificate = (X509Certificate) keyStore.getCertificate(alias);

            List<Certificate> certList = new ArrayList<Certificate>();
            certList.add(x509Certificate);

            CMSTypedData cmsTypedData = new CMSProcessableByteArray(plainText);

            Store certStore = new JcaCertStore(certList);

            CMSSignedDataGenerator cmsSignedDataGenerator = new CMSSignedDataGenerator();
            ContentSigner contentSigner = new JcaContentSignerBuilder(signAlgorithm).setProvider("BC")
                    .build(privateKey);

            cmsSignedDataGenerator.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(
                    new JcaDigestCalculatorProviderBuilder().setProvider("BC").build()).build(contentSigner,
                    x509Certificate));

            cmsSignedDataGenerator.addCertificates(certStore);

            CMSSignedData cmsSignedData = cmsSignedDataGenerator.generate(cmsTypedData, true);

            return cmsSignedData.getEncoded();
        } catch (KeyStoreException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (OperatorCreationException e) {
            e.printStackTrace();
        }  catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }  catch (CMSException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean verifyMessage(byte[] cipherText, byte[] plainText, int validateLevel){
        boolean isVerify = false;

        CMSSignedData cmsSignedData = null;
        try {
            CMSProcessable signedContent = new CMSProcessableByteArray(plainText);
            InputStream is = new ByteArrayInputStream(cipherText);

            cmsSignedData = new CMSSignedData(signedContent, is);
            Store certStore = cmsSignedData.getCertificates();

//            // 获取原文
//            CMSProcessable content = cmsSignedData.getSignedContent();
//            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
//            content.write(bOut);
//
//            String file = "/Users/user/Downloads/test.doc";
////            System.out.println("bOut = " + bOut.toString());
//            FileOutputStream fos = new FileOutputStream(file);
//            fos.write(bOut.toByteArray());
//            fos.close();

            SignerInformationStore signerInformationStore = cmsSignedData.getSignerInfos();
            Collection collection = signerInformationStore.getSigners();
            Iterator it = collection.iterator();

            SignerInformation signerInformation = (SignerInformation) it.next();

            Collection certCollection = certStore.getMatches(signerInformation.getSID());
            Iterator iterator = certCollection.iterator();
            X509CertificateHolder certificate = (X509CertificateHolder) iterator.next();

            if(validateLevel == LEVEL_ALL){
                mCertChain.add(new JcaX509CertificateConverter().setProvider( "BC" ).getCertificate(certificate));
                if(!validate()){
                    mCertChain.remove(mCertChain.size() - 1);
                    return false;
                }
                mCertChain.remove(mCertChain.size() - 1);
            }else if(validateLevel == LEVEL_ONE){
                if(!validateLevelOne(new JcaX509CertificateConverter().setProvider( "BC" ).getCertificate(certificate))){
                    return false;
                }
            }


            if(signerInformation.verify(new JcaSimpleSignerInfoVerifierBuilder().setProvider("BC").build(certificate))){
                isVerify = true;
            }else{
                isVerify = false;
            }
        } catch (CMSException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (OperatorCreationException e) {
            e.printStackTrace();
        }

        return isVerify;

    }

    public static void setPfxInfo(String _pfxPath, String _pfxPin){
        pfxPath = _pfxPath;
        pfxPin = _pfxPin;
    }

    public static void setCertPath(X509Certificate[] certList){
        if(mCertChain == null){
            mCertChain = new ArrayList<>();
        }else{
            mCertChain.clear();
        }

        for (Certificate cert : certList) {
            mCertChain.add(cert);
        }
    }

    public static void setCertificate(X509Certificate certificate){
        mCertificate = certificate;
    }

    private static boolean validate(){
        try{
            CollectionCertStoreParameters params = new CollectionCertStoreParameters(mCertChain);
            CertStore store = CertStore.getInstance("Collection", params, "BC");

            // 创建对应的证书路径
            CertificateFactory fact = CertificateFactory.getInstance("X.509", "BC");
            List certChain = new ArrayList();

            certChain.addAll(mCertChain);
            Collections.reverse(certChain);
            certChain.remove(certChain.size() - 1);

            CertPath certPath = fact.generateCertPath(certChain);
            Set trust = Collections.singleton(new TrustAnchor((X509Certificate) mCertChain.get(0), null));

            // 验证
            CertPathValidator validator = CertPathValidator.getInstance("PKIX", "BC");
            PKIXParameters param = new PKIXParameters(trust);

            param.addCertStore(store);
            param.setDate(new Date());
            param.setRevocationEnabled(false);

            try {
                CertPathValidatorResult result = validator.validate(certPath, param);
                System.out.println("证书链校验成功");
                return true;
            } catch (CertPathValidatorException e) {
                System.out
                        .println("证书链校验失败出错证书" + e.getIndex() + ", 出错详细信息: " + e.getMessage());
            }
        }catch (InvalidAlgorithmParameterException e){
            e.printStackTrace();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch (NoSuchProviderException e){
            e.printStackTrace();
        }catch (CertificateException e){
            e.printStackTrace();
        }

        return false;
    }

    // 应需求添加，安全性较低，不建议使用
    private static boolean validateLevelOne(Certificate signCertParam){
        X509Certificate caCert = (X509Certificate) mCertificate;
        X509Certificate signCert = (X509Certificate) signCertParam;

        try{
            signCert.verify(caCert.getPublicKey(), "BC");
            System.out.println("上级证书校验成功");
            return true;
        }catch (CertificateException e){
            e.printStackTrace();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch (InvalidKeyException e){
            e.printStackTrace();
        }catch (NoSuchProviderException e){
            e.printStackTrace();
        }catch (SignatureException e) {
            e.printStackTrace();
        }

        return false;
    }
}
