import java.io.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int count = 0;
        for(char c : str.toCharArray()){
            if(c<68) count += 3;
            else if(c<71) count += 4;
            else if(c<74) count += 5;
            else if(c<77) count += 6;
            else if(c<80) count += 7;
            else if(c<84) count += 8;
            else if(c<87) count += 9;
            else count += 10;
        }
        System.out.println(count);
    }
    
}