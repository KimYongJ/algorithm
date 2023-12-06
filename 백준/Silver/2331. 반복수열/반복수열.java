// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
class Main{
    
    public static void main(String[] args)throws Exception{
    	ArrayList<Integer> list = new ArrayList<>();
        int n = read();
        int r = read();
        DFS(n, r, list);
    }
    public static void DFS(int n, int r, ArrayList<Integer> list){
        if(list.contains(n)){
            System.out.print(list.indexOf(n));
            return;
        }
        list.add(n);
        int result = 0;
        while(n != 0){
            result += (int)Math.pow(n%10,r);
            n/=10;
        }
        DFS(result, r, list);
    }
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}