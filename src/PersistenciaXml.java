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

public class PersistenciaXml
{
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

    static void generarHtmlRanking()
    {
        try
        {
            // El Transformer aplica la plantilla XSL al XML y escribe el HTML
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer(new StreamSource(new File(Constantes.FICHERO_XSL)));
            transformer.transform(new StreamSource(new File(Constantes.FICHERO_RANKING)),
                    new StreamResult(new File(Constantes.FICHERO_HTML)));
        }
        catch (Exception e)
        {
            System.out.println("No se pudo generar el HTML: " + e.getMessage());
        }
    }

    static void escribirXml(Document doc, String ruta) throws Exception
    {
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(new DOMSource(doc), new StreamResult(new File(ruta)));
    }
}
