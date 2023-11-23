// https://github.com/KimYongJ/algorithm

class Main{
    
    final static int INF = 10_000_001;
    
    public static void main(String[] args)throws Exception{
        StringBuilder sb = new StringBuilder();
        int N = read();
        int M = read();
    
        int[][] arr = new int[N+1][N+1];
    
        // 플로이드와샬 알고리즘을 위한 셋팅, 자기자신은 0, 아닌것은 MAX로 셋팅
        for(int i=1; i<=N; i++)
            for(int j=1; j<=N; j++){
                if(i==j) continue;
                arr[i][j] = INF;
            }
        
        // 값 셋팅
        for(int i=0; i<M; i++){

            int nodeA = read();
            int nodeB = read();
            int dist = read();
            if(arr[nodeA][nodeB]> dist)
            	arr[nodeA][nodeB] = dist;
        }
        
        // 플로이드와샬 알고리즘 시작
        for(int k=1; k<=N; k++) {
            for(int i=1; i<=N; i++) {
            	if(k==i) continue;
                for(int j=1; j<=N; j++) {
                	if(i==j || j==k) continue;
                    if(arr[i][j] > arr[i][k]+arr[k][j]) {
                        arr[i][j] = arr[i][k]+arr[k][j];
                    }
                }
            }
        }

        
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                int num = arr[i][j];
                if(num==INF) 
                    num = 0;
                sb.append(num).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
        
    }
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}