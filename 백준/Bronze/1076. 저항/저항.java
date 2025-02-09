//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1076
//2초 / 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		HashMap<String, Integer> map = new HashMap<>();
		map.put("black", 0);
		map.put("brown", 1);
		map.put("red", 2);
		map.put("orange", 3);
		map.put("yellow", 4);
		map.put("green", 5);
		map.put("blue", 6);
		map.put("violet", 7);
		map.put("grey", 8);
		map.put("white", 9);

		long num1 = map.get(br.readLine()) * 10;
		long num2 = map.get(br.readLine());
		long num3 = (long)Math.pow(10, map.get(br.readLine()));

		System.out.print((num1 + num2) * num3);
	}
}
