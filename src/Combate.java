import java.util.Scanner;

/**
 * Resolucion de un encuentro por turnos entre el heroe y un enemigo.
 */
public class Combate
{
    /**
     * Gestiona un combate completo con un bucle while hasta que haya resultado.
     * Solo atacar o curarse con exito gastan turno y provocan la respuesta del
     * enemigo; consultar inventario, cambiar arma o una curacion fallida no.
     * Un enemigo con vida 0 nunca llega a atacar.
     *
     * @param scanner        lector de teclado
     * @param estadoHeroe    {vida, pociones, arma}; se modifica por referencia
     * @param inventario     pociones del heroe; se modifica por referencia
     * @param nombreEnemigo  nombre que se muestra en los mensajes
     * @param ataquesEnemigo nombres de los ataques del enemigo
     * @param vidaEnemigo    vida inicial del enemigo (copia local, se pierde al salir)
     * @return VICTORIA, DERROTA o ABANDONO (ver Constantes)
     */
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
                    danio = numeroAleatorio(Constantes.ATAQUE_MIN, Constantes.ATAQUE_MAX);
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

    /**
     * Numero entero aleatorio con Math.random() entre min y max, ambos incluidos.
     * Math.random() da [0, 1); al multiplicar por (max - min + 1) y truncar
     * salen los valores 0..(max - min), y al sumar min queda el rango pedido.
     *
     * @param min limite inferior (incluido)
     * @param max limite superior (incluido)
     * @return valor entre min y max
     */
    static int numeroAleatorio(int min, int max)
    {
        return min + (int) (Math.random() * (max - min + 1));
    }
}
