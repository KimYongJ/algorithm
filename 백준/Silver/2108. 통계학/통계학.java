import java.io.*;

class Main{
    public static final int MAX = 4000;
    public static int[] b = new int[8001];
    public static StringBuilder sb = new StringBuilder();
    public static int l;
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        l = Integer.parseInt(br.readLine());
        double sum = 0;
        int max=-MAX, min=MAX;
        for(int i=0; i<l; i++){
            int num = Integer.parseInt(br.readLine());
            b[num+MAX]++;
            sum+=num;
            if(num>max) max = num;
            if(num<min) min = num;
        }
        sb.append(Math.round(sum/l)).append("\n");
        cntFunc();
        sb.append(max-min);
        System.out.println(sb);        
    }
    public static void cntFunc(){ // 중앙값 + 최빈값 + 최소최대차이
        int max = -MAX-1, num = 0, sum = 0, c = l/2+1;
        boolean flag = true;
        for(int x=0; x<MAX*2+1; x++){
            if(b[x]>max){
                max = b[x];
                num = x;
            }
            if(flag){
                sum += b[x];
                if(sum>=c){
                    c=x;
                    flag = false;
                }
            }
        }
        sum=0;
        for(int y=0; y<MAX*2+1; y++){
            if(b[y]==max)
                sum++;
            if(sum==2){
                num = y;
                break;
            }
        }
        sb.append(c-MAX).append("\n").append(num-MAX).append("\n");
    }
}