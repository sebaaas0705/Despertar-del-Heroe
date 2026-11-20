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

---

## 3. Diseño de la solución

### Pseudocódigo general

```
cargar ranking desde ranking.xml
HACER
    mostrar menu
    leer opcion valida entre 1 y 4
    SEGUN opcion
        1: iniciarPartida
        2: mostrarRanking
        3: mostrarAyuda
        4: despedirse
MIENTRAS opcion != 4

iniciarPartida:
    leer nombre (no vacio), elegir arma
    crear estado {100, 3, arma} e inventario con 3 pociones
    enemigo = 0, resultado = VICTORIA, puntos = 0
    MIENTRAS enemigo < 3 Y resultado == VICTORIA
        resultado = simularCombate(enemigo)
        SI resultado == VICTORIA: puntos += 100, enemigo++
    SI resultado == VICTORIA: puntos += vida
    registrar en ranking, guardar XML y generar HTML

simularCombate:
    MIENTRAS no haya resultado
        mostrar estado y acciones, leer accion
        1 atacar: daño aleatorio; SI el enemigo sigue vivo, ataca
        2 pocion: SI se ha podido beber, el enemigo ataca
        3 inventario / 5 cambiar arma: no gastan turno
        4 abandonar
        comprobar si el enemigo o el heroe han llegado a 0
```

### Constantes

Todas están en `Constantes.java` para no tener números sueltos por el código:
`VIDA_MAXIMA = 100`, `POCIONES_INICIALES = 3`, `CURACION_POCION = 25`,
`VIDA_ENEMIGOS = {40, 60, 80}`, `DANIO_ENEMIGO_MIN = 5`, `DANIO_ENEMIGO_MAX = 15`,
`PUNTOS_POR_ENEMIGO = 100`, `CAPACIDAD_INVENTARIO = 3`, `CAPACIDAD_RANKING = 10`,
`ATAQUE_MIN = 10`, `ATAQUE_MAX = 20`, los nombres de las armas, los nombres de los ficheros, las posiciones del array del
héroe (`VIDA`, `POCIONES`, `ARMA`) y los resultados de un combate
(`SIN_RESOLVER`, `DERROTA`, `VICTORIA`, `ABANDONO`).

### Variables y arrays principales

| Nombre | Tipo | Para qué sirve |
|--------|------|----------------|
| `estadoHeroe` | `int[3]` | Vida, pociones que quedan y arma elegida |
| `inventario` | `String[3]` | Las pociones; el contador es `estadoHeroe[POCIONES]` |
| `nombresRanking` | `String[10]` | Nombres del ranking |
| `puntosRanking` | `int[10]` | Puntos del ranking (misma posición = mismo jugador) |
| `numRegistros` | `int` | Cuántos puestos del ranking están ocupados |
| `vidaEnemigo` | `int` | Vida del enemigo durante su combate |
| `puntos` | `int` | Puntuación de la partida |
| `resultado` | `int` | Cómo ha acabado el combate |

### Métodos

| Clase | Método | Qué hace | Devuelve |
|-------|--------|----------|----------|
| DespertardelHeroe | `main` | Carga datos y lleva el menú | - |
| Menu | `mostrarMenu`, `mostrarAyuda` | Muestran texto | - |
| Menu | `leerOpcion(scanner, min, max)` | Lee un número válido | opción |
| Heroe | `leerNombre(scanner)` | Lee un nombre no vacío | nombre |
| Heroe | `elegirArma(scanner)` | Menú de armas | posición del arma |
| Heroe | `crearEstado(arma)`, `crearInventario()` | Datos iniciales | arrays nuevos |
| Partida | `iniciarPartida(...)` | Tres combates y puntuación | `numRegistros` |
| Combate | `simularCombate(...)` | Un combate por turnos | resultado |
| Combate | `numeroAleatorio(min, max)` | Número con `Math.random()` | número |
| Enemigo | `atacar(...)` | Quita vida al héroe | - |
| Pocima | `usarPocion(...)` | Bebe una poción si se puede | si gasta turno |
| Pocima | `mostrarInventario(...)` | Recorre las posiciones ocupadas | - |
| Ranking | `registrarEnRanking(...)` | Mete la partida y avisa | `numRegistros` |
| Ranking | `insertarOrdenado(...)` | Inserta desplazando | posición o -1 |
| Ranking | `mostrarRanking(...)` | Lista o mensaje de vacío | - |
| PersistenciaXml | `guardarRanking`, `cargarRanking`, `guardarInventario`, `cargarInventario`, `generarHtmlRanking`, `escribirXml` | Ficheros XML y HTML | según el método |

### Paso de parámetros: primitivos y arrays

Los `int` se pasan por copia: si `registrarEnRanking` hace `numRegistros++`, fuera
no cambia nada. Por eso devuelve el nuevo valor y en `main` se hace
`numRegistros = Partida.iniciarPartida(...)`.

Los arrays se pasan por referencia (se copia la dirección del array, no su
contenido). Por eso la vida y las pociones del héroe están en `int[] estadoHeroe`:
cuando `Enemigo.atacar` cambia `estadoHeroe[VIDA]`, el cambio se ve en el combate y
en la partida. Lo mismo pasa con los arrays del ranking y del inventario.
