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
