# [Platinum III] Slot Machines - 14959 

[문제 링크](https://www.acmicpc.net/problem/14959) 

### 성능 요약

메모리: 22932 KB, 시간: 280 ms

### 분류

KMP, 문자열

### 제출 일자

2024년 12월 12일 22:01:28

### 문제 설명

<p>Slot machines are popular game machines in casinos. The slot machine we are considering has six places where a figure appears. By combination of figures, one may earn or lose money. There are ten kinds of figures, so we will represent a figure with a number between 0 and 9. Then we can use a six-digit number <em>w</em> = <em>w</em><sub>1</sub><em>w</em><sub>2</sub><em>w</em><sub>3</sub><em>w</em><sub>4</sub><em>w</em><sub>5</sub><em>w</em><sub>6</sub> where 0 ≤ <em>w</em><sub>1</sub>, <em>w</em><sub>2</sub>, <em>w</em><sub>3</sub>, <em>w</em><sub>4</sub>, <em>w</em><sub>5</sub>, <em>w</em><sub>6</sub> ≤ 9 to represent one possible outcome of the slot machine.</p>

<p style="text-align:center"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/14959/1.png" style="height:140px; width:175px"></p>

<p style="text-align:center">Figure I.1. The layout of a slot machine.</p>

<p>Old slot machines were made up with mechanical components, but nowadays they were replaced by PC-based systems. This change made one critical flaw: they are based on pseudo-random number generators and the outcome sequences of a slot machine are periodic. Let <em>T</em>[<em>i</em>] be the <em>i</em>-th outcome of a slot machine. At first, there is a truly random sequence of length <em>k</em>,<em>T</em>[<em>1</em>],<em>T</em>[<em>2</em>],…,<em>T</em>[<em>k</em>]. Then there exists one positive number such that <em>T</em>[<em>i</em>+<em>p</em>] = <em>T</em>[<em>i</em>]for all possible values of <em>i</em>(><em>k</em>). Once an attacker can find out the exact values of <em>k</em> and <em>p</em>, he or she can exploit this fact to beat the casino by betting a lot of money when he or she knows the outcome with a good combination in advance.</p>

<p>For example, you have first six numbers of outcome sequences: 612534, 3157, 423, 3157, 423, and 3157. Note that we can remove first 0’s. Therefore, 3157 represents 003157 and 423 represents 000423. You want to know its tenth number. If you know the exact values of <em>k</em> and <em>p</em>, then you can predict the tenth number. However, there are many candidates for <em>k</em> and <em>p</em>: one extreme case is <em>k</em>=5 and <em>p</em>=1, and another is <em>k</em>=0 and <em>p</em>=6. The most probable candidate is the one where both <em>k</em> and <em>p</em> are small. So, our choice is the one with the smallest <em>k</em>+<em>p</em>. If there are two or more such pairs, we pick the one where <em>p</em> is the smallest. With our example, after some tedious computation, we get <em>k</em>=1 and <em>p</em>=2.</p>

<p>Assume that you have <em>n</em> consecutive outcomes of a slot machine, <em>T</em>[1], <em>T</em>[2], …, <em>T</em>[<em>n</em>]. Write a program to compute the values of <em>k</em> and <em>p</em> satisfying the above-mentioned condition.</p>

### 입력 

 <p>Your program is to read from standard input. The first line contains a positive integer <em>n</em> (1 ≤ <em>n</em> ≤ 1,000,000), representing the length of numbers we have observed up to now in the outcome sequence. The following line contains <em>n</em> numbers. Each of these numbers is between zero and 999,999.</p>

### 출력 

 <p>Your program is to write to standard output. Print two integers <em>k</em> and <em>p</em> in one line.</p>

