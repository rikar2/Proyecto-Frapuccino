/*
 * UbicacionUsuario.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;


class UbicacionUsuario extends MainScreen  
{
 private CoordenadasGPS coord = new CoordenadasGPS();
 private String latitud = "";
 private String longitud = "";
 private String altitud = "";
 private Mapa mapa;
  
 public UbicacionUsuario()
 {    
  LabelField title = new LabelField("Frapuccino v1.0 Mi ubicación", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
  setTitle(title);
  this.latitud = coord.getLatitud()+"";
  this.longitud = coord.getLongitud()+"";
  System.out.println(this.latitud);
  System.out.println(this.longitud);
  mapa = new Mapa(latitud, longitud);
    
 }
} 
