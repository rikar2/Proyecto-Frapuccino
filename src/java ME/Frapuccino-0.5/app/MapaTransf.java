/*
 * MapaTransf.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

import javax.microedition.io.file.FileConnection;
import javax.microedition.io.*;
import net.rim.device.api.system.EncodedImage;
import java.io.*;


class MapaTransf 
{
  private FileConnection fc;
   
  public MapaTransf() 
  {    
    
  }
  
  
public void guardarMapa(EncodedImage imagen, String nombre)
{
    try {
        fc = (FileConnection)Connector.open("file:///store/home/user/pictures/"+nombre+".png",Connector.READ_WRITE);
        
        if(!fc.exists())
        {
         fc.create();
        }          
         
         OutputStream oStream = fc.openDataOutputStream();
         oStream.write(imagen.getData());
         oStream.flush();
         oStream.close(); 
         fc.close(); 
        
    } catch(Exception e) {
    
        System.out.println("-->"+e.toString());
    }
         
}
 
} 
