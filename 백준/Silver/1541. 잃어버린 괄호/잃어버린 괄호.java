import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = (br.readLine()).split("\\-");
        int sum = 0;
        for(int i=0; i<str.length; i++){
            for(String s : str[i].split("\\+")){
                int num = Integer.parseInt(s);
                sum -= i==0 ? num*-1 : num;
            }
        }
        System.out.println(sum);
    }
}