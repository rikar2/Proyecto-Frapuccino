/*
 * ConversionCoordenadas.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.UiApplication;
import javax.microedition.io.*;
import java.io.*;

class ConversionCoordenadas 
{
 private String[] coordenadas = {"", "", "", ""};
 private String[] lugares = {"", ""};
 private ConseguirCoordenadas cons;  
 private Mapa mapa;
 private int zoom;
 
 public ConversionCoordenadas(String lugar)
 {
  lugares[0] = lugar;
  cons = new ConseguirCoordenadas();
  crearNombre();
  this.zoom = 14;
 }
 
 public ConversionCoordenadas(String lugar, String lugar2)
 {
  lugares[0] = lugar;
  lugares[1] = lugar2;
  cons = new ConseguirCoordenadas(); 
  crearNombre();
  this.zoom = 10;
 }
 
 public void pushCoordenadasEnMapa()
 {
  mapa = new Mapa(coordenadas);
  mapa.setZoom(this.zoom);
  mapa.mostrarMapa();
 }
 
 private void crearNombre()
 {  
    String adress = "";
    String adress1 = "";
    int x = 0;
    
    while(x < this.lugares.length)
    {
     adress = this.lugares[x];
      for(int i = 0; i < adress.trim().length(); i++) 
      {
       if(adress.trim().charAt(i) == ' ')
       {
        adress1 += "%";
       }
       else
       {
        adress1 += adress.trim().charAt(i);
       }
      }
      this.lugares[x] = adress1;
      x++;
      adress1 = "";
      adress = "";
     }
    
    cons.setNombreLugar(lugares);
    cons.start();
    
    try {
        cons.join();   
    } catch (Exception e){}
    
    this.lugares = cons.getCoordenadas();
    ordenarSepararCoordenadas();
 }
 
 
 private void ordenarSepararCoordenadas()
 {
   int x = 0;
   int cont = 0;
   int contadorComas = 0;
   String coord = "";
   
     while(( x < this.lugares.length ) && !(this.lugares[x].equals("")))
     {
       coord = this.lugares[x];
        for(int i = 0; i < coord.length() ; i++)
        {   
         if( contadorComas == 2 && coord.charAt(i) != ',' )
         {
           this.coordenadas[cont] += coord.charAt(i);         
          }
           else
             if( contadorComas == 3 && coord.charAt(i) != ',' )
             {
               this.coordenadas[cont + 1] += coord.charAt(i);   
             }      
          
           if(coord.charAt(i) == ',')
           {
            contadorComas++;
            } 
          }
        
       x++;
       cont = x * 2;
       coord = "";
       contadorComas = 0;
     }     
 }
 
 /*public String[] getCoordenadasL()
 {
  return this.coordenadas;
 }*/
}
  
  
class ConseguirCoordenadas extends Thread
{
 private HttpConnection conn = null;
 private InputStream input = null;
 private String ired = "";
 private String[] lugares = {"", ""};
 private InterfazRed interfazRed = new InterfazRed();
 
 public ConseguirCoordenadas()
 {
  this.ired = interfazRed.getInterfazRed();
 }
 
 public void run()
 {
  int x = 0; 
   
   while(( x < this.lugares.length ) && !(this.lugares[x].equals("")))
   {
    conn = null;
    input = null;
     
     try {
        conn = (HttpConnection)Connector.open("http://maps.google.com/maps/geo?q="+this.lugares[x]+"&output=csv&;interface="+this.ired);
        conn.setRequestMethod(HttpConnection.POST);
        input = conn.openInputStream();
     
        byte[] data = new byte[256];
        int len = 0;
        
        StringBuffer raw = new StringBuffer();
    
        while ( -1 != (len = input.read(data)) ) {
            raw.append(new String(data, 0, len));
        }
    
        this.lugares[x] = raw.toString();
           
      } catch (Exception e) {
        
            UiApplication.getUiApplication().invokeLater(new Runnable() {
                public void run() {
                    Dialog.alert("No hay conexión a internet");
                }
            });    
      }
   
    x++;
   }    
  x = 0;
 }   

 public void setNombreLugar(String[] lugares)
 {
  this.lugares = lugares;
 }
 
 public String[] getCoordenadas()
 {
  return this.lugares;  
 } 
}


 
