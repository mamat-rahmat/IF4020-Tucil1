/**
 * Created by Mamat Rahmat on 2/3/2016.
 */
public class VigenereCipherExtended {
    public static String encrypt(String plaintext, String key) {
        String ciphertext = "";
        for(int i=0, j=0; i<plaintext.length(); i++, j=(j+1)%key.length()){
            char c = plaintext.charAt(i);
            int code = ((AsciiUtils.getCode(c) + AsciiUtils.getCode(key.charAt(j))) % 256);
            ciphertext += AsciiUtils.getAscii(code);
        }
        return ciphertext;
    }

    public static String decrypt(String ciphertext, String key) {
        String plaintext = "";
        for(int i=0, j=0; i<ciphertext.length(); i++, j=(j+1)%key.length()){
            char c = ciphertext.charAt(i);
            int code = ((AsciiUtils.getCode(c) - AsciiUtils.getCode(key.charAt(j)) + 256) % 256);
            plaintext += AsciiUtils.getAscii(code);
        }
        return plaintext;
    }
}
