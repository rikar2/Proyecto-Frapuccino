/*
 * InterfazRed.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */


import javax.microedition.rms.RecordStore;

class InterfazRed  
{
 private RecordStore rs = null;
 private int index=0;
 private String interfazSeleccionada = "";   
    
 public InterfazRed()
 {
  AbrirRecordStore();
    
    if (CheckRecordStore())
    {
      EscribirRecords();
    }
    
  LeerRecords();
  CerrarRecordsStore();
  seleccionaInterFazRed(); 
 }
 
 private boolean CheckRecordStore()
 {
   try{  
       // System.out.println("Nuemero records --> "+rs.getNumRecords());
        int i = rs.getNumRecords();
        
        if (i == 0)
        {
            return true;
        }
        
   } catch (Exception e) {
       
        System.out.println("Error : CheckRecordStore"+e.toString());
   }
  return false; 
 }
 
 private void ActualizarRecordStore()
 {
   String rec = index+"";
   byte[] data = rec.getBytes();
   try {
    rs.setRecord(1, data, 0, data.length); 
   } catch (Exception e) {
   
   }
 }
 
 private void AbrirRecordStore()
 {
    try {
        rs = RecordStore.openRecordStore("cambios", true);
        
    } catch(Exception e) {
        
        System.out.println(e.toString());
    }
 }
 
 private void CerrarRecordsStore()
 {
    try {
        rs.closeRecordStore(); 
        
    } catch(Exception e) {
        
        System.out.println(e.toString());
    }
 }
 
 private void LeerRecords()
 {
  try
    {
      // Intentionally make this too small to test code below
      byte[] recData = new byte[5]; 
      int len;

      for (int i = 1; i <= rs.getNumRecords(); i++)      
      {
        if (rs.getRecordSize(i) > recData.length)
          recData = new byte[rs.getRecordSize(i)];
       
        len = rs.getRecord(i, recData, 0);
       // System.out.println("Record #" + i + ": " + new String(recData, 0, len));
       // System.out.println("------------------------------");                        
       this.index = Integer.parseInt(new String(recData, 0, len));
      }
    }
    catch (Exception e)
    {
      System.out.println(e.toString());
    }
  }   
    

 private void EscribirRecords()
 {
   String s = this.index+"";
   
   byte[] rec = s.getBytes();

    try
    {
      rs.addRecord(rec, 0, rec.length);
    }
    catch (Exception e)
    {
      System.out.println(e.toString());
    }
 }
 
 public int getIndexRed()
 {
    solicitar(); 
    return this.index;
 }
  
 public void setIndexRed(int index)
 {
   this.index = index;
   modificar();
 }
 
 public String getInterfazRed()
 {
    return this.interfazSeleccionada;
 }
 
 private void modificar()
 {
   AbrirRecordStore();
   ActualizarRecordStore();
   CheckRecordStore();
   EscribirRecords();
   LeerRecords();
   CerrarRecordsStore();
   seleccionaInterFazRed();    
 }
 
 private void solicitar()
 {
  AbrirRecordStore();
    
    if (CheckRecordStore())
    {
      EscribirRecords();
    }
    
    LeerRecords();
    CerrarRecordsStore();
    seleccionaInterFazRed();   
 }
 
 private void seleccionaInterFazRed()
 {
    switch(this.index)
    {
     case 0:
            interfazSeleccionada = "default";
            break;
     case 1: 
            interfazSeleccionada = "tcp-ip";
            break;
     case 2:
            interfazSeleccionada = "bes";
            break;
     case 3: 
            interfazSeleccionada = "bis";
            break;
     case 4:
            interfazSeleccionada = "wifi";
            break;
     default:
            interfazSeleccionada = "error";    
    
    }   
 }  

} 
