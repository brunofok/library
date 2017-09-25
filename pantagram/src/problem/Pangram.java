package problem;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Pangram {

	static final Integer ALPHABET_SIZE = 26;
	
	public static void main(String[] args) throws IOException {
			
		Scanner in = new Scanner(System.in);
		
		String str = in.nextLine();	
		in.close();
		
		if (isPangram(str))
			System.out.println("pangram");
		else
			System.out.println("not pangram");
	}
	
	private static Boolean isPangram(String str){
		
		Map<String, Boolean> alph = generateAlphabet();
			
		if (str == null || str.length() < ALPHABET_SIZE)
			return false;
		
		str = str.toLowerCase();
		int found = 0;
		
		for (int i=0; i<str.length(); i++){
			char letter = str.charAt(i);
			if (letter >= 'a' && letter <= 'z'){
				String sLetter = Character.toString(letter);
				if (!alph.get(sLetter)){
					found++;
					alph.replace(sLetter, true);
				}
			}
		}
		
		return (found == ALPHABET_SIZE);
	}

	private static Map<String, Boolean> generateAlphabet() {
		Map<String, Boolean> alphabet = new HashMap<String, Boolean>();
		
		for (char c='a'; c<='z'; c++){			
			alphabet.put(Character.toString(c), false);
		}
		
		return alphabet;
	}

}
