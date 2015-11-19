# The Traveling Salesman Problem

Práctica del Tema 3 para la asignatura de Complejidad Computacional, Grado en Ingeniería Informática impartido por la Universidad de la Laguna.

El ** objetivo ** consiste en elaborar una implementación de un algoritmo de ramificación a acotación que resuelva de forma exacta el problema conocido como ["The Traveling Salesman Problem" (TSP)](https://en.wikipedia.org/wiki/Travelling_salesman_problem).

Con ese propósito se seguirán los siguientes pasos:

### Sesión 1 y 2. Lectura de ficheros en formato TSPLIB XML:

** Elaboración de dos clases: **

  1. La primera clase almacena la matriz de distancias del grado asociado a la entrada.

  2. La segunda clase que tiene como uno de sus atributos la primera clase, y que añada un campo para el nombre de la entrada en formato [TSPLIB](http://comopt.ifi.uni-heidelberg.de/software/TSPLIB95/). Esto será un procedimiento que permita la lectura de un fichero XML.

### Sesión 3 y 4. Tour por los nodos del grafo:

  Tercera clase que representa un árbol de ramificación del grafo.

  Esta clase tiene los siguientes atributos:

    1. La clase con los datos de entrada.
    2. La clase con el mejor tour obtenido hasta el momento.
    3. El mejor valor obtenido del tour almacenado.


    ** Funciones principales de la clase que nos calculará el tour: **

    [NN algorithm](https://en.wikipedia.org/wiki/Nearest_neighbour_algorithm)
    [2-OPT](https://en.wikipedia.org/wiki/2-opt)