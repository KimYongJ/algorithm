// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;
public class Value{ // 단어와 최단거리를 담을 객체 선언
    int len;
    String word;
    Value(String word, int len){
        this.word   = word;
        this.len    = len;
    }
}
class Solution {

    public int solution(String begin, String target, String[] words) {
        
        ArrayDeque<Value> q = new ArrayDeque<>();
        
        boolean[] visit = new boolean[words.length];// 방문 유무 체크
        
        q.add(new Value(begin,0));// 큐에 첫번 째 데이터를 담는다.
        
        while(!q.isEmpty()){
            Value value = q.poll(); // 시작객체를 꺼낸다
            if(target.equals(value.word)){ // 종료 조건
                return value.len;
            }
            for(int i=0; i<words.length; i++){// words 배열을 순차적으로 순회
                if(check(value.word,words[i]) &&!visit[i]){ // 큐에 넣지 않은 단어이면서 두 단어의 글자 차이가 1개이하인 경우만 BFS 진행
                    visit[i] = true;
                    q.add(new Value(words[i],value.len+1));
                }
            }
        }
        return 0;
    }
    public boolean check(String a, String b){// 단어가 하나만 차이나는지 체크 
        int cnt = 0;
        for(int i=0; i<a.length(); i++){
            if(a.charAt(i) != b.charAt(i))
                cnt++;
            if(cnt>1)
                return false;
        }
        return true;
    }
}