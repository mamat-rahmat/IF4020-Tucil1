/**
 * Created by Mamat Rahmat on 2/4/2016.
 */
public class PlayFairCipherTest {
    public static void main(String[] args) {
        String key = "STANDSEARCHBACK";
        char[][] matrix = PlayFairCipher.getMatrixFromKey(key);
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                System.out.print(Character.toString(matrix[i][j]));
            }
            System.out.println();
        }

        String plaintext = "NATTERJACKTOAD";

        System.out.println("#encrypting");
        System.out.println("plaintext = " + plaintext + ", key = " + key);
        String ciphertext = PlayFairCipher.encrypt(plaintext, key);
        System.out.println("ciphertext = " + ciphertext);

        System.out.println("#decrypting");
        System.out.println("ciphertext = " + ciphertext + ", key = " + key);
        plaintext = PlayFairCipher.decrypt(ciphertext, key);
        System.out.println("plaintext = " + plaintext);

    }
}
