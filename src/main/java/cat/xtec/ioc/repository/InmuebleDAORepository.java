
package cat.xtec.ioc.repository;
import cat.xtec.ioc.domain.Inmueble;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 *
 * @author root
 */
public interface InmuebleDAORepository {
    
    Inmueble getInmuebleById(Integer id_vivienda);
    
    Set<Inmueble> getAllInmueblesByVendedor(Integer idVendedor);
    
    List<Inmueble> getInmueblesByAnuncio(String anuncio);
    
    List<Inmueble> getAllInmuebles();
    
    List<Inmueble> getQueryCriteria(float pMin, float pMax, String anuncio);
    
    List<Inmueble> getQueryCriteriaDos(float pMin, float pMax, String anuncio, String ubicacion);
    
    List<Inmueble> getQueryCriteriaTres(float pMin, float pMax, String anuncio, String tipo);
    
    List<Inmueble> getQueryCriteriaCuatro(float pMin, float pMax, String anuncio, String ubicacion, String tipo);
    
    void addInmueble(Inmueble inmueble, Integer idVendedor);
    
    void updateInmueble(Inmueble inmueble);
    
    void deleteInmueble(Inmueble inmueble, Integer idVendedor);
}
