//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;
class Main{
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Integer> arr, list1, list2;
		StringBuilder sb = new StringBuilder();
		int num = Integer.parseInt(st.nextToken());
		while(num != 0) 
		{
			arr		= new ArrayList<>();
			list1	= new ArrayList<>();
			list2	= new ArrayList<>();
			for(int i=0; i<num; i++)
			{
				arr.add(Integer.parseInt(st.nextToken()));
			}
			
			Collections.sort(arr);
			// 0이 아닌 가장 작은 값을 list1에 넣는다.
			Iterator<Integer> ite = arr.iterator();
			while(ite.hasNext()) {
				int n = ite.next();
				if(n != 0) {
					if(list1.isEmpty()) {
						list1.add(n);
						ite.remove();
					}
					else if(list2.isEmpty()) {
						list2.add(n);
						ite.remove();
						break;
					}
				}
			}
			
			// list1과 list2에 각각 하나씩 숫자를 넣는다.
			for(int i=0; i<arr.size(); i++) {
				if(list1.size() == list2.size()) {
					list1.add(arr.get(i));
				}else {
					list2.add(arr.get(i));
				}
			}
			
			sb.append(getNum(list1) + getNum(list2)).append('\n');
			
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
		}
		System.out.print(sb.toString());
	}
}