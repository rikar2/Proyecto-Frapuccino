/*
 * Main.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;

public class Main extends UiApplication
{
 public static void main(String[] args)
 {
   Main ma = new Main();
   ma.enterEventDispatcher();
 }
 
 public Main()
 {
  pushScreen(new SUGC()); 
 }     
} 
