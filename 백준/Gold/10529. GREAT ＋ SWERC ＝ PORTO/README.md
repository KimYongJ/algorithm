# [Gold IV] GREAT + SWERC = PORTO - 10529 

[문제 링크](https://www.acmicpc.net/problem/10529) 

### 성능 요약

메모리: 304752 KB, 시간: 4752 ms

### 분류

백트래킹, 브루트포스 알고리즘, 수학, 문자열

### 제출 일자

2024년 12월 23일 22:06:44

### 문제 설명

<p>We want to have a great SWERC at Porto this year and we approached this challenge in several ways. We even framed it as a word addition problem, similar to the classic SEND+MORE=MONEY, where each letter stands for a single digit (0, 1, 2, ..., 8, 9) that makes the arithmetic operation correct. In word additions different letters cannot be assigned the same digit and the leftmost letter in a word cannot be zero (0). In particular, a single letter term cannot be zero.</p>

<p style="text-align: center;"><img alt="" src="https://www.acmicpc.net/upload/images2/greatswerc.png" style="height:187px; width:245px"></p>

<p>To solve this word addition problem we had to find positive digits for G, S and P, and digits for R, E, A, T, W, C, O, so that each letter has a different digit and the sum is correct. It turns out that, unlike the classical SEND+MORE=MONEY which has a single solution, GREAT+SWERC=PORTO has six solutions.</p>

<ul>
	<li>T=7, E=3, W=9, G=1, A=0, P=4, S=2, C=8, R=6, O=5</li>
	<li>T=7, E=3, W=9, G=2, A=0, P=4, S=1, C=8, R=6, O=5</li>
	<li>T=8, E=5, W=1, G=3, A=7, P=9, S=6, C=4, R=0, O=2</li>
	<li>T=8, E=5, W=1, G=6, A=7, P=9, S=3, C=4, R=0, O=2</li>
	<li>T=9, E=5, W=2, G=1, A=8, P=7, S=6, C=4, R=0, O=3</li>
	<li>T=9, E=5, W=2, G=6, A=8, P=7, S=1, C=4, R=0, O=3</li>
</ul>

<p>Having more than one solution does not make GREAT+SWERC=PORTO a good problem to solve by hand, but it is still a piece of cake for a programer. Moreover, it gives us another reason to organize SWERC again next year and, who knows, in years to come!</p>

<p>Given a word addition problem, compute the number of solutions (possibly zero).</p>

### 입력 

 <p>A line with an integer n, followed by n lines containing a word each with maximum length of 10 letters. The first n−1 words are the terms to be added and the last line is the result. Words contain only capital letters. If words have different lengths, they must be interpreted as aligning to the right. For instance, in the SEND+MORE=MONEY problem, the D of the first word and E of the second word align with the Y of the final word. You can also assume that the size of the last word is greater than or equal to the maximum size of the preceding words, and moreover, at most ten distinct letters are involved in a word problem.</p>

### 출력 

 <p>A single line with an integer: the number of solutions of the word addition problem given as input.</p>

