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
      <xsl:apply-templates select="from|to"/>
    </xsl:template>
    
    
    <xsl:template match="from">
        <xsl:text>INSERT INTO Customer (name, type) VALUES ('</xsl:text>
        <xsl:value-of select="."/>
        <xsl:text>', 'employee')</xsl:text>
        <xsl:value-of select="$n"/>
    </xsl:template>
    
    
    <xsl:template match="to">
        <xsl:text>INSERT INTO Customer (name, type) VALUES ('</xsl:text>
        <xsl:value-of select="."/>
        <xsl:text>', 'customer')</xsl:text>
        <xsl:value-of select="$n"/>
        
    </xsl:template>
    
    
</xsl:stylesheet>