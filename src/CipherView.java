/**
 * Created by hp on 2/4/2016.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

public class CipherView {

    private static JFrame frame;
    private static JPanel panel;
    private static JLabel cipherLabel, textLabel, keyLabel, outtypeLabel;
    private static JButton encryptButton, decryptButton;
    private static JComboBox<String> box;
    private static JTextArea outputTextArea;
    private static ButtonGroup group;

    public static void main(String[] args) {
        frame = new JFrame("Tucil1 - Simple Cipher");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {

        panel.setLayout(null);

        cipherLabel = new JLabel("Cipher Algorithm");
        cipherLabel.setBounds(10, 10, 80, 25);
        panel.add(cipherLabel);

        String[] distros = new String[]{"Vigenere Cipher", "Vigenere Cipher Extended", "PlayFair Cipher"};
        box = new JComboBox<>(distros);
        box.setBounds(100, 10, 360, 25);
        panel.add(box);

        textLabel = new JLabel("Text");
        textLabel.setBounds(10, 40, 80, 25);
        panel.add(textLabel);

        JTextField textText = new JTextField(20);
        textText.setBounds(100, 40, 360, 25);
        panel.add(textText);

        keyLabel = new JLabel("Key");
        keyLabel.setBounds(10, 70, 80, 25);
        panel.add(keyLabel);

        JTextField keyText = new JTextField(20);
        keyText.setBounds(100, 70, 360, 25);
        panel.add(keyText);

        outtypeLabel = new JLabel("Output Type");
        outtypeLabel.setBounds(10, 100, 80, 25);
        panel.add(outtypeLabel);

        JRadioButton radStandard = new JRadioButton("Standard", true);
        JRadioButton radNoSpace = new JRadioButton("No Space");
        JRadioButton radBlock = new JRadioButton("5-Char Block");
        radStandard.setMnemonic(KeyEvent.VK_S);
        radNoSpace.setMnemonic(KeyEvent.VK_N);
        radBlock.setMnemonic(KeyEvent.VK_B);
        radStandard.setBounds(100, 100, 100, 25);
        radNoSpace.setBounds(200, 100, 100, 25);
        radBlock.setBounds(300, 100, 100, 25);
        panel.add(radStandard);
        panel.add(radNoSpace);
        panel.add(radBlock);

        ButtonGroup group = new ButtonGroup();
        group.add(radStandard);
        group.add(radNoSpace);
        group.add(radBlock);


        encryptButton = new JButton("Encrypt");
        encryptButton.setBounds(160, 210, 80, 25);
        panel.add(encryptButton);
        encryptButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = box.getSelectedItem().toString();
                String plaintext = textText.getText().toString();
                String key = keyText.getText().toString();
                String outputtype = getSelectedButtonText(group);

                if(selected.equals("Vigenere Cipher")) {
                    String ciphertext = VigenereCipher.encrypt(plaintext, key);
                    outputTextArea.setText("");
                    outputTextArea.append("PLAINTEXT  : " + plaintext + "\n");
                    outputTextArea.append("KEY        : " + key + "\n");
                    outputTextArea.append("CIPHERTEXT : " + ciphertext + "\n");

                } else if(selected.equals("Vigenere Cipher Extended")) {
                    String ciphertext = VigenereCipherExtended.encrypt(plaintext, key);
                    outputTextArea.setText("");
                    outputTextArea.append("PLAINTEXT  : " + plaintext + "\n");
                    outputTextArea.append("KEY        : " + key + "\n");
                    outputTextArea.append("CIPHERTEXT : " + ciphertext + "\n");
                } else if(selected.equals("PlayFair Cipher")) {
                    String ciphertext = PlayFairCipher.encrypt(plaintext, key);
                    outputTextArea.setText("");
                    outputTextArea.append("PLAINTEXT  : " + plaintext + "\n");
                    outputTextArea.append("KEY        : " + key + "\n");
                    outputTextArea.append("CIPHERTEXT : " + ciphertext + "\n");
                }
            }
        });

        decryptButton = new JButton("Decrypt");
        decryptButton.setBounds(230, 210, 80, 25);
        panel.add(decryptButton);
        decryptButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = box.getSelectedItem().toString();
                String ciphertext = textText.getText().toString();
                String key = keyText.getText().toString();
                String outputtype = getSelectedButtonText(group);

                if(selected.equals("Vigenere Cipher")) {
                    String plaintext = VigenereCipher.decrypt(ciphertext, key);
                    outputTextArea.setText("");
                    outputTextArea.append("CIPHERTEXT : " + ciphertext + "\n");
                    outputTextArea.append("KEY        : " + key + "\n");
                    outputTextArea.append("PLAINTEXT  : " + plaintext + "\n");
                } else if(selected.equals("Vigenere Cipher Extended")) {
                    String plaintext = VigenereCipherExtended.decrypt(ciphertext, key);
                    outputTextArea.setText("");
                    outputTextArea.append("CIPHERTEXT : " + ciphertext + "\n");
                    outputTextArea.append("KEY        : " + key + "\n");
                    outputTextArea.append("PLAINTEXT  : " + plaintext + "\n");
                } else if(selected.equals("PlayFair Cipher")) {
                    String plaintext = PlayFairCipher.decrypt(ciphertext, key);
                    outputTextArea.setText("");
                    outputTextArea.append("CIPHERTEXT : " + ciphertext + "\n");
                    outputTextArea.append("KEY        : " + key + "\n");
                    outputTextArea.append("PLAINTEXT  : ");
                    for(int i=0; i<plaintext.length(); i++) {
                        outputTextArea.append(""+plaintext.charAt(i));
                        if(i%2==1) outputTextArea.append(" ");
                    }
                    outputTextArea.append("\n");
                }
            }
        });

        outputTextArea = new JTextArea();
        Font font = new Font("Courier New", Font.PLAIN, 14);
        outputTextArea.setFont(font);
        outputTextArea.setBounds(10, 250, 470, 100);
        panel.add(outputTextArea);
    }


    public static String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
}
