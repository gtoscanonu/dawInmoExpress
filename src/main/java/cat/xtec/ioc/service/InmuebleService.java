package cat.xtec.ioc.service;

import cat.xtec.ioc.domain.Inmueble;
import java.util.List;
import java.util.Set;

/**
 * Interface InmuebleService define los métodos que implementará la clase InmuebleServiceImpl
 * Mediante esta interface podemos separar la especificación de los métodos de la implementación de ellos
 * @author: root
 */

public interface InmuebleService {
    
    Inmueble getInmuebleById(Integer id_vivienda);
    
    Set<Inmueble> getAllInmueblesByVendedor(Integer idVendedor);
    
    List<Inmueble> getInmueblesByAnuncio(String anuncio);
    
    List<Inmueble> getAllInmuebles();
    
    List<Inmueble> getQueryCriteria(float pMin, float pMax, Integer nHab, String ubicacion, String tipo, String anuncio);
    
    void addInmueble(Inmueble inmueble, Integer idVendedor);
    
    void updateInmueble(Inmueble inmueble);
    
    void deleteInmueble(Inmueble inmueble, Integer idVendedor);
    
    void deletefk(Inmueble inmueble, Integer idVendedor);
    
    Integer getContadorVisitas(Integer idVivienda);
    
    void setContadorVisitas(Integer idVivienda);
}

