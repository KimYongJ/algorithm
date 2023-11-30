import java.util.Scanner;
class Main{
    public static void main(String[] args)throws Exception{
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        int divN = n/4;
        
        for(int i=0; i<divN; i++){
            System.out.print("long ");
        }
        System.out.print("int");
    }
}