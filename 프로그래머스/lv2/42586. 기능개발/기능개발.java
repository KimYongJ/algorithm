import java.util.ArrayList;
import java.util.List;
class Solution {
    static int len;
    public List<Integer> solution(int[] p, int[] s) {
        len = p.length;
        // 몇일남았는지 프로그래스별로 일자를 구해 리스트에 담는다.
        for(int i=0; i<len; i++){
            p[i] = (int)Math.ceil((100-p[i])/(double)s[i]);
        }
        // 리스트에서 자기보다 뒤에있는데 숫자가 작은것들은 +1해준다.
        List<Integer> result = new ArrayList<>();
        int base = p[0];
        int num = 1;
        for(int i=1; i<len; i++){
            if(base>=p[i]){
                num++;
            }else{
                result.add(num);
                num = 1;
                base = p[i];
            }
            if(len==i+1){
                result.add(num);
                break;
            }
        }
        return result;
    }
}