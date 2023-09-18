//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int idx = k-1;
        
        for(int i=0; i<n; i++){
            list.add(i+1);
        }
        
        sb.append("<");
        
        while(list.size()!=1){
            int num = list.get(idx);
            list.remove(idx);
            sb.append(num).append(", ");
            idx = (idx+k-1)%list.size();
        }
        
        sb.append(list.get(0)).append(">");
        
        System.out.println(sb.toString());
    }
}