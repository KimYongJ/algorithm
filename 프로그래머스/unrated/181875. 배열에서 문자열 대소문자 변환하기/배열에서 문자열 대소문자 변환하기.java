class Solution {
    public String[] solution(String[] arr) {
        for(int i=0; i<arr.length; i++)
            arr[i] = i%2==1 ? arr[i].toUpperCase() : arr[i].toLowerCase();
        return arr;
    }
}