# Pruebas - El despertar del héroe

Las entradas están en `pruebas/entradas` y se pueden lanzar todas con
`pruebas\ejecutar_pruebas.bat` desde la raíz del proyecto. Las salidas quedan en
`pruebas/salidas` (no se suben a Git).

Como el combate usa `Math.random()`, en las pruebas de combate no se comprueba un
daño exacto sino que esté dentro del rango y que se cumplan las reglas.

## Casos obligatorios (validación de entradas)

| # | Caso | Entrada | Resultado esperado | Resultado obtenido | ¿OK? |
|---|------|---------|--------------------|--------------------|------|
| 1 | Nombre vacío | Intro sin escribir nada y luego `   ` (solo espacios) | Avisa y vuelve a pedir el nombre | "El nombre no puede quedar vacio." dos veces y pide otra vez | Sí |
| 2 | Entrada no numérica | `hola` en el menú, `abc` al elegir arma | Avisa sin cerrar el programa | "Debes escribir un numero." y vuelve a preguntar | Sí |
| 3 | Opción fuera de rango | `9` y `0` en el menú, `7` al elegir arma | Avisa y vuelve a preguntar | "Opcion fuera de rango." y vuelve a preguntar | Sí |

Archivo de entrada: `01_validaciones.txt`.

## Casos obligatorios (partida)

| # | Caso | Entrada | Resultado esperado | Resultado obtenido | ¿OK? |
|---|------|---------|--------------------|--------------------|------|
| 4 | Victoria | Espada y atacar siempre (`05_partida_atacando.txt`) | Vence a los 3 enemigos, puntos = 300 + vida final | "Has completado la aventura, Victor!", 3 de 3, 305 puntos (le quedaban 5 de vida) | Sí |
| 5 | Derrota | Mismo archivo; las líneas sobrantes empiezan otra partida que acaba en derrota | La vida se queda en 0 (nunca negativa), puntos = 100 x enemigos | "Te quedan 0." y "Has caido en combate", 2 de 3, 200 puntos sin bonus | Sí |
| 6 | Uso de pociones | `02_inventario_y_abandono.txt` | Con vida llena no gasta turno ni ataca el enemigo; con vida baja cura y el enemigo responde; se quita del inventario | "Tu vida ya esta al maximo." sin ataque enemigo. Con 89 de vida: "Te curas 11. Vida: 100." y el Goblin ataca. El inventario pasa de 3/3 a 2/3 | Sí |
| 7 | Consultar inventario | Opción 3 en combate | Lista solo las pociones que hay, no gasta turno | Lista 3 pociones y luego 2; el enemigo no ataca | Sí |
| 8 | Abandono | Opción 4 en combate | Termina la partida y se guarda en el ranking con los puntos que lleve | "Abandonas la aventura, Ana.", 0 puntos, registrada en el ranking | Sí |

Comprobación de rangos en la partida del caso 4: los golpes del héroe dieron
entre 10 y 20 y los del enemigo entre 5 y 15. Un enemigo derrotado no llega a
atacar (después de "Goblin derrotado!" no sale ningún ataque del Goblin).

### Error encontrado y corregido

Al probar el caso 6 salía "Te curas 25. Vida: 100." aunque el héroe tenía 89, o sea
que en realidad solo se curaba 11. Se cambió `usarPocion` para calcular lo que se
cura de verdad (`vida después - vida antes`).

## Ranking

| # | Caso | Entrada | Resultado esperado | Resultado obtenido | ¿OK? |
|---|------|---------|--------------------|--------------------|------|
| 9 | Ranking vacío | Sin `ranking.xml`, opción 2 (`03_ranking_vacio_y_ayuda.txt`) | Mensaje informativo | "Todavia no hay partidas registradas." | Sí |
| 10 | Ranking entre partidas | Jugar, salir y volver a abrir el programa | Las puntuaciones se cargan desde `ranking.xml` | Al abrir otra vez, la opción 2 muestra las partidas anteriores | Sí |
| 11 | Empate | Dos partidas con 0 puntos (Sebas y luego Ana) | El registro más antiguo queda delante | 1. Sebas - 0, 2. Ana - 0 | Sí |
| 12 | Ranking lleno, puntuación baja | `ranking_lleno.xml` (el décimo tiene 100) y abandonar con 0 (`04`) | No entra | "Tu puntuacion no entra en el ranking." y siguen los mismos 10 | Sí |
| 13 | Ranking lleno, empate con el décimo | Mismo ranking, vencer al Goblin y abandonar con 100 (`06`) | No entra porque tiene que ser mayor que el décimo | 100 puntos y "Tu puntuacion no entra en el ranking." | Sí |
| 14 | Ranking lleno, puntuación alta | Mismo ranking y ganar la partida | Entra en su sitio y se pierde el décimo | Ganó con 327: "Registrado en el ranking en la posicion 3." (detrás de 380 y 350) y Dani (100) desaparece del XML | Sí |
| 15 | Reinicio de partida | Gastar una poción, abandonar y empezar otra (`07`) | La segunda empieza con 100 de vida y 3 pociones | "Segunda empieza con 100 de vida y 3 pociones." e inventario 3/3 | Sí |
| 16 | HTML del ranking | Terminar una partida y abrir `ranking.html` | Tabla con puesto, nombre y puntos generada con `ranking.xsl` | Se crea la tabla en el mismo orden que el XML | Sí |
