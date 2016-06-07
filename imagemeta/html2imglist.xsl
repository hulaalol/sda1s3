<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0"
    xmlns="http://www.w3.org/1999/xhtml">

    <xsl:output indent="yes" method="xml" />

    <xsl:template match="/html">
        <html >
            <head>
                <style> <!-- a courtesy to all non-CSS geeks on this planet -->                    
         table, th, td {
           border:1px solid black;
           border-collapse: collapse;
         }
       </style>
                <title>List of images</title>
            </head>
            <body>
                
                
                
                <xsl:if test=".//img">
                    
                    <h2> List of Images </h2>
                    
                    <table>
                        
                        <tr>
                            
                            <th>
                                No.
                            </th>
                            
                            <th>
                                URL
                            </th>
                            
                            <th>
                                alt description
                            </th>
                            
                            
                        </tr>
                        
                        
                        <xsl:for-each select=".//img">
                            
                            
                            <tr>
                                <td>
                                    <xsl:value-of select="position()"/>
                                </td>
                                
                                <td>
                                    <xsl:value-of select="@src"/>  
                                </td>
                                
                                <td>
                                    <xsl:if test="@alt != ''">  
                                        <xsl:value-of select="@alt"/>     
                                    </xsl:if>
                                    
                                    <xsl:if test="not(@alt)">
                                        -   
                                    </xsl:if>
                                    
                                </td>
                                
                                
                                
                                
                                
                            </tr>
                            
                            
                            
                        </xsl:for-each>
                        
                    </table> 

                </xsl:if> 
                
                <xsl:if test="not(.//img)">

                    <h2>Input document does not contain any images.</h2>

                </xsl:if>

            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>