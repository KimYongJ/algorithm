//https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
import java.util.Comparator;
class Solution {
    
    private ArrayList<int[]> list = new ArrayList<>(); // 결과들을 담을 리스트 선언
    
    public int[] solution(int[] sequence, int k) {
        int left = 0; // 왼쪽 포인터
        int right = 0;// 오른쪽 포인터
        int len = sequence.length;// 포인터의 최대 길이
        int sum = sequence[0]; // 각인자들의 합을 담을 변수
        
        while(left!=len && right!=len){// 종료조건으로 left, right 포인터가 끝까지 갔을 때 종료
            if(k==sum){// 더한 값과 k값이 같다면 결과 리스트에 추가해준다.
                list.add(new int[]{left,right,right-left});
            }
            if(right<len && sum<k){// 항상 오른쪽 포인터 부터 이동하며 조건으로 포인터의 끝이 아니면서 합(sum)이 k보다 작을 때 
                right++;// 오른쪽 포인터 이동
                if(right<len){//최대 포인터 이동 가능 길이보다 작을 때 sum에 해당 시퀀스 값을 더해준다.
                    sum += sequence[right];
                }
            }else{// 오른쪽 포인터의 이동이 최대 포인터 이동 가능 길이만큼 갔거나, sum이 k의 값을 넘었을 때 왼쪽 포인터 이동한다.
                sum-= sequence[left];// 기존 에 더했던 왼쪽포인터 값 삭제 
                left++;// 왼쪽 포인터 추가
            }
        }
        list.sort(new Comparator<int[]>(){//list에 값이 무작위로 들어가있기 때문에 문제의 조건에 맞게 소팅해줘야 한다.
            @Override
            public int compare(int[] a,int[] b){
                if(a[2]==b[2]){// 두 결과 값의 수열 길이가 같을 때 
                    return a[0]-b[0]; // left 결과 값이 작은 것이 앞쪽으로 간다.
                }
                return a[2]-b[2];// 두 결과 값의 수열 길이가 짧은것이 앞쪽으로 간다.
            }
        });
        int[] result = list.get(0);// 소팅결과로 가장 짧거나 왼쪽 인덱스가 가장 작은게 list의 0번째 인덱스에 있다.
        return new int[]{result[0],result[1]};
    }
}