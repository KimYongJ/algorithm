// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
import java.util.Collections;
class Solution {
    
    public int solution(int[] picks, String[] minerals) {
        
        ArrayList<Energy> list = new ArrayList<>(); // 다이아, 철, 스톤에 대해 피로도를 담을 리스트
        int[][] score = {{1,1,1},{5,1,1},{25,5,1}}; 
        int total_picks = picks[0] + picks[1] + picks[2];
        
        // 초기 for문에서는 dia, iron, stone에 대해 피로도만 저장한다.
        for(int i=0; i<minerals.length; i+=5){
            
            if(total_picks==0) break;
            
            int dia=0, iron=0, stone=0;
            
            for(int j=i; j<i+5; j++){
                if(j==minerals.length) break;
                
                int type = minerals[j].equals("diamond") ? 0 :
                           minerals[j].equals("iron") ? 1 : 2;
                
                dia   += score[0][type];
                iron  += score[1][type];
                stone += score[2][type];
            }
            list.add(new Energy(dia,iron,stone));
            total_picks--;
        }
        Collections.sort(list,(a,b)->b.stone-a.stone); // 돌 피로도를 기준으로 내림차순한다.
        int result = 0;
        // 마지막에 결과를 구한다. 돌 피로도가 높은 순서대로 정렬하고, 다이아몬드로 캔다 즉 돌 피로도가 높은건 다이아몬드-> 철 -> 스톤 순으로 캐야 한다. 
        for(Energy e : list){
            if(picks[0]>0){
                result += e.dia;
                picks[0]--;
            }else if(picks[1]>0){
                result += e.iron;
                picks[1]--;
            }else if(picks[2]>0){
                result += e.stone;
                picks[2]--;
            }
        }
        return result;
    }
    
    class Energy{
        int dia;
        int iron;
        int stone;
        public Energy(int dia, int iron, int stone){
            this.dia = dia;
            this.iron = iron;
            this.stone = stone;
        }
    }
}