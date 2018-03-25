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
    
    Set<Inmueble> getAllInmueblesByVendedor(Integer idVendedor);
    
    List<Inmueble> getInmueblesByTipus(String tipo);
    
    List<Inmueble> getAllInmuebles();
    
    List<Inmueble> getQueryCriteria(float pMin, float pMax, Integer nHab, String ubicacion, String tipo);
    
    void addInmueble(Inmueble inmueble, Integer idVendedor);
    
    void updateInmueble(Inmueble inmueble);
    
    void deleteInmueble(Inmueble inmueble, Integer idVendedor);
}

