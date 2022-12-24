import java.io.*;
class Main{
    public static int[] count = new int[246913];
    public static void main(String[] args)throws Exception{
        countFunction(); // 시작부터 소수판별해서 저장함
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = 1;
        while((n = Integer.parseInt(br.readLine()))!=0)
            sb.append(count[2*n] - count[n]).append("\n");
        System.out.println(sb);
    }
    // 소수 갯수 누적해서 미리 저장해 놓는다.
    public static void countFunction(){
        int countNum = 0;
        for(int i=2; i<=246912; i++){
            if(check(i))
                countNum++;            
            count[i] = countNum;
        }
    }
    // 소수가 맞는지 확인하는 함수
    public static boolean check(int n){
        for(int i=2; i*i<=n; i++)
            if(n%i==0) return false;
        return true;
    }
}