/**
 * Created by Mamat Rahmat on 2/3/2016.
 */

public class PlayFairCipher {
    public static char[][] getMatrixFromKey(String key) {
        String keystringbasic = key + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String keystring = "";
        for(int i=0; i<keystringbasic.length(); i++) {
            if (keystringbasic.charAt(i) == 'J') {
                keystring += 'I';
            } else {
                keystring += keystringbasic.charAt(i);
            }
        }

        char[][] res = new char[5][5];
        int it=0;
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                boolean assigned = false;
                while(!assigned) {
                    char next = keystring.charAt(it);

                    boolean used = false;
                    for(int k=0; k<it; k++) {
                        if(next == keystring.charAt(k))
                            used = true;
                    }

                    if(!used) {
                        res[i][j] = next;
                        assigned = true;
                    } else {
                        it++;
                    }
                }
                it++;
            }
        }

        return res;
    }

    public static String getPreparedText(String text) {
        String result = "";

        // change 'J' to 'I'
        // and if next character is same as previous character, insert 'Z' between them
        char next, prev=' ';
        for(int i=0; i<text.length(); i++) {
            char c = text.charAt(i);
            if(Character.isLetter(c)) {
                if(c == 'J') {
                    next = 'I';
                } else {
                    next = text.charAt(i);
                }

                if((i>0) && (next == prev))
                    result += 'Z';

                result += next;
                prev = next;
            }
        }

        // add 'Z' if result length is odd
        if((result.length()%2) == 1)
            result += 'Z';

        return result;
    }

    public static String getFinishedText(String text) {
        String result = "";

        // and if there is 'Z' between same character, delete it
        for(int i=0; i<text.length(); i++) {
            char c = text.charAt(i);
            if(i==0) {
                result += c;
            } else if (i==text.length()-1) {
                if((c=='Z') && (text.length()%2==0)) {

                } else {
                    result += c;
                }
            } else {
                if((c == 'Z') && (text.charAt(i-1) == text.charAt(i+1))) {

                } else {
                    result += c;
                }
            }
        }

        return result;
    }

    public static String[] getBigramFromText(String text) {
        int size = text.length() / 2;
        String[] result = new String[size];

        for(int i=0; i<size; i++) {
            result[i] = Character.toString(text.charAt(2*i)) + Character.toString(text.charAt(2*i+1));
        }

        return result;
    }

    public static String getTextFromBigram(String[] bigram) {
        String text = "";
        for(int i=0; i<bigram.length; i++) {
            text += bigram[i].charAt(0);
            text += bigram[i].charAt(1);
        }
        return text;
    }

    public static int getPosR(char[][] matrix, char c) {
        int res = 0;
        for(int i=0; i<5; i++)
            for(int j=0; j<5; j++)
                if(matrix[i][j] == c)
                    res = i;
        return res;
    }

    public static int getPosC(char[][] matrix, char c) {
        int res = 0;
        for(int i=0; i<5; i++)
            for(int j=0; j<5; j++)
                if(matrix[i][j] == c)
                    res = j;
        return res;
    }

    public static String encrypt(String plaintext, String key) {
        String ciphertext = "";
        char[][] matrix = getMatrixFromKey(key);
        String preparedText = getPreparedText(plaintext);
        String[] bigram = getBigramFromText(preparedText);
        String[] result = new String[bigram.length];

        for(int i=0; i<bigram.length; i++) {
            int r1 = getPosR(matrix, bigram[i].charAt(0));
            int c1 = getPosC(matrix, bigram[i].charAt(0));

            int r2 = getPosR(matrix, bigram[i].charAt(1));
            int c2 = getPosC(matrix, bigram[i].charAt(1));

            if(r1==r2) {
                result[i] = Character.toString(matrix[r1][(c1+1)%5]) + Character.toString(matrix[r2][(c2+1)%5]);
            } else if (c1==c2) {
                result[i] = Character.toString(matrix[(r1+1)%5][c1]) + Character.toString(matrix[(r2+1)%5][c2]);
            } else {
                result[i] = Character.toString(matrix[r1][c2]) + Character.toString(matrix[r2][c1]);
            }
        }

        ciphertext = getTextFromBigram(result);
        return ciphertext;
    }

    public static String decrypt(String ciphertext, String key) {
        String plaintext = "";
        char[][] matrix = getMatrixFromKey(key);
        String[] bigram = getBigramFromText(ciphertext);
        String[] result = new String[bigram.length];

        for(int i=0; i<bigram.length; i++) {
            int r1 = getPosR(matrix, bigram[i].charAt(0));
            int c1 = getPosC(matrix, bigram[i].charAt(0));

            int r2 = getPosR(matrix, bigram[i].charAt(1));
            int c2 = getPosC(matrix, bigram[i].charAt(1));

            if(r1==r2) {
                result[i] = Character.toString(matrix[r1][(c1+4)%5]) + Character.toString(matrix[r2][(c2+4)%5]);
            } else if (c1==c2) {
                result[i] = Character.toString(matrix[(r1+4)%5][c1]) + Character.toString(matrix[(r2+4)%5][c2]);
            } else {
                result[i] = Character.toString(matrix[r1][c2]) + Character.toString(matrix[r2][c1]);
            }
        }

        String resulttext = getTextFromBigram(result);
        plaintext = getFinishedText(resulttext);
        return plaintext;
    }
}
