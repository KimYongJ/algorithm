import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        ArrayDeque<Integer> q = new ArrayDeque<>();
        int n = Integer.parseInt(br.readLine());
        
        for(int i=0; i<n; i++){
            String[] cmd = br.readLine().split(" ");
            switch(cmd[0]){
                case "push":
                    q.add(Integer.parseInt(cmd[1]));
                    break;
                case "pop":
                    sb.append(q.isEmpty() ? -1 : q.poll()).append("\n");
                    break;
                case "size":
                    sb.append(q.size()).append("\n");
                    break;
                case "empty":
                    sb.append(q.isEmpty() ? 1 : 0).append("\n");
                    break;
                case "front":
                    sb.append(q.isEmpty() ? -1 : q.peek()).append("\n");
                    break;
                case "back":
                    sb.append(q.isEmpty() ? -1 : q.peekLast()).append("\n");
                    break;
            }
        }
        System.out.println(sb.toString());
    }
}