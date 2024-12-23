# [Gold IV] Barbells - 13751 

[문제 링크](https://www.acmicpc.net/problem/13751) 

### 성능 요약

메모리: 223776 KB, 시간: 672 ms

### 분류

백트래킹, 비트마스킹, 브루트포스 알고리즘

### 제출 일자

2024년 12월 23일 20:35:27

### 문제 설명

<p>Your local gym has b bars and p plates for barbells. In order to prepare a weight for lifting, you must choose a single bar, which has two sides. You then load each side with a (possibly empty) set of plates. For safety reasons, the plates on each side must balance; they must sum to the same weight. The combination of plates on either side might be different, but the total weight on either side must be the same. What weights are available for lifting?</p>

<p><img alt="" src="https://onlinejudgeimages.s3.amazonaws.com/problem/13751/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202016-11-19%20%EC%98%A4%EC%A0%84%202.13.57.png" style="height:141px; width:360px"></p>

### 입력 

 <p>Each input will consist of a single test case. Note that your program may be run multiple times on different inputs. The first line of input contains two integers, b and p (1 ≤ b,p ≤ 14), representing the number of bars and plates. Then, there are b lines each containing a single integer x (1 ≤ x ≤ 10<sup>8</sup> ) which are the weights of the bars. After that, there are p lines each containing a single integer y (1 ≤ y ≤ 10<sup>8</sup> ) which are the weights of the plates. </p>

### 출력 

 <p>Output a sorted list of all possible lifting weights, one per line. There must be no duplicates.</p>

