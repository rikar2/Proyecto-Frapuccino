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


class TrazadoDosLugares extends MainScreen 
{
 public TrazadoDosLugares()
 {    
  LabelField title = new LabelField("Frapuccino v1.0 Búsqueda de lugares", LabelField.ELLIPSIS | LabelField.USE_ALL_WIDTH);
  setTitle(title);
  run();
 }
 
public void run() {
       
  boolean flag;
  String lugar;
  String lugar2;
  flag = true;
  
       do {
           
            String[] selections = {"Buscar","Cancelar"};            
            Dialog addDialog = new Dialog("¿Como llegar? ", selections, null, 0, null);           
            EditField inputField = new EditField("Desde : ","");
            EditField inputField2 = new EditField("Hasta : " ,"");            
            addDialog.add(inputField);          
            addDialog.add(inputField2); 
            
            if(addDialog.doModal() == 0) // User selected "Add".
            {
                 if(!(inputField.getText().equals("")) && !(inputField.getText().equals("")))
                 {
                    flag = false;
                    lugar = inputField.getText();
                    lugar2 = inputField2.getText();
                    ConversionCoordenadas conv = new ConversionCoordenadas(lugar, lugar2);
                 }    
            }
            else
            {
                flag = false;
            }
            
        } while (flag);
 }

} 
