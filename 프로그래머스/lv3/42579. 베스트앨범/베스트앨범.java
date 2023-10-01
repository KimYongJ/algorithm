// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
class Node{
    int idx, play;
    Node(int idx,int play){
        this.idx = idx;
        this.play = play;
    }
}
class Solution {
    public ArrayList<?> solution(String[] genres, int[] plays) {
        HashMap<String,Integer> genKeyString = new HashMap<>(); // 장르 당 총 재생 횟수 저장
        HashMap<String,ArrayList<Node>> hmlist = new HashMap<>();// 장르 당 고유번호 저장
        
        for(int i=0; i<genres.length; i++){
            
            genKeyString.put(genres[i],genKeyString.getOrDefault(genres[i],0)+plays[i]);// 장르당 총 재생 횟수 담는 코드
            
            // 이하 장르당 고유번호 저장 코드
            ArrayList<Node> list = hmlist.getOrDefault(genres[i],new ArrayList<>());
            list.add(new Node(i,plays[i]));
            hmlist.put(genres[i],list);
        }
        // 이하 장르당 총 재생횟수 저장 및, 재생횟수기준 내림차순 정렬
        ArrayList<Map.Entry<String,Integer>> maplist = new ArrayList<>(genKeyString.entrySet());
        Collections.sort(maplist,(a,b)->b.getValue()-a.getValue());
        
        ArrayList<Integer> result = new ArrayList<>();// 결과

        for(int i=0; i<maplist.size(); i++){
            String genre = maplist.get(i).getKey(); // 가장 많이 재생된 장르
            ArrayList<Node> list = hmlist.get(genre);// 가장 많이 재생된 장르의 고유번호 리스트
            
            Collections.sort(list,(a,b)->{// 장르당 고유번호 내림차순 , 같을 경우 고유번호기준 오름차순 정렬
                if(b.play==a.play){
                    return a.idx - b.idx;
                }else{
                    return b.play-a.play;
                }
            });
            
            result.add(list.get(0).idx);// 결과 추가
            if(list.size()>1)// 값이 2개이상일 경우 결과추가 한번더함
                result.add(list.get(1).idx);
        }
        
        return result;
    }
}
