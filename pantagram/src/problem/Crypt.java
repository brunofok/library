package problem;

import java.util.*;

public class Crypt {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		Integer n = Integer.parseInt(in.nextLine());
		String text = in.nextLine();
		Integer k = Integer.parseInt(in.nextLine());
		
		in.close();
		
		System.out.println(encrypt(text, k));

	}

	private static String encrypt(String text, Integer k) {
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<text.length(); i++){
			char c = text.charAt(i);
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
				c = rotate(c, k);
			
			sb.append(Character.toString(c));
		}
		
		return sb.toString();
	}

	private static char rotate(char c, Integer k) {
		final Integer lowerStart = 97;
		final Integer upperStart = 65;
		Integer start;
		
		start = c >= lowerStart ? lowerStart: upperStart;
			
		return (char)((((int)(c) - start + k) % 26) + start);
	}

}
