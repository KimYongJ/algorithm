import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        
        int l = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<l; i++)
            a.add(Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<l; i++)
            b.add(Integer.parseInt(st.nextToken()));
        
        Collections.sort(a);
        Collections.sort(b,Collections.reverseOrder());
        
        int sum = 0;
        for(int i=0; i<l; i++)
            sum += a.get(i)*b.get(i);
        
        System.out.println(sum);
        
    }
}