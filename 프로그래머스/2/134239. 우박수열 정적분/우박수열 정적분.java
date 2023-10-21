// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
class Solution {
    
    ArrayList<Double> list = new ArrayList<>(); // 우박수열의 사다리꼴 넓이를 담을 list
    ArrayList<Double> result = new ArrayList<>();//
    
    public ArrayList<Double> solution(int k, int[][] ranges) {
        k = ubak(k); 
        for(int[] range : ranges){
            int a = range[0];
            int b = k+range[1];
            if(a==0 && range[1]==0){
                result.add(list.get(k));
            }else if(a>b){
                result.add(-1.0);
            }else if(a<=b){
                double n = list.get(b) - list.get(a);
                result.add(n);
            }
        }
        
        return result;
    }
    public int ubak(int k){
        int cnt = 0;
        int before = 0;
        int idx = 1;
        list.add(0.0);
        while(k!=1){
            before = k;
            k = k%2==0 ? k/2 : k*3+1;
            double num = (double)(before+k)/2; // 사다리 꼴 넓이
            list.add( num + list.get(idx++ -1) );
            cnt++;
        }
        return cnt;
    }
}