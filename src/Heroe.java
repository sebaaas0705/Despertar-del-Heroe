import java.util.Scanner;

/**
 * Creacion de los datos del heroe al empezar cada partida.
 * No es una clase de dominio: solo agrupa metodos static que devuelven
 * arrays basicos con el estado inicial.
 */
public class Heroe
{
    /**
     * Pide el nombre del heroe y no deja continuar si queda vacio
     * (tambien si solo se escriben espacios, gracias a trim()).
     *
     * @param scanner lector de teclado
     * @return nombre no vacio y sin espacios a los lados
     */
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

    /**
     * Crea el estado inicial del heroe. Se usa un int[] y no tres int sueltos
     * porque los arrays se pasan por referencia: si simularCombate cambia la vida,
     * el cambio se ve fuera del metodo.
     *
     * @param arma posicion del arma elegida en Constantes.NOMBRES_ARMAS
     * @return array {vida, pociones, arma} con los valores iniciales
     */
    static int[] crearEstado(int arma)
    {
        return new int[] {Constantes.VIDA_MAXIMA, Constantes.POCIONES_INICIALES, arma};
    }

    /**
     * Muestra las armas disponibles y deja elegir una.
     *
     * @param scanner lector de teclado
     * @return posicion del arma elegida (empieza en 0)
     */
    static int elegirArma(Scanner scanner)
    {
        System.out.println("\n--- Elige tu arma ---");
        for (int i = 0; i < Constantes.NOMBRES_ARMAS.length; i++)
        {
            System.out.println((i + 1) + ". " + Constantes.NOMBRES_ARMAS[i] + " ("
                    + Constantes.GOLPES_ARMAS[i] + ")");
        }
        int arma = Menu.leerOpcion(scanner, 1, Constantes.NOMBRES_ARMAS.length) - 1;
        System.out.println("Has elegido: " + Constantes.NOMBRES_ARMAS[arma] + ".");
        return arma;
    }

    /**
     * Crea un inventario nuevo con todas las pociones iniciales.
     * Cada partida llama a este metodo, asi que los datos se reinician.
     *
     * @return array de tamano fijo con las pociones en las primeras posiciones
     */
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
