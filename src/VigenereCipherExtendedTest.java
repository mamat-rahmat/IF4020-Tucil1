/**
 * Created by Mamat Rahmat on 2/3/2016.
 */
public class VigenereCipherExtendedTest {
    public static void main(String[] args) {
        test("THIS PLAINTEXT", "sony");
        test("Jawa Timur Bakal Tenggelam\n" +
                "\n" +
                "Semburan lumpur panas di desa Porong, Sidoarjo, Jawa Timur belum juga berakhir. Sudah beberapa desa tenggelam. Entah sudah berapa rumah, bangunan, pabrik, dan sawah yang tenggelam. \n" +
                "\n" +
                "Sampai kapan semburan lumpur berhenti, tiada yang tahu. Teknologi manusia tidak berhasil menutupi lubang semburan. Jika semburan lumpur tidak berhenti juga, mungkin Jawa Timur akan tenggelam", "langitbiru");
    }

    public static void test(String plaintext, String key) {
        System.out.println("#encrypting");
        System.out.println("plaintext = " + plaintext + ", key = " + key);
        String ciphertext = VigenereCipherExtended.encrypt(plaintext, key);
        System.out.println("ciphertext = " + ciphertext);
        System.out.println();

        System.out.println("#decrypting");
        System.out.println("ciphertext = " + ciphertext + ", key = " + key);
        plaintext = VigenereCipherExtended.decrypt(ciphertext, key);
        System.out.println("plaintext = " + plaintext);
        System.out.println();
    }
}
