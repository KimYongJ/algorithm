def solution(files):
    answer = []
    temp = []
    for idx, file in enumerate(files):
        head, number = -1, -1
        for i in range(len(file)):
            if head == -1 and "0"<= file[i] <= "9":
                head = i
            elif head != -1 and number == -1 and ("0" > file[i] or "9" < file[i]):
                number = i
                
        if number == -1:
            number = len(file)

        temp.append([idx, file[:head].upper(), int(file[head:number])])
    temp.sort(key = lambda x : (x[1], x[2], x[0]))
    for t in temp:
        answer.append(files[t[0]])
                
    return answer