
package bc;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;
import utils.Log;

import java.io.*;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class BCTest {

    private static String resPath = "/Users/user/Documents/Koal/cert/";
//    private static String resPath = "/Users/user/Documents/git/gitlab/AndroidNativeCipherSignTools/res/";
    private static String certPath = resPath + "lwb2.pfx";
//    private static String certPath = resPath + "sm2.pfx";
    private static String subCaPath = resPath + "subca_sm2.cer";
    private static String rootCaPath = resPath + "rootca_sm2.cer";

    public static void main(String[] args) throws Exception {
        // 不校验证书链
        validateLevelNone();
        // 仅校验一级
//        validateLevelOne();
        // 校验完整证书链
//        validateLevelAll();
    }



    private static void validateLevelNone() throws Exception {
        System.out.println("-----------------------不校验证书链流程-----------------------");
        // 验证国密p7签名验签
        BcUtils.setPfxInfo(certPath, "123456");

        String file = "/Users/user/Downloads/mbedtls-mbedtls-2.9.0.tar";
//        String file = "/Users/user/Downloads/20170720格尔PKI移动安全中间件技术白皮书.docx";
//        String file = "/Users/user/Downloads/pkcs-7.asn";
        FileInputStream fis = new FileInputStream(file);
        byte[] buf = new byte[fis.available()];
        int len = fis.read(buf);

        byte[] plainText = buf;
//        byte[] plainText = "Hello World".getBytes();
        long start = System.currentTimeMillis();
        System.out.println("begin time = " + start);
        byte[] cipherText = BcUtils.signMessage(plainText);
        long end = System.currentTimeMillis();
        System.out.println("end time = " + (end -start));

        String outPath = "/Users/user/test/pkcs7/signMessage_Pkcs7_lwb2.der";
//        Log.writeByteArray(cipherText,outPath);

//        System.out.println("SM3WithSM2签名B64：" + new String(Base64.encode(cipherText)));
        start = System.currentTimeMillis();
        System.out.println("begin time = " + start);
        boolean result = BcUtils.verifyMessage(cipherText, plainText, BcUtils.LEVEL_NONE);
        System.out.println("验签结果：" + result);
        end = System.currentTimeMillis();
        System.out.println("end time = " + (end -start));

        // 删除原先 生成的apk
        isExistsDelte("res/targetNone.apk");

        System.out.println("-----------------------不校验证书链流程-----------------------");
    }

    private static void validateLevelOne(){
        System.out.println("-----------------------仅校验上级证书流程-----------------------");
        // 验证国密p7签名验签
        BcUtils.setPfxInfo(certPath, "123456");
        try{
            Provider provider = new BouncyCastleProvider();
            Security.addProvider(provider);
            CertificateFactory certificateFactoryLevelOne = CertificateFactory.getInstance("X.509", "BC");
            FileInputStream fileInputStreamLevelOne = new FileInputStream(subCaPath);
            X509Certificate subCaLevelOne = (X509Certificate) certificateFactoryLevelOne.generateCertificate(fileInputStreamLevelOne);

            BcUtils.setCertificate(subCaLevelOne);
        }catch (CertificateException e){
            e.printStackTrace();
        }catch (NoSuchProviderException e){
            e.printStackTrace();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        byte[] plainText = "Hello World".getBytes();
        byte[] cipherText = BcUtils.signMessage("1".getBytes());
        System.out.println("SM3WithSM2签名B64：" + new String(Base64.encode(cipherText)));
        boolean result = BcUtils.verifyMessage(cipherText, "1".getBytes(), BcUtils.LEVEL_ONE);
        System.out.println("验签结果：" + result);

        System.out.println("-----------------------仅校验上级证书流程-----------------------");
    }

    private static void validateLevelAll(){
        System.out.println("-----------------------校验完整证书链流程-----------------------");
        // 验证国密p7签名验签
        BcUtils.setPfxInfo(certPath, "123456");

        try{
            Provider provider = new BouncyCastleProvider();
            Security.addProvider(provider);
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509", "BC");
            FileInputStream fileInputStream = new FileInputStream(rootCaPath);
            X509Certificate rootCa = (X509Certificate) certificateFactory.generateCertificate(fileInputStream);
            fileInputStream = new FileInputStream(subCaPath);
            X509Certificate subCa = (X509Certificate) certificateFactory.generateCertificate(fileInputStream);
            X509Certificate[] certificates = new X509Certificate[2];
            certificates[0] = rootCa;
            certificates[1] = subCa;
            BcUtils.setCertPath(certificates);
        }catch (CertificateException e){
            e.printStackTrace();
        }catch (NoSuchProviderException e){
            e.printStackTrace();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }


        byte[] plainText = "Hello World".getBytes();
        byte[] cipherText = BcUtils.signMessage("1".getBytes());
        System.out.println("SM3WithSM2签名B64：" + new String(Base64.encode(cipherText)));
        boolean result = BcUtils.verifyMessage(cipherText, "1".getBytes(), BcUtils.LEVEL_ALL);
        System.out.println("验签结果：" + result);

        System.out.println("-----------------------校验完整证书链流程-----------------------");
    }

    private static void isExistsDelte(String path) {
        File file = new File(path);
        if (file.exists() && file.isFile()){
            file.delete();
        }
    }
}
