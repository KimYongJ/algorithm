# [Platinum II] Frequent values - 6515 

[문제 링크](https://www.acmicpc.net/problem/6515) 

### 성능 요약

메모리: 131372 KB, 시간: 1828 ms

### 분류

자료 구조, mo's, 오프라인 쿼리, 세그먼트 트리

### 제출 일자

2025년 5월 6일 11:05:35

### 문제 설명

<p>You are given a sequence of n integers a<sub>1</sub> , a<sub>2</sub> , ... , a<sub>n</sub> in non-decreasing order. In addition to that, you are given several queries consisting of indices i and j (<em>1 ≤ i ≤ j ≤ n</em>). For each query, determine the most frequent value among the integers a<sub>i</sub> , ... , a<sub>j</sub>.</p>

### 입력 

 <p>The input consists of several test cases. Each test case starts with a line containing two integers n and q (<em>1 ≤ n, q ≤ 100000</em>). The next line contains n integers a<sub>1</sub> , ... , a<sub>n</sub> (<em>-100000 ≤ a<sub>i</sub> ≤ 100000</em>, for each <em>i ∈ {1, ..., n}</em>) separated by spaces. You can assume that for each <em>i ∈ {1, ..., n-1}: a<sub>i</sub> ≤ a<sub>i+1</sub></em>. The following q lines contain one query each, consisting of two integers i and j (<em>1 ≤ i ≤ j ≤ n</em>), which indicate the boundary indices for the query.</p>

<p>The last test case is followed by a line containing a single <em>0</em>.</p>

### 출력 

 <p>For each query, print one line with one integer: The number of occurrences of the most frequent value within the given range.</p>

