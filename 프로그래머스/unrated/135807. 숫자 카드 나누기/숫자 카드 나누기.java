// https://github.com/KimYongJ/algorithm
import java.util.HashSet;
class Solution {
    
    private int result = 0;
    
    public int solution(int[] arrayA, int[] arrayB) {
        
        HashSet<Integer> aSet = getDivisor(arrayA);// 배열들의 공약수를 구한다.
        HashSet<Integer> bSet = getDivisor(arrayB);// 배열들의 공약수를 구한다.
        
        getResultSet(arrayA,bSet); // arrayA를 bSet의 인자들로 나눌 수 없는 것만 구한다.
        getResultSet(arrayB,aSet); // arrayB를 aSet의 인자들로 나눌 수 없는 것만 구한다.
        
        return result;
    }
    public void getResultSet(int[] arrayA, HashSet<Integer> set){
        Loop : for(int s : set){
            for(int a : arrayA){
                if(a%s==0)
                    continue Loop;
            }
            result = Math.max(s,result); // 가장 큰 값만 결과에 담는다.
        }
    }
    public HashSet<Integer> getDivisor(int[] array){ // 특정 배열에 대해 공약수를 Set에 담아 준다.
        HashSet<Integer> divisor = new HashSet<>();
        for(int i=1; i*i<=array[0]; i++){ // 첫번째 인수에 대해 약수를 divisor에 담는다.
            if(array[0]%i==0){
                divisor.add(i);
                divisor.add(array[0]/i);
            }
        }
        
        for(int a : array){ // 인자들을 돌면서 공약수가 아닌 것을 제거한다.
            HashSet<Integer> delete = new HashSet<>();
            
            for(int d : divisor)
                if(a%d!=0)
                    delete.add(d);
            
            for(int del : delete)
                divisor.remove(del);
        }
        return divisor;
    }
}