public class Ranking
{
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
