// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;

class Main{
    
    static int result = -1, N, A, B, P, a, b;
    static boolean visit[];
    static ArrayList<Integer>[] adList;
    
    public static void main(String[] args)throws Exception{
        N = read();
        visit = new boolean[N+1];
        adList = new ArrayList[N+1]; // 인접리스트 생성
        for(int i=1; i<=N; i++) // 인접 리스트 초기화
            adList[i] = new ArrayList<>();
        
        A = read();
        B = read();
        P = read();
        
        for(int i=0; i<P; i++){ // 인접 노드 전달 받음
            a = read();
            b = read();
            adList[a].add(b);
            adList[b].add(a);
        }
        DFS(A,0);
        System.out.println(result);
    }
    public static void DFS(int nowNode,int dist){
        if(visit[nowNode]) return;
        if(nowNode == B){
            result = dist;return;
        }
        visit[nowNode] = true;
        for(int i=0; i<adList[nowNode].size(); i++)
            DFS( adList[nowNode].get(i) , dist+1);
    }
    // 빠른 입력을 위한 함수
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}

/******************* 이하 BFS 방식 *******************/
//import java.util.ArrayDeque;
//import java.util.ArrayList;
//class Main{
// public static void main(String[] args)throws Exception{
//     int result = -1;
//     int N 		= read();
//     
//     ArrayList<Integer>[] adList 	= new ArrayList[N+1]; // 인접리스트 생성
//     boolean visit[] 				= new boolean[N+1];
//     for(int i=1; i<=N; i++) // 인접 리스트 초기화
//         adList[i] = new ArrayList<>();
//     
//     int A 		= read();
//     int B 		= read();
//     int P 		= read();
//     
//     for(int i=0; i<P; i++){
//         int a 	= read();
//         int b 	= read();
//         adList[a].add(b);
//         adList[b].add(a);
//     }
//     
//     ArrayDeque<Node> q = new ArrayDeque<Node>(){{add(new Node(A,0));}};
//     Loop:
//     while(!q.isEmpty()){
//         Node now 		= q.poll();
//         int nowNode 	= now.node;
//         int nowDist 	= now.dist;
//         
//         if(visit[nowNode]) continue; // 방문했던 노드는 스킵
//         visit[nowNode] = true;
//         
//         for(int i=0; i<adList[nowNode].size(); i++){
//             int nextNode 	= adList[nowNode].get(i);
//             int nextDist 	= nowDist + 1;
//             if(nextNode 	== B){
//                 result 	= nextDist;
//                 break Loop;
//             }
//             q.add(new Node(nextNode, nextDist));
//         }
//         
//     }
//     System.out.println(result);
// }
// // 빠른 입력을 위해 만든 함수
// public static int read() throws Exception {
//		int c, n = System.in.read() & 15;
//		boolean isNegative = n == 13;
//		if (isNegative) n = System.in.read() & 15;
//		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
//		if (c == 13) System.in.read();
//		return isNegative ? ~n + 1 : n;
//	}
// 
//}
//class Node{
// int node, dist;
// Node(int node, int dist){
//     this.node = node;
//     this.dist = dist;
// }
//}