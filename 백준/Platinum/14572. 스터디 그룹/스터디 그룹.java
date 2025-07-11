//https://www.acmicpc.net/problem/14572
//2초 128MB
//3 3 10 // 학생의 수(1<=100,000), 알고리즘 수(1<=30), 그룹내 학생의 실력차(0<=1,000,000,000)
//1 20//(학생수만큼 2줄씩 반복) i번 학생이 알고있는 알고리즘의 수(0<=30), 해당 학생의 실력(0<=십억)
//1// i번 학생이 알고 있는 알고리즘들이 주어짐(1<=K)
//1 10
//2
//1 0
//3
//최대효율성 : 4

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static long res;
	static int N, K, D;
	static int union;// 그룹 내의 학생들이 아는 모든 알고리즘의 수
	static long algoCount[];
	static List<Student> student;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 학생의 수(1<=100,000)
		K = Integer.parseInt(st.nextToken());// 알고리즘 수(1<=30)
		D = Integer.parseInt(st.nextToken());// 그룹내 학생의 실력차(0<=1,000,000,000)
		student = new ArrayList<>();
		algoCount = new long[K + 1];
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			List<Integer> list = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<K; j++)
				list.add(Integer.parseInt(st.nextToken()));

			student.add(new Student(K, L, list));
		}
		
		Collections.sort(student);
		
		for(int r=0, l = 0; r<student.size(); r++)
		{
			Student right = student.get(r);

			while(right.L - student.get(l).L > D)
			{
				for(int a : student.get(l).list)
					if(--algoCount[a] == 0)
						--union;
				++l;
			}
			
			for(int a : right.list)
				if(++algoCount[a] == 1)
					++union;
			
			long cnt = r - l + 1;
			int intersec = 0;
			
			for(int o=1; o<=K; o++)
				if(algoCount[o] == cnt)
					++intersec;
			
			res = Math.max((union - intersec) * cnt, res);
		}
		
		System.out.print(res);
	}
	static class Student implements Comparable<Student>{
		int K, L;
		List<Integer> list;
		Student(int K, int L, List<Integer> list){
			this.K = K;// 학생이 알고있는 알고리즘 수
			this.L = L;// 학생의 실력
			this.list = list;// 알고있는 알고리즘 종류
		}
		@Override
		public int compareTo(Student o) {
			return L - o.L;
		}
	}
}