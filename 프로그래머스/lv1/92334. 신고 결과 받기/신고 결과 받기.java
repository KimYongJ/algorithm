import java.util.*;

class Solution {
    public int[] solution(String[] list, String[] report, int k) {
        HashMap<String,Integer> reported = new HashMap<>(); // 신고당한 사람 : 횟수
        HashMap<String,HashSet<String>> reporter = new HashMap<>(); // 신고당한 사람 : 신고한사람list
        int[] result = new int[list.length];

        for(String item : report){
            String[] tmp = item.split(" ");
            
            HashSet<String> set = new HashSet<>();
            if(reporter.containsKey(tmp[1])){
                set = reporter.get(tmp[1]);
                if(set.contains(tmp[0])) continue;
            }
            set.add(tmp[0]);
            reporter.put(tmp[1],set);
            
            reported.put(tmp[1],reported.getOrDefault(tmp[1],0)+1);
            
        }
        for(String item : reporter.keySet())
            if(reported.get(item)>=k)
                for(String name : reporter.get(item))
                    for(int i=0; i<list.length; i++)
                        if(name.equals(list[i])) result[i]++;
        
        return result;
    }

}