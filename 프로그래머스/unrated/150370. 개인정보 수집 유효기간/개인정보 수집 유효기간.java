import java.util.*;
class Solution {
    static HashMap<String,Integer> map = new HashMap<>();
    public int[] solution(String t, String[] terms, String[] privacies) {
        List<Integer> list =  new ArrayList<>();
        for(String s : terms){
            String[] x = s.split(" ");
            map.put(x[0],Integer.parseInt(x[1]));
        }
        for(int i=0; i<privacies.length; i++){
            String[] pr = privacies[i].split(" ");
            int a = getDay(t);
            int b = getDay(pr[0]) + map.get(pr[1])*28;
            if(a>=b)
                list.add(i+1);
        }
        
        int[] result = new int[list.size()];
        Iterator itor = list.iterator();
        int idx = 0;
        while(itor.hasNext()){
            result[idx++] = (int)itor.next();
        }
        
        return result;
    }
    public int getDay(String s){
        StringTokenizer st = new StringTokenizer(s,"\\.");
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        return year*12*28+month*28+day;
    }
}