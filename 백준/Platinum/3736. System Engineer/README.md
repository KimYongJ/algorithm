# [Platinum III] System Engineer - 3736 

[문제 링크](https://www.acmicpc.net/problem/3736) 

### 성능 요약

메모리: 33280 KB, 시간: 1940 ms

### 분류

이분 매칭

### 제출 일자

2025년 6월 3일 17:34:05

### 문제 설명

<p>Bob is a skilled system engineer. He is always facing challenging problems, and now he must solve a new one. He has to handle a set of servers with differing capabilities available to process job requests from persistent sources - jobs that need to be processed over a long or indefinite period of time. A sequence of persistent job requests arrives revealing a subset of servers capable of servicing their request. A job is processed on a single server and a server processes only one job. Bob has to schedule the maximum number of jobs on the servers. For example, if there are 2 jobs j<sub>1</sub>, j<sub>2</sub> and 2 servers s<sub>1</sub>, s<sub>2</sub>, job j<sub>1</sub> requiring the server s<sub>1</sub>, and job j<sub>2</sub> requiring also the server s<sub>1</sub> In this case Bob can schedule only one job. Can you help him?</p>

<p>In the general case there are n jobs numbered from 0 to n-1, n servers numbered from n to 2 *n-1, and a sequence of job requests. The problem asks to find the maximum number of jobs that can be processed.</p>

### 입력 

 <p>The program input is from a text file (at most 1 MB). Each data set in the file stands for a particular set of jobs. A data set starts with the number n (n ≤ 10000) of jobs, followed by the list of required servers for each job, in the format: job<sub>number</sub>: (nr_servers) s1 … s<sub>nr_servers</sub> The program prints the maximum number of jobs that can be processed.</p>

<p>White spaces can occur freely in the input. The input data are correct and terminate with an end of file.</p>

### 출력 

 <p>For each set of data the program prints the result to the standard output from the beginning of a line. An input/output sample is in the table bellow.</p>

