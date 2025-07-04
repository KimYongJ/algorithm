# [Gold IV] Networking - 3803 

[문제 링크](https://www.acmicpc.net/problem/3803) 

### 성능 요약

메모리: 22544 KB, 시간: 196 ms

### 분류

그래프 이론, 최소 스패닝 트리

### 제출 일자

2025년 7월 4일 21:40:57

### 문제 설명

<p>You are assigned to design network connections between certain points in a wide area. You are given a set of points in the area, and a set of possible routes for the cables that may connect pairs of points. For each possible route between two points, you are given the length of the cable that is needed to connect the points over that route. Note that there may exist many possible routes between two given points. It is assumed that the given possible routes connect (directly or indirectly) each two points in the area. Your task is to design the network for the area, so that there is a connection (direct or indirect) between every two points (i.e., all the points are interconnected, but not necessarily by a direct cable), and that the total length of the used cable is minimal.</p>

### 입력 

 <p>The input file consists of a number of data sets. Each data set defines one required network. The first line of the set contains two integers: the first defines the number P of the given points, and the second the number R of given routes between the points. The following R lines define the given routes between the points, each giving three integer numbers: the first two numbers identify the points, and the third gives the length of the route. The numbers are separated with white spaces. A data set giving only one number P=0 denotes the end of the input. The data sets are separated with an empty line.</p>

<p>The maximal number of points is 50. The maximal length of a given route is 100. The number of possible routes is unlimited. The nodes are identified with integers between 1 and P (inclusive). The routes between two points i and j may be given as i j or as j i.</p>

### 출력 

 <p>For each data set, print one number on a separate line that gives the total length of the cable used for the entire designed network.</p>

