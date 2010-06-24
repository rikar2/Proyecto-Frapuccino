/*
 * SUG.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;

class SUG extends MainScreen
{
 private CoordenadasGPS coord;

 
 public SUG()
 {
   LabelField title = new LabelField("Frapuccino 0.5 Geográfica", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);     
   setTitle(title);
   coord = new CoordenadasGPS();
   coord.start();
 }
 
 private MenuItem buscar = new MenuItem("Búsqueda", 1, 2) 
 {
  Busqueda busc;
    public void run() 
    {   
     busc = new Busqueda();
    }
 };
 
 private MenuItem ubicacion = new MenuItem("¿Donde estoy?", 2, 2) 
 {
  UbicacionUsuario ubic;
    public void run() 
    {
     ubic = new UbicacionUsuario(coord);
    }
 };
 
 private MenuItem trazar = new MenuItem("Trazar dos lugares", 3, 2) 
 {
  TrazadoDosLugares traz;
    public void run() 
    {
     traz = new TrazadoDosLugares();         
    }
 };
 
 private MenuItem conf = new MenuItem("Configuración", 5, 2) 
 {
    public void run() 
    {
     UiApplication.getUiApplication().pushScreen(new InterfazRedUI());     
    }
 };
 
 private MenuItem salir = new MenuItem("Salir", 6, 2) 
 {
    public void run() 
    {
     UiApplication uiapp = UiApplication.getUiApplication();
     uiapp.popScreen(uiapp.getActiveScreen());  
    }
 };
 
 protected void makeMenu(Menu menu, int context)
 {
  menu.add(buscar);
  menu.add(ubicacion);
  menu.add(trazar);   
  menu.add(MenuItem.separator(4));   
  menu.add(conf);
  menu.add(salir);
 }     
} 
