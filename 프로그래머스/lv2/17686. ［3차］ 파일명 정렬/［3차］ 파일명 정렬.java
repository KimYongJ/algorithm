import java.util.Arrays;
public class Solution {

    public String[] solution(String[] files) {
        Arrays.sort(files,(A,B)->{
            A = A.toUpperCase();
            B = B.toUpperCase();
            String head1 = A.split("[0-9]")[0];
            String head2 = B.split("[0-9]")[0];
            int numb1 = Integer.parseInt(A.split("[^0-9]+")[1]);// 숫자를 제외한 모든 문자가 한번이상 반복됨을의미(+를붙였기 때문)
            int numb2 = Integer.parseInt(B.split("[^0-9]+")[1]);
            int result = head1.compareTo(head2);
            if(result==0) 
                result = numb1-numb2;
            return result;
        });
        return files;
    }
}