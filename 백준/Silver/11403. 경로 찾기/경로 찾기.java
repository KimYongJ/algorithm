// https://github.com/KimYongJ/algorithm
class Main{

    public static void main(String[] args)throws Exception{
        int N = read();
        int[][] result = new int[N][N];
        // 입력된 인접 리스트를 담는 코드
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                result[i][j] = read();
            }
        }
        
        for(int k=0; k<N; k++) // 이전 연결 가능한 노드
            for(int i=0; i<N; i++) // 현재 노드
                for(int j=0; j<N; j++){// 연결 가능한 노드
                    if(result[i][k] != 0 && result[k][j] != 0)
                        result[i][j] = 1;
                }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++)
                sb.append(result[i][j]).append(' ');
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