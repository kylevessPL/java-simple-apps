import java.util.*;
import java.io.*;

class WektoryRoznejDlugosciException extends Exception {
    public final int lenOfA, lenOfB;
    public WektoryRoznejDlugosciException(int lenOfA, int lenOfB) {
        super("Vectors must be the same length!");
        this.lenOfA = lenOfA;
        this.lenOfB = lenOfB;
    }
}

public class zad6 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(System.in))) {
            Vector<Double> AB = null;
            do {
                System.out.print("Enter first vector: ");
                Vector<Double> A = readVec(rd.readLine());
                System.out.print("Enter second vector: ");
                Vector<Double> B = readVec(rd.readLine());
                try {
                    AB = addVec(A, B);
                    break;
                } catch (WektoryRoznejDlugosciException e) {
                    System.out.println("An error occurred: " + e.getMessage() + "\n" + e.lenOfA + " != " + e.lenOfB);
                    System.out.println("Please input vectors again.");
                }
            } while (true);
            saveVec(AB);
        }
    }

    static Vector<Double> readVec(String in) {
        Vector<Double> vec = new Vector<Double>();
        try (Scanner scan = new Scanner(in).useLocale(Locale.US)) {   // using US locale to pass double value with dot
            while (scan.hasNext()) {
                if (scan.hasNextDouble())
                    vec.add(scan.nextDouble());
                else
                    scan.next();
            }
        }
        return vec;
    }

    static Vector<Double> addVec(Vector<Double> A, Vector<Double> B) throws WektoryRoznejDlugosciException {
        if (A.size() != B.size())
            throw new WektoryRoznejDlugosciException(A.size(), B.size());
        Vector<Double> AB = new Vector<Double>(A.size());
        for (int i = 0; i < A.size(); ++i)
            AB.add(A.elementAt(i) + B.elementAt(i));
        return AB;
    }

    static void saveVec(Vector<Double> AB) throws IOException {
        try (PrintWriter pr = new PrintWriter(new OutputStreamWriter(new FileOutputStream("output.txt")))) {
            for (int i = 0; i < AB.size(); ++i)
                pr.print(AB.elementAt(i) + " ");
        }
    }
}