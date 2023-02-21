import java.math.*;

public class Main {
    public static int gcd(int x, int y) { // функция поиска наибольшего общего делителя
        return y!=0 ? gcd(y,x%y) : x;
    }
    public static int find_e(int F) {
        int e = 2;
        while (gcd(e,F) != 1) e++; // у взаимно простых чисел НОД = 1
        return e;
    }
    public static int find_d(int e, int F) {
        int i = 1;
        while ((i*F+1) % e != 0) i++; // ищем число, удовлетворяющее равенству (d*e)modF = 1
        return (i*F+1) / e;
    }
    public static String coding(String str, int e, int n) {
        String new_str = "";
        BigInteger s;
        int num = 0;
        for (int i=0; i<str.length(); i++) {
            s = BigInteger.valueOf((int) (str.charAt(i) - '@')); // перевод в ASCII
            s = s.pow(e).mod(BigInteger.valueOf(n)); // C = (M^e) mod n
            num = s.intValue() + (int) '@'; // обратно в char
            new_str += (char) num;
        }
        return new_str;
    }
    public static String decoding(String str, int d, int n) {
        String new_str = "";
        BigInteger s;
        int num = 0;
        for (int i=0; i<str.length(); i++) {
            s = BigInteger.valueOf((int) (str.charAt(i) - '@')); // перевод в ASCII
            s = s.pow(d).mod(BigInteger.valueOf(n)); // M = (C^d) mod n
            num = s.intValue() + (int) '@'; // обратно в char
            new_str += (char) num;
        }
        return new_str;
    }

    public static void main(String[] args) {
        String str = "BOARD"; // открытое сообщение
        int p = 5, q = 19;
        System.out.println(str);
        System.out.println("p = " + p + ", q = " + q);
        System.out.println("");

        int n = p * q;
        System.out.println("n = " + n);

        int F = (p-1)*(q-1);
        System.out.println("F = " + F);

        int e = find_e(F);
        System.out.println("e = " + e);

        int d = find_d(e, F);
        System.out.println("d = " + d);
        System.out.println("");

        String new_str = coding(str, e, n); // шифрование
        /*String new_str_ = "";
        for (int i=0; i<new_str.length(); i++)
            new_str_ += (char) ((new_str.charAt(i) - '@') % 26 + '@'); // приведение к нормальному для печати виду
        System.out.println(new_str_);*/
        System.out.println(new_str);
        System.out.println("");

        String source_text = decoding(new_str, d, n); // декодирование
        System.out.println(source_text);
    }
}