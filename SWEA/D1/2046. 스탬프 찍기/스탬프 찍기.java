import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

class Solution{
    public static void main(String[] args)throws Exception{
        BufferedWriter w = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner in = new Scanner(System.in);
        int l = in.nextInt();
        for(int i=0; i<l; i++)
            w.write("#");
        w.flush();
    }
}