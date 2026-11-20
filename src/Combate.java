import java.util.Scanner;

public class Combate
{
    static int simularCombate(Scanner scanner, int[] estadoHeroe,
            String[] inventario, String nombreEnemigo, String[] ataquesEnemigo, int vidaEnemigo)
    {
        int resultado = Constantes.SIN_RESOLVER;

        while (resultado == Constantes.SIN_RESOLVER)
        {
            System.out.println("\nTu vida: " + estadoHeroe[Constantes.VIDA] + "/"
                    + Constantes.VIDA_MAXIMA
                    + "   Pociones: " + estadoHeroe[Constantes.POCIONES]
                    + "   Arma: " + Constantes.NOMBRES_ARMAS[estadoHeroe[Constantes.ARMA]]
                    + "   Vida " + nombreEnemigo + ": " + vidaEnemigo);
            System.out.println("1. Atacar");
            System.out.println("2. Usar pocion");
            System.out.println("3. Consultar inventario");
            System.out.println("4. Abandonar");
            System.out.println("5. Cambiar arma");

            int accion = Menu.leerOpcion(scanner, 1, 5);
            int danio;

            switch (accion)
            {
                case 1:
                    int arma = estadoHeroe[Constantes.ARMA];
                    danio = numeroAleatorio(Constantes.ATAQUE_MIN_ARMAS[arma],
                            Constantes.ATAQUE_MAX_ARMAS[arma]);
                    vidaEnemigo = Math.max(0, vidaEnemigo - danio);
                    System.out.println(Constantes.GOLPES_ARMAS[arma] + " con tu "
                            + Constantes.NOMBRES_ARMAS[arma] + " al " + nombreEnemigo
                            + " por " + danio
                            + ". Le quedan " + vidaEnemigo + ".");
                    if (vidaEnemigo > 0)
                    {
                        Enemigo.atacar(estadoHeroe, nombreEnemigo, ataquesEnemigo);
                    }
                    break;

                case 2:
                    if (Pocima.usarPocion(estadoHeroe, inventario))
                    {
                        Enemigo.atacar(estadoHeroe, nombreEnemigo, ataquesEnemigo);
                    }
                    break;

                case 3:
                    Pocima.mostrarInventario(inventario, estadoHeroe[Constantes.POCIONES]);
                    break;

                case 4:
                    resultado = Constantes.ABANDONO;
                    break;

                case 5:
                    // Cambiar de arma no consume turno: el enemigo no ataca
                    estadoHeroe[Constantes.ARMA] = Heroe.elegirArma(scanner);
                    break;
            }

            if (resultado == Constantes.SIN_RESOLVER)
            {
                if (vidaEnemigo <= 0)
                {
                    System.out.println(nombreEnemigo + " derrotado!");
                    resultado = Constantes.VICTORIA;
                }
                else if (estadoHeroe[Constantes.VIDA] <= 0)
                {
                    resultado = Constantes.DERROTA;
                }
            }
        }
        return resultado;
    }

    static int numeroAleatorio(int min, int max)
    {
        return min + (int) (Math.random() * (max - min + 1));
    }
}
