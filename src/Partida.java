import java.util.Scanner;

public class Partida
{
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
