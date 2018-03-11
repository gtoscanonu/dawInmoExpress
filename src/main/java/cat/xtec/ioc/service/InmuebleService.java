package cat.xtec.ioc.service;

import cat.xtec.ioc.domain.Inmueble;
import java.util.List;
import java.util.Set;

/**
 *
 * @author root
 */
public interface InmuebleService {
    
     Inmueble getInmuebleById(Integer id_vivienda);
    
    Set<Inmueble> getAllInmuebles(Integer idVendedor);
    
    List<Inmueble> getInmueblesByTipus(String tipo);
    
    void addInmueble(Inmueble inmueble, Integer idVendedor);
    
    void updateInmueble(Inmueble inmueble);
    
    void deleteInmueble(Inmueble inmueble);
    
}
