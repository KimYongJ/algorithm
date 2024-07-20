//https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
import java.util.Collections;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		StringBuilder list1 = new StringBuilder();
		StringBuilder list2 = new StringBuilder();
		ArrayList<Integer> arr;
		boolean flag;
		int firstNonZero;
		int num = read();
		while(num != 0) 
		{
			arr		= new ArrayList<>();
			flag	= true;
			firstNonZero = -1;
			list1.setLength(0);
			list2.setLength(0);
			
			for(int i=0; i<num; i++)
				arr.add(read());
			
			Collections.sort(arr);

			while(arr.get(++firstNonZero) == 0);// 0이 아닌 수를 찾음
			
			list1.append(arr.get(firstNonZero));// 0이 아닌 수를 list1과 list2에 각각 넣음
			arr.remove(firstNonZero);
			list2.append(arr.get(firstNonZero));// 0이 아닌 수를 list1과 list2에 각각 넣음
			arr.remove(firstNonZero);
			
			// list1과 list2에 각각 하나씩 숫자를 넣는다.
			for(int i=0; i<arr.size(); i++) 
			{
				if(flag)list1.append(arr.get(i));
				else	list2.append(arr.get(i));
				flag = !flag;
			}

			sb.append(Integer.parseInt(list1.toString()) + Integer.parseInt(list2.toString()))
				.append('\n');
			
			num = read();
		}
		System.out.print(sb.toString());
	}
}