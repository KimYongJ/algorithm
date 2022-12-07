class Solution {
    public String solution(int age) {
        StringBuilder sb = new StringBuilder();
        while(age>0){
            sb.append((char)(age%10+97)+"");
            age/=10;
        }
        return sb.reverse().toString();
    }
}