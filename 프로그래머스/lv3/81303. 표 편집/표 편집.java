import java.util.Stack;
class Solution {
    class Node{
        Node prev = null;
        Node next = null;
        boolean isDelete = false;
    }
    public String solution(int n, int k, String[] cmd) {
        Stack<Node> st = new Stack<>();
        Node[] nodeArr = new Node[n];
        nodeArr[0] = new Node();
        for(int i=1; i<n; i++){
            nodeArr[i] = new Node();
            nodeArr[i-1].next = nodeArr[i];
            nodeArr[i].prev = nodeArr[i-1];
        }
        Node now = nodeArr[k];
        for(String com : cmd){
            char c = com.charAt(0);
            if(c=='U'){
                int cnt = Integer.parseInt(com.substring(2));
                for(int i=0; i<cnt; i++) now = now.prev;
            }else if(c=='D'){
                int cnt = Integer.parseInt(com.substring(2));
                for(int i=0; i<cnt; i++) now = now.next;
            }else if(c=='C'){
                st.push(now);
                now.isDelete = true;
                Node prev = now.prev;
                Node next = now.next;
                if(prev != null){
                   prev.next = next; 
                }
                if(next != null){
                    next.prev = prev;
                    now = next;
                }else{
                    now = prev;
                }
            }else if(c=='Z'){
                Node node = st.pop();
                node.isDelete = false;
                Node prev = node.prev;
                Node next = node.next;
                if(prev != null){
                    prev.next = node;
                }
                if(next != null){
                    next.prev = node;
                }
                
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            if(nodeArr[i].isDelete) sb.append('X');
            else sb.append('O');
        }
        
        return sb.toString();
    }
}