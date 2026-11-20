import java.util.Scanner;

public class Heroe
{
    static String leerNombre(Scanner scanner)
    {
        String nombre = "";
        while (nombre.isEmpty())
        {
            System.out.print("Nombre del heroe: ");
            // Si se cierra la entrada (Ctrl+Z, Ctrl+C...) salimos limpio en vez de petar
            if (!scanner.hasNextLine())
            {
                System.out.println("\nEntrada cerrada, saliendo.");
                System.exit(0);
            }
            nombre = scanner.nextLine().trim();
            if (nombre.isEmpty())
            {
                System.out.println("El nombre no puede quedar vacio.");
            }
        }
        return nombre;
    }

    static int[] crearEstado(int arma)
    {
        return new int[] {Constantes.VIDA_MAXIMA, Constantes.POCIONES_INICIALES, arma};
    }

    static int elegirArma(Scanner scanner)
    {
        System.out.println("\n--- Elige tu arma ---");
        for (int i = 0; i < Constantes.NOMBRES_ARMAS.length; i++)
        {
            System.out.println((i + 1) + ". " + Constantes.NOMBRES_ARMAS[i] + " ("
                    + Constantes.ATAQUE_MIN_ARMAS[i] + "-"
                    + Constantes.ATAQUE_MAX_ARMAS[i] + " de danio)");
        }
        int arma = Menu.leerOpcion(scanner, 1, Constantes.NOMBRES_ARMAS.length) - 1;
        System.out.println("Has elegido: " + Constantes.NOMBRES_ARMAS[arma] + ".");
        return arma;
    }

    static String[] crearInventario()
    {
        String[] inventario = new String[Constantes.CAPACIDAD_INVENTARIO];
        for (int i = 0; i < Constantes.POCIONES_INICIALES; i++)
        {
            inventario[i] = "Pocion";
        }
        return inventario;
    }
}
