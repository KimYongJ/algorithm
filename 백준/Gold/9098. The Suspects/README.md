# [Gold IV] The Suspects - 9098 

[문제 링크](https://www.acmicpc.net/problem/9098) 

### 성능 요약

메모리: 18816 KB, 시간: 200 ms

### 분류

자료 구조, 분리 집합

### 제출 일자

2025년 7월 18일 21:41:17

### 문제 설명

<p>Severe acute respiratory syndrome (SARS), an atypical pneumonia of unknown aetiology, was recognized as a global threat in mid-March 2003. To minimize transmission to others, the best strategy is to separate the suspects from others.</p>

<p>In the Not-Spreading-Your-Sickness University (NSYSU), there are many student groups. Students in the same group intercommunicate with each other frequently, and a student may join several groups. To prevent the possible transmissions of SARS, the NSYSU collects the member lists of all student groups, and makes the following rule in their standard operation procedure (SOP).</p>

<p>Once a member in a group is a suspect, all members in the group are suspects.</p>

<p>However, they find that it is not easy to identify all the suspects when a student is recognized as a suspect. Your job is to write a program which finds all the suspects.</p>

### 입력 

 <p>The input file contains several cases. Each test case begins with two integers n and m in a line, where n is the number of students, and m is the number of groups. You may assume that 0 < n ≤ 30000 and 0 ≤ m ≤ 500. Every student is numbered by a unique integer between 0 and n−1, and initially student 0 is recognized as a suspect in all the cases. This line is followed by m member lists of the groups, one line per group. Each line begins with an integer k by itself representing the number of members in the group. Following the number of members, there are k integers representing the students in this group. All the integers in a line are separated by at least one space.</p>

<p>A case with n = 0 and m = 0 indicates the end of the input, and need not be processed.</p>

### 출력 

 <p>For each case, output the number of suspects in one line.</p>

