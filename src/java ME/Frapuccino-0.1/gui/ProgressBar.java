/*
 * ProgressBar.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

/**
 * Defines ProgressBar object. Creates popup screen with title, and perpetually updating
 * progress gauge. Instantiate and run as thread to start progress update. Call
 * remove() method when finished to remove popup screen and shutdown thread.
 */
//package mrm.util;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.container.DialogFieldManager;
import net.rim.device.api.ui.container.PopupScreen;

public class ProgressBar extends Thread {

  private int maximum, timeout;
  private boolean detener;
  private PopupScreen popup;
  private GaugeField gaugeField;
  private ButtonField cancelar = new ButtonField("Cancelar");

   public ProgressBar(String title, int maximum, int timeout) {
           this.maximum = maximum;
           this.timeout = timeout;
           this.detener = false;

           DialogFieldManager manager = new DialogFieldManager();

           popup = new PopupScreen(manager);
           gaugeField = new GaugeField(null, 1, maximum, 1, GaugeField.NO_TEXT);

         manager.addCustomField(new LabelField(title));
         manager.addCustomField(gaugeField);
         cancelar.setChangeListener(listener);
         manager.addCustomField(cancelar);
    }

     /**
     * run() method for starting thread
     */

   public void run() {
            

                UiApplication.getUiApplication().invokeLater(new Runnable() {
                  public void run() {
                            UiApplication.getUiApplication().pushScreen(popup);
                    }
                });

           int iterations = 0;

           while (!detener) {
                       try {
                          Thread.sleep(timeout);
                 } catch (Exception e) {
                        }

                     if (++iterations > maximum)
                            iterations = 1;

                       gaugeField.setValue(iterations);
            
               }

             if (popup.isDisplayed()) {
                     UiApplication.getUiApplication().invokeLater(new Runnable() {
                          public void run() {
                                try {
                                    UiApplication.getUiApplication().popScreen(popup);
                                } catch (Exception e){
                                    
                                    System.out.println("popScreen -->"+e);
                                }
                             
                             }
                      });
            }
      
    }
     /**
     * Method to shutdown the thread and remove the popup screen
    *  
    */

   public void detenerElHilo() 
   {
     this.detener = true;
   }
   
    FieldChangeListener listener = new FieldChangeListener() {
         public void fieldChanged(Field field, int context) {
             ButtonField cancelar = (ButtonField) field;
             UiApplication.getUiApplication().invokeLater(new Runnable() {
                          public void run() {
                                UiApplication uiapp = UiApplication.getUiApplication();
                                uiapp.popScreen(uiapp.getActiveScreen());
                          }
            });
         }
    };

}
 


 
