//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main{
    public static void main(String[] args)throws Exception{
        final int INF = 100_000_001;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        
        ArrayList<Node>[] list = new ArrayList[N+1]; // 노드의 연결과 가중치를 담을 list 배열
        
        for(int i=1; i<=N; i++)// 배열 초기화
            list[i] = new ArrayList<Node>();
            
        for(int i=0; i<B; i++){// 노드 정보를 입력 받는다.
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b,d)); // a노드에 인접한 b노드와 d를 넣음 
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());// 시작 인덱스
        int end = Integer.parseInt(st.nextToken());// 종료인덱스
        
        // 다익스트라 알고리즘을 위해 거리를 내림차순으로 하는 우선순위 큐 생성
        PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.dist-b.dist);
        boolean[] visit = new boolean[N+1];// 방문담을 배열
        int[] dist = new int[N+1];// start노드에서 각노드까지 거리를 담을 배열
        Arrays.fill(dist,INF);// 초기값을 나올 수 있는 가장 큰 값으로 합니다.
        dist[start] = 0;// 자기 자신은 거리 0으로 초기화 합니다.
        pq.add(new Node(start,0));// 큐에 첫데이터를 넣어 줍니다.
        
        while(!pq.isEmpty()){// 큐가 빌 때까지 반복
            Node node = pq.poll();
            int nowNode = node.node;
            int nowDist = node.dist;
            
            if(!visit[nowNode]){// 큐에서 꺼낸 노드가 방문한 적 없어야 다익스트라 시작
                visit[nowNode] = true;// 방문처리 
                
                for(int i=0; i<list[nowNode].size(); i++){// 꺼낸 노드와 인접한 노드들을 순차적으로 뽑는다.
                    Node node2 = list[nowNode].get(i);// 꺼낸 노드와 인접한 노드
                    int nextNode = node2.node;// 인접 노드 번호
                    int nextDist = node2.dist;// 꺼낸노드와 인접노드번호까지 거리
                    int distSum = nowDist + nextDist;// start부터 꺼낸 노드를 거쳐 꺼낸 노드의 인접노드까지의 거리
                    if(dist[nextNode] > distSum){// 기존 값보다 꺼낸노드를 거쳐가는게 빠르다면 이하 연산
                        dist[nextNode] = distSum;
                        pq.add(new Node(nextNode,distSum));
                    }
                }
            }
        }
        
        System.out.println(dist[end]);
    }
}
class Node{
    int node,dist;
    Node(int node, int dist){
        this.node = node;
        this.dist = dist;
    }
}