/*
 * CoordenadasGPS.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

import javax.microedition.location.*;

class CoordenadasGPS
{
 private LocationProvider provider = null;
 private LocationListener loc = null;
 private Location location = null;
 private Coordinates cord = null;
 private double Latitude = 0;
 private double Longitud = 0;
 private double Altitud = 0;

  
  public CoordenadasGPS()
  {
   super();
   BuscarSatelites();
  }
  
  private void BuscarSatelites()
  {
     Criteria cr = new Criteria();
     cr.setHorizontalAccuracy(50);
     cr.setVerticalAccuracy(50);
     
     try {
          provider = LocationProvider.getInstance(cr);
     } catch(LocationException e) {
         
     }
      
     try {
          location = provider.getLocation(-1);
          provider.setLocationListener(loc, -1, 0, 0);
     } catch(Exception e){
         
     }
     
     try {       
        
        cord = location.getQualifiedCoordinates();
        
     }catch (Exception e){
           
     }
        
            
     if(cord != null)
     {
      this.Latitude = cord.getLatitude();
      this.Longitud = cord.getLongitude();
      this.Altitud = cord.getAltitude();
     
     }  
  }
  
  public double getLatitud()
  {
   return this.Latitude;   
  }  
  
  public double getLongitud()
  {
   return this.Longitud; 
  }
  
  public double getAltitud()
  {
    return this.Altitud;
  }
  
}

