import java.io.*;
import java.util.*;

public class Main {
    private void solution(int n, int[][] arr) {
        StringBuilder answer = new StringBuilder();
        int[] nums = new int[500001];
        int startIndex = 0;
        int nextIndex = 0;
        double frontSum = 0;
        double backSum = 0;
        
        for (int i = 0; i < n; i++) {
            if (arr[i][0] == 1) {
                nums[nextIndex] = arr[i][1];
                backSum += arr[i][1];
                nextIndex += 1;

                int count = nextIndex - startIndex;
                if (count % 2 == 0) {
                    int midIndex = startIndex + count / 2 - 1;
                    frontSum += nums[midIndex];
                    backSum -= nums[midIndex];
                }
            } else {
                int count = nextIndex - startIndex;

                if (frontSum > backSum) {
                    answer.append((long)backSum).append('\n');
                    backSum = 0;
                    nextIndex = startIndex + count / 2;
                } else {
                    answer.append((long)frontSum).append('\n');
                    frontSum = 0;
                    startIndex += count / 2;
                }

                count = nextIndex - startIndex;
                if (count == 1) {
                    frontSum = 0;
                    backSum = nums[startIndex];
                } else if (backSum == 0) {
                    int last = count / 2 - 1;
                    for (int j = count - 1; j != last; j--) {
                        frontSum -= nums[startIndex + j];
                        backSum += nums[startIndex + j];
                    }
                } else {
                    int last = count / 2;
                    for (int j = 0; j != last; j++) {
                        backSum -= nums[startIndex + j];
                        frontSum += nums[startIndex + j];
                    }
                }
            }
        }
        
        for (int i = startIndex; i < nextIndex; i++) {
            answer.append(nums[i]).append(' ');
        }
        if (answer.length() > 0) {
            answer.setLength(answer.length() - 1);
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] temp = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(temp[0]);
            arr[i][1] = arr[i][0] == 2 ? 0 : Integer.parseInt(temp[1]);
        }

        new Main().solution(n, arr);
    }
}