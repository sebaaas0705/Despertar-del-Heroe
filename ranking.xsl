<?xml version="1.0" encoding="UTF-8"?>
<!-- Plantilla XSLT: convierte ranking.xml en una página HTML -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <!-- Se aplica a la raíz del XML -->
    <xsl:template match="/">
        <html lang="es">
            <head>
                <meta charset="UTF-8"/>
                <title>Ranking - El despertar del heroe</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background-color: #1e1e2f;
                        color: #f0f0f0;
                        text-align: center;
                    }
                    h1 {
                        color: #f5c542;
                    }
                    table {
                        margin: 0 auto;
                        border-collapse: collapse;
                        width: 400px;
                    }
                    th, td {
                        border: 1px solid #555;
                        padding: 8px;
                    }
                    th {
                        background-color: #f5c542;
                        color: #1e1e2f;
                    }
                    tr:nth-child(even) {
                        background-color: #2c2c44;
                    }
                    .vacio {
                        color: #aaaaaa;
                        font-style: italic;
                    }
                </style>
            </head>
            <body>
                <h1>Ranking</h1>
                <xsl:choose>
                    <!-- Si hay al menos un jugador, se pinta la tabla -->
                    <xsl:when test="ranking/jugador">
                        <table>
                            <tr>
                                <th>Puesto</th>
                                <th>Nombre</th>
                                <th>Puntos</th>
                            </tr>
                            <!-- Una fila por cada <jugador>, en el orden del XML -->
                            <xsl:for-each select="ranking/jugador">
                                <tr>
                                    <td><xsl:value-of select="position()"/></td>
                                    <td><xsl:value-of select="nombre"/></td>
                                    <td><xsl:value-of select="puntos"/></td>
                                </tr>
                            </xsl:for-each>
                        </table>
                    </xsl:when>
                    <xsl:otherwise>
                        <p class="vacio">Todavia no hay partidas registradas.</p>
                    </xsl:otherwise>
                </xsl:choose>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
