<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:tns="http://www.example.com/Club">

    <xsl:template match="/">
        <html>
            <body>
                <h2>Beers</h2>
                <table>
                    <tr bgcolor="#9acd32">
                        <th>Name</th>
                        <th>Type</th>
                        <th>Al</th>
                        <th>Manufacturer</th>
                        <th>RevolNumber</th>
                        <th>Transparency</th>
                        <th>Filtered</th>
                    </tr>
                    <xsl:for-each select="tns:schema/beers/beer">
                        <tr>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="type"/></td>
                            <td><xsl:value-of select="al"/></td>
                            <td><xsl:value-of select="manufacturer"/></td>
                            <td><xsl:value-of select="chars/revolNumber"/></td>
                            <td><xsl:value-of select="chars/transparency"/></td>
                            <td><xsl:value-of select="chars/filtered"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>