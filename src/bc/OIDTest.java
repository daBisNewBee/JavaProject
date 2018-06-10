package bc;

import org.bouncycastle.util.encoders.Hex;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.Oid;
import utils.Log;

public class OIDTest {
    public static void main(String[] args) throws Exception {
        String s = "1.2.840.114283";
        Oid oid = new Oid(s);
        byte[] der = oid.getDER();
        String hex = Hex.toHexString(der);
        System.out.println("hex = " + hex);

        Log.writeByteArray(der,"/Users/user/test/pkcs7/string.cer");

        oid = new Oid(der);
        System.out.println("oid = " + oid);

    }
}
