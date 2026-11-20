/**
 * Ranking en memoria con dos arrays paralelos: nombres[i] y puntos[i] son
 * siempre del mismo jugador. Se mantiene ordenado de mayor a menor.
 */
public class Ranking
{
    /**
     * Registra una partida terminada y avisa al jugador de su puesto.
     * Devuelve el numero de registros porque es un int (se pasa por copia) y el
     * metodo no podria cambiarlo; los arrays si cambian por referencia.
     *
     * @param nombres      array de nombres del ranking
     * @param puntos       array de puntuaciones del ranking
     * @param numRegistros registros ocupados antes de insertar
     * @param nombre       nombre del heroe
     * @param puntosNuevos puntos obtenidos en la partida
     * @return numero de registros ocupados despues de insertar
     */
    static int registrarEnRanking(String[] nombres, int[] puntos,
            int numRegistros, String nombre, int puntosNuevos)
    {
        int posicion = insertarOrdenado(nombres, puntos, numRegistros, nombre, puntosNuevos);

        if (posicion < 0)
        {
            System.out.println("Tu puntuacion no entra en el ranking.");
            return numRegistros;
        }

        if (numRegistros < Constantes.CAPACIDAD_RANKING)
        {
            numRegistros++;
        }
        System.out.println("Registrado en el ranking en la posicion " + (posicion + 1) + ".");
        return numRegistros;
    }

    /**
     * Inserta un registro en su sitio desplazando los peores una posicion.
     * En un empate el nuevo queda detras del antiguo, porque solo se para cuando
     * es estrictamente mayor. Con el ranking lleno solo entra si supera al decimo,
     * y el ultimo registro se pierde.
     *
     * @param nombres      array de nombres del ranking
     * @param puntos       array de puntuaciones del ranking
     * @param numRegistros registros ocupados antes de insertar
     * @param nombre       nombre del jugador
     * @param puntosNuevos puntos del jugador
     * @return posicion donde se ha insertado, o -1 si no entra
     */
    static int insertarOrdenado(String[] nombres, int[] puntos,
            int numRegistros, String nombre, int puntosNuevos)
    {
        boolean lleno = (numRegistros == Constantes.CAPACIDAD_RANKING);

        if (lleno && !(puntosNuevos > puntos[Constantes.CAPACIDAD_RANKING - 1]))
        {
            return -1;
        }

        int posicion = 0;
        while (posicion < numRegistros && !(puntosNuevos > puntos[posicion]))
        {
            posicion++;
        }

        int ultimo = lleno ? Constantes.CAPACIDAD_RANKING - 1 : numRegistros;
        for (int i = ultimo; i > posicion; i--)
        {
            nombres[i] = nombres[i - 1];
            puntos[i] = puntos[i - 1];
        }

        nombres[posicion] = nombre;
        puntos[posicion] = puntosNuevos;
        return posicion;
    }

    /**
     * Muestra el ranking por consola o un aviso si todavia esta vacio.
     *
     * @param nombres      array de nombres
     * @param puntos       array de puntuaciones
     * @param numRegistros registros ocupados
     */
    static void mostrarRanking(String[] nombres, int[] puntos, int numRegistros)
    {
        System.out.println("\n--- RANKING ---");
        if (numRegistros == 0)
        {
            System.out.println("Todavia no hay partidas registradas.");
            return;
        }
        for (int i = 0; i < numRegistros; i++)
        {
            System.out.println((i + 1) + ". " + nombres[i] + " - " + puntos[i] + " puntos");
        }
    }
}
