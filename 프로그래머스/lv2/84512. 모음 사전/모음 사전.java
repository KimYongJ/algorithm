class Solution {
    static int x;
    static String[] arr = {"A","E","I","O","U"};
    static boolean endCondition = false;
    public int solution(String word) {
        DFS("",0,word);
        return x;
    }
    public void DFS(String part,int depth,String word){
        if(word.equals(part)){
            endCondition = true;
        }
        if(depth==5){
            return;
        }
        for(int i=0; i<5; i++){
            if(!endCondition){
                x++;
                DFS(part+arr[i],depth+1,word);
            }
        }
    }
}