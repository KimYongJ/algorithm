import java.io.*;
class Solution {
    public String solution(String my_string) {
        StringBuffer sb=new StringBuffer(my_string);
        return sb.reverse().toString();
    }
}