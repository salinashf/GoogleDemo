
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polygon;

@ManagedBean(name = "addMarkersView")
@SessionScoped
public class Controlador implements Serializable {

    private MapModel emptyModel;
    private List<LatLng> puntos = new ArrayList<LatLng>();
    private List<Marker> puntosMarcado = new ArrayList<Marker>();
    
    private Polygon pl = new Polygon();

       
    public  void IniciarPuntos() {
        /*
        Lat:-0.17904252477311822, Lng:-78.48014831542969
        Lat:-0.17256233830107812, Lng:-78.48225116729736
        Lat:-0.1794716761151606, Lng:-78.49456787109375
        Lat:-0.16869997444794702, Lng:-78.48941802978516
        Lat:-0.18771137990150227, Lng:-78.48113536834717
        */	
         Marker markerA = new Marker(new LatLng(-0.17904252477311822, -78.48014831542969), "Estatico A");
         Marker markerB = new Marker(new LatLng(-0.17256233830107812, -78.48225116729736), "Estatico B");
         Marker markerC = new Marker(new LatLng(-0.1794716761151606, -78.49456787109375), "Estatico C");
         Marker markerD = new Marker(new LatLng(-0.16869997444794702, -78.48941802978516), "Estatico D");
         Marker markerE = new Marker(new LatLng(-0.18771137990150227, -78.48113536834717), "Estatico E");
         
        puntosMarcado.add(markerA);
        puntosMarcado.add(markerB);
        puntosMarcado.add(markerC);
        puntosMarcado.add(markerD);
        puntosMarcado.add(markerE);

        emptyModel.addOverlay(markerA);
        emptyModel.addOverlay(markerB);
        emptyModel.addOverlay(markerC);
        emptyModel.addOverlay(markerD);
        emptyModel.addOverlay(markerE); 
        
    }
    private String distancia ;

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    private String title;

    private double lat;

    private double lng;

    @PostConstruct
    public void init() {
        emptyModel = new DefaultMapModel();
        IniciarPuntos();
    }

    public MapModel getEmptyModel() {
        return emptyModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double CalcularDistancia() {

        double x1, x2, y1, y2, d; //Declaración de la variables            

        x1 = puntos.get(0).getLat();
        y1 = puntos.get(0).getLng();
        x2 = puntos.get(1).getLat();
        y2 = puntos.get(1).getLng();
        d = Math.hypot(x2 - x1, y2 - y1);
        return d;
    }

    public void dibujar(ActionEvent actionEvent) {

        for (LatLng p : puntos) {
            pl.getPaths().add(p);
        }
        emptyModel.addOverlay(pl);
        distancia  =  "    A:"+ puntos.get(0).toString() + "      B: "+  puntos.get(1).toString() + "         Distancia " + CalcularDistancia();
    
        
    }

    public void addMarker() {
        Marker marker = new Marker(new LatLng(lat, lng), title);
        puntos.add(new LatLng(lat, lng));
        System.out.println(lat+" *** "+ lng);
        emptyModel.addOverlay(marker);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Punto Agregado", "Lat:" + lat + ", Lng:" + lng));
    }
}
