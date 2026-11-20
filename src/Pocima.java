public class Pocima
{
    static boolean usarPocion(int[] estadoHeroe, String[] inventario)
    {
        if (estadoHeroe[Constantes.POCIONES] <= 0)
        {
            System.out.println("No te quedan pociones.");
            return false;
        }
        if (estadoHeroe[Constantes.VIDA] >= Constantes.VIDA_MAXIMA)
        {
            System.out.println("Tu vida ya esta al maximo.");
            return false;
        }

        // Se retira la ultima posicion ocupada; nunca se sale del array
        int posicion = estadoHeroe[Constantes.POCIONES] - 1;
        if (posicion < 0 || posicion >= inventario.length || inventario[posicion] == null)
        {
            System.out.println("Esa posicion del inventario esta vacia.");
            return false;
        }
        inventario[posicion] = null;
        estadoHeroe[Constantes.POCIONES]--;

        estadoHeroe[Constantes.VIDA] = Math.min(Constantes.VIDA_MAXIMA,
                estadoHeroe[Constantes.VIDA] + Constantes.CURACION_POCION);
        System.out.println("Te curas " + Constantes.CURACION_POCION + ". Vida: "
                + estadoHeroe[Constantes.VIDA] + ".");
        return true;
    }

    static void mostrarInventario(String[] inventario, int numPociones)
    {
        System.out.println("--- Inventario ---");
        if (numPociones == 0)
        {
            System.out.println("(vacio)");
        }
        for (int i = 0; i < numPociones; i++)
        {
            System.out.println((i + 1) + ". " + inventario[i]);
        }
    }
}
