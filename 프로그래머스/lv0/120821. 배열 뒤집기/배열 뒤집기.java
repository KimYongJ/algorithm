class Solution {
    public int[] solution(int[] num_list) {
        int j = num_list.length;
        int[] result= new int[j--];
        for(int i=0;i<num_list.length;i++)
            result[j--]=num_list[i];

        return result;
    }
}