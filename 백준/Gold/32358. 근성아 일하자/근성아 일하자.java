//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/32358
//1초 / 1024MB
//요약 : type이 1일 때 배열에 값을 넣고, 2일 때 현재 위치에서 가장가까운 곳이동하면서 그 거리를 더한다.
import java.util.PriorityQueue;
class Main{
	public static void main(String args[])throws Exception{
		PriorityQueue<Integer> left	= new PriorityQueue<>((a,b)->b-a);
		PriorityQueue<Integer> right= new PriorityQueue<>();
		int N		= read();
		int now		= 0;
		long res	= 0;
		
		while(N-->0)
		{
			if(read() == 1)
			{
				int value = read();
				if(now < value)
					right.add(value);
				else if(value < now)
					left.add(value);
			}
			else
			{
				while(!left.isEmpty() && !right.isEmpty())
				{
					int l = now - left.peek();
					int r = right.peek() - now;
					if(l <= r)
					{
						res += l;
						now = left.poll();
					}
					else
					{
						res += r;
						now = right.poll();
					}
				}
				while(!left.isEmpty())
				{
					res += now - left.peek();
					now = left.poll();
				}
				while(!right.isEmpty())
				{
					res += right.peek() - now;
					now = right.poll();
				}
			}
		}
		System.out.print(res);
	}
	static int read() throws Exception {
	  int c, n = System.in.read() & 15;
	  boolean m = n == 13;
	  if (m)n = System.in.read() & 15;
	  while ((c = System.in.read()) >= 48) {
	  n = (n << 3) + (n << 1) + (c & 15);}
	  return m ? ~n + 1 : n;
	}
}




// 이하 TreeSet 이용 방법
//import java.util.TreeSet;
//class Main{
//	public static void main(String[] args)throws Exception{
//		TreeSet<Integer> set = new TreeSet<>();
//		int N		= read();
//		int now		= 0;
//		long res	= 0;
//
//		while(N-->0)
//		{
//			int type = read();
//			if(type == 1)
//			{
//				int value = read();
//				if(value != now)
//					set.add(value);
//			}
//			else
//			{
//				while(!set.isEmpty())
//				{
//					Integer l = set.lower(now);	// floor는 now값 포함, heigher는 미포함
//					Integer r = set.higher(now);// ceiling은 now 값 포함, lower는 미포함
//					int value = 0;
//					if(l == null)
//						value = r;
//					else if(r == null)
//						value = l;
//					else
//					{
//						int diff1 = Math.abs(l - now);
//						int diff2 = Math.abs(r - now);
//						value = diff1 <= diff2 ? l : r;
//					}
//					
//					res += Math.abs(now - value);
//					now = value;
//					set.remove(now);
//				}
//			}
//		}
//		System.out.print(res);
//	}
//    static int read() throws Exception {
//        int c, n = System.in.read() & 15;
//        boolean m = n == 13;
//        if (m)n = System.in.read() & 15;
//        while ((c = System.in.read()) >= 48) {
//        n = (n << 3) + (n << 1) + (c & 15);}
//        return m ? ~n + 1 : n;
//    }
//}