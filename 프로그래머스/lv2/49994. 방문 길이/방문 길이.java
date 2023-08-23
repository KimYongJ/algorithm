import java.util.Set;
import java.util.HashSet;
class Solution {
    int nowX,nowY,nexX,nexY;
    public int solution(String d) {
        Set<Integer> set = new HashSet<>();
        nowX = nowY = nexX = nexY = 5;
        for(char c : d.toCharArray()){
            if(c=='U'){
                if(nexY-1<0) continue;
                nexY--; 
            }else if(c=='D'){
                if(nexY+1>10)continue;
                nexY++;
            }else if(c=='R'){
                if(nexX+1>10)continue;
                nexX++;
            }else if(c=='L'){
                if(nexX-1<0)continue;
                nexX--;
            }
            int now = nowX*9+nowY*99+nexX*999+nexY*9999; // 앞뒤를 연결해줘야 하는데 String하니 시간이오래걸림 그렇기에 숫자로진행
            int next= nexX*9+nexY*99+nowX*999+nowY*9999; // 앞뒤를 연결해줘야 하는데 String하니 시간이오래걸림 그렇기에 숫자로진행
            if(!set.contains(now) && !set.contains(next)){
                set.add(now);
                set.add(next);
            }
            nowX = nexX;
            nowY = nexY;
        }
        return set.size()/2;
    }
}