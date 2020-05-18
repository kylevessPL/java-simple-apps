import java.io.*;
import java.util.*;

public class zad5 {
    public static void main(String[] args) throws Exception {
        System.out.println("Warning. Terminal must be switched to raw-mode before processing.");
        String path;
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Enter filepath: ");
            path = scan.nextLine();
            try (Reader r = new InputStreamReader(new FileInputStream(path))) {
                try (PrintStream p = new PrintStream(new FileOutputStream("path.txt"))) {
                    p.print(path);
                }
                System.out.println();
                try (Scanner keyPress = new Scanner(System.in)) {
                    do {
                        keyPress.nextLine();
                        Random rand = new Random();
                        int max = rand.nextInt(5) + 1;
                        int count = 0;
                        try {
                            while (count < max) {
                                int ch;
                                if ((ch = r.read()) == -1) {
                                    throw new EOFException();
                                }
                                System.out.print((char) ch);
                                count++;
                            }
                        } catch (EOFException e) {
                            System.out.println("\n\nReached the end of file");
                            return;
                        }
                        System.out.println();
                    } while (true);
                }
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred: " + e.getLocalizedMessage());
            }
        }
    }
}
