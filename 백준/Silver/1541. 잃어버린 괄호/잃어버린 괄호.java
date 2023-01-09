import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = ("("+br.readLine().replaceAll("\\-",")-(")+")").split("\\-");
        int sum = 0;
        for(int i=0; i<str.length; i++){
            int sum1 = 0;
            str[i] = str[i].substring(1,str[i].length()-1);
            for(String s : str[i].split("\\+"))
                sum1 += Integer.parseInt(s);
            sum -= i==0 ? sum1*-1 : sum1;
        }
        System.out.println(sum);
    }
}