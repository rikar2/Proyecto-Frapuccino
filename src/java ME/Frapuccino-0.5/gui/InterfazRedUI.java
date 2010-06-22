/*
 * InterfazRedUI.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;


class InterfazRedUI extends MainScreen
{
 private static String inter[] = { "DEFAULT", "TCP-IP", "BES", "BIS", "WIFI" }; 
 private ObjectChoiceField selec = new ObjectChoiceField("Tipo de Conexión : ", this.inter , 0);
 private InterfazRed interfaz = new InterfazRed();
 private int index = 0;
 private SaveMenuItem _saveMenuItem;  
  
 public InterfazRedUI()
 {
   this.index = interfaz.getIndexRed();
   selec.setSelectedIndex(this.index);
   
   LabelField title = new LabelField("Configuración de la conexión.", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
   setTitle(title);
   add(selec);
 }
 
  private MenuItem cambios = new MenuItem("Guardar cambios", 1, 2) 
 {
    public void run() 
    {
     index = selec.getSelectedIndex(); 
     interfaz.setIndexRed(index);
     UiApplication uiapp = UiApplication.getUiApplication();
     uiapp.popScreen(uiapp.getActiveScreen());
    }
 };
 
 private MenuItem closeItem = new MenuItem("Cerrar", 3, 2) 
 {
    public void run() {
       if ((Dialog.ask(Dialog.D_YES_NO, "¿Desea Salir?", Dialog.NO)) == Dialog.YES)
       {
        UiApplication uiapp = UiApplication.getUiApplication();
        uiapp.popScreen(uiapp.getActiveScreen());   
       }
    }
};    
 
 protected void makeMenu(Menu menu, int context)
 {
  menu.add(cambios);   
  menu.add(MenuItem.separator(2));   
  menu.add(closeItem);
 }
 
 private class SaveMenuItem extends MenuItem
 {
    private SaveMenuItem()
    {
       super("Guardar" , 1, 2);
    }
        
    public void run()
    {            
     if (onSave())
     {
        UiApplication uiapp = UiApplication.getUiApplication();
        uiapp.popScreen(uiapp.getActiveScreen());
     }
    }
  }
  
 protected boolean onSave()
 {
  index = selec.getSelectedIndex(); 
  interfaz.setIndexRed(index);
  
  return true; 
 }      
     
} 
