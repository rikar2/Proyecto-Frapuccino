/*
 * MapaUI.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */
    
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;

class MapaUI extends MainScreen 
{
 private BitmapField field = new BitmapField();
 private EncodedImage imagen;
 private int zoom = 0;
 private String tipoMapa = "";
 private String[] coordenadas = {"", "", "", ""};
 private MapaManipula mapam;
 private MapaTransf mapatransf;
 private MapaUI mapaUI;
 
  public MapaUI(byte[] buffer, String[] coordenadas, String tipoMapa, int zoom)
  {
   this.mapaUI = this;
   this.zoom = zoom;
   this.tipoMapa = tipoMapa;
   this.coordenadas = coordenadas;
   mapam = new MapaManipula(this.coordenadas);

   LabelField title = new LabelField("Frapuccino v1.0 Mapa", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
   setTitle(title);
   imagen = null;
   
        try {
            imagen = EncodedImage.createEncodedImage(buffer, 0, buffer.length);
            this.field.setImage(this.imagen);
             
        } catch (Exception e) {
             System.out.println("Error --> "+e.toString());
        }  
  
    add(this.field);
  
  }
  
  
   private MenuItem hibrida = new MenuItem("Vista híbrida", 1, 2) 
 {
    public void run() 
    {
     UiApplication uiapp = UiApplication.getUiApplication();
     uiapp.popScreen(uiapp.getActiveScreen());
     
     UiApplication.getUiApplication().invokeLater(new Runnable() {
                          public void run() {
                             try {
                               
                               UiEngine eng = Ui.getUiEngine();
                               eng.popScreen(mapaUI);   
                               
                            } catch (Exception e) {
                                System.out.println("--->"+e);
                            }
                          }
     });
     
     
     mapam.nuevoMapa(); 
     mapam.mantenerNivelZoom(zoom);    
     mapam.cambiarTipoMapa(tipoMapa);
    }
 };
 
 private MenuItem mapa = new MenuItem("Vista de mapa", 1, 2) 
 {
    public void run() 
    {
     UiApplication uiapp = UiApplication.getUiApplication();
     uiapp.popScreen(uiapp.getActiveScreen());
     mapam.nuevoMapa(); 
     mapam.mantenerNivelZoom(zoom); 
     mapam.cambiarTipoMapa(tipoMapa);
    }
 };
 
 private MenuItem acercar = new MenuItem("Acercar", 3, 2) 
 {
    public void run() 
    {
     UiApplication uiapp = UiApplication.getUiApplication();
     uiapp.popScreen(uiapp.getActiveScreen());
     mapam.nuevoMapa();
     mapam.mantenerTipoMapa(tipoMapa);  
     mapam.acercarMapa(zoom);
    }
 };
 
 private MenuItem alejar = new MenuItem("Alejar", 4, 2) 
 {
    public void run() 
    {
     UiApplication uiapp = UiApplication.getUiApplication();
     uiapp.popScreen(uiapp.getActiveScreen());
     mapam.nuevoMapa();
     mapam.mantenerTipoMapa(tipoMapa);    
     mapam.alejarMapa(zoom);
    }
 };
 
 private MenuItem guardar = new MenuItem("Guardar mapa", 6, 2) 
 {
    public void run() 
    {
      boolean flag;
        String lugar;
        do {
            flag = true;
            String[] selections = {"Guardar","Cancelar"};            
            Dialog addDialog = new Dialog("Guardar el mapa", selections, null, 0, null);           
            EditField inputField = new EditField("Nombre : ","");            
            addDialog.add(inputField);           
            add(new SeparatorField());
            
            if(addDialog.doModal() == 0) // User selected "Add".
            {
                if(!(inputField.getText().equals("")))
                {
                   mapatransf = new MapaTransf();
                   mapatransf.guardarMapa(imagen, inputField.getText().trim());
                   UiApplication uiapp = UiApplication.getUiApplication();
                   uiapp.popScreen(uiapp.getActiveScreen()); 
                }
            } 
            else
            {
          
                 flag = false;
            }
     
        } while ( flag );  
   
    }
 };
 
  private MenuItem enviarb = new MenuItem("Enviar por Bluetooth", 7, 2) 
 {
    public void run() 
    {
       //en contrucción
    }
 };
 
 private MenuItem enviars = new MenuItem("Enviar por MMS", 8, 2) 
 {
    public void run() 
    {
     //en contrucción
    }
 };
 
  private MenuItem publicar = new MenuItem("Publicar en Facebook", 10, 2) 
 {
    public void run() 
    {
    
    }
 };
 
 private MenuItem volver = new MenuItem("Volver al menú", 12, 2) 
 {
    public void run() 
    {
     UiApplication uiapp = UiApplication.getUiApplication();
     uiapp.popScreen(uiapp.getActiveScreen());  
    }
 };
 
 private MenuItem salir = new MenuItem("Salir", 13, 2) 
 {
    public void run() 
    {
     UiApplication uiapp = UiApplication.getUiApplication();
     uiapp.popScreen(uiapp.getActiveScreen());  
    }
 };
 
 
 
 protected void makeMenu(Menu menu, int context)
 {
  if(this.tipoMapa.equals("mobil"))
  {
    menu.add(hibrida);
  }
  else 
  {
    menu.add(mapa);
  }
  
  menu.add(MenuItem.separator(2));
  menu.add(acercar); 
  menu.add(alejar);  
  menu.add(MenuItem.separator(5));
  menu.add(guardar);
  menu.add(enviarb);
  menu.add(enviars);
  menu.add(MenuItem.separator(9));
  menu.add(publicar);
  menu.add(MenuItem.separator(11));
  menu.add(volver);
  menu.add(salir);
 }     
  
} 
