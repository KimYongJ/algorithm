import java.util.HashMap;
class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> hm = new HashMap<>();
        int changeCnt = 0, len = record.length;
        for(int i=0; i<len; i++){
            String[] data = record[i].split(" ");
            if("Leave".equals(data[0])) continue;
            if("Change".equals(data[0])) changeCnt++;
            hm.put(data[1],data[2]);
        }
        String[] result = new String[record.length-changeCnt];
        int idx = 0;
        for(int i=0; i<record.length; i++){
            String[] data = record[i].split(" ");
            if("Change".equals(data[0])) continue;
            result[idx++] = makeString(hm.get(data[1]),data[0]);
        }
        return result;
    }
    public String makeString(String name,String param){
        StringBuilder sb = new StringBuilder();
        String str = "Enter".equals(param) ? "님이 들어왔습니다." : "님이 나갔습니다.";
        return sb.append(name).append(str).toString();
    }
}