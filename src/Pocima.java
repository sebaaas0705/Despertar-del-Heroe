/**
 * Uso y consulta del inventario de pociones (array fijo + contador).
 */
public class Pocima
{
    /**
     * Consume la ultima pocion ocupada del inventario y cura al heroe sin pasar
     * de la vida maxima. Si no hay pociones o la vida esta llena no hace nada.
     *
     * @param estadoHeroe {vida, pociones, arma}; el contador de pociones hace de
     *                    numero de posiciones ocupadas del inventario
     * @param inventario  array de pociones; la posicion usada queda a null
     * @return true si se ha bebido la pocion (gasta turno), false si no
     */
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

        // Si la vida pasaria de 100 se queda en 100, asi que se cura menos de 25
        int vidaAntes = estadoHeroe[Constantes.VIDA];
        estadoHeroe[Constantes.VIDA] = Math.min(Constantes.VIDA_MAXIMA,
                vidaAntes + Constantes.CURACION_POCION);
        int curado = estadoHeroe[Constantes.VIDA] - vidaAntes;
        System.out.println("Te curas " + curado + ". Vida: "
                + estadoHeroe[Constantes.VIDA] + ".");
        return true;
    }

    /**
     * Recorre solo las posiciones ocupadas (hasta el contador, no hasta length).
     * No gasta turno.
     *
     * @param inventario  array de pociones
     * @param numPociones numero de posiciones ocupadas
     */
    static void mostrarInventario(String[] inventario, int numPociones)
    {
        System.out.println("--- Inventario ---");
        if (numPociones == 0)
        {
            System.out.println("(vacio)");
        }
        for (int i = 0; i < numPociones; i++)
        {
            System.out.println((i + 1) + ". " + inventario[i] + " (+"
                    + Constantes.CURACION_POCION + " de vida)");
        }
        System.out.println("Huecos ocupados: " + numPociones + "/" + inventario.length);
    }
}
