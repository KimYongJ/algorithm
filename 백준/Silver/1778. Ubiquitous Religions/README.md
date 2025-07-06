# [Silver I] Ubiquitous Religions - 1778 

[문제 링크](https://www.acmicpc.net/problem/1778) 

### 성능 요약

메모리: 19320 KB, 시간: 184 ms

### 분류

자료 구조, 분리 집합, 그래프 이론, 그래프 탐색

### 제출 일자

2025년 7월 6일 13:50:50

### 문제 설명

<p>There are so many different religions in the world today that it is difficult to keep track of them all. You are interested in finding out how many different religions students in your university believe in.</p>

<p>You know that there are n students in your university (0 < <em>n</em> ≤ 50000). It is infeasible for you to ask every student their religious beliefs. Furthermore, many students are not comfortable expressing their beliefs. One way to avoid these problems is to ask <em>m</em> (0 ≤ <em>m</em> ≤  <em>n</em>(<em>n</em>-1)/2) pairs of students and ask them whether they believe in the same religion (e.g. they may know if they both attend the same church). From this data, you may not know what each person believes in, but you can get an idea of the upper bound of how many different religions can be possibly represented on campus. You may assume that each student subscribes to at most one religion.</p>

### 입력 

 <p>The input consists of a number of cases. Each case starts with a line specifying the integers <em>n</em> and <em>m</em>. The next <em>m</em> lines each consists of two integers i and j, specifying that students i and j believe in the same religion. The students are numbered 1 to <em>n</em>. The end of input is specified by a line in which <em>n</em> = <em>m</em> = 0.</p>

### 출력 

 <p>For each test case, print on a single line the case number (starting with 1) followed by the maximum number of different religions that the students in the university believe in.</p>

