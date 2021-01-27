
package rabinpkc;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Random;
import java.util.*;


class RabinPKC {
public static BigInteger p,q,m,n,c;
private static Random r = new SecureRandom();
private static BigInteger TWO = BigInteger.valueOf(2);
private static BigInteger THREE = BigInteger.valueOf(3);
private static BigInteger FOUR = BigInteger.valueOf(4);
private int bitlength = 512;

public static BigInteger[] generateKey(int bitLength)
{
BigInteger p = blumPrime(bitLength / 2);
BigInteger q = blumPrime(bitLength / 2);
BigInteger N = p.multiply(q);
return new BigInteger[] { N, p, q };
}
public static BigInteger encrypt(BigInteger m,
BigInteger N)

{
return m.modPow(TWO, N);
}
public static BigInteger[] decrypt(BigInteger c,
BigInteger p,
BigInteger q)

{
BigInteger N = p.multiply(q);
BigInteger p1 = c.modPow(p

.add(BigInteger.ONE)
.divide(FOUR),
p);
BigInteger p2 = p.subtract(p1);
BigInteger q1 = c.modPow(q

.add(BigInteger.ONE)

.divide(FOUR),
q);
BigInteger q2 = q.subtract(q1);

BigInteger[] ext = Gcd(p, q);
BigInteger y_p = ext[1];
BigInteger y_q = ext[2];

BigInteger d1 = y_p.multiply(p)
.multiply(q1)
.add(y_q.multiply(q)
.multiply(p1))

.mod(N);
BigInteger d2 = y_p.multiply(p)
.multiply(q2)
.add(y_q.multiply(q)
.multiply(p1))

.mod(N);
BigInteger d3 = y_p.multiply(p)
.multiply(q1)
.add(y_q.multiply(q)
.multiply(p2))

.mod(N);
BigInteger d4 = y_p.multiply(p)
.multiply(q2)
.add(y_q.multiply(q)
.multiply(p2))

.mod(N);

return new BigInteger[] { d1, d2, d3, d4 };
}
public static BigInteger[] Gcd(BigInteger a, BigInteger b)


{
BigInteger s = BigInteger.ZERO;
BigInteger old_s = BigInteger.ONE;
BigInteger t = BigInteger.ONE;
BigInteger old_t = BigInteger.ZERO;
BigInteger r = b;
BigInteger old_r = a;
while (!r.equals(BigInteger.ZERO)) {
BigInteger q = old_r.divide(r);
BigInteger tr = r;
r = old_r.subtract(q.multiply(r));
old_r = tr;
BigInteger ts = s;
s = old_s.subtract(q.multiply(s));
old_s = ts;
BigInteger tt = t;
t = old_t.subtract(q.multiply(t));
old_t = tt;
}
return new BigInteger[] { old_r, old_s, old_t };
}
public static BigInteger blumPrime(int bitLength)
{
BigInteger p;
do {
p = BigInteger.probablePrime(bitLength, r);
} while (!p.mod(FOUR).equals(THREE));
return p;
}
public static void main(String[] args)
{
{

BigInteger[] key = RabinPKC.generateKey(512);
n = key[0];
p = key[1];
q = key[2];
String finalMessage = null;
int i = 1;
System.out.println("Enter message");
Scanner sc=new Scanner(System.in);
String s = sc.nextLine();
m
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