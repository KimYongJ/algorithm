# [Diamond IV] Bliski Brojevi - 18277 

[문제 링크](https://www.acmicpc.net/problem/18277) 

### 성능 요약

메모리: 29420 KB, 시간: 1244 ms

### 분류

자료 구조, 세그먼트 트리, 오프라인 쿼리, mo's

### 제출 일자

2025년 5월 22일 00:57:35

### 문제 설명

<p>Opće je poznato da je gospodin Malnar član Mense – međunarodne organizacije u koju se moguće učlaniti isključivo rezultatom na standardiziranom IQ testu koji je bolji od 98% ukupne populacije. Možda je manje poznato da je gospodin Malnar također član Menze – međunarodne organizacije u koju se moguće učlaniti isključivo ako je vaš godišnji unos standardnih porcija u restoranima viši od 98% populacije. Jednom se prilikom i sam gospodin Malnar zabunio, pa je pri ulasku u Menzu predočio člansku iskaznicu Mense. Vijest se brzo širila hodnicima Menze pa je dio znatiželjnih članova odlučio testirati kognitivne sposobnosti gospodina Malnara nakon idućeg grupnog objeda.</p>

<p>Znatiželjnici su skupili prazne tanjure i od njih napravili n hrpa tako da se prva hrpa sastojala od jednog tanjura, druga od dva, i tako sve do n-te hrpe koja se sastojala od n tanjura. Potom su te hrpe ispromiješali, a gospodin Malnar je trebao odgovarati na q brzopoteznih pitanja. Svako pitanje je bilo istog oblika, a glasilo je: „Kolika je najmanja razlika u broju tanjura nekih dvaju hrpa koje se nalaze između l-te i r-te hrpe?”. Formalno, neka je broj tanjura i-te hrpe označen s p<sub>i</sub>, tada gospodin Malnar treba odrediti:</p>

<p style="text-align: center;">min<sub>l≤i<j≤r</sub>|p<sub>i</sub> - p<sub>j</sub>|</p>

<p>Zanimljivo je da je gospodin Malnar na sva pitanja odgovorio unutar dvije sekunde te da pritom nije potrošio više od 512 MiB memorije. Vi to zasigurno ne možete, ali možda biste mogli napisati takav program.</p>

### 입력 

 <p>U prvom su retku prirodni brojevi n i q (1 ≤ n, q ≤ 3 · 10<sup>4</sup>) iz teksta zadatka.</p>

<p>U drugom se retku nalazi permutacija p duljine n iz teksta zadatka.</p>

<p>U sljedećih se q redaka nalaze po dva prirodna broja l i r (1 ≤ l < r ≤ n) iz teksta zadatka.</p>

### 출력 

 <p>U i-tom retku ispišite odgovor na i-ti upit iz ulaza.</p>

