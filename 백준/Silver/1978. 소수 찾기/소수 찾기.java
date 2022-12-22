import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        String[] str = br.readLine().split(" ");
        int cnt = 0;
        for(String s : str)
            if(check(s))
                cnt++;
        
        System.out.println(cnt);
    }
    public static boolean check(String s){
        int num = Integer.parseInt(s);
        int cnt = 0;
        if(num==1)
        	return false;
        for(int i=2; i*i<=num; i++)
            if(num%i==0)
                return false;

        return true;
    }
}