import java.util.*;
class Solution {
    public int solution(int[] r, boolean[] bool) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.data-b.data);
        for(int i=0; i<bool.length; i++)
            if(bool[i]) 
                pq.add(new Node(r[i],i));

        int result = 0;
        
        for(int i=0,p=10000; i<3; i++,p/=100)
            result += p*pq.poll().idx;
        
        return result;
    }
    class Node{
    int data, idx;
    Node(int data, int idx){
        this.data=data;
        this.idx=idx;
    }
}
}
