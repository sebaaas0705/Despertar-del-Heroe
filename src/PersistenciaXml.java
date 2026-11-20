import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Guardado y carga del ranking y del inventario en ficheros XML con DOM,
 * y generacion de ranking.html aplicando la plantilla ranking.xsl.
 * Los fallos de fichero se avisan por consola sin cerrar el juego.
 */
public class PersistenciaXml
{
    /**
     * Escribe el ranking en ranking.xml (un nodo jugador por registro ocupado).
     *
     * @param nombres      array de nombres
     * @param puntos       array de puntuaciones
     * @param numRegistros registros ocupados
     */
    static void guardarRanking(String[] nombres, int[] puntos, int numRegistros)
    {
        try
        {
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().newDocument();
            Element raiz = doc.createElement("ranking");
            doc.appendChild(raiz);

            for (int i = 0; i < numRegistros; i++)
            {
                Element jugador = doc.createElement("jugador");
                Element eNombre = doc.createElement("nombre");
                eNombre.setTextContent(nombres[i]);
                Element ePuntos = doc.createElement("puntos");
                ePuntos.setTextContent(String.valueOf(puntos[i]));
                jugador.appendChild(eNombre);
                jugador.appendChild(ePuntos);
                raiz.appendChild(jugador);
            }
            escribirXml(doc, Constantes.FICHERO_RANKING);
        }
        catch (Exception e)
        {
            System.out.println("No se pudo guardar el ranking: " + e.getMessage());
        }
    }

    /**
     * Lee ranking.xml si existe y rellena los arrays, ya ordenados.
     *
     * @param nombres array de nombres a rellenar
     * @param puntos  array de puntuaciones a rellenar
     * @return numero de registros cargados (0 si no hay fichero o es invalido)
     */
    static int cargarRanking(String[] nombres, int[] puntos)
    {
        int numRegistros = 0;
        File fichero = new File(Constantes.FICHERO_RANKING);
        if (!fichero.exists())
        {
            return 0;
        }
        try
        {
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().parse(fichero);
            NodeList jugadores = doc.getElementsByTagName("jugador");

            // Se inserta cada registro ordenado: si alguien edita el XML a mano
            // y lo desordena o mete mas de 10, el ranking sigue siendo valido
            for (int i = 0; i < jugadores.getLength(); i++)
            {
                Element jugador = (Element) jugadores.item(i);
                String nombre = jugador.getElementsByTagName("nombre")
                        .item(0).getTextContent().trim();
                int puntosLeidos = Integer.parseInt(jugador
                        .getElementsByTagName("puntos").item(0).getTextContent().trim());

                if (!nombre.isEmpty() && puntosLeidos >= 0
                        && Ranking.insertarOrdenado(nombres, puntos, numRegistros,
                                nombre, puntosLeidos) >= 0
                        && numRegistros < Constantes.CAPACIDAD_RANKING)
                {
                    numRegistros++;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("No se pudo leer el ranking: " + e.getMessage());
            numRegistros = 0;
        }
        return numRegistros;
    }

    /**
     * Guarda en inventario.xml las pociones que le quedaban al heroe.
     *
     * @param inventario  array de pociones
     * @param numPociones posiciones ocupadas
     */
    static void guardarInventario(String[] inventario, int numPociones)
    {
        try
        {
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().newDocument();
            Element raiz = doc.createElement("inventario");
            doc.appendChild(raiz);

            for (int i = 0; i < numPociones; i++)
            {
                Element objeto = doc.createElement("objeto");
                objeto.setTextContent(inventario[i]);
                raiz.appendChild(objeto);
            }
            escribirXml(doc, Constantes.FICHERO_INVENTARIO);
        }
        catch (Exception e)
        {
            System.out.println("No se pudo guardar el inventario: " + e.getMessage());
        }
    }

    /**
     * Lee inventario.xml de la ultima partida. Solo se usa como informacion,
     * porque cada partida nueva empieza con el inventario completo.
     *
     * @param inventario array a rellenar
     * @return pociones leidas, o -1 si el fichero no existe
     */
    static int cargarInventario(String[] inventario)
    {
        File fichero = new File(Constantes.FICHERO_INVENTARIO);
        if (!fichero.exists())
        {
            return -1;
        }
        int numObjetos = 0;
        try
        {
            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().parse(fichero);
            NodeList objetos = doc.getElementsByTagName("objeto");
            for (int i = 0; i < objetos.getLength()
                    && numObjetos < Constantes.CAPACIDAD_INVENTARIO; i++)
            {
                inventario[numObjetos] = objetos.item(i).getTextContent();
                numObjetos++;
            }
        }
        catch (Exception e)
        {
            System.out.println("No se pudo leer el inventario: " + e.getMessage());
            numObjetos = 0;
        }
        return numObjetos;
    }

    /**
     * Transforma ranking.xml en ranking.html con la plantilla ranking.xsl.
     */
    static void generarHtmlRanking()
    {
        try
        {
            // El Transformer aplica la plantilla XSL al XML y escribe el HTML
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer(new StreamSource(new File(Constantes.FICHERO_XSL)));
            transformer.transform(new StreamSource(new File(Constantes.FICHERO_RANKING)),
                    new StreamResult(new File(Constantes.FICHERO_HTML)));
            System.out.println("Ranking actualizado en " + Constantes.FICHERO_RANKING
                    + " y " + Constantes.FICHERO_HTML + ".");
        }
        catch (Exception e)
        {
            System.out.println("No se pudo generar el HTML: " + e.getMessage());
        }
    }

    /**
     * Vuelca un documento DOM a fichero con sangria de 2 espacios.
     *
     * @param doc  documento a escribir
     * @param ruta nombre del fichero de destino
     * @throws Exception si no se puede escribir el fichero
     */
    static void escribirXml(Document doc, String ruta) throws Exception
    {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(new DOMSource(doc), new StreamResult(new File(ruta)));
    }
}
