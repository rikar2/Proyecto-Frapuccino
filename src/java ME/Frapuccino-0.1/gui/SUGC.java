/*
 * SUGC.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;

class SUGC extends MainScreen implements FieldChangeListener 
{
 private ButtonField geografic = new ButtonField("Geografic");
 private ButtonField comercial = new ButtonField("Comercial");
 
 
 public SUGC()
 {
   super();   
   LabelField title = new LabelField("Frapuccino v1.0", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);  
   setTitle(title);
   add(geografic);
   add(comercial);
   
   geografic.setChangeListener(this);
   comercial.setChangeListener(this);
 
 }
 
 public void fieldChanged(Field field, int context)
 {
   ButtonField btn = (ButtonField) field;
   
    if(btn.getLabel().equals("Geografic"))
    {
      UiApplication.getUiApplication().pushScreen(new SUG());
    }
    else
    
    { 
    
    }
   
 }
 
 private MenuItem conf = new MenuItem("Configuración", 1, 2) 
 {
    public void run() 
    {
     UiApplication.getUiApplication().pushScreen(new InterfazRedUI());     
    }
 };
 
 private MenuItem closeItem = new MenuItem("Cerrar", 3, 2) 
 {
    public void run() {
       if ((Dialog.ask(Dialog.D_YES_NO, "¿Desea Salir?", Dialog.NO)) == Dialog.YES)
       {
        onClose();
       }
    }
};    
 
 protected void makeMenu(Menu menu, int context)
 {
  menu.add(conf);   
  menu.add(MenuItem.separator(2));   
  menu.add(closeItem);
 }     
} 
