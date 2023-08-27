class Solution {
    static int x;
    String arr[] = {"A","E","I","O","U"}, targetWord;
    boolean endCondition = false;
    public int solution(String w) {
        targetWord = w;
        DFS("",0);
        return x;
    }
    public void DFS(String part,int depth){
        if(targetWord.equals(part) || depth==5) {
            if(targetWord.equals(part)) endCondition = true;
            return;
        }
        
        for(int i=0; i<5; i++){
            if(!endCondition){
                x++;
                DFS(part+arr[i],depth+1);
            }else return;
        }
    }
}