### Sobre o Projeto
Este projeto é um simulador que compara o desempenho de diferentes algoritmos de substituição de páginas em sistemas de memória virtual. Com ele, é possível ver quantas "faltas de página" cada algoritmo gera ao longo de uma sequência de páginas.

### Como Funciona
Interface Gráfica: A interface, feita com Java Swing, permite ao usuário inserir uma sequência de páginas (ex.: 7,0,1,2,0,3,0,4,2,3) e o número de frames (espaços de memória disponíveis).

### Algoritmos:
FIFO (First In, First Out): Substitui sempre a página mais antiga em memória.
LRU (Least Recently Used): Remove a página que foi menos recentemente usada.
Clock (Relógio): Dá uma "segunda chance" para as páginas, usando um ponteiro circular para decidir qual página substituir.
Ótimo (Optimal): Remove a página que será usada mais tarde no futuro (serve apenas para comparação, já que exige conhecimento perfeito do futuro).
Resultados: Após inserir a sequência e iniciar a simulação, o simulador exibe o número de faltas de página para cada algoritmo.

### Como Usar
Insira a sequência de páginas no campo de texto e o número de frames (ex.: 3).
Clique em "Simular" e veja os resultados para cada algoritmo.
