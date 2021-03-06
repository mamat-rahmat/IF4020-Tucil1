/**
 * Created by Mamat Rahmat on 2/3/2016.
 */
public class VigenereCipher {
    public static String encrypt(String plaintext, String key) {
        plaintext = plaintext.toUpperCase();
        key = key.toUpperCase();

        String ciphertext = "";
        for(int i=0, j=0; i<plaintext.length(); i++){
            char c = plaintext.charAt(i);
            if (c >= 'A' && c <='Z') {
                ciphertext += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
                j = (j+1)%key.length();
            } else {
                ciphertext += c;
            }

        }
        return ciphertext;
    }

    public static String decrypt(String ciphertext, String key) {
        ciphertext = ciphertext.toUpperCase();
        key = key.toUpperCase();

        String plaintext = "";
        for(int i=0, j=0; i<ciphertext.length(); i++){
            char c = ciphertext.charAt(i);
            if (c >= 'A' && c <='Z') {
                plaintext += (char) ((c - key.charAt(j) + 26) % 26 + 'A');
                j = (j+1)%key.length();
            } else {
                plaintext += c;
            }
        }
        return plaintext;
    }
}
