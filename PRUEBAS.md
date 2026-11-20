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
