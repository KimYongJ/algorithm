import java.io.*;

class Main{
    public static int[] b = new int[8001];
    public static StringBuilder sb = new StringBuilder();
    public static int l;
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        l = Integer.parseInt(br.readLine());
        double sum = 0;
        int min =4000, max = -4000;
        for(int i=0; i<l; i++){
            int num = Integer.parseInt(br.readLine());
            b[num+4000]++;
            sum+=num;
            if(num>max) max = num;
            if(num<min) min = num;
        }
        sb.append(Math.round(sum/l)).append("\n");
        center();
        cntFunc();
        sb.append(max-min).append("\n");
        System.out.println(sb);        
    }
    public static void center(){ // 중앙값
        int c = l/2+1;
        int sum = 0;
        for(int i=0; i<8001; i++){
            sum += b[i];
            if(sum>=c){
                sb.append(i-4000).append("\n");
                return;
            }
        }
        
        
    }
    public static void cntFunc(){ // 최빈값
        int max = -4001, num =0;
        for(int x=0; x<8001; x++)
            if(b[x]>max){
                max = b[x];
                num = x;
            }
        int cnt = 0;
        for(int y=0; y<8001; y++){
            if(b[y]==max)
                cnt++;
            if(cnt==2){
                sb.append(y-4000).append("\n");
                return;
            }
        }
        sb.append(num-4000).append("\n");
    }
}