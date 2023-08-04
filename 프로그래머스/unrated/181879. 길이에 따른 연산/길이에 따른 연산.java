class Solution {
    public int solution(int[] list) {
        int answer = 1;
        if(list.length>10){
            for(int x : list) answer += x;
            return --answer;
        }else{
            for(int x : list) answer *= x;
            return answer;
        }
    }
}