// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
class Solution {
    
    ArrayList<Double> list = new ArrayList<>(); // 우박수열의 사다리꼴 넓이를 담을 list
    ArrayList<Double> result = new ArrayList<>();// 결과를 반환할 리스트
    
    public ArrayList<Double> solution(int k, int[][] ranges) {
        k = ubak(k); // 몇번 우박수열을 해야하는지 구해 온다.
        for(int[] range : ranges){
            int a = range[0]; // 우박수열의 값을 구할 시작 범위
            int b = k+range[1];// 우박수열의 값을 구할 끝 범위
            if(a==0 && range[1]==0){// [0,0]인 경우 가장 마지막 값을 가져온다.
                result.add(list.get(k));
            }else if(a>b){ // 시작점이 끝점보다 커서 유효하지 않은 구간일 경우
                result.add(-1.0);
            }else if(a<=b){ // 유효한 구간일 경우 햐당 구간의 값을 구해준다. DB로 값을 list에 담았기 때문에 아래와 같은 코드가 가능하다.
                double n = list.get(b) - list.get(a);
                result.add(n);
            }
        }
        
        return result;
    }
    public int ubak(int k){
        int before = 0; // 이전 k의 값(사다리꼴 넓이의 왼쪽변 구할 때 사용)
        int idx = 1;    // 리스트의 인덱스이며, 1부터 시작토록한다.
        list.add(0.0);  // 리스트의 초기값을 넣어준다.
        while(k!=1){    // k가 1이 될 때까지 무한 반복
            before = k; // 이전 값을 담을 변수에 현재 k를 셋팅한다.
            k = k%2==0 ? k/2 : k*3+1;// k를 변환한다.(콜라츠 추측)
            double num = (double)(before+k)/2; // 사다리 꼴 넓이
            list.add( num + list.get(idx++ -1) ); // 사다리 꼴 넓이를 구할 때 dp형식으로 구한다.
        }
        return idx-1;
    }
}