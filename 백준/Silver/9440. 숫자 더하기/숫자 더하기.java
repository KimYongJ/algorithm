//https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
import java.util.Collections;
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static long getNum(ArrayList<Integer> list) {
		long idx = 1;
		long sum = 0;
		for(int i=list.size()-1; i>=0; i--) {
			sum += list.get(i) * idx;
			idx *= 10;
		}
		return sum;
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		ArrayList<Integer> arr, list1, list2;
		int firstNonZero;
		int num = read();
		while(num != 0) 
		{
			arr		= new ArrayList<>();
			list1	= new ArrayList<>();
			list2	= new ArrayList<>();
			firstNonZero = -1;
			
			for(int i=0; i<num; i++)
				arr.add(read());
			
			Collections.sort(arr);

			while(arr.get(++firstNonZero) == 0);// 0이 아닌 수를 찾음
			
			list1.add(arr.get(firstNonZero));// 0이 아닌 수를 list1과 list2에 각각 넣음
			arr.remove(firstNonZero);
			list2.add(arr.get(firstNonZero));// 0이 아닌 수를 list1과 list2에 각각 넣음
			arr.remove(firstNonZero);
			
			// list1과 list2에 각각 하나씩 숫자를 넣는다.
			for(int i=0; i<arr.size(); i++)
			{
				if(list1.size() == list2.size())
				{
					list1.add(arr.get(i));
				}
				else 
				{
					list2.add(arr.get(i));
				}
			}
			
			sb.append(getNum(list1) + getNum(list2)).append('\n');
			
			num = read();
		}
		System.out.print(sb.toString());
	}
}