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


class Busqueda extends MainScreen 
{
 public Busqueda()
 {
  LabelField title = new LabelField("Frapuccino v1.0 Búsqueda de lugares", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
  setTitle(title);  
  run(); 
 }
 
 public void run()
 {  
        boolean flag = true;
        String lugar = "";
        do {
            String[] selections = {"Buscar","Cancelar"};            
            Dialog addDialog = new Dialog("Búsqueda de lugares", selections, null, 0, null);           
            EditField inputField = new EditField("Buscar : ","");            
            addDialog.add(inputField);           
            
            if(addDialog.doModal() == 0) // User selected "Add".
            {
                if(!(inputField.getText().equals("")))
                {
                    flag = false;
                    lugar = inputField.getText().toString();
                    ConversionCoordenadas conv = new ConversionCoordenadas(lugar);
                    
                }
            } 
            else
            {
          
                 flag = false;
            }
     
        } while ( flag );
  }
 
} 
