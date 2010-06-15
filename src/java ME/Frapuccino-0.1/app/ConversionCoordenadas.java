/*
 * ConversionCoordenadas.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

import javax.microedition.io.*;
import java.io.*;

class ConversionCoordenadas 
{
 private String ired = "";
 private String[] coordenadas = {"", "" , "", ""};  
 private InterfazRed interfazRed = new InterfazRed();
 
 public ConversionCoordenadas(String lugar)
 {
  this.ired = interfazRed.getInterfazRed();    
  crearNombre(lugar);
  Mapa mapa = new Mapa(coordenadas);
  mapa.mostrarMapa();
 }
 
 public ConversionCoordenadas(String lugar, String lugar2)
 { 
  this.ired = interfazRed.getInterfazRed();    
  crearNombre(lugar, lugar2);
  Mapa mapa = new Mapa(coordenadas);
  mapa.mostrarMapa();
 }
 
 private void crearNombre(String adress)
 {  
    String adress1 = "";
    String text = "";
    String cord = "";;
    
    for(int i = 0; i < adress.trim().length(); i++) {
        if(adress.trim().charAt(i) == ' ')
        {
         adress1 += "%";
        }
        else
        {
         adress1 += adress.trim().charAt(i);
        }
           
    }
    
    text = convertirCoordenadas(adress1);
  //  System.out.println(adress1);
    cord = crearLat(text);
    separarCoord(cord);                    
  }
  
  private void crearNombre(String adress, String adress2)
  {
      String adress1 = "";
      String adress3 = "";
      String text = "";
      String cord = "";
       
           for(int i = 0; i < adress.trim().length(); i++) {
                if(adress.trim().charAt(i) == ' ')
                {
                    adress1 += "%";
                }
                else
                {
                 adress1 += adress.trim().charAt(i);
                }
           
           }
           
           for(int i = 0; i < adress2.trim().length(); i++) {
                if(adress2.trim().charAt(i) == ' ')
                {
                    adress3 += "%";
                }
                else
                {
                 adress3 += adress2.trim().charAt(i);
                }
           
            }
              
      text = convertirCoordenadas(adress1);
      cord = crearLat(text);
      separarCoord(cord);  
      text = convertirCoordenadas(adress3);
      cord = crearLat(text);
      separarCoord2(cord);                    
    } 
   
   
      
  private String convertirCoordenadas(String address)
  {
   HttpConnection conn = null;
   InputStream input = null;
   String text = "";
    
    try {
        conn = (HttpConnection)Connector.open("http://maps.google.com/maps/geo?q="+address+"&output=csv&;interface="+this.ired);
        conn.setRequestMethod(HttpConnection.POST);
        input = conn.openInputStream();
     
        byte[] data = new byte[256];
        int len = 0;
        
        StringBuffer raw = new StringBuffer();
    
        while ( -1 != (len = input.read(data)) ) {
            raw.append(new String(data, 0, len));
        }
    
        text = raw.toString();
           
    } catch (Exception e) {
        
        
    }
 //System.out.println(text);
 return text;
 }
  
 private String crearLat(String dat) 
 {
   int cont=0;
   String coord = "";
   
   for(int i = 0; i < dat.length() ; i++)
   {
    try {
        
        if (cont == 2 || cont == 3)
        {
          coord += dat.charAt(i);  
        }
       
         
        if (dat.charAt(i) == ',')
        {
          cont++;
        }     
    
     }   
     catch (Exception e)
     {
        System.out.println("Error --> "+e.toString());
     }
   
    }
 //System.out.println(coord);   
 return coord;   
 }
 
 private void separarCoord(String coord)
 {
  boolean esLatitud = true;
  
  for(int i = 0; i < coord.length(); i++)
  {
    
    if(coord.charAt(i) == ',')
    {
       esLatitud = false; 
    }   
  
    if(esLatitud)
    {
     this.coordenadas[0] += coord.charAt(i);    
    }
    else
    if(coord.charAt(i) != ',')
    {
     this.coordenadas[1] += coord.charAt(i);   
    }
        
  
    }      
 }
 
  private void separarCoord2(String coord)
 {
  boolean esLatitud = true;
  
  for(int i = 0; i < coord.length(); i++)
  {
    
    if(coord.charAt(i) == ',')
    {
       esLatitud = false; 
    }   
  
    if(esLatitud)
    {
     this.coordenadas[2] += coord.charAt(i);    
    }
    else
    if(coord.charAt(i) != ',')
    {
     this.coordenadas[3] += coord.charAt(i);   
    }
        
  
    }      
 }
 
 
} 
