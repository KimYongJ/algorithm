//https://www.acmicpc.net/problem/4312
//1초 128MB
//1
//7
//14
//783
//1125900981634049
//0
//답
//{ }
//{ 3, 9 }
//{ 1, 9, 27 }
//{ 3, 9, 27, 6561, 19683 }
//{ 59049, 3486784401, 205891132094649, 717897987691852588770249 }
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		List<BigInteger> list = new ArrayList<>();
		BigInteger zero = BigInteger.ZERO;
		BigInteger one = BigInteger.ONE;
		BigInteger three = BigInteger.valueOf(3);
		
		while(true)
		{
			BigInteger num = new BigInteger(br.readLine());
			
			if(num.equals(zero))
				break;
			
			num = num.subtract(one);
			
			BigInteger pow = one;
			
			while(num.compareTo(zero) > 0)
			{
				if(num.testBit(0))
					list.add(pow);
				
				num = num.shiftRight(1);
				
				pow = pow.multiply(three);
			}
			
			sb.append(toString(list)).append('\n');
			
			list.clear();
		}
		System.out.print(sb);
	}
	public static String toString(List<BigInteger> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("{ ");
		for(int i=0; i<list.size(); i++)
		{
			sb.append(list.get(i));
			if(i + 1 < list.size())
				sb.append(',');
			sb.append(' ');
		}
		sb.append('}');
		return sb.toString();
	}
}