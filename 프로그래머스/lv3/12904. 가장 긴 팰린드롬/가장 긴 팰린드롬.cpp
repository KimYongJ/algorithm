#include <iostream>
#include <string>
using namespace std;
bool sliding(string& s, int len, int start){
    if(len ==1)
        return true;
    int end = start + len -1;
    int mid = start +len /2;
    while(start<=mid)
    {
        if(s[start] != s[end])
            return false;
        start ++;
        end--;
    }
    return true;
}
int solution(string s)
{
    int min = 1;
    int max = s.size();
    while(true){
        for (int len = max;len>=1;){
            for(size_t idx = 0;idx<s.length();idx++){
                if(idx+len > s.size())
                    break;
                if(sliding(s,len,idx)){
                    min = len;
                    return min;
                        
                }
            }
        len = len-1;
        }
        return min;
    }
    return min;
}