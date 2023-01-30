import java.util.*;

class Solution {
    public int[] solution(String[] list, String[] report, int k) {
        HashMap<String,Integer> reported = new HashMap<>(); // 신고당한 사람 : 횟수
        HashMap<String,Set<String>> reporter = new HashMap<>(); // 신고당한 사람 : 신고한사람list

        int[] result = new int[list.length];

        for(String item : report){
            String a = item.split(" ")[0];
            String b = item.split(" ")[1];
            
            Set<String> set = new HashSet<>();
            if(reporter.containsKey(b)){
                set = reporter.get(b);
                if(set.contains(a)) continue;
            }
            set.add(a);
            reporter.put(b,set);
            
            reported.put(b,reported.getOrDefault(b,0)+1);
            
        }
        for(String item : reporter.keySet())
            if(reported.get(item)>=k)
                for(String name : reporter.get(item))
                    for(int i=0; i<list.length; i++)
                        if(name.equals(list[i])) result[i]++;
        
        return result;
    }

}