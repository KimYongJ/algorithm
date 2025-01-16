# [Silver IV] Pair Sum - 9728 

[문제 링크](https://www.acmicpc.net/problem/9728) 

### 성능 요약

메모리: 15216 KB, 시간: 148 ms

### 분류

자료 구조, 해시를 사용한 집합과 맵, 두 포인터

### 제출 일자

2025년 1월 16일 22:48:13

### 문제 설명

<p>You are given an integer array of size N and an integer M. This array has (N*(N-1))/2 different pairs. You need to calculate how many of those pairs have the sum equal to M. For example, if the array is {1,2,3,4} and M is 5, then there are exactly 2 pairs {1,4} and {2,3} whose sum is equal to M. </p>

### 입력 

 <p>The first line has a positive integer T, T <= 100,000, denoting the number of test cases. This is followed by each test case per line. </p>

<p>Each test case consists of two lines. The first line contains 2 integers N and M separated by a single space. N is between 2 and 20,000 inclusive. The second line consists of the N integers separated by a single space, denoting the values of the given array. All the numbers in the array are between 1 and 1,000,000,000 inclusive. They are distinct and sorted in increasing order.</p>

### 출력 

 <p>For each test case, the output contains a line in the format Case #x: R, where x is the case number (starting from 1) and R is the number of pairs whose sum is exactly the given M. </p>

