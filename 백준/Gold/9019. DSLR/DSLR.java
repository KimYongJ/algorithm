// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Main{
    
    static StringBuilder sb = new StringBuilder();
    static int start, end;
    static char[] cmdList = {'D','S','L','R'};
    static ArrayDeque<Order> q;
    static boolean[] visit;
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        while(T-->0){
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            visit = new boolean[10000];
            BFS();
        }
        
        System.out.println(sb);
    }
    public static void BFS(){
        q = new ArrayDeque<>();
        
        insertData("",start);// 큐에 값을 넣는다.
        
        while(!q.isEmpty()){
            Order o = q.poll();
            
            if(o.num == end){ // 종료조건
                sb.append(o.cmd).append('\n');
                return;
            }
            
            insertData(o.cmd,o.num);// 큐에 값을 넣는다.        
        }
    }
    public static void insertData(String base,int num){
        for(int i=0; i<4; i++) {
        	int result = DSLR(num,i);
        	if(!visit[result]) {
        		visit[result] = true;
        		q.add(new Order(base + cmdList[i] , result));
        	}
        }
        
    }
    public static int DSLR(int n,int cmd){
        switch(cmd){
            case 0: return (2*n)%10000;
            case 1: return n==0 ? 9999 : n-1;
            case 2: return (n % 1000) * 10 + n / 1000;  
            case 3: return (n % 10) * 1000 + n / 10;
        }
        return 0;
    }
}
class Order{
    String cmd = "";
    int num;
    public Order(String cmd, int num){
        this.cmd = cmd;
        this.num = num;
    }
}