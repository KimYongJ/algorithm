import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int l = Integer.parseInt(br.readLine());
        int[] a = new int[l] , b = new int[l];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<l; i++)
            a[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<l; i++)
            b[i] = Integer.parseInt(st.nextToken());
        
        Arrays.sort(a);
        b = Arrays.stream(b).boxed().sorted(Collections.reverseOrder())
                            .mapToInt(Integer::intValue).toArray();
        
        int sum = 0;
        for(int i=0; i<l; i++)
            sum += a[i]*b[i];
        
        System.out.println(sum);
        
    }
}