/**
 * Comportamiento del enemigo durante el combate.
 */
public class Enemigo
{
    /**
     * El enemigo elige uno de sus ataques al azar y quita vida al heroe.
     * La vida se deja en 0 como minimo para que nunca sea negativa.
     *
     * @param estadoHeroe   {vida, pociones, arma}; la vida cambia por referencia
     * @param nombreEnemigo nombre del enemigo para el mensaje
     * @param ataques       nombres de los ataques posibles de este enemigo
     */
    static void atacar(int[] estadoHeroe, String nombreEnemigo, String[] ataques)
    {
        // Elige uno de sus ataques al azar
        String nombreAtaque = ataques[Combate.numeroAleatorio(0, ataques.length - 1)];
        int danio = Combate.numeroAleatorio(Constantes.DANIO_ENEMIGO_MIN,
                Constantes.DANIO_ENEMIGO_MAX);
        estadoHeroe[Constantes.VIDA] = Math.max(0, estadoHeroe[Constantes.VIDA] - danio);
        System.out.println("El " + nombreEnemigo + " usa " + nombreAtaque + " y te quita " + danio
                + ". Te quedan " + estadoHeroe[Constantes.VIDA] + ".");
    }
}
