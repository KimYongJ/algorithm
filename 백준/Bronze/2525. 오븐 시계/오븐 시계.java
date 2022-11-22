import java.util.*;
class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t=sc.nextInt();
        int m=sc.nextInt();
        int d=sc.nextInt();
        
        int min=60*t+m;
        min+=d;
        
        t=(min/60)%24;
        m=min%60;
        
        
        System.out.println(t+" "+m);
    }
}