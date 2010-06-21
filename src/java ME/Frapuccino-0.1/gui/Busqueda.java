/*
 * Busqueda.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;
import java.lang.Thread;



class Busqueda extends MainScreen 
{
 private BusquedaIngreso busc;
 
 public Busqueda()
 {           
  busc = new BusquedaIngreso();
  busc.start();  
 }
 
}

class BusquedaIngreso extends Thread
{
 private boolean noHayTexto;
 private String lugar = "";  
 private PopupScreen popup;
 private EditField inputField;
 private ConversionCoordenadas conv;
 private ButtonField buscar = new ButtonField("Buscar");
 private LabelField label = new LabelField("");
 
 public BusquedaIngreso()
 {
     DialogFieldManager manager = new DialogFieldManager();
     popup = new PopupScreen(manager);
     inputField = new EditField("Lugar: ", "");
     manager.addCustomField(new LabelField("Buscar Lugares"));
     manager.addCustomField(inputField);
     buscar.setChangeListener(listener);
     manager.addCustomField(new SeparatorField());
     manager.addCustomField(buscar);
     manager.addCustomField(label);
   
 }
 
 public void run()
 {
    noHayTexto = true;
 
    UiApplication.getUiApplication().invokeLater(new Runnable() {
        public void run() {
                  try {
                      UiApplication.getUiApplication().pushScreen(popup);
                   } catch (Exception e) {
                            
                   }
        }
     });
                
                
      while(noHayTexto)
      {
       
        try {  
            Thread.sleep(100);
        } catch (Exception e) {
        
        }
        /*
        lab = label.getText();
        largo = lab.length();
        
         switch(largo)
         {
          case 0:
                label.setText(".");
                break;
          case 1:
                 label.setText(".");
                break;
  
          case 2:
                 label.setText(".");
                 break;
            
          case 3:
                 label.setText("");
         }*/
      
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
   
   conv = new ConversionCoordenadas(lugar);
   
  }
  
    FieldChangeListener listener = new FieldChangeListener() {
         public void fieldChanged(Field field, int context) {
             ButtonField buscar = (ButtonField) field;
             UiApplication.getUiApplication().invokeLater(new Runnable() {
                          public void run() {
                              
                              if(!(inputField.getText().equals("")))
                              {
                                lugar = inputField.getText().toString(); 
                                 noHayTexto = false;
                   
                              }
                          }
               });
         }
    };
 }
 


 
 
