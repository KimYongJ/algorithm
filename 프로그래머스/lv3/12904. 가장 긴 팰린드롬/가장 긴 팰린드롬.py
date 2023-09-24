def solution(s):
    answer = 1
    length = len(s)
    for i in range(length):
        end = length-1
        while True:
            if end >= length:
                end -= 1
                break
            if s[i] != s[end]:
                end -= 1
            else:
                if check_pal(s[i:end+1]):
                    answer = max(answer, end+1-i)
                    break
                end -= 1
    return answer

def check_pal(sub_str):
    sub_length = len(sub_str)
    for i in range(sub_length//2):
        if sub_str[i] != sub_str[-(i+1)]:
            return False
    return True