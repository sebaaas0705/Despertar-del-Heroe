# Guion de la presentación y la defensa

Archivo final: `DAM_DESPERTAR_HEROE_Yanamango_Valverde_Sebastian_Presentacion.pdf` (o .pptx)

## Diapositivas

1. **Portada** - El despertar del héroe, nombre, curso y módulos.
2. **Reglas** - 100 de vida, 3 pociones de 25, ataque 10-20, enemigos 40/60/80 que
   pegan 5-15, 100 puntos por enemigo y la vida final si se gana.
3. **Diseño de los datos** - `estadoHeroe` = {vida, pociones, arma}, inventario
   `String[3]` + contador, ranking con dos arrays paralelos de 10.
4. **Métodos** - esquema de qué clase llama a cuál: main -> Menu / Partida ->
   Combate -> Enemigo / Pocima -> Ranking -> PersistenciaXml.
5. **Primitivos vs arrays** - por qué `numRegistros` se devuelve y por qué la vida
   va en un array.
6. **Ranking** - cómo se inserta, por qué un empate queda detrás y qué pasa si está
   lleno. Enseñar `ranking.html`.
7. **Pruebas** - tabla resumida y el error de la curación que se corrigió.
8. **Git** - captura de las ramas y algunos commits.

## Demostración en consola (en este orden)

1. Borrar `ranking.xml` y enseñar "Todavia no hay partidas registradas."
2. Ayuda.
3. Nueva partida: nombre vacío, una letra en el menú de armas, elegir espada.
4. Combate: consultar inventario, intentar curarse con la vida llena (no gasta
   turno), atacar, curarse con la vida baja (el enemigo responde).
5. Abandonar o terminar la partida y ver el ranking.
6. Salir, volver a ejecutar y enseñar que el ranking sigue ahí.

## Cambios que puede pedir el profesor (dónde tocar)

| Cambio | Dónde |
|--------|-------|
| Rango de daño del héroe | `Constantes.ATAQUE_MIN` / `ATAQUE_MAX` |
| Rango de daño del enemigo | `Constantes.DANIO_ENEMIGO_MIN` / `DANIO_ENEMIGO_MAX` |
| Vida de los enemigos | `Constantes.VIDA_ENEMIGOS` |
| Número de pociones o curación | `Constantes.POCIONES_INICIALES`, `CURACION_POCION` (y `CAPACIDAD_INVENTARIO`) |
| Tamaño del ranking | `Constantes.CAPACIDAD_RANKING` |
| Validar algo más en una opción | `Menu.leerOpcion` |
| Recorrido del inventario | `Pocima.mostrarInventario` (bucle hasta `numPociones`) |
| Recorrido del ranking | `Ranking.mostrarRanking` y el `for` de desplazamiento en `insertarOrdenado` |
