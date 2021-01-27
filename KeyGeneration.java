package rabinpkc;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.*;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

public class KeyGeneration extends RabinPKC{
public static BigInteger n,p,q,c,m;
public static String finalMessage;
public static void main(String[] args)
{
{ BigInteger[] key = RabinPKC.generateKey(512);
n = key[0];
p = key[1];
q = key[2];
finalMessage = null;
int i = 1;
System.out.println("Enter message");
Scanner sc=new Scanner(System.in);
String s = sc.nextLine();
BigInteger m
= new BigInteger(
s.getBytes(
Charset.forName("ascii")));

BigInteger c = RabinPKC.encrypt(m, n);
System.out.println("Encrypted Message : " + c.toString());
BigInteger[] m2 = RabinPKC.decrypt(c, p, q);
for (BigInteger b : m2) {
String dec = new String(

b.toByteArray(),
Charset.forName("ascii"));
if (dec.equals(s)) {
finalMessage = dec;
}
i++;
}
System.out.println("Message received by Receiver : "
+ finalMessage);
}
}
}