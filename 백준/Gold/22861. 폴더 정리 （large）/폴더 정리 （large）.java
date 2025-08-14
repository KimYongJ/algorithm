//https://www.acmicpc.net/problem/22861
//1.5초 1024MB
//3 6// 메인 폴더에 있는 폴더의 총수(1<=1,000), 파일의 총수(1<=1,000)가 주어진다.
//main FolderA 1 // 상위 폴더이름 , 현재 폴더or파일 이름, 어떤건지 알려주는 플레그가 주어짐(1은 폴더, 0은 파일)
//main FolderB 1
//FolderA File1 0
//FolderA File2 0
//FolderB FolderC 1
//FolderC File4 0
//FolderC File5 0
//FolderB File1 0
//FolderB File3 0
//1 // 옮기는 횟수 k(0<=1,000)
//main/FolderB main/FolderA // k줄에 걸쳐 폴더경로 A와 B가 공백으로 주어짐 옮기는 행위는 입력 순서대로 수행되어야 한다. A하위에 있는 모든 것을 B하위로 옮긴다.
//3 // 쿼리개수 Q가 주어진다(1<=1,000)
//main // 쿼리마다 main으로 부터 폴더의 경로 정보가 들어온다. 
//main/FolderA
//main/FolderA/FolderC
//출력 : 쿼리 마다 폴더 하위에 있는 파일의 종류의 개수와 파일의 총 개수를 출력한다. 파일의 종류는 중복을 제거, 총 개수는 중복제거를하지 않는다.
//5 5
//5 5
//2 2
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

class Main{
	
	static int N, M;
	static int folderIdx, fileIdx;
	static int dp[][];
	static Node folder[];
	static HashMap<String, Integer> folderMap, fileMap;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 메인 폴더에 있는 폴더의 총수(1<=1,000)
		M = Integer.parseInt(st.nextToken());// 파일의 총수(1<=1,000)가 주어진다.
		dp = new int[N + 2][2];
		folder = new Node[N + 2];
		folderMap = new HashMap<>();
		fileMap = new HashMap<>();
		
		for(int i=0; i<=N+1; i++)
			folder[i] = new Node();
		
		for(int i=0; i<N + M; i++)
		{
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken(); // 상위 폴더
			String b = st.nextToken(); // 하위 폴더 혹은, 하위 파일
			int flag = Integer.parseInt(st.nextToken());// 1 : 폴더, 0 : 파일
			
			int u = getFolderIdx(a);
			
			if(flag == 0)// 파일인 경우
				folder[u].fileList.add(getFileIdx(b));// 해당 노드의 파일 리스트에 파일정보 삽입
			else// 폴더 인덱스
				folder[u].folderList.add(getFolderIdx(b));// 해당 노드의 폴더리스트에 폴더 정보 삽입
		}
		
		int K = Integer.parseInt(br.readLine());// 옮기는 횟수 k(0<=1,000)
		while(K-->0)
		{
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			String b = st.nextToken();
			move(a.split("/"), b.split("/"));// a정보를 b로 모두 옮기고 a노드의 모든 정보는 지운다.
		}
		
		dfs(folderMap.get("main"));// main부터 깊이 우선 탐색을 진행하며 dp 배열에 파일 종류와 파일 총 개수를 담는다.
		
		StringBuilder sb = new StringBuilder();
		
		int Q = Integer.parseInt(br.readLine());
		
		while(Q-->0)
		{
			String[] str = br.readLine().split("/");
			
			int start = folderMap.get(str[str.length-1]);
			
			sb.append(dp[start][0]).append(' ').append(dp[start][1]).append('\n');
		}
		
		System.out.print(sb);
	}
	static int dfs(int node) {
		int total = folder[node].fileList.size();
		
		for(int nextNode : folder[node].folderList)
		{
			total += dfs(nextNode);
			folder[node].fileList.addAll(folder[nextNode].fileList);
		}
		
		dp[node][0] = folder[node].fileList.size(); // 파일의 종류 개수(중복 제거)
		dp[node][1] = total;// 파일의 총수
		
		return total;
	}
	static void move(String a[], String b[]) {
		int from = folderMap.get(a[a.length - 1]);
		int to = folderMap.get(b[b.length - 1]);
		
		folder[to].fileList.addAll(folder[from].fileList);
		folder[to].folderList.addAll(folder[from].folderList);
		folder[from].fileList.clear();
		folder[from].folderList.clear();
	}
	static int getFolderIdx(String s) {
		if(!folderMap.containsKey(s))
			folderMap.put(s, ++folderIdx);
		
		return folderMap.get(s);
	}
	static int getFileIdx(String s) {
		if(!fileMap.containsKey(s))
			fileMap.put(s, ++fileIdx);
		
		return fileMap.get(s);
	}
	static class Node{
		Set<Integer> fileList; // 파일 리스트 : 파일은 겹치면 안되기 때문
		List<Integer> folderList;// 폴더 리스트 : 폴더는 애초 겹치는 이름이 없음
		Node(){
			fileList = new HashSet<>();
			folderList = new ArrayList<>();
		}
	}
}