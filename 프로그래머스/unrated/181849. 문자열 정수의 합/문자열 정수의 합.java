class Solution {
    public int solution(String n) {
        int answer = 0;
        for(char c : n.toCharArray())
            answer += c-'0';
        return answer;
    }
}