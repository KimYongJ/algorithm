//https://github.com/KimYongJ/algorithm
class Solution {
 
 private int max=1, Ryan[];
 
 public int[] solution(int n, int[] Apeach) {
     Ryan = new int[]{-1};
     int[] temp = new int[11];
     DFS(0, n,Apeach,temp);
     return Ryan;
 }
 public void DFS(int depth, int n,int[] Apeach,int[] temp){
     if(depth==n){
         int ryan = 0, apeach = 0;
         for(int i=0; i<11; i++){
             if(!(temp[i]==0 && Apeach[i]==0)){
                 if(temp[i]>Apeach[i]) ryan += 10-i;
                 else apeach += 10-i;
             }
         }
         int score = ryan-apeach;
         if(max<=score){
             max = score;
             Ryan = temp.clone();
         }
         return;
     }
     for(int i=0; i<11 && temp[i]<=Apeach[i]; i++){
         temp[i]++;
         DFS(depth+1,n,Apeach,temp);
         temp[i]--;
     }
 }
}