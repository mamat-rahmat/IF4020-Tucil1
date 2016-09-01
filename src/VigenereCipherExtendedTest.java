/**
 * Created by Mamat Rahmat on 2/3/2016.
 */
public class VigenereCipherExtendedTest {
    public static void main(String[] args) {
        String plaintext = "MAMATRAHMAT";
        String key = "MAMATRAHMAT";

        System.out.println("#encrypting");
        System.out.println("plaintext = " + plaintext + ", key = " + key);
        String ciphertext = VigenereCipherExtended.encrypt(plaintext, key);
        System.out.println("ciphertext = " + ciphertext);

        System.out.println();

        System.out.println("#decrypting");
        System.out.println("ciphertext = " + ciphertext + ", key = " + key);
        plaintext = VigenereCipherExtended.decrypt(ciphertext, key);
        System.out.println("plaintext = " + plaintext);
    }
}
