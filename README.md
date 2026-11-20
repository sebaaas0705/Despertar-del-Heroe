# El despertar del héroe

Aventura textual por turnos en consola (Java). Proyecto de la 1.ª evaluación de
1.º DAM: Programación, Entornos de desarrollo y Lenguaje de marcas.

El héroe tiene que vencer a tres enemigos seguidos (Goblin, Orco y Dragón). En
cada turno puede atacar, beber una poción, mirar el inventario, cambiar de arma o
abandonar. Al terminar, la puntuación se guarda en un ranking de 10 puestos que
se conserva en `ranking.xml` y se puede ver como página web en `ranking.html`.

## Requisitos

- JDK 17 o superior (probado con Java 27).
- No necesita librerías externas: el XML se maneja con `javax.xml`, que viene en el JDK.

Para comprobar la versión: `java -version` y `javac -version`.

## Compilar y ejecutar

Desde la carpeta raíz del proyecto (donde está `ranking.xsl`):

```
javac -d bin src/*.java
java -cp bin DespertardelHeroe
```

En Windows (CMD o PowerShell) es igual pero con `src\*.java`.

Hay que ejecutarlo desde la raíz porque el programa busca `ranking.xsl` y crea
`ranking.xml`, `inventario.xml` y `ranking.html` en la carpeta actual.
Para probar el caso de ranking vacío basta con borrar `ranking.xml`.

## Pruebas

```
pruebas\ejecutar_pruebas.bat
```

Los casos de prueba con su resultado están en [PRUEBAS.md](PRUEBAS.md).

## Estructura

```
src/
  DespertardelHeroe.java   Clase principal: main y menu (do-while + switch)
  Constantes.java          Todos los valores fijos del juego
  Menu.java                Menu, ayuda y lectura validada de opciones
  Heroe.java               Nombre, arma, estado inicial e inventario del heroe
  Partida.java             Una partida: tres combates y puntuacion
  Combate.java             Combate por turnos (while) y numeros aleatorios
  Enemigo.java             Ataque del enemigo
  Pocima.java              Usar y consultar pociones
  Ranking.java             Ranking con dos arrays paralelos ordenados
  PersistenciaXml.java     Guardar/cargar XML y generar el HTML
ranking.xsl                Plantilla XSLT que pasa el ranking a HTML
pruebas/                   Entradas de prueba, ranking de ejemplo y script
PRUEBAS.md                 Tabla de pruebas
docs/                      Memoria y guion de la presentacion
```
