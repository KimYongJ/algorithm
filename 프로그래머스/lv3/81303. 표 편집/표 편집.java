import java.util.Stack;
class Solution {
    class Node{
        Node prev = null;
        Node next = null;
        boolean isDelete = false;
    }
    
    public String solution(int n, int k, String[] cmd) {
        Stack<Node> trashCan = new Stack<>();
        Node[] nodeArr = new Node[n];
        nodeArr[0] = new Node();
        for(int i=1; i<n; i++){
            nodeArr[i] = new Node();
            nodeArr[i].prev = nodeArr[i-1];
            nodeArr[i-1].next = nodeArr[i];
        }
        Node now = nodeArr[k];
        
        for(String c : cmd){
            char condition = c.charAt(0);
            if(condition=='U'){
                int cnt = Integer.parseInt(c.substring(2));
                for(int i=0; i<cnt; i++) now = now.prev;
            }else if(condition=='D'){
                int cnt = Integer.parseInt(c.substring(2));
                for(int i=0; i<cnt; i++) now = now.next;
            }else if(condition=='C'){
                    now.isDelete = true;
                    trashCan.push(now);
                    Node prev = now.prev;
                    Node next = now.next;
                    if(prev!=null){
                        prev.next = next;
                    }
                    if(next!=null){
                        next.prev = prev;
                        now = next;
                    }else{
                        now = prev;
                    }
            }else{
                    Node node = trashCan.pop();
                    Node prev1 = node.prev;
                    Node next1 = node.next;
                    node.isDelete = false;
                    if(prev1 != null){
                        prev1.next = node;
                    }
                    if(next1 != null){
                        next1.prev = node;
                    }
            }
        }
        StringBuilder sb =new StringBuilder();
        for(int i=0; i<n; i++){
            if(nodeArr[i].isDelete) sb.append('X');
            else    sb.append('O');
        }
        return sb.toString();
    }
}