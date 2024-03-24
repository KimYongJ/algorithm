import java.util.*;
class Solution {
    int baseNode, donut, bar, eight;
    ArrayList<Integer>[] adlist;
    HashSet<Point> useEdge = new HashSet<>();
    HashSet<Integer> useNode = new HashSet<>();
    public void init(int[][] edges){    // baseNode를 찾고, 인접리스트를 만든다.
        HashSet<Integer> node = new HashSet<>();
        adlist = new ArrayList[1_000_001];
        for(int i=1; i<1_000_001; i++)
        {
            adlist[i] = new ArrayList<>();
            node.add(i);
        }
        for(int e[] : edges)
        {
            adlist[e[0]].add(e[1]);     // 인접리스트 생성
            node.remove((Integer)e[1]); // 진입차수가 있는것 삭제
        }
        Iterator<Integer> ite = node.iterator();
        while(ite.hasNext())
        {
            int n = ite.next();
            if(adlist[n].size() >= 2)
            {
                baseNode = n;           // baseNode의 조건 : 진입차수가0개이고, 진출 차수가 2이상이다. 위에서 진입차수0인건 걸렀으므로 2이상인 경우 baseNode임
                return;
            }
        }

    }
    public int[] solution(int[][] edges) {
        init(edges);                                    // baseNode를 찾고, 인접리스트 세팅
        for(int i=0; i<adlist[baseNode].size(); i++){
            int next = adlist[baseNode].get(i);
            useEdge = new HashSet<>();                  // 사용한 간선 정보를 담을 set
            useNode = new HashSet<>();                  // 지나친 노드를 담을 set
            DFS(next);
            if(useNode.size() == useEdge.size())        // 방문한 정점과 간선 개수가 같은 경우 도넛 그래프
                donut++;
            else if(useNode.size()-1 == useEdge.size()) // 방문시 사용한 간선과 정점-1 개수가 같을 때 막대그래프
                bar++;
            else                                        // 그외는 8자모양 그래프
                eight++;  
        }        
        return new int[]{baseNode, donut, bar, eight};
    }
    public void DFS(int nowNode)
    {
        if(useNode.size() < useEdge.size())             // 빠른 종료를위해 8자모양 조건일 때 종료 
            return;
        useNode.add(nowNode);
        for(int i=0; i<adlist[nowNode].size(); i++){
            int nextNode = adlist[nowNode].get(i);
            if(useEdge.add(new Point(nowNode, nextNode)))
                DFS(nextNode);
        }
    }
        
}
class Point{
    int a, b;
    Point(int a, int b){this.a=a; this.b=b;}
    @Override
    public boolean equals(Object o){
        Point p = (Point) o;
        return p.a==a && p.b==b;
    }
    @Override
    public int hashCode(){return Objects.hash(a,b);}
}