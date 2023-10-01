// https://github.com/KimYongJ/algorithm
import java.util.PriorityQueue;
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
            
            genKeyString.put(genres[i],genKeyString.getOrDefault(genres[i],0)+plays[i]);
            
            ArrayList<Node> list = hmlist.getOrDefault(genres[i],new ArrayList<>());
            
            list.add(new Node(i,plays[i]));
            
            hmlist.put(genres[i],list);
        }
        
        
        PriorityQueue<Integer> q = new PriorityQueue<>((a,b)->b-a); // 재생횟수 담을 우선순위큐(내림차순)
        HashMap<Integer,String> genKeyInteger = new HashMap<>();// key: 재생횟수 , value : 장르
        
        for(Map.Entry<String,Integer> set : genKeyString.entrySet()){// 키 벨류 반전 및 재생 횟수 내림차순 정렬
            q.add(set.getValue());
            genKeyInteger.put(set.getValue(),set.getKey());
        }
        
        ArrayList<Integer> result = new ArrayList<>();// 결과

        while(!q.isEmpty()){
            int qData = q.poll();
            String genre = genKeyInteger.get(qData); // 가장 많이 재생된 장르
            ArrayList<Node> list = hmlist.get(genre);// 가장 많이 재생된 장르의 고유번호 리스트
            
            Collections.sort(list,(a,b)->{
                if(b.play==a.play){
                    return a.idx - b.idx;
                }else{
                    return b.play-a.play;
                }
            });
            
            result.add(list.get(0).idx);
            if(list.size()>1)
                result.add(list.get(1).idx);
        }
        
        return result;
    }
}