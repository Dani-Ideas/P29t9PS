instalation directory    /home/robute/altera/13.1


1 estabelcer la gramatica por el mike
Actualizacion: se creo el objeto "GramaticaParser", es capas de danalizar un texto de entrada que ntega la gramatica libre de contexto y trasformarla en un map que contega las regals de produccion, estas son una de las ventajas a comparacion de la version anterior:
Flujo de procesamiento:

Estructura de datos: Se usa un HashMap donde:

Key: String con el no terminal (ej: "S")

Value: Lista de producciones, donde cada producción es un array de Strings

Lectura del archivo: Usa BufferedReader para leer eficientemente línea por línea

División inicial: Separa la cabeza de la regla (no terminal) del cuerpo usando ->

Ej: Para "S -> AB | a":

partes[0] = "S"

partes[1] = "AB | a"

Alternativas: Divide el cuerpo en diferentes producciones usando |

Ej: ["AB", "a"]

Procesamiento de cada alternativa: Para cada posible producción:

Elimina espacios alrededor con trim()

Divide por espacios usando split("\\s+") (expresión regular para 1+ espacios)

Ej: "A B" se convierte en ["A", "B"]

Almacenamiento final: Cada producción se guarda como array de símbolos

Fundamentación de las afirmaciones:

Construir árbol sintáctico:

Verdadero porque:

Cada producción está almacenada como secuencia de símbolos (terminales y no terminales)

La estructura jerárquica (Map con listas de producciones) refleja la relación padre-hijos

Ej: Para la regla S → AB, el árbol tendría:

S
├─ A
└─ B
Realizar derivaciones:

Verdadero porque:

El mapa permite acceder instantáneamente a todas las producciones de un no terminal

Cada producción está lista para ser usada en expansiones sucesivas

Ej: Para derivar S:

java
List<String[]> produccionesS = gramatica.get("S");
// Seleccionar una producción (ej: ["A","B"]) y derivar cada símbolo
Generar tabla de parsing LR:

Verdadero porque:

La estructura contiene todas las reglas necesarias para construir los items LR(0)

Cada producción está numerable (índice en la lista) para identificadores únicos

Ej: Para el item LR S → •AB:

Cabeza: S

Producción: ["A","B"]

Posición del punto: 0

Manejar producciones epsilon (ε):

Verdadero porque:

Una producción vacía se representaría como un array vacío []

El split por espacios manejaría ε como símbolo explícito

Ej: Para A → ε:

txt
A -> ε
Se convertiría en:

java
producciones.add(new String[]{"ε"}); // o [] dependiendo de la convención
Ventajas clave de la estructura:

Acceso rápido: O(1) para obtener producciones de cualquier no terminal

Jerarquía clara: Relación directa no-terminal → producciones

Flexibilidad: Cada producción es una secuencia ordenada de símbolos

Extensible: Fácil de añadir metadatos (ej: número de regla)

Ejemplo de transformación completa:

Archivo de entrada (gramatica.txt):

S -> A B | a
A -> a | ε
B -> b C
C -> c
Estructura resultante:

java
{
  "S": [ ["A","B"], ["a"] ],
  "A": [ ["a"], ["ε"] ],
  "B": [ ["b","C"] ],
  "C": [ ["c"] ]
}
Esta estructura es ideal para algoritmos de análisis sintáctico porque:

Mantiene la relación original de las reglas

Preserva el orden de los símbolos en cada producción

Permite fácil iteración y manipulación

Separa claramente terminales y no terminales

La elección de String[] para las producciones (en lugar de List<String>) es eficiente para:

Acceso rápido por índice

Bajo overhead de memoria

Fácil conversión a otras estructuras si fuera necesario

2 creacion del arbol sintactico:


3 Selecionar el tipo de analizador: Universal, decendenta, acendernte?(pag.192)

dado que el programa se llama LRS lo más sensato es usar acendernte o LR

4 inclurir metodo de panico?? si o no? por ahora no
