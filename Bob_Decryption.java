package rabinpkc;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Random;
import javax.swing.JOptionPane;
import static rabinpkc.KeyGeneration.finalMessage;
import static rabinpkc.KeyGeneration.n;
import static rabinpkc.KeyGeneration.p;
import static rabinpkc.KeyGeneration.q;
import static rabinpkc.RabinPKC.Gcd;

public class Bob_Decryption extends javax.swing.JFrame {
public Bob_Decryption() {
initComponents();
}
@SuppressWarnings("unchecked")
private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
BigInteger[] key = RabinPKC.generateKey(512);
BigInteger n = key[0];
BigInteger p = key[1];
BigInteger q = key[2];
String finalMessage = null;
int i = 1;
String s=jTextField3.getText();
BigInteger m = new BigInteger(
s.getBytes(
Charset.forName("ascii")));
BigInteger c = RabinPKC.encrypt(m, n);
jTextField3.setText(""+c);
BigInteger[] m2 = RabinPKC.decrypt(c,p, q);

for (BigInteger b : m2) {
String dec = new String(
b.toByteArray(),
Charset.forName("ascii"));
if (dec.equals(s)) {
finalMessage = dec;
}
i++;
}
jTextField4.setText(""+finalMessage);
JOptionPane.showMessageDialog(null,"The Decrypted message for the recently Encrypted message is: "+finalMessage);
}
private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {

JOptionPane.showMessageDialog(null,"Thank you!");
System.exit(0);
}
public static void main(String args[]) {
java.awt.EventQueue.invokeLater(new Runnable() {
public void run() {
new Bob_Decryption().setVisible(true);
}
});
}