/*
 * MapaManipula.java
 *
 * © <your company here>, 2003-2008
 * Confidential and proprietary.
 */


class MapaManipula 
{
 private String[] coordenadas = {"", "", "", ""};
 private Mapa mapa;
    
    public MapaManipula(String[] coordenadas) 
    {    
     this.coordenadas = coordenadas;
     mapa = new Mapa(this.coordenadas);
    }
    
    
    public void cambiarTipoMapa(String tipoMapa)
    {    
        if(tipoMapa.equals("mobil"))
        {
         mapa.setTipoMapa("hybrid");    
        }
        else
        {
         mapa.setTipoMapa("mobil");      
        }
     mapa.mostrarMapa();
    }
    
    public void mantenerTipoMapa(String tipoMapa)
    {
     mapa.setTipoMapa(tipoMapa);
    }
    
    public void mantenerNivelZoom(int zoom)
    {
     mapa.setZoom(zoom);
    }
    
    public void acercarMapa(int zoom)
    {
     mapa.setZoom(++zoom);
     mapa.mostrarMapa();  
    }

    public void alejarMapa(int zoom)
    {
     mapa.setZoom(--zoom);
     mapa.mostrarMapa();  
    }
    
} 
