/**
 * Valores fijos del juego. Todo numero del enunciado esta aqui para no
 * repartir "valores magicos" por el codigo: si cambia una regla, se toca
 * solo esta clase.
 */
public class Constantes
{
    // --- Heroe ---
    static final int VIDA_MAXIMA = 100;
    static final int POCIONES_INICIALES = 3;
    static final int CURACION_POCION = 25;

    // --- Armas (misma posicion = misma arma en los dos arrays) ---
    static final String[] NOMBRES_ARMAS = {"Espada", "Hacha", "Daga"};
    static final String[] GOLPES_ARMAS = {"Tajo", "Hachazo", "Estocada"};
    // Todas las armas pegan lo que dice el enunciado (10-20);
    // solo cambia el nombre del arma y del golpe
    static final int ATAQUE_MIN = 10;
    static final int ATAQUE_MAX = 20;

    // --- Enemigos (en orden de dificultad creciente) ---
    static final String[] NOMBRES_ENEMIGOS = {"Goblin", "Orco", "Dragon"};
    // Cada fila son los ataques de un enemigo; en cada turno usa uno al azar
    static final String[][] ATAQUES_ENEMIGOS = {
        {"Zarpazo", "Mordisco", "Pedrada"},
        {"Garrotazo", "Embestida", "Cabezazo"},
        {"Llamarada", "Coletazo", "Bola de fuego"}
    };
    static final int[] VIDA_ENEMIGOS = {40, 60, 80};
    static final int DANIO_ENEMIGO_MIN = 5;
    static final int DANIO_ENEMIGO_MAX = 15;

    // --- Puntuacion ---
    static final int PUNTOS_POR_ENEMIGO = 100;

    // --- Tamano de los arrays fijos ---
    static final int CAPACIDAD_INVENTARIO = 3;
    static final int CAPACIDAD_RANKING = 10;

    // --- Ficheros (se buscan/crean en la carpeta desde la que se ejecuta) ---
    static final String FICHERO_RANKING = "ranking.xml";
    static final String FICHERO_INVENTARIO = "inventario.xml";
    static final String FICHERO_XSL = "ranking.xsl";
    static final String FICHERO_HTML = "ranking.html";

    // --- Posiciones dentro del array estadoHeroe = {vida, pociones, arma} ---
    static final int VIDA = 0;
    static final int POCIONES = 1;
    static final int ARMA = 2;

    // --- Posibles resultados de un combate ---
    static final int SIN_RESOLVER = -1;
    static final int DERROTA = 0;
    static final int VICTORIA = 1;
    static final int ABANDONO = 2;
}
