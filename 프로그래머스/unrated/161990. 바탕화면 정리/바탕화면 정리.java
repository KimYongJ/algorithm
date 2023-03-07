class Solution {
    public int[] solution(String[] a) {
        int start=-1, end=0, left=a[0].length(), right=0;
        for(int i=0; i<a.length; i++){
            String s = a[i];
            for(int j=0; j<s.length(); j++){
                char c = s.charAt(j);
                if(start==-1 && c=='#') start=i;
                if(c=='#'){
                    end = i;
                    if(left>j)
                        left = j;
                    if(right<j)
                        right = j;
                }
            }
        }
        return new int[]{start,left,end+1,right+1};
    }
}
// 처음시작되는 #을 찾고 
// 마지막배열의 #을 찾는다. 마지막은 +1해준다.
// 가장 왼쪽의 #을 찾는다.
// 가장 오른쪽의 #을 찾는다. 오른쪽은 +1을해준다.