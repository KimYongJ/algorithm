import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] arr = br.readLine().toCharArray();
        long num = 0;
        for(int i=0; i<n; i++){
            num += (long)((arr[i]-'a')+1)*(long)Math.pow(31,i);
        }
        System.out.println(num%1234567891);
    }
}