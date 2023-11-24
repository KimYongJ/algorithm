// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
class Main{
  public static void main(String[] args)throws Exception{
    
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int start = Integer.parseInt(st.nextToken());
    int end = Integer.parseInt(st.nextToken());
    if(end<=start){
      System.out.println(start-end); return;
    }
    int min = Integer.MAX_VALUE;
    int[] value = {-1,1,2};
    boolean[] visit = new boolean[end*2];
    ArrayDeque<Node> q = new ArrayDeque<>();
    
    q.add(new Node(start,0));
    while(!q.isEmpty()){
      Node node = q.poll();
      visit[node.node] = true;
      for(int i=0; i<3; i++){
        int newNode;
        int newDist = node.dist;
        if(i==2){ // 곱하기할 때 
          newNode = node.node * value[i];
        }else{ // +1,-1할 때
          newNode = node.node + value[i];
          newDist += 1;
        }
        if(newNode<=0 || newNode >=end*2 || visit[newNode])
          continue;
        if(newNode == end) {
        	min = Math.min(min, newDist);
        	continue;
        }
        q.add(new Node(newNode, newDist));
      }
    }

    System.out.println(min);
  }
}
class Node{
  int node,dist;
  public Node(int node, int dist){
    this.node = node;
    this.dist = dist;
  }
}