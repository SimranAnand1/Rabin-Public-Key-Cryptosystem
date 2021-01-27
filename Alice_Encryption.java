package rabinpkc;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import static rabinpkc.KeyGeneration.finalMessage;
import static rabinpkc.KeyGeneration.n;
import static rabinpkc.KeyGeneration.p;
import static rabinpkc.KeyGeneration.q;

public class Alice_Encryption extends javax.swing.JFrame {

public Alice_Encryption() {
initComponents();
}

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {


BigInteger[] key = RabinPKC.generateKey(512);
n = key[0];
p = key[1];
q = key[2];
finalMessage = null;
int i = 1;
String s=JTextField.getText();
BigInteger m
= new BigInteger(
s.getBytes(
Charset.forName("ascii")));
BigInteger c = RabinPKC.encrypt(m, n);
JOptionPane.showMessageDialog(null,"The Sent message is: "+s);
jTextField2.setText(""+c);
JOptionPane.showMessageDialog(null,"The Encrypted message is "+c);
}

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
JOptionPane.showMessageDialog(null,"Kindly run the Decryption file to proceed to Bob’s end");
System.exit(0);
}

public static void main(String args[]) {
java.awt.EventQueue.invokeLater(new Runnable() {
public void run() {
new Alice_Encryption().setVisible(true);
}
});
}