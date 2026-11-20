# El despertar del héroe

Aventura textual por turnos en consola (Java). Proyecto de la 1.ª evaluación de
1.º DAM: Programación, Entornos de desarrollo y Lenguaje de marcas.

## Requisitos

- Java 17 o superior (probado con Java 27).
- No necesita librerías externas: el XML se maneja con `javax.xml`, incluido en el JDK.

## Compilar y ejecutar

Desde la carpeta raíz del proyecto (donde está `ranking.xsl`):

```
javac -d bin src/*.java
java -cp bin DespertardelHeroe
```

En Windows con PowerShell o CMD funciona igual (`src\*.java`).

Es importante ejecutar desde la raíz: el programa busca `ranking.xsl` y crea
`ranking.xml`, `inventario.xml` y `ranking.html` en la carpeta actual.

## Estructura

```
src/                     Código fuente (clase principal: DespertardelHeroe)
ranking.xsl              Plantilla XSLT que convierte el ranking en HTML
```
