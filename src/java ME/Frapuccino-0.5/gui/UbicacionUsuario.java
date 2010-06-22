/*
 * UbicacionUsuario.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;

class UbicacionUsuario 
{
 private ProgressBar pro;
 private BuscarCoordenadas busc;
 
 public UbicacionUsuario(CoordenadasGPS coord)
 {
   pro = new ProgressBar("Buscando satélites. Espere...", 100, 100);
   pro.start();
   busc = new BuscarCoordenadas(coord, pro);
   busc.start(); 
 }
  
}

class BuscarCoordenadas extends Thread
{
  private String[] coordenadas = {"", "", "", ""};
  private Mapa mapa;
  private boolean flag = true;
  private CoordenadasGPS coord;
  private ProgressBar pro;
 
    public BuscarCoordenadas(CoordenadasGPS coord, ProgressBar pro)
    {
      this.coord = coord;
      this.pro = pro;  
    }
    
    public void run()
    {
         do {
            if(!(coord.getLatitud() == 0.0 && coord.getLongitud() == 0.0))
            {  
             // pro.setIncremento(100)
             pro.detenerElHilo();
             coordenadas[0] = coord.getLatitud()+"";
             coordenadas[1] = coord.getLongitud()+"";
             flag = false; 
             mapa = new Mapa(coordenadas);
             mapa.mostrarMapa();          
            }
        } while ( flag );
        
     this.flag = true;
    }
    
}

