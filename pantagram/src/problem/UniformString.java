package problem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class UniformString {

	private static Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
        Scanner in = new Scanner(System.in);
//        String s = in.next();
//        int n = in.nextInt();
        
        BufferedReader br1 = new BufferedReader(new FileReader("/home/bruno/input05.txt"));
        //BufferedReader br1 = new BufferedReader(new FileReader("/opt/example.txt"));
        
        String s = br1.readLine();
        int n = Integer.parseInt(br1.readLine());
        Date ini = Calendar.getInstance().getTime();
        for(int a0 = 0; a0 < n; a0++){
            //int x = in.nextInt();
        	int x = Integer.parseInt(br1.readLine());
            if (isUniform(s, x))
            	System.out.println("Yes");
            else
            	System.out.println("No");
        }
        Date end = Calendar.getInstance().getTime();
        
        System.out.println("t: " + (end.getTime() - ini.getTime()));
        
        System.out.println(cache);
        
        in.close();
        br1.close();
    }
	
	private static Boolean isUniform(String str, int x){
		Boolean result = false;
		int sum = 0;
		int i = 0;
		int n = str.length();
		int c1 = 0;
		
		if (cache.isEmpty()){
		
			do{			
				c1 = (int)str.charAt(i) - 96;
				sum = sum + c1;			
				if (i < (n-1) && ((int)str.charAt(i+1) - 96) != c1){
					upsertToHigherValue(c1, sum);
					sum = 0;
				}
				i = i + 1;
				
			}while(i < n);
			
			upsertToHigherValue(c1, sum);		
		}
		
		if ((n*26) < x){
			result = false;
		}
		else
		{
			for (Entry<Integer, Integer> e : cache.entrySet()){
				if ((x % e.getKey()) == 0 && x <= e.getValue()){
					return true;
				}
			}
		}
		return result;
	}
	
	/*
	 * Insert or update the value in a map when the new Value is higher than the current one. 
	 */
	private static void upsertToHigherValue(int key, int newValue) {
		cache.compute(key, (k, v) -> (v == null || v < newValue) ? newValue : v);
	}
}