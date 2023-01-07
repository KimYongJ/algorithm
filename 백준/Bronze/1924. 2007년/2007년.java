import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int day = y,cnt = 1;
        while(cnt<x){
            if(cnt==1||cnt==3||cnt==5||cnt==7||cnt==8||cnt==10) day += 31;
            else if(cnt==4||cnt==6||cnt==9||cnt==11) day += 30;
            else day += 28; cnt++;
        }
        switch(day%7){
            case 1:
                System.out.println("MON"); break;
            case 2:
                System.out.println("TUE"); break;
            case 3:
                System.out.println("WED"); break;
            case 4:
                System.out.println("THU"); break;
            case 5:
                System.out.println("FRI"); break;
            case 6:
                System.out.println("SAT"); break;
            case 0:
                System.out.println("SUN"); break;
        }
    }
}