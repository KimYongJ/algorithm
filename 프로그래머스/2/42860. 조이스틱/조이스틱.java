// https://github.com/KimYongJ/algorithm
class Solution {
    public int solution(String name) {
        int result = 0;
        int move = name.length()-1;
        for(int i=0; i<name.length(); i++){
            char c = name.charAt(i);
            result += c<78 ? c-65 : 91-c;// 'N'보다 작으냐 크냐에 따라 계산이 달라짐
            int end = i+1;
            if(end<name.length() && name.charAt(end)=='A'){
                while(end<name.length() && name.charAt(end)=='A')
                    end++;
                move = Math.min(move , i*2+name.length()-end); // 앞으로 갔다 뒤로 가는 경우
                move = Math.min(move , (name.length()-end)*2 + i); // 뒤로갔다 앞으로 가는 경우
            }
        }
        return result + move;
    }
}