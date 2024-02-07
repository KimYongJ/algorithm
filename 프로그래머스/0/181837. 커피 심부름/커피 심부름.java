class Solution {
    public int solution(String[] order) {
        int answer = 0;
        for(String str : order)
            answer += str.contains("cafelatte") ? 5000 : 4500;
        return answer;
    }
}