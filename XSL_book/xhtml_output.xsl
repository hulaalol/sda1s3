<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet  xmlns="http://www.w3.org/1999/xhtml"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    
    
    
    <xsl:output method="xhtml" indent="yes"
        doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
        doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"/> 
    
    
    
    <xsl:template match="/book">
        
        <html>
            
            <head>
                <title>
                    
                    <xsl:text>Book Info Website</xsl:text>
                    
                </title>
                
                
            </head>
            
            
            <body>
                
                <h1>
                   <xsl:value-of select="title"/> 
                </h1>
                
                <h2>
                    <xsl:text>Chapters:</xsl:text>  
                </h2>
                
                
                <ol type="1">
                
                <xsl:for-each select="chapter">
                    
                    <li> <h3><xsl:value-of select="@title"/></h3>
                     
                        <ul>
                            <xsl:for-each select="./paragraph">
                                
                                <li><xsl:value-of select="."/></li>
         
                            </xsl:for-each>
  
                        </ul>
                 
                 
                 
                 </li>   
                    
                    
                    
                    
                </xsl:for-each>
                
                </ol>
            </body>
    
        </html>
        
        
        
        
    </xsl:template>
    
    
    
    
    
    
</xsl:stylesheet>