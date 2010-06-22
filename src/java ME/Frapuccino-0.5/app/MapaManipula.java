/*
 * MapaManipula.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */



import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;
import java.lang.Thread;


class MapaManipula extends Thread
{
 private PopupScreen popup;
 private String[] coordenadas = {"", "", "", ""};
 private static String[] choice = {"mobil", "hybrid", "roadmap", "satellite", "terrain"};
 private Mapa mapa;
 private int zoom;
 private String tipoMapa, tipo;
 private boolean pushBoton;
 private DialogFieldManager manager;
 private ButtonField cambiar;
 private ButtonField cancelar;
 private ObjectChoiceField tipos;
 private NumericChoiceField niveles; 
 private MapaUI mapaui; 
  
    public MapaManipula(String[] coordenadas, int zoom, String tipoMapa, MapaUI mapaui) 
    { 
     this.mapaui = mapaui;   
     this.coordenadas = coordenadas;
     this.zoom = zoom;
     this.tipoMapa = tipoMapa;
     }
    
    
    public void crearPopup(String tipo)
    {
     manager = new DialogFieldManager();
     popup = new PopupScreen(manager);
     this.tipo = tipo;
  
        if(tipo.equals("zoom"))
        {
            crearPopupZoom();
        }
        else
        {
            crearPopupTipoMapa();
        }      
        
    }
    
    private void crearPopupZoom()
    {
      manager.addCustomField(new LabelField("Nivel de Zoom"));
      niveles = new NumericChoiceField("Niveles de Zoom",0 ,20, 1);
      niveles.setSelectedValue(this.zoom); 
      cambiar = new ButtonField("Cambiar ");
      cancelar = new ButtonField("Cancelar");
      manager.addCustomField(niveles);
      manager.addCustomField(new SeparatorField());
      manager.addCustomField(cambiar);
      manager.addCustomField(cancelar);
      cambiar.setChangeListener(listener);
      cancelar.setChangeListener(listener2);
    }
    
    private void crearPopupTipoMapa()
    {
      manager.addCustomField(new LabelField("Tipo de mapas"));
      tipos = new ObjectChoiceField("Seleccione el tipo", choice, getIndexChoice());
      cambiar = new ButtonField("Cambiar");
      cancelar = new ButtonField("Cancelar");
      manager.addCustomField(tipos);
      manager.addCustomField(new SeparatorField());
      manager.addCustomField(cambiar);
      manager.addCustomField(cancelar);
      cambiar.setChangeListener(listener);  
      cancelar.setChangeListener(listener2);
    }
    
    private int getIndexChoice()
    {
        for(int i = 0; i < choice.length ; i++)
        {
            if(choice[i].equals(this.tipoMapa))
            {
             return i;   
            }   
        }
        
      return 0;  
    }
    
    public void run()
    {
     pushBoton = false;
       
      UiApplication.getUiApplication().invokeLater(new Runnable() {
        public void run() {
                  try {
                      UiApplication.getUiApplication().pushScreen(popup);
                   } catch (Exception e) {
                            
                   }
        }
      });
       
        while(!pushBoton)
        {
            try {
               Thread.sleep(100); 
            } catch (Exception e) {
            
                
            }
            
        }
        
     if (popup.isDisplayed()) {
            UiApplication.getUiApplication().invokeLater(new Runnable() {
               public void run() {
                  try {
                       UiApplication.getUiApplication().popScreen(popup);
                   } catch (Exception e){
                                    
                                    
                   }
                             
                 
                 }
            });
       }
   
   
    if(tipo.equals("zoom"))
    {
      int nivel;
      mapa = new Mapa(coordenadas, mapaui);
      nivel = niveles.getSelectedValue();
      mapa.setZoom(nivel);
      mapa.setTipoMapa(tipoMapa);
      mapa.mostrarMapa();
    }
    else 
        if(tipo.equals("tipo"))
        {
         String tipoSelec = choice[tipos.getSelectedIndex()]; 
         mapa = new Mapa(coordenadas, mapaui); 
         mapa.setZoom(zoom);
         mapa.setTipoMapa(tipoSelec);
         mapa.mostrarMapa();
        }
                 
  }  
   
 FieldChangeListener listener = new FieldChangeListener()   {
         public void fieldChanged(Field field, int context) {
             pushBoton = true;   
          }
     };
 
  FieldChangeListener listener2 = new FieldChangeListener() {
         public void fieldChanged(Field field, int context) {
             pushBoton = true;
             tipo = "";  
          }
     };
   
 }   
    
  
