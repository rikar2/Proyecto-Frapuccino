/*
 * Mapa.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

import net.rim.device.api.ui.*;
import javax.microedition.io.*;
import java.io.*;


class Mapa 
{
 private String ired = "";
 private int zoom = 14;
 private String tipoMapa = "mobil";
 private byte[] buffer;
 private String[] coordenadas = {"", "", "", ""};
 private InterfazRed interfazRed = new InterfazRed();
 
 public Mapa(String[] coordenadas)
 {
  ired = interfazRed.getInterfazRed();
  this.coordenadas = coordenadas;  
 }
 
 
 public void mostrarMapa()
 {
   String URL = "";
   
   if(this.coordenadas[2].equals("") && this.coordenadas[3].equals(""))
   {
      URL = crearURL(this.coordenadas[0], this.coordenadas[1]);     
   }
   else
   {
      URL = crearURL2(this.coordenadas[0], this.coordenadas[1], this.coordenadas[2], this.coordenadas[3]); 
   }
   
   conseguirMapa(URL);
   UiApplication.getUiApplication().pushScreen(new MapaUI(this.buffer, this.coordenadas, tipoMapa, zoom)); 
 }
 
/* public Mapa(String URL)
 {
  conseguirMapa(URL);
  UiApplication.getUiApplication().pushScreen(new MapaUI(this.buffer, this.coordenadas, tipoMapa, zoom));   
 }*/
 

 private String crearURL(String latitud, String longitud)
 {
  return "http://maps.google.com/staticmap?center="+latitud+","+longitud+"&zoom="+zoom+"&size=512x512&maptype="+tipoMapa+"&markers="+latitud+","+longitud+",bluem|&key=MAPS_API_KEY&sensor=true_or_false;interface="+this.ired;      
 }
 
 private String crearURL2(String latitud, String longitud, String latitud2, String longitud2)
 {
  return "http://maps.google.com/staticmap?&zoom="+zoom+"&size=512x512&maptype="+tipoMapa+"&markers="+latitud+","+longitud+",blueo|"+latitud2+","+longitud2+",yellowd&key=MAPS_API_KEY&sensor=true_or_false&path=rgba:0xff0000ff,weight:5|"+latitud+","+longitud+"|"+latitud2+","+longitud2+";interface="+this.ired;        
 }
 
 private void conseguirMapa(String URL)
 {
   HttpConnection conn = null;
   InputStream input = null;
   this.buffer = null;
      
      try {
        conn = (HttpConnection)Connector.open(URL);
        conn.setRequestMethod(HttpConnection.GET);
        input = conn.openInputStream();
        int largo = (int)conn.getLength();
        
         if (largo > 0)
         {
          buffer = new byte[largo];    
          input.read(buffer);
         }
         
        } catch(Exception e) {
            System.out.print("Error 1:"+e.toString());
        
        }
    
        try {
            if (conn != null)
            {
             conn.close();
            }
            if (input != null)
            {
             input.close();  
            }
        } catch (Exception e) {
        
            System.out.print("Error 2:"+e.toString());
 
        }      
     
  }
  
public void setZoom(int zoom)
{
 this.zoom = zoom;   
}

public void setTipoMapa(String tipoMapa)
{
 this.tipoMapa = tipoMapa;   
}
   
} 
