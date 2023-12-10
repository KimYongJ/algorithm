// github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
    
    static int R, C, arr[][], visit[][], result;
    static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
    
    public static void main(String[] args)throws Exception{
        BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
        R 					= read();
        C 					= read();
        arr 				= new int[R+2][C+2];
        visit 				= new int[R+2][C+2];
        
        for(int i=1; i<=R; i++){
            String str 		= br.readLine();
            for(int j=1; j<=C; j++)
                arr[i][j] 	= str.charAt(j-1)-'A'; 		// 숫자로 해당 문자를 변환해 넣음
        }
        for(int j=0; j<C+2; j++) { 						// 상 하에 1,1좌표 값으로 패딩을 넣어 불필요한 좌표 유효성검증을 없앰
        	arr[0][j] 		= arr[1][1];
        	arr[R+1][j] 	= arr[1][1];
        }
        for(int i=0; i<R+2; i++) { 						// 좌 우에 1,1좌표 값으로 패딩을 넣어 불필요한 좌표 유효성검증을 없앰
        	arr[i][0] 		= arr[1][1];
        	arr[i][C+1] 	= arr[1][1];
        }
        
        DFS(1,1,(1<<arr[0][0]),1); 						// 방문한 문자를 bit로 연산한다. A부터 Z까지 26개이므로 26비트로 계산가능
        
        System.out.println(result);
    }
    public static void DFS(int i, int j, int visit_bit, int cnt){
    	if(visit[i][j] == visit_bit || result == 26)	// 해당 좌표에 visit_bit 값이 똑같이 들어간적이 있다면 스킵, 26이여도 스킵 
    		return; 		
    	
    	visit[i][j] = visit_bit;						// 해당 좌표에 visit_bit를 바인딩( 해당좌표에 같은 문자열 방문이 있다면 스킵됨.
        for(int xy[] : dxy){
            int newI = i + xy[0]; 						// 새로운 좌표 I 생성
            int newJ = j + xy[1]; 						// 새로운 좌표 J 생성
            if((visit_bit & (1<<arr[newI][newJ]))== 0) 	// &연산자를 쓰는이유는 visit_bit와 해당 넣으려는 비트가 겹치는게 하나도 없어야 되기때문(그래야 방문안한것이니까)
            	DFS(newI, newJ, (visit_bit | (1<<arr[newI][newJ])), cnt+1);// 새로 만든 좌표 전달, 좌표전달시 visit_bit에 해당 좌표의 비트 마킹처리
        }
        if( result < cnt)
            result = cnt;
    }
    // 빠른 입력을 위해 만든 함수
    public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}


}

/**********************이하 BFS 방식****************************/
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayDeque;
//import java.util.StringTokenizer;
//
//class Main{
//    
//    static int R, C, arr[][];
//    static int result = 0;
//    static int dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
//    static int visit[][];
//    
//    public static void main(String[] args)throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        
//        R = Integer.parseInt(st.nextToken());
//        C = Integer.parseInt(st.nextToken());
//        arr = new int[R+2][C+2];
//        visit = new int[R+2][C+2];
//        
//        for(int i=1; i<=R; i++){
//            String str = br.readLine();
//            for(int j=1; j<=C; j++)
//                arr[i][j] = str.charAt(j-1)-'A'; 		// 숫자로 해당 문자를 변환해 넣음
//        }
//        for(int j=0; j<C+2; j++) { 						// 상 하에 1,1좌표 값으로 패딩을 넣어 불필요한 좌표 유효성검증을 없앰
//        	arr[0][j] = arr[1][1];
//        	arr[R+1][j] = arr[1][1];
//        }
//        for(int i=0; i<R+2; i++) { 						// 좌 우에 1,1좌표 값으로 패딩을 넣어 불필요한 좌표 유효성검증을 없앰
//        	arr[i][0] = arr[1][1];
//        	arr[i][C+1] = arr[1][1];
//        }
//
//
//        ArrayDeque<Node> q = new ArrayDeque<>();
//        q.add(new Node(1,1,1, 1<<arr[1][1]));			// 순서 : i좌표, j좌표, 횟수, 방문한 문자 비트로 변환 체크한 int변수
//        visit[1][1] = 1<<arr[1][1]; 											// visit배열에 해당 방문 문자열 저장
//        while(!q.isEmpty()) {
//        	
//        	Node now = q.poll(); 												// 큐에서 데이터를 하나 꺼낸다.
//        	
//        	result = Math.max(result, now.cnt);									// 꺼낸 데이터와 최종 결과 중 큰 것을 결과에 저장
//        	
//        	for(int xy[] : dxy) {
//        		int newI = now.i + xy[0]; 										// 새로운 세로 좌표 생성
//        		int newJ = now.j + xy[1]; 										// 새로운 가로 좌표 생성
//        		if((now.visit_bit & (1<<arr[newI][newJ])) == 0) { 				// 생성한 좌표의 값이 이미 방문한 적이 없담면 이하 실행
//        			int next_visit_bit = (now.visit_bit | (1<<arr[newI][newJ]));// 방문 문자 비트에 추가
//        			if(visit[newI][newJ] != next_visit_bit) {
//	        			visit[newI][newJ] = next_visit_bit; 					// 생성 좌표에 문자열 방문 체크비트들 저장
//	        			q.add(new Node(newI, newJ,now.cnt+1, next_visit_bit));	// 저장 순서 : i좌표, j좌표, 거친 횟수, 지금까지 방문한 문자열들을 비트로 바꾼 int변수
//        			}
//        		}
//        	}
//        }
//        System.out.println(result);
//    }
//}
//class Node{
//	int i, j, cnt, visit_bit;
//	Node(int i, int j, int cnt, int visit_bit) {
//		this.i = i;
//		this.j = j;
//		this.cnt = cnt;
//		this.visit_bit = visit_bit;
//	}
//}