/*
 * MapaTransf.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */


import net.rim.device.api.system.EncodedImage;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.ui.component.*;
import javax.microedition.io.file.FileConnection;
import javax.microedition.io.*;
import java.lang.Thread;
import java.io.*;


class MapaTransf extends Thread
{
  private FileConnection fc;
  private PopupScreen popup;
  private boolean pushBoton;
  private DialogFieldManager manager;
  private ButtonField guardar;
  private ButtonField cancelar;
  private EditField inputField; 
  private EncodedImage imagen;
  private String nombre = "";
   
   
  public MapaTransf(EncodedImage imagen) 
  {    
   this.imagen = imagen;
   
   DialogFieldManager manager = new DialogFieldManager();
   popup = new PopupScreen(manager);
   manager.addCustomField(new LabelField("Guardar el mapa"));
   guardar = new ButtonField("Guardar");
   cancelar = new ButtonField("Cancelar");
   inputField = new EditField("Nombre: ", "");
   manager.addCustomField(inputField);
   manager.addCustomField(new SeparatorField());
   manager.addCustomField(guardar);
   manager.addCustomField(cancelar);
   guardar.setChangeListener(listener);
   cancelar.setChangeListener(listener2);
   
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
        
      try {
            fc = (FileConnection)Connector.open("file:///store/home/user/pictures/"+nombre+".png",Connector.READ_WRITE);
      } catch (Exception e) {
       
      }
    
    if(!nombre.equals(""))
    {
        try {
            if(!fc.exists())
            {
             fc.create();
            }             
           
            OutputStream oStream = fc.openDataOutputStream();
            oStream.write(imagen.getData());
            oStream.flush();
            oStream.close(); 
            fc.close();
            
            UiApplication.getUiApplication().invokeLater(new Runnable() {
               public void run() {
                   
                    Dialog.alert(nombre+" Guardado en el dispositivo"); 
               }
            }); 
 
          } catch(Exception e) {
                
             UiApplication.getUiApplication().invokeLater(new Runnable() {
               public void run() {
                   
                    Dialog.alert("No se pudo guardar el mapa."); 
               }
            });
             
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

}
   
     
   FieldChangeListener listener = new FieldChangeListener()   {
         public void fieldChanged(Field field, int context) {
             
             if(!inputField.getText().equals(""))
             {
                pushBoton = true;
                nombre = inputField.getText().trim();   
             }
         }
     };
 
  FieldChangeListener listener2 = new FieldChangeListener() {
         public void fieldChanged(Field field, int context) {
             pushBoton = true;
             
          }
     };
 
} 
