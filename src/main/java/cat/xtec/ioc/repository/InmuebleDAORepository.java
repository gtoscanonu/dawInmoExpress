
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
    
    List<Inmueble> getInmueblesByTipus(String tipo);
    
    void addInmueble(Inmueble inmueble, Integer idVendedor);
    
    void updateInmueble(Inmueble inmueble);
    
    void deleteInmueble(Inmueble inmueble, Integer idVendedor);
}
