// https://github.com/KimYongJ/algorithm
/*
 * 제한시간 1초 : 1억번 안에 연산이 끝나야 합니다.
 * 최대 메모리 256M 바이트 : 인접 노드를 생성할 때 int형 2차원 배열로 하면 메모리 초과가 납니다. 
 * 그 이유는 int는 4byte입니다. 최대 노드의 갯수 N이 10만개일 수 있습니다. N이 10만개일 때
 * 2차원 배열 생성시 배열에만 약150메가 바이트가 소모됩니다. 그렇기에 필요한 인접 노드만 저장할 수 있도록
 * list로 선언해야 합니다.
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
class Main{

	public static void main(String[] args)throws Exception{
		int N, parent[];
		boolean[] visit;
		ArrayList<Integer>[] list;
	    
		N = read(); // 노드 갯수 입력 받음
	    list 	= new ArrayList[N+1]; 	// 인접노드를 담을 리스트 생성
	    parent 	= new int[N+1]; 		// 노드 번호를 index로하여 부모노드정보를 담을 배열
	    visit 	= new boolean[N+1];		// 방문 체크 배열 선언
	    
	    for(int i=0; i<N+1; i++)  // 인접노드를 담을 리스트 초기화 
	    	list[i] = new ArrayList<Integer>();
	    
	    for(int i=0; i<N-1; i++) {
	    	int a = read();
	    	int b = read();
	    	list[a].add(b); // 인접 노드 삽입
	    	list[b].add(a); // 인접 노드 삽입
	    }
	    
	    ArrayDeque<Integer> q = new ArrayDeque<>(); // 인접 노드 탐색을 위한 큐 선언
	    q.add(1); // 루트노드 1 부터 탐색 시작
	    visit[1] = true; // 로트노드 1 방문 처리 
	    while(!q.isEmpty()) { // 큐가 빌 때 까지 반복 
	    	int nowNode = q.poll(); // 노드하나를 꺼냅니다. 처음은 1이겠지요
	    	for(int i=0; i<list[nowNode].size(); i++) { // ex) 1의 인접 노드들 탐색
	    		int nextNode = list[nowNode].get(i); // 인접노드를 nextNode 변수에 담아 연산
	    		if(!visit[nextNode]) { // 인접 노드가 방문하지 않았다면 이하 연산 실행 
	    			visit[nextNode] = true; // 인접 노드 방문 처리 
	    			parent[nextNode] = nowNode; // 인접노드의 부모노드를 nowNode로 초기화
	    			q.add(nextNode); // 인접노드를 큐에 담아 BFS탐색토록 함 
	    		}
	    	}
	    }
	    
	    // 이하 결과 출력 로직
	    StringBuilder sb = new StringBuilder();
	    for(int i=2; i<=N; i++) 
	    	sb.append(parent[i]).append('\n');
	    
		System.out.println(sb);
	}
	// 빠른 입력을 위해 만듦
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}