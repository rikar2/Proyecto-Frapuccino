/*
 * MapaUI.java
 *
 * � <your company here>, 2003-2008
 * Confidential and proprietary.
 */
    
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;



class MapaUI extends MainScreen 
{
 private int zoom = 0;
 private String tipoMapa = "";
 private String[] coordenadas = {"", "", "", ""};
 private MapaManipula mapam;
 private MapaTransf mapatransf;
 private MapaUI mapaui;
 private EncodedImage imagen;
 private static final int HORZ_SCROLL_FACTOR = 8;
 private static final int VERT_SCROLL_FACTOR  = 8;
 Bitmap bitmap;
 int left = 0;
 int top = 0;
 int maxLeft = 0;
 int maxTop = 0;

 
  public MapaUI(byte[] buffer, String[] coordenadas, String tipoMapa, int zoom)
  {
   this.mapaui = this;
   this.zoom = zoom;
   this.tipoMapa = tipoMapa;
   this.coordenadas = coordenadas;
   imagen = null;
   
        try {
             imagen = EncodedImage.createEncodedImage(buffer, 0, buffer.length);
             bitmap = imagen.getBitmap();
            // BitmapField bitmapField = new BitmapField(imagen.getBitmap(), FIELD_HCENTER | FOCUSABLE);
        } catch (Exception e) {
             System.out.println("Error --> "+e.toString());
        }
        
        if (bitmap.getWidth() > Graphics.getScreenWidth()) {
                           maxLeft = bitmap.getWidth() - Graphics.getScreenWidth();
        }
                      
        if (bitmap.getHeight() > Graphics.getScreenHeight()) {
                         maxTop = bitmap.getHeight() - Graphics.getScreenHeight();
        }  
  
     LabelField title = new LabelField("Frapuccino v1.0 Mapa", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
     setTitle(title);
     //add(bitmapField);
   }
   
 
 private MenuItem TipoMapa = new MenuItem("Tipos de mapa", 1, 2) 
 {
    public void run() 
    {
      mapam = new MapaManipula(coordenadas, zoom, tipoMapa, mapaui); 
      mapam.crearPopup("tipo");
      mapam.start();
    }
 };
 
 private MenuItem Zoom = new MenuItem("Zoom", 2, 2) 
 {
    public void run() 
    {
      mapam = new MapaManipula(coordenadas, zoom, tipoMapa, mapaui); 
      mapam.crearPopup("zoom");
      mapam.start(); 
    }
 };
 
 
 
 private MenuItem guardar = new MenuItem("Guardar mapa", 4, 2) 
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
 
  private MenuItem enviarb = new MenuItem("Enviar por Bluetooth", 5, 2) 
 {
    public void run() 
    {
       //en contrucci�n
    }
 };
 
 private MenuItem enviars = new MenuItem("Enviar por MMS", 6, 2) 
 {
    public void run() 
    {
     //en contrucci�n
    }
 };
 
  private MenuItem publicar = new MenuItem("Publicar en Facebook", 8, 2) 
 {
    public void run() 
    {
    
    }
 };
 
 private MenuItem volver = new MenuItem("Volver al men�", 10, 2) 
 {
    public void run() 
    {
     UiApplication uiapp = UiApplication.getUiApplication();
     uiapp.popScreen(uiapp.getActiveScreen());  
    }
 };
 
 private MenuItem salir = new MenuItem("Salir", 11, 2) 
 {
    public void run() 
    {
     UiApplication uiapp = UiApplication.getUiApplication();
     uiapp.popScreen(uiapp.getActiveScreen());  
    }
 };
 
 
 
 protected void makeMenu(Menu menu, int context)
 {
 
  menu.add(TipoMapa);
  menu.add(Zoom);
  menu.add(MenuItem.separator(3)); 
  menu.add(guardar);
  menu.add(enviarb);
  menu.add(enviars);  
  menu.add(MenuItem.separator(7));
  menu.add(publicar);
  menu.add(MenuItem.separator(9));
  menu.add(volver);
  menu.add(salir);
  
 }
 
 
 protected void paint(Graphics graphics) 
 {          
   if (bitmap != null) 
   {
        graphics.drawBitmap(0, 0, Graphics.getScreenWidth(), Graphics.getScreenHeight(), bitmap, left, top);
   }
 }
              
 protected boolean navigationMovement(int dx, int dy, int status, int time) 
 {  
 
    left += (dx * HORZ_SCROLL_FACTOR);
    top += (dy * VERT_SCROLL_FACTOR);
    
    if (left < 0) left = 0;
        if (top < 0) top = 0;
            if (left > maxLeft) left = maxLeft;
                if (top > maxTop) top = maxTop;
                       invalidate();

  return true;
 }
}  
 
 //////////// Para API 5.0.0   
 /*
 protected boolean navigationClick(int status, int time)
        {
            // Push a new ZoomScreen if track ball or screen is clicked
            UiApplication.getUiApplication().pushScreen(new ZoomScreen(                           
            return true;
        }
  
} */


