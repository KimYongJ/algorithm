class Solution {
    static int x;
    String arr[] = {"A","E","I","O","U"}, word;
    boolean endCondition = false;
    public int solution(String w) {
        word = w;
        DFS("",0);
        return x;
    }
    public void DFS(String part,int depth){
        if(word.equals(part)){
            endCondition = true;
        }
        if(depth==5){
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