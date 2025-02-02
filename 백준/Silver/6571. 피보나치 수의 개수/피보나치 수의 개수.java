import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        BigInteger upperLimit = BigInteger.TEN.pow(100);
        
        // 문제 의도에 맞는 피보나치 수열: 1, 2, 3, 5, ...
        ArrayList<BigInteger> list = new ArrayList<>();
        BigInteger f1 = BigInteger.ONE;        // 첫 항: 1
        BigInteger f2 = BigInteger.valueOf(2);   // 두 번째 항: 2
        list.add(f1);
        list.add(f2);
        
        while (true) {
            BigInteger next = f1.add(f2);
            if (next.compareTo(upperLimit) > 0) break;
            list.add(next);
            f1 = f2;
            f2 = next;
        }
        
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            BigInteger lower = new BigInteger(st.nextToken());
            BigInteger upper = new BigInteger(st.nextToken());
            if (lower.add(upper).equals(BigInteger.ZERO))
                break;
            
            int idx1 = binarySearchLowerBound(list, lower);
            int idx2 = binarySearchUpperBound(list, upper);
            
            int count = (idx1 > idx2 ? 0 : idx2 - idx1 + 1);
            sb.append(count).append('\n');
        }
        System.out.print(sb);
    }
    
    // 리스트에서 target 이상의 첫 번째 원소의 인덱스를 찾음
    public static int binarySearchLowerBound(ArrayList<BigInteger> list, BigInteger target) {
        int s = 0, e = list.size() - 1, res = list.size();
        while (s <= e) {
            int mid = (s + e) >> 1;
            if (list.get(mid).compareTo(target) >= 0) {
                res = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return res;
    }
    
    // 리스트에서 target 이하의 마지막 원소의 인덱스를 찾음
    public static int binarySearchUpperBound(ArrayList<BigInteger> list, BigInteger target) {
        int s = 0, e = list.size() - 1, res = -1;
        while (s <= e) {
            int mid = (s + e) >> 1;
            if (list.get(mid).compareTo(target) <= 0) {
                res = mid;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return res;
    }
}
