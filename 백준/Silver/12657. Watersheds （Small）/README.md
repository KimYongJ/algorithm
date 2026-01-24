# [Silver II] Watersheds (Small) - 12657 

[문제 링크](https://www.acmicpc.net/problem/12657) 

### 성능 요약

메모리: 14404 KB, 시간: 104 ms

### 분류

자료 구조, 그래프 이론, 그래프 탐색, 너비 우선 탐색, 분리 집합

### 제출 일자

2026년 1월 24일 20:40:47

### 문제 설명

<p>Geologists sometimes divide an area of land into different regions based on where rainfall flows down to. These regions are called <em>drainage basins</em>.</p>

<p>Given an elevation map (a 2-dimensional array of altitudes), label the map such that locations in the same drainage basin have the same label, subject to the following rules.</p>

<ul>
	<li>From each cell, water flows down to at most one of its 4 neighboring cells.</li>
	<li>For each cell, if none of its 4 neighboring cells has a lower altitude than the current cell's, then the water does not flow, and the current cell is called a <em>sink</em>.</li>
	<li>Otherwise, water flows from the current cell to the neighbor with the lowest altitude.</li>
	<li>In case of a tie, water will choose the first direction with the lowest altitude from this list: North, West, East, South.</li>
</ul>

<p>Every cell that drains directly or indirectly to the same sink is part of the same drainage basin. Each basin is labeled by a unique lower-case letter, in such a way that, when the rows of the map are concatenated from top to bottom, the resulting string is lexicographically smallest. (In particular, the basin of the most North-Western cell is always labeled 'a'.)</p>

### 입력 

 <p>The first line of the input file will contain the number of maps, <strong>T</strong>. <strong>T</strong> maps will follow, each starting with two integers on a line -- <strong>H</strong> and <strong>W</strong> -- the height and width of the map, in cells. The next <strong>H</strong> lines will each contain a row of the map, from north to south, each containing <strong>W</strong> integers, from west to east, specifying the altitudes of the cells.</p>

<p>Limits</p>

<ul>
	<li><strong>T</strong> ≤ 100;</li>
	<li>1 ≤ <strong>H</strong>, <strong>W</strong> ≤ 10;</li>
	<li>0 ≤ altitudes < 10.</li>
	<li>There will be at most two basins.</li>
</ul>

### 출력 

 <p>For each test case, output 1+<strong>H</strong> lines. The first line must be of the form</p>

<pre>Case #<strong>X</strong>:</pre>

<p>where <strong>X</strong> is the test case number, starting from 1. The next <strong>H</strong> lines must list the basin labels for each of the cells, in the same order as they appear in the input.</p>

