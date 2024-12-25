//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12002
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

class Point{int y, x;Point(int y, int x){this.y=y;this.x=x;}}
class Pos{int idx, value;Pos(int i, int v){idx=i; value=v;}}

class Main{
	
	public static void main(String[] args)throws Exception{
		long area	= 1L<<60;
		int N		= read();
		Pos Y[]		= new Pos[N];
		Pos X[]		= new Pos[N];
		Point[] point= new Point[N];
		
		TreeMap<Integer, Integer> ymap = new TreeMap<>();
		TreeMap<Integer, Integer> xmap = new TreeMap<>();
		
		for(int i=0,y,x; i<N; i++)
		{
			y		= read();
			x		= read();
			Y[i]	= new Pos(i, y);						// y의 값과 인덱스 값을 넣는다.
			X[i]	= new Pos(i, x);						// x의 값과 인덱스 값을 넣는다.
			point[i]= new Point(y, x);						// 입력된 y,x 값을 그대로 넣는다.
			ymap.put(y, ymap.getOrDefault(y, 0) + 1);		// 각각 y,x 좌표 값이 몇번 나오는지 체킹
			xmap.put(x, xmap.getOrDefault(x, 0) + 1);		// 각각 y,x 좌표 값이 몇번 나오는지 체킹
		}
		
		Arrays.sort(Y, (a,b)-> a.value - b.value);
		Arrays.sort(X, (a,b)-> a.value - b.value);
		
		TreeSet<Integer> idxSet = new TreeSet<>();			// 지울 소들의 인덱스를 담는다.(오름차순으로 자동정렬)
		for(int i=0; i<3; i++)								// 좌표 크기별로 오름차순 정렬된 배열에서 각각의 point배열에서의 idx를 idxSet에담음
		{
			idxSet.add(X[i].idx);
			idxSet.add(Y[i].idx);
			idxSet.add(X[N - 1 - i].idx);
			idxSet.add(Y[N - 1 - i].idx);
		}
		
		ArrayList<Integer> idxList = new ArrayList<>(idxSet);
		
		for(int i=0; i<idxList.size(); i++)
			for(int j=i+1; j<idxList.size(); j++)
				for(int z=j+1; z<idxList.size(); z++)
				{
					Point p1 = point[idxList.get(i)];
					Point p2 = point[idxList.get(j)];
					Point p3 = point[idxList.get(z)];
					
					delete(xmap, p1.x);
					delete(xmap, p2.x);
					delete(xmap, p3.x);
					delete(ymap, p1.y);
					delete(ymap, p2.y);
					delete(ymap, p3.y);
					
					long res = (long)(xmap.lastKey()-xmap.firstKey()) * (ymap.lastKey() - ymap.firstKey());
					if(res < area)
						area = res;
					
					update(xmap, p1.x);
					update(xmap, p2.x);
					update(xmap, p3.x);
					update(ymap, p1.y);
					update(ymap, p2.y);
					update(ymap, p3.y);
				}
		
		System.out.print(area);
	}
	public static void delete(TreeMap<Integer,Integer> map, int key) {
		int now = map.getOrDefault(key, 0);
		if(now <= 1)
			map.remove(key);
		else
			map.put(key, now - 1);
	}
	public static void update(TreeMap<Integer, Integer> map, int key) {
		map.put(key, map.getOrDefault(key, 0) + 1);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}