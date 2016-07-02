<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs"
    version="2.0">
    
    <xsl:output method="xml" indent="yes"/>
    <xsl:strip-space elements="*"/>
    
    
    <xsl:template match="book">
        <html>
            <head>
                <title>
                    <xsl:value-of select="title"/>
                </title>
            </head>
            
            <body>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>
    

    <xsl:template match="book/title">
        <h1>
            <xsl:value-of select="."/> 
        </h1>
        
    </xsl:template>


    <xsl:template match="title">
        <h2>
            <xsl:value-of select="."/>  
        </h2>
    </xsl:template>

    <xsl:template match="para">
        <p>
            <xsl:value-of select="."/> 
        </p>
    </xsl:template>
    
    <xsl:template match="chapter">
        <xsl:apply-templates select="title"/>
        <xsl:apply-templates select="para"/> 
    </xsl:template>
    
    
</xsl:stylesheet>