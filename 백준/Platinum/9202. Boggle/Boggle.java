// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


class TrieNode{
	boolean isEnd;
	TrieNode[] child;
	TrieNode(){
		isEnd = false;
		child = new TrieNode[26];
	}
}
class TrieRoot{
	TrieNode root = new TrieNode();
	void insert(String str) {
		TrieNode current = root;
		int idx, len = str.length();
		for(int i=0; i<len; i++) 
		{
			idx = str.charAt(i)-'A';
			if(current.child[idx]== null)
				current.child[idx] = new TrieNode();
			current = current.child[idx];
		}
		current.isEnd = true;
	}
	boolean search(String str) {
		TrieNode current = root;
		int idx, len = str.length();
		for(int i=0; i<len; i++) {
			idx = str.charAt(i)-'A';
			if(current.child[idx] == null)
				return false;
			current = current.child[idx];
		}
		return current.isEnd;
	}
}
class Main
{	
	static int dxy[][] 		= {{1,0},{0,1},{-1,0},{0,-1}, {-1,-1},{-1,1},{1,-1},{1,1}};
	static int scoreArr[] 	= {0, 0, 0, 1, 1, 2, 3, 5, 11};
	static int repeat, MAX, SCORE, WORD_CNT;
	static char map[][];
	static boolean visit[][];
	static TrieRoot root;
	static StringBuilder sb;
	static HashSet<String> result;
	static ArrayList<String> list;

	public static void DFS(int y, int x, int depth, String str) {
		if(depth == MAX)
			return;
		
		int nextY, nextX;
		String nextStr;
		for(int xy[] : dxy) 
		{
			nextY = y + xy[0];
			nextX = x + xy[1];
			if(nextY>=0 && nextX>=0 && nextY<4 && nextX<4 && !visit[nextY][nextX]) 
			{
				visit[nextY][nextX] = true;
				nextStr = str + map[nextY][nextX];
				if(!result.contains(nextStr) && root.search(nextStr)) 
					result.add(nextStr);
				DFS(nextY, nextX, depth + 1, nextStr);
				visit[nextY][nextX] = false;
			}
		}
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb 		= new StringBuilder();
		root 	= new TrieRoot();
		repeat 	= Integer.parseInt(br.readLine());
		String str;
		while(repeat-->0) 
		{
			str = br.readLine();
			root.insert(str);			// 트라이 자료구조에 해당 문자열을 넣음
			if(str.length() > MAX)
				MAX = str.length();		// DFS탐색시 깊이를 찾기위한 코드
		}
		br.readLine();
		repeat = Integer.parseInt(br.readLine());
		while(repeat-->0) 
		{
			map 	= new char[4][4];
			visit 	= new boolean[4][4];
			result 	= new HashSet<>();
			list 	= new ArrayList<>();
			SCORE	= 0;
			for(int i=0; i<4; i++)
				map[i] = br.readLine().toCharArray();
			
			for(int i=0; i<4; i++)
				for(int j=0; j<4; j++)
				{
					visit[i][j] = true;
					DFS(i,j,0,map[i][j]+"");
					visit[i][j] = false;
				}

//			list.addAll(result);
//			
//			Collections.sort(list, (a,b)->{
//				int len1 = ((String) a).length();
//				int len2 = ((String) b).length();
//				if(len1>len2)
//					return -1;
//				else if(len1 == len2)
//					return a.compareTo(b);
//				return 1;
//			});
//			
//			sb.append(SCORE).append(' ')
//				.append(list.get(0)).append(' ')
//				.append(list.size()).append('\n');
			Iterator<String> it = result.iterator();
			String longest = "";
			while(it.hasNext()) {
				String word = it.next();
				SCORE += scoreArr[word.length()];
				if(word.length() >= longest.length()) {
					if(word.length() > longest.length() || word.compareTo(longest) < 0)
						longest = word;
				}
			}
			sb.append(SCORE).append(' ')
				.append(longest).append(' ')
				.append(result.size()).append('\n');
			if(repeat != 0)
				br.readLine();
		}
		System.out.println(sb);
	}
}

