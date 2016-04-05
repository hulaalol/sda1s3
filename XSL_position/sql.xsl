<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">
   
   
    <xsl:output method="text"/>
 
    <xsl:variable name="n">
        <xsl:text>
</xsl:text>                
    </xsl:variable>
   
    <xsl:template match="/memo">
        <xsl:for-each select="from[@id]|to[@id]">
                <xsl:text>INSERT INTO Customer (id, name) VALUES ('</xsl:text>
                <xsl:value-of select="@id"/>
                    <xsl:text>', '</xsl:text>
                    <xsl:value-of select="."/>
                        <xsl:text>')</xsl:text>
                        <xsl:value-of select="$n"/>
            
        </xsl:for-each>
    </xsl:template>
    
    
</xsl:stylesheet>