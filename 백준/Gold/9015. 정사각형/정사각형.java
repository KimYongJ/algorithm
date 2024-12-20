//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/9015
import java.util.HashSet;
class Pos{
	int x, y;
	Pos(int x,int y){
		this.x=x;
		this.y=y;
	}
	@Override
	public boolean equals(Object obj) {
		Pos p = (Pos)obj;
		return x == p.x && y == p.y;
	}
	@Override
	public int hashCode() {
		return x * 31 + y;
	}
}

class Main{
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
	public static void main(String[] args)throws Exception{
		StringBuilder	sb = new StringBuilder();
		int T = read();
		while(T-->0)
		{
			HashSet<Pos> set = new HashSet<>();
			int N		= read();//4 ≤ n ≤ 3,000
			int area	= 0;
			Pos[] pos	= new Pos[N];
			
			for(int i=0; i<N; i++)
			{
				//-10000 이상 +10000이하의 정수, 동일 위치는 주어지지 않음
				pos[i] = new Pos(read(),read());
				set.add(pos[i]);
			}
			
			for(int i=0; i<N; i++)
				for(int j=0; j<N; j++)
					if(i!=j)
					{
						int plusY = pos[i].y - pos[j].y;
						int plusX = pos[i].x - pos[j].x;
						Pos p1 = new Pos(pos[i].x + plusY, pos[i].y - plusX);
						Pos p2 = new Pos(pos[j].x + plusY, pos[j].y - plusX);
						if(set.contains(p1) && set.contains(p2))
							area = Math.max(area, plusY*plusY + plusX*plusX);
					}
			
			sb.append(area).append('\n');
		}
		System.out.print(sb);
	}
}