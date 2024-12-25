//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12002

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Point{int y, x;Point(int y, int x){this.y=y;this.x=x;}}
class Pos{int idx, value;Pos(int i, int v){idx=i; value=v;}}

class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long area	= 1L<<60;
		int N		= Integer.parseInt(br.readLine());
		Pos Y[]		= new Pos[N];
		Pos X[]		= new Pos[N];
		Point[] point= new Point[N];
		
		TreeMap<Integer, Integer> ymap = new TreeMap<>();
		TreeMap<Integer, Integer> xmap = new TreeMap<>();
		
		for(int i=0,y,x; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			y		= Integer.parseInt(st.nextToken());
			x		= Integer.parseInt(st.nextToken());
			Y[i]	= new Pos(i, y);						// y의 값과 인덱스 값을 넣는다.
			X[i]	= new Pos(i, x);						// x의 값과 인덱스 값을 넣는다.
			point[i]= new Point(y, x);						// 입력된 y,x 값을 그대로 넣는다.
			ymap.put(y, ymap.getOrDefault(y, 0) + 1);		// 각각 y,x 좌표 값이 몇번 나오는지 체킹
			xmap.put(x, xmap.getOrDefault(x, 0) + 1);		// 각각 y,x 좌표 값이 몇번 나오는지 체킹
		}
		
		Arrays.sort(Y, (a,b)-> a.value - b.value);
		Arrays.sort(X, (a,b)-> a.value - b.value);
		
		HashSet<Integer> idxSet = new HashSet<>();
		for(int i=0; i<3; i++)
		{
			idxSet.add(X[i].idx);
			idxSet.add(Y[i].idx);
			idxSet.add(X[N - 1 - i].idx);
			idxSet.add(Y[N - 1 - i].idx);
		}
		
		ArrayList<Integer> idxList = new ArrayList<>(idxSet);
		
		Collections.sort(idxList);
		
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
}