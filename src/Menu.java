import java.util.Scanner;

public class Menu
{
    static void mostrarMenu()
    {
        System.out.println("\n===== EL DESPERTAR DEL HEROE =====");
        System.out.println("1. Nueva partida");
        System.out.println("2. Consultar ranking");
        System.out.println("3. Ayuda");
        System.out.println("4. Salir");
    }

    static void mostrarAyuda()
    {
        System.out.println("\n--- AYUDA ---");
        System.out.println("Empiezas con " + Constantes.VIDA_MAXIMA + " de vida y "
                + Constantes.POCIONES_INICIALES + " pociones de "
                + Constantes.CURACION_POCION + ".");
        System.out.println("Al empezar eliges arma (y puedes cambiarla en combate):");
        for (int i = 0; i < Constantes.NOMBRES_ARMAS.length; i++)
        {
            System.out.println("  " + Constantes.NOMBRES_ARMAS[i] + ": "
                    + Constantes.ATAQUE_MIN_ARMAS[i] + "-"
                    + Constantes.ATAQUE_MAX_ARMAS[i] + " de danio.");
        }
        System.out.println("Debes vencer a 3 enemigos seguidos:");
        for (int i = 0; i < Constantes.NOMBRES_ENEMIGOS.length; i++)
        {
            System.out.println("  " + Constantes.NOMBRES_ENEMIGOS[i] + ": "
                    + Constantes.VIDA_ENEMIGOS[i] + " de vida.");
        }
        System.out.println("Cada enemigo vale " + Constantes.PUNTOS_POR_ENEMIGO
                + " puntos. Si ganas la aventura, se suma tu vida final.");
    }

    static int leerOpcion(Scanner scanner, int min, int max)
    {
        int opcion = min - 1;
        boolean valida = false;

        while (!valida)
        {
            System.out.print("Elige una opcion (" + min + "-" + max + "): ");

            // Si se cierra la entrada (Ctrl+Z, Ctrl+C...) salimos limpio en vez de petar
            if (!scanner.hasNextLine())
            {
                System.out.println("\nEntrada cerrada, saliendo.");
                System.exit(0);
            }

            if (scanner.hasNextInt())
            {
                opcion = scanner.nextInt();
                scanner.nextLine();

                if (opcion >= min && opcion <= max)
                {
                    valida = true;
                }
                else
                {
                    System.out.println("Opcion fuera de rango.");
                }
            }
            else
            {
                scanner.nextLine();
                System.out.println("Debes escribir un numero.");
            }
        }
        return opcion;
    }
}
