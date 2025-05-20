# [Diamond IV] Array Study - 16264 

[문제 링크](https://www.acmicpc.net/problem/16264) 

### 성능 요약

메모리: 271548 KB, 시간: 2060 ms

### 분류

mo's, 오프라인 쿼리, 누적 합, 제곱근 분할법

### 제출 일자

2025년 5월 20일 23:42:17

### 문제 설명

<p>Vasya likes to study arrays. Recently his parents presented him with an array a that contains elements equal to 1 and  - 1. Vasya immediately started to study it.</p>

<p>Additionally Vasya likes zeroes. So he decided to consider various subarrays a[l<sub>i</sub>, ..., r<sub>i</sub>] of array a. For each subarray he tries to find the maximum length of its subarray with the sum equal to 0. If there is no such subarray, he considers the answer to be 0. Vasya has written down q subarray requests [l<sub>i</sub>, r<sub>i</sub>], and now he wants to find the sum of answers to them.</p>

<p>For example, let us consider sample test.</p>

<ul>
	<li>subarray [1, 5]: the maximal subarray with sum 0 — [2, 5];</li>
	<li>subarray [1, 3]: the maximal subarray with sum 0 — [2, 3];</li>
	<li>subarray [2, 4]: the maximal subarray with sum 0 — [2, 3];</li>
	<li>subarray [3, 4]: no subarray with sum 0;</li>
	<li>subarray [3, 5]: the maximal subarray with sum 0 — [4, 5].</li>
</ul>

<p>So the sum of answers for the sample test is 4 + 2 + 2 + 0 + 2 = 10.</p>

### 입력 

 <p>Input data contains several test cases. The first line contains the number of test cases t (1 ≤ t ≤ 1000).</p>

<p>Each of t test cases is described in the following way: the first line of the description contains n — the number of elements of the array (1 ≤ n ≤ 10<sup>5</sup>).</p>

<p>The following line contains n integers a<sub>i</sub> — elements of the array (a<sub>i</sub> =  - 1 or a<sub>i</sub> = 1).</p>

<p>The following line contains q — the number of subarrays that Vasya is interested in (1 ≤ q ≤ 10<sup>5</sup>).</p>

<p>Each of the following q lines contains two integers l<sub>i</sub>, r<sub>i</sub> — left and right border of the i-th subarray (1 ≤ l<sub>i</sub> ≤ r<sub>i</sub> ≤ n)</p>

<p>It is guaranteed that the sum of n in all test cases of one input data doesn't exceed 10<sup>5</sup>, the sum of q in all test cases of one input data doesn't exceed 10<sup>5</sup>.</p>

### 출력 

 <p>For each test output one integer — the sum of answers for the given q subarrays.</p>

