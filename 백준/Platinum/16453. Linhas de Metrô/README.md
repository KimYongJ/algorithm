# [Platinum III] Linhas de Metrô - 16453 

[문제 링크](https://www.acmicpc.net/problem/16453) 

### 성능 요약

메모리: 99816 KB, 시간: 1024 ms

### 분류

자료 구조, Heavy-light 분할, 느리게 갱신되는 세그먼트 트리, 최소 공통 조상, 세그먼트 트리, 트리

### 제출 일자

2025년 4월 28일 20:01:36

### 문제 설명

<p>O sistema de metrô de uma grande cidade é formado por um conjunto de estações e por túneis que ligam alguns pares de estações. O sistema foi desenhado de forma que existe exatamente uma sequência de túneis ligando qualquer par de estações. As estações nas quais apenas um túnel chega são chamadas de terminais. Há várias linhas de trens que fazem viagens de ida e volta entre duas estações terminais, transitando pelo caminho único entre elas. A população está reclamando das linhas atuais e, por isso, o prefeito ordenou uma reformulação total das linhas. Como o sistema possui muitas estações, nós precisamos ajudar os engenheiros que estão tentando decidir quais pares de terminais passarão a definir uma linha.</p>

<p>A figura ilustra um sistema onde as estações terminais são mostradas como círculos preenchidos e as não-terminais são mostradas como círculos vazios. Na parte esquerda, veja que se o par (A,B) definir uma linha e o par (C,D) definir outra, elas não terão qualquer estação em comum. Mas, na parte direita, podemos ver que se os pares (E,F) e (G,H) definirem duas linhas, elas terão duas estações em comum.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/71c8cc1f-916d-468d-baa7-215e8d014406/-/preview/" style="width: 524px; height: 197px;"></p>

<p>Dada a descrição do sistema de túneis e uma sequência de Q consultas constituídas de dois pares de terminais, seu programa deve computar, para cada consulta, quantas estações em comum as linhas definidas pelos dois pares teriam.</p>

### 입력 

 <p>A primeira linha da entrada contém dois inteiros N (5 ≤ N ≤ 10<sup>5</sup>) e Q (1 ≤ Q ≤ 20000), representando respectivamente o número de estações e o número de consultas. As estações são numeradas de 1 até N. Cada uma das N −1 linhas seguintes contém dois inteiros distintos U e V , 1 ≤ U, V ≤ N, indicando que existe um túnel entre as estações U e V . Cada uma das Q linhas seguintes contém quatro inteiros distintos A, B, C e D (1 ≤ A, B, C, D ≤ N), representando uma consulta: as duas linhas de trem são definidas pelos pares (A, B) e (C, D).</p>

### 출력 

 <p>Para cada consulta, seu programa deve imprimir uma linha contendo um inteiro representando quantas estações em comum teriam as duas linhas de trem definidas pela consulta.</p>

