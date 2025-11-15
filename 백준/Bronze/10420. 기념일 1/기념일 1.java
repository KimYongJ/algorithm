//https://www.acmicpc.net/problem/10420
//1초 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate date = LocalDate.parse("2014-04-02", format);
		
		LocalDate nextDate = date.plusDays(Integer.parseInt(br.readLine()) - 1);
		
		System.out.print(nextDate.format(format));
	}
}