import java.util.Arrays;
import java.util.Comparator;
public class Solution {
    public String[] solution(String[] files) {
        Arrays.sort(files,new Comparator<String>(){
            @Override
            public int compare(String A,String B){
                A = A.toUpperCase();
                B = B.toUpperCase();
                String head1 = A.split("[0-9]")[0];
                String head2 = B.split("[0-9]")[0];
                int numb1 = Integer.parseInt(A.replace(head1,"").split("[^0-9]")[0]);
                int numb2 = Integer.parseInt(B.replace(head2,"").split("[^0-9]")[0]);
                if(head1.compareTo(head2)==0){
                    return numb1-numb2;
                }else{
                    return head1.compareTo(head2);
                }
            }
        });
        return files;
    }
}