// https://github.com/KimYongJ/algorithm
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Collections;
class Solution {
    
    private ArrayList<String> result = new ArrayList<>();
    private HashMap<String,Integer> hm;
    private char[] strArr;
    private boolean[] visit;
    private int maxCount = 0;
    
    public ArrayList<String> solution(String[] orders, int[] course) {
        for(int i=0; i<orders.length; i++){ // 조합을 구하기 위해 orders의 내용을 오름차순 정렬
            char[] arr = orders[i].toCharArray();
            
            Arrays.sort(arr);
            
            orders[i] = String.valueOf(arr);
        }
        
        for(int n : course){
            hm = new HashMap<>();
            
            // 각각의 주문들에 대해 n개의 조합을 생성해 hm에 담는다.
            for(String order : orders){
                visit = new boolean[order.length()];
                strArr = order.toCharArray();
                combination(order.length(),0,order.length()-n);//순서 : 최종 구할길이 , 시작인덱스, 깊이 
                //(깊이 = (최종 구할길이 - 뽑을 조합수) //이것은 조합을 구하는 공식이다.
            }
            // hashmap에 담긴 조합들 중 가장 큰 숫자인 것들만 결과 리스트에 담는다.
            for(Entry<String,Integer> entry : hm.entrySet()){
                if(entry.getValue()==maxCount && maxCount>1){
                    result.add(entry.getKey());
                }
            }
            maxCount = 0;
        }
        Collections.sort(result);
        return result;
    }
                        //순서 : 최종 구할길이 , 시작인덱스, 깊이
    public void combination(int maxlen,int start,int depth){
        if(maxlen==depth){
            String str = printString(); // 완성된 문자열 출력
            int count = hm.getOrDefault(str,0)+1;// 해시맵에 해당 문자열이 있는지 확인
            hm.put(str,count);
            maxCount = Math.max(maxCount,count);
            return;
        }
        for(int i=start; i<maxlen; i++){
            visit[i] = true;
            combination(maxlen,i+1,depth+1);
            visit[i] = false;
        }
    }
    public String printString(){ // 완성된 조합을 문자열로 출력해주는 함수
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<strArr.length; i++){
            if(visit[i]){
                sb.append(strArr[i]);
            }
        }
        return sb.toString();
    }
}