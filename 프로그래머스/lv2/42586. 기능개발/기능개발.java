import java.util.ArrayList;
import java.util.List;
class Solution {
    public List<Integer> solution(int[] p, int[] s) {
        // 몇일남았는지 프로그래스별로 일자를 구해 리스트에 담는다.
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<p.length; i++){
            double num1 = 100-p[i];
            int num2 = (int)Math.ceil(num1/s[i]);
            list.add(num2);
        }
        // 리스트에서 자기보다 뒤에있는데 숫자가 작은것들은 +1해준다.
        List<Integer> result = new ArrayList<>();
        int base = list.get(0);
        int num = 1;
        for(int i=1; i<list.size(); i++){
            if(base>=list.get(i)){
                num++;
                if(list.size()==i+1){
                    result.add(num);
                    break;
                }
            }else{
                result.add(num);
                if(list.size()==i+1) {
                    result.add(1);
                    break;
                }
                num = 1;
                base = list.get(i);
            }
        }
        return result;
    }
}