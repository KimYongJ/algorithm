//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6571
//1초 / 256mb

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		BigInteger upperBound	= BigInteger.TEN.pow(100);
		
		ArrayList<BigInteger> list = new ArrayList<>();
		BigInteger f1 = BigInteger.ONE;
		BigInteger f2 = BigInteger.TWO;
		list.add(f1);
		list.add(f2);
		
		while(true)
		{
			BigInteger next = f1.add(f2);
			if(next.compareTo(upperBound) > 0)
				break;
			list.add(next);
			f1 = f2;
			f2 = next;
		}
		
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			BigInteger lower = new BigInteger(st.nextToken());
			BigInteger upper = new BigInteger(st.nextToken());
			if(lower.add(upper).equals(BigInteger.ZERO))
				break;
			
			int idx1 = binarySearch1(list, lower);
			int idx2 = binarySearch2(list, upper);
			if(idx1 < 0 || idx2 < 0)
				sb.append(0).append('\n');
			else
				sb.append(idx1 > idx2 ? 0 : idx2 - idx1 + 1).append('\n');
		}
		System.out.print(sb);
	}
	public static int binarySearch1(ArrayList<BigInteger> list, BigInteger target) {
		int s = 0;
		int e = list.size() - 1;
		int res = -1;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			BigInteger now = list.get(mid);
			if(target.compareTo(now) <= 0) {
				res = mid;
				e = mid - 1;
			}
			else
				s = mid + 1;
		}
		return res;
	}
	public static int binarySearch2(ArrayList<BigInteger> list, BigInteger target) {
		int s = 0;
		int e = list.size() - 1;
		int res = -1;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			BigInteger now = list.get(mid);
			if(now.compareTo(target) <= 0) {
				res = mid;
				s = mid + 1;
			}
			else
				e = mid - 1;
		}
		return res;
	}
}