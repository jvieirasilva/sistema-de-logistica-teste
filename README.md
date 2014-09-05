sistema-de-logistica-teste
=============================

Enunciado

Uma empresa brasileira está desenvolvendo um novo sistema de logística e a tarefa será desenvolver o novo sistema de entregas visando sempre o menor custo.

Para popular a base de dados o sistema precisa expor um Webservice que aceite o formato de malha logística (exemplo abaixo). 

Nesta mesma requisição o requisitante deverá informar um nome para este mapa. 

É importante que os mapas sejam persistidos para evitar que a cada novo deploy todas as informações desapareçam. 

O formato de malha logística é bastante simples, cada linha mostra uma rota: ponto de origem, ponto de destino e distância entre os pontos em quilômetros.

A B 10
B D 15
A C 20
C D 30
B E 50
D E 30

Com os mapas carregados o requisitante irá procurar o menor valor de entrega e seu caminho. 
Para isso ele passará o nome do ponto de origem, nome do ponto de destino, 
autonomia do caminhão (km/l) e o valor do litro do combustível. Este é o papel do Webservice.

Exemplo de entrada: origem A, destino D, autonomia 10, valor do litro 2,50; resposta é rota A B D com custo de 6,75.

Ao receber essas informações, constatei que a melhor estrutura de dados para representar essa malha logística é um grafo.

[imagem do grafo no arquivo "grafo da malha logistica do teste.jpg"]

Este é um grafo ordenado, ou dígrafo, porque seus pares estão ordenados alfabeticamente:

{ <A, B> , <B, D> , <A, C> , <C, D> , <B, E> , <D, E> }

Estas são as incidências que ocorrem no grafo acima:

Os nós A e B incidem no arco de 10km e este arco incide tanto em A quanto em B.
B e D incidem no arco de 15km e vice-versa.
A e C incidem em 20km e vice-versa.
C e D incidem em 30km e vice-versa.
B e E incidem no primeiro arco de 50km e vice-versa.
D e E incidem no segundo arco de 50km e vice-versa.

Este também é um grafo ponderado, ou rede, onde cada arco tem um número associado a ele.
