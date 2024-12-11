# [Gold V] Secret - 13220 

[문제 링크](https://www.acmicpc.net/problem/13220) 

### 성능 요약

메모리: 36184 KB, 시간: 380 ms

### 분류

KMP, 문자열

### 제출 일자

2024년 12월 11일 22:36:30

### 문제 설명

<p>Alice needs to send a secret password to Bob. The password consists of N​space-separated integers. She decides to use a messenger, Eve, to send the password. To ensure that Eve does not steal the password, Alice uses a method of encoding she invented -- by writing it in a loop.</p>

<p>For example, if the password is “37 20 71 33 97”, Alice writes it down as “20 71 33 97 37”. She notifies Bob beforehand that the starting point for the message is the 5th integer, so he knows to decode the message starting from there. To make the password harder to guess, she may also use a different starting point. For example, Alice can also write the password as “71 33 97 37 20” where the starting point is the 4th integer.</p>

<p>Being an experienced hacker, Eve managed to figure out Alice’s encoding scheme (but not the starting points). Furthermore, Eve was Alice’s messenger twice, hence she has two of Alice’s encoded messages. Eve wishes to know whether it is possible that Alice has sent the same secret password twice.</p>

<p>Your task is work out whether it is possible two of those encoded messages are for the same secret password.</p>

### 입력 

 <p>Line 1: A single positive integer N​corresponding to the number of integers in the secret password. (N​ ≤ 100,000)</p>

<p>Line 2: N space-separated positive integers a<sub>i</sub> corresponding to the integers in the first encoded message. (a<sub>i</sub> ≤ 1,500,000,000)</p>

<p>Line 3: N space-separated positive integers b<sub>i</sub>​ corresponding to the integers in the second encoded message. (b<sub>i</sub> ≤ 1,500,000,000)</p>

### 출력 

 <p>YES or NO indicating whether it is possible that both loops are for the same password.</p>

