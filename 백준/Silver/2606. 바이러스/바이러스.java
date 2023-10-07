// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int r = Integer.parseInt(br.readLine());
        
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();// 인접 리스트를 담을 리트스 선언
        for(int i=0; i<=n; i++)// 인접리스트 초기화
            list.add(new ArrayList<Integer>());
        
        for(int i=0; i<r; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            list.get(a).add(b);// 인접리스트 세팅
            list.get(b).add(a);// 인접리스트 세팅
        }
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] visit = new boolean[n+1];// 방문 노드 체크
        visit[1] = true; // 1을 방문처리
        q.add(1);// 1을 큐에 삽입
        
        int result = 0; // 횟수 카운팅
        
        while(!q.isEmpty()){
            
            int node = q.poll(); // 기준노드를 뽑아온다.
            
            ArrayList<Integer> as_list = list.get(node);// 뽑은 기준 노드의 인접리스트를 가져온다.
            
            for(int i=0; i<as_list.size(); i++){
                
                int as_node = as_list.get(i);//인접 리스트를 순차적으로 탐색하며 해당 노드를 방문하지 않았다면 큐에 값을 넣으며 결과에 +1을 한다.
                
                if(!visit[as_node]){
                    visit[as_node] = true;
                    q.add(as_node);
                    result++;
                }
            }
        }
        
        System.out.println(result);
    }
}