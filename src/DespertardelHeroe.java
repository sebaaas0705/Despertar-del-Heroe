import java.util.Scanner;

/**
 * EL DESPERTAR DEL HEROE - aventura textual por turnos.
 * Clase principal: carga los datos guardados y gestiona el menu con
 * do-while y switch. El trabajo real esta en los metodos de las otras clases.
 */
public class DespertardelHeroe
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        String[] nombresRanking = new String[Constantes.CAPACIDAD_RANKING];
        int[] puntosRanking = new int[Constantes.CAPACIDAD_RANKING];
        int numRegistros = PersistenciaXml.cargarRanking(nombresRanking, puntosRanking);

        String[] inventarioGuardado = new String[Constantes.CAPACIDAD_INVENTARIO];
        int pocionesGuardadas = PersistenciaXml.cargarInventario(inventarioGuardado);
        if (pocionesGuardadas >= 0)
        {
            System.out.println("Inventario de la ultima partida: "
                    + pocionesGuardadas + " pocion(es).");
        }

        int opcion;
        do
        {
            Menu.mostrarMenu();
            opcion = Menu.leerOpcion(scanner, 1, 4);

            switch (opcion)
            {
                case 1:
                    numRegistros = Partida.iniciarPartida(scanner, nombresRanking,
                            puntosRanking, numRegistros);
                    break;
                case 2:
                    Ranking.mostrarRanking(nombresRanking, puntosRanking, numRegistros);
                    break;
                case 3:
                    Menu.mostrarAyuda();
                    break;
                case 4:
                    System.out.println("Hasta pronto, heroe.");
                    break;
            }
        } while (opcion != 4);

        scanner.close();
    }
}
