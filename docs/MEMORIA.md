# EL DESPERTAR DEL HÉROE · Aventura textual en Java

**Estudiante:** Sebastian Yanamango Valverde
**Curso:** 1.º DAM · 1.ª evaluación
**Módulos:** Programación, Entornos de desarrollo y Lenguaje de marcas
**Fecha:** noviembre de 2026

> Formato de entrega (PDF): Arial o Times New Roman 12, interlineado 1,5, portada,
> índice y páginas numeradas. Nombre del archivo:
> `DAM_DESPERTAR_HEROE_Yanamango_Valverde_Sebastian_Memoria.pdf`

## Índice

1. Portada e índice
2. Introducción y análisis
3. Diseño de la solución
4. Implementación
5. Pruebas
6. Desarrollo con Git
7. Uso de IA
8. Conclusiones y recursos

---

## 2. Introducción y análisis

### El encargo

Un estudio de videojuegos quiere un prototipo de "El despertar del héroe" antes de
seguir desarrollándolo. Es un juego por turnos en consola en el que el jugador crea
un héroe y tiene que superar tres combates seguidos contra enemigos cada vez más
fuertes. La aventura acaba al vencer al tercero, al quedarse sin vida o al abandonar.

### Reglas

- El héroe empieza con 100 de vida y 3 pociones que curan 25.
- El ataque del héroe hace entre 10 y 20 de daño.
- Los enemigos tienen 40, 60 y 80 de vida y hacen entre 5 y 15 de daño.
- La vida nunca baja de 0 ni sube de 100 al curarse.
- Cada enemigo derrotado da 100 puntos. Si se gana la aventura se suma la vida final.
- Toda partida terminada, también abandonada, se intenta meter en el ranking.
- El ranking tiene 10 puestos, ordenado de mayor a menor, y se guarda en XML.

### Alcance

Una aplicación de consola con métodos `static`, tipos básicos y arrays de tamaño
fijo. No se usan clases de dominio, herencia, `ArrayList` ni excepciones propias.
El ranking y el inventario se guardan en ficheros XML y el ranking además se puede
ver en una página HTML generada con XSLT (parte de Lenguaje de marcas).

### Ampliaciones propias

- **Armas:** al empezar se elige espada, hacha o daga. Todas hacen 10-20 como pide
  el enunciado; cambia el nombre del arma y del golpe (tajo, hachazo, estocada).
  Se puede cambiar de arma en combate sin gastar turno.
- **Ataques con nombre:** cada enemigo tiene tres ataques y usa uno al azar.
- **ranking.html:** página con la tabla del ranking generada con `ranking.xsl`.

### Necesidades del jugador

- Saber en todo momento su vida, sus pociones, su arma y la vida del enemigo.
- Que un error al escribir no cierre el juego ni le haga perder el turno.
- Poder consultar las reglas (Ayuda) y el ranking desde el menú.
