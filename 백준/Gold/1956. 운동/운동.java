//https://github.com/KimYongJ/algorithm

class Main{
    
    public static void main(String[] args)throws Exception{
        final int INF = 4_000_001;
        int minDist = INF;
        int V = read();
        int E = read();
        int arr[][] = new int[V+1][V+1];
        
        for(int i=1; i<=V; i++) {
        	for(int j=1; j<=V; j++) {
        		if(i==j) continue;
        		arr[i][j] = INF;
        	}
        }
        
        for(int i=0; i<E; i++){
            int a = read(), b = read(), d = read();
            
            arr[a][b] = d;
        }
        
        // 플로이드 와샬로 모든 정점에서 모든 정점으로 거리를 구합니다.
        for(int k=1; k<=V; k++)
            for(int i=1; i<=V; i++){
                if(k==i) continue;
                for(int j=1; j<=V; j++){
                    if(i==j || k==j) continue;
                    if(arr[i][j] > arr[i][k] + arr[k][j]){
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        
        // i -> j 부터 j->i 까지 도달 가능하다면 사이클로 봅니다. 
        for(int i=1; i<=V; i++) {
        	for(int j=1; j<=V; j++) {
        		if(i==j) continue;
        		if(arr[i][j] != INF && arr[j][i] != INF) {
        			minDist = Math.min(minDist, arr[i][j] + arr[j][i]);
        		}
        	}
        }

        System.out.println(minDist == INF ? -1 : minDist);
    }
	public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return n;
	}
}