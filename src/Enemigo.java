public class Enemigo
{
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
