public class zad2 {
	public static void main(String[] args) {
		if(args.length!=3) {
			System.out.println("Za malo parametrow");
			return;
		}
		String txt;
		try {
			txt = args[0];
			if(!txt.matches("[a-zA-Z]+")) {
				throw new Exception("Niepoprawny lancuch znakow!");
			}
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
			return;
		}
		int x;
		int y;
		try {
			x = Integer.valueOf(args[1]);
			y = Integer.valueOf(args[2]);
		}
		catch(NumberFormatException e) {
			System.out.println("Niepoprawny przedzial liczbowy: " + e.getLocalizedMessage());
			return;
		}
		for(int i=x; i<=y; i++) {
			System.out.print(txt.charAt(i));
		}
		System.out.println();
	}
}
