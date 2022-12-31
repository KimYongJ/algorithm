import java.io.*;
import java.util.*;
import java.util.stream.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] copy = arr.clone();
        Arrays.sort(copy);
        HashMap<Integer,Integer> hm = new HashMap<>();
        int rank = 0;
        for(int key : copy)
            if(!hm.containsKey(key))
                hm.put(key,rank++);
        
        for(int key : arr)
            sb.append(hm.get(key)).append(" ");
            
        System.out.println(sb);       
    }
}