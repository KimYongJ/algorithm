import java.util.*;
class Solution {
    static int idx1,idx2,i;
    public String solution(String[] c1, String[] c2, String[] g) {
        Stack<int[]> stack = new Stack<>();
        boolean isRollback = false;
        for(i=0; i<g.length; i++){
            if(isRollback){
                if(c2[idx2].equals(g[i])){
                    idx2++; isRollback=false; continue;
                }else if(!stack.empty()){
                    int[] rollBack = stack.pop();
                    idx1=rollBack[0];
                    idx2=rollBack[1];
                    i=rollBack[2];
                    continue;
                }
                else
                    break;
            }
            if(idx1 < c1.length && c1[idx1].equals(g[i]) && idx2 < c2.length && c2[idx2].equals(g[i])){
                stack.push(new int[]{idx1,idx2,i}); // 선택했던 방향은?
                idx1++; continue;
            }else if(idx1 < c1.length && c1[idx1].equals(g[i])){
                idx1++; continue;
            }else if(idx2 < c2.length && c2[idx2].equals(g[i])){
                idx2++; continue;
            }else if(!stack.empty()){
                int[] rollBack = stack.pop();
                idx1=rollBack[0];
                idx2=rollBack[1];
                i=rollBack[2];
                isRollback=true;
                continue;
            }else
                break;
        }
        if(i==g.length)
            return "Yes";
        return "No";
    }
}