/*
 * TrazadoDosLugares.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */


import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;
import java.lang.Thread;


class TrazadoDosLugares 
{
 private TrazadoDosLugaresIngresar traz;
 
 public TrazadoDosLugares()
 {    
  traz = new TrazadoDosLugaresIngresar();
  traz.start();    
 }
} 

class TrazadoDosLugaresIngresar extends Thread
{
 private boolean noHayTexto;
 private boolean cancelo;
 private String lugar = "", lugar2 = "";  
 private PopupScreen popup;
 private EditField inputField;
 private EditField inputField2;
 private ConversionCoordenadas conv;
 private ButtonField buscar = new ButtonField("Buscar");
 private ButtonField cancelar = new ButtonField("Cancelar");
  
  public TrazadoDosLugaresIngresar()
  {
   DialogFieldManager manager = new DialogFieldManager();
   popup = new PopupScreen(manager);
   inputField = new EditField("Origen: ", "");
   inputField2 = new EditField("Destino: ","");
   manager.addCustomField(new LabelField("Buscar Lugares"));
   manager.addCustomField(inputField);
   manager.addCustomField(inputField2);
   buscar.setChangeListener(listener);
   cancelar.setChangeListener(listener2);
   manager.addCustomField(new SeparatorField());
   manager.addCustomField(buscar); 
   manager.addCustomField(cancelar);  
  }
  
  public void run()
  {  
    noHayTexto = true;
    cancelo = false;
 
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
  
  if(!cancelo)
  {    
    conv = new ConversionCoordenadas(lugar, lugar2);
    conv.pushCoordenadasEnMapa();
  }
 }
 
 FieldChangeListener listener = new FieldChangeListener() {
       public void fieldChanged(Field field, int context) {
               if(!(inputField.getText().equals("")) && !(inputField2.getText().equals("")))
               {
                 lugar = inputField.getText().toString();
                 lugar2 = inputField2.getText().toString(); 
                 noHayTexto = false;
               }
          }
   };
   
  FieldChangeListener listener2 = new FieldChangeListener() {
         public void fieldChanged(Field field, int context) {
  
                    cancelo = true;
                    noHayTexto = false;  
                    
          }
  };       
   
         
}
