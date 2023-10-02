//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        
        HashSet<Integer> set = new HashSet<>(20);
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if("all".equals(cmd)){
                set = new HashSet<>(){{
                    add(1); add(2); add(3); add(4); add(5); add(6); add(7);
                    add(8); add(9); add(10); add(11); add(12); add(13); add(14);
                    add(15); add(16); add(17); add(18); add(19); add(20);
                }};
            }else if("empty".equals(cmd)){
                set = new HashSet<>(20);
            }else{
                int x = Integer.parseInt(st.nextToken());
                if("add".equals(cmd)){
                    set.add(x);
                }else if("remove".equals(cmd)){
                    set.remove(x);
                }else if("toggle".equals(cmd)){
                    if(set.contains(x)){
                        set.remove(x);
                    }else{
                        set.add(x);
                    }
                }else{
                    int num = set.contains(x) ? 1 : 0;
                    sb.append(num).append("\n");
                }
            }
        }
        System.out.print(sb.toString());
    }
}