/**
 * Created by Mamat Rahmat on 2/4/2016.
 */
public class PlayFairCipherTest {
    public static void main(String[] args) {
        String key = "KEYWORD";
        PlayFairCipher pf = new PlayFairCipher();
        String[][] matrix = pf.getMatrixFromKey(key);
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }

        String plaintext = "AJI JANGAN SAMPAI MAATII";

        System.out.println("#encrypting");
        System.out.println("plaintext = " + plaintext + ", key = " + key);
        String ciphertext = pf.encrypt(plaintext, key);
        System.out.println("ciphertext = " + ciphertext);

        System.out.println("#decrypting");
        System.out.println("ciphertext = " + ciphertext + ", key = " + key);
        plaintext = pf.decrypt(ciphertext, key);
        System.out.println("plaintext = " + plaintext);

    }
}
