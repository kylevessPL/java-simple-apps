import java.util.*;

public class zad3 {
	public static void main(String[] args) {
		try {
			Random rand = new Random();
			int r = rand.nextInt(100 + 1);
			int p = 0;
			Scanner in = new Scanner(System.in);
			try {
				while(true) {
					System.out.print("Podaj liczbe: ");
					int i = in.nextInt();
					p++;
					if(i == r) {
						System.out.println("Zgadles! Twoja liczba prob: " + p);
						p = 0;
						String c;
						do {
							System.out.print("\nChcesz grac dalej?\n[T/N]: ");
							c = in.next();
							if(c.equalsIgnoreCase("N"))
								return;
						} while(!c.equalsIgnoreCase("T"));
						System.out.println();
					}
					else if(i < r)
						System.out.println("Za malo!\n");
					else
						System.out.println("Za duzo!\n");
				}
			}
			finally {
				in.close();
			}
		}
		catch(Exception e) {
			System.out.println("Parametr wejsciowy ma byc poprawna liczba!");
		}
	}
}