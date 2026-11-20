import java.util.Scanner;

/**
 * Desarrollo de una partida completa: tres encuentros seguidos.
 */
public class Partida
{
    /**
     * Crea un heroe nuevo (todos los datos reiniciados), encadena los tres
     * combates, calcula la puntuacion y registra la partida en el ranking,
     * tambien si termina por derrota o abandono.
     * Puntuacion = 100 x enemigos derrotados + vida final solo si gana los 3.
     *
     * @param scanner        lector de teclado
     * @param nombresRanking array de nombres del ranking
     * @param puntosRanking  array de puntuaciones del ranking
     * @param numRegistros   registros ocupados antes de la partida
     * @return registros ocupados despues de la partida
     */
    static int iniciarPartida(Scanner scanner, String[] nombresRanking,
            int[] puntosRanking, int numRegistros)
    {
        String nombre = Heroe.leerNombre(scanner);

        int[] estadoHeroe = Heroe.crearEstado(Heroe.elegirArma(scanner));
        String[] inventario = Heroe.crearInventario();
        System.out.println(nombre + " empieza con " + estadoHeroe[Constantes.VIDA]
                + " de vida y " + estadoHeroe[Constantes.POCIONES] + " pociones.");

        int puntos = 0;
        int resultado = Constantes.VICTORIA;
        int enemigo = 0;

        while (enemigo < Constantes.VIDA_ENEMIGOS.length && resultado == Constantes.VICTORIA)
        {
            System.out.println("\n>>> Encuentro " + (enemigo + 1) + " de "
                    + Constantes.VIDA_ENEMIGOS.length + ": "
                    + Constantes.NOMBRES_ENEMIGOS[enemigo] + " con "
                    + Constantes.VIDA_ENEMIGOS[enemigo] + " de vida <<<");

            resultado = Combate.simularCombate(scanner, estadoHeroe, inventario,
                    Constantes.NOMBRES_ENEMIGOS[enemigo], Constantes.ATAQUES_ENEMIGOS[enemigo],
                    Constantes.VIDA_ENEMIGOS[enemigo]);

            if (resultado == Constantes.VICTORIA)
            {
                puntos += Constantes.PUNTOS_POR_ENEMIGO;
                enemigo++;
            }
        }

        if (resultado == Constantes.VICTORIA)
        {
            puntos += estadoHeroe[Constantes.VIDA];
            System.out.println("\nHas completado la aventura, " + nombre + "!");
        }
        else if (resultado == Constantes.DERROTA)
        {
            System.out.println("\nHas caido en combate, " + nombre + ".");
        }
        else
        {
            System.out.println("\nAbandonas la aventura, " + nombre + ".");
        }
        System.out.println("Enemigos derrotados: " + enemigo + " de "
                + Constantes.VIDA_ENEMIGOS.length);
        System.out.println("Puntuacion final: " + puntos);

        numRegistros = Ranking.registrarEnRanking(nombresRanking, puntosRanking,
                numRegistros, nombre, puntos);
        PersistenciaXml.guardarRanking(nombresRanking, puntosRanking, numRegistros);
        PersistenciaXml.generarHtmlRanking();
        PersistenciaXml.guardarInventario(inventario, estadoHeroe[Constantes.POCIONES]);

        return numRegistros;
    }
}
