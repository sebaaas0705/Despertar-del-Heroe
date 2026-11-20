public class Constantes
{
    static final int VIDA_MAXIMA = 100;
    static final int POCIONES_INICIALES = 3;
    static final int CURACION_POCION = 25;
    static final String[] NOMBRES_ARMAS = {"Espada", "Hacha", "Daga"};
    static final String[] GOLPES_ARMAS = {"Tajo", "Hachazo", "Estocada"};
    static final int[] ATAQUE_MIN_ARMAS = {10, 5, 13};
    static final int[] ATAQUE_MAX_ARMAS = {20, 25, 17};
    static final String[] NOMBRES_ENEMIGOS = {"Goblin", "Orco", "Dragon"};
    // Cada fila son los ataques de un enemigo; en cada turno usa uno al azar
    static final String[][] ATAQUES_ENEMIGOS = {
        {"Zarpazo", "Mordisco", "Pedrada"},
        {"Garrotazo", "Embestida", "Cabezazo"},
        {"Llamarada", "Coletazo", "Bola de fuego"}
    };
    static final int[] VIDA_ENEMIGOS = {40, 60, 80};
    static final int DANIO_ENEMIGO_MIN = 7;
    static final int DANIO_ENEMIGO_MAX = 17;
    static final int PUNTOS_POR_ENEMIGO = 100;

    static final int CAPACIDAD_INVENTARIO = 3;
    static final int CAPACIDAD_RANKING = 10;

    static final String FICHERO_RANKING = "ranking.xml";
    static final String FICHERO_INVENTARIO = "inventario.xml";
    static final String FICHERO_XSL = "ranking.xsl";
    static final String FICHERO_HTML = "ranking.html";

    static final int VIDA = 0;
    static final int POCIONES = 1;
    static final int ARMA = 2;

    static final int SIN_RESOLVER = -1;
    static final int DERROTA = 0;
    static final int VICTORIA = 1;
    static final int ABANDONO = 2;
}
