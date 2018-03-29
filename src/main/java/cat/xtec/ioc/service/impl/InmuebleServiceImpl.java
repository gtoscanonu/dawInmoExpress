package cat.xtec.ioc.service.impl;

import cat.xtec.ioc.domain.Inmueble;
import cat.xtec.ioc.domain.Vendedor;
import cat.xtec.ioc.repository.InmuebleDAORepository;
import cat.xtec.ioc.repository.VendedorDAORepository;
import cat.xtec.ioc.service.InmuebleService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InmuebleServiceImpl implements InmuebleService {
    
    @Autowired
    private InmuebleDAORepository inmuebleDAORepository;
    @Autowired
    private VendedorDAORepository vendedorDAORepository;
/*    
    public InmuebleServiceImpl(VendedorDAORepository vendedorDAORepository, InmuebleDAORepository inmuebleDAORepository){
        this.inmuebleDAORepository=inmuebleDAORepository;
        this.vendedorDAORepository=vendedorDAORepository;
    }
*/
    @Override
    public Inmueble getInmuebleById(Integer id_vivienda) {
       return inmuebleDAORepository.getInmuebleById(id_vivienda);
    }

    @Override
    public Set<Inmueble> getAllInmueblesByVendedor(Integer idVendedor) {
        return inmuebleDAORepository.getAllInmueblesByVendedor(idVendedor);
    }
    
    @Override
    public List<Inmueble> getAllInmuebles() {
        return inmuebleDAORepository.getAllInmuebles();
    }

    @Override
    public List<Inmueble> getInmueblesByAnuncio(String anuncio) {
        return inmuebleDAORepository.getInmueblesByAnuncio(anuncio);
    }

    @Override
    public void addInmueble(Inmueble inmueble, Integer idVendedor) {
       inmuebleDAORepository.addInmueble(inmueble, idVendedor);
    }
        

    @Override
    public void updateInmueble(Inmueble inmueble){
        inmuebleDAORepository.updateInmueble(inmueble);
    }

    @Override
    public void deleteInmueble(Inmueble inmueble, Integer idVendedor) {
        inmuebleDAORepository.deleteInmueble(inmueble, idVendedor);
    }

    @Override
    public List<Inmueble> getQueryCriteria(float pMin, float pMax, Integer nHab, String ubicacion, String tipo, String anuncio) {
       List<Inmueble> inmuebles = new ArrayList<>();
       List<Inmueble> inmuebles2 = new ArrayList<>();
     
      if (ubicacion.toLowerCase().equals("totes les zones") && tipo.toLowerCase().equals("tots els tipus")){
          inmuebles =  inmuebleDAORepository.getQueryCriteria(pMin, pMax, anuncio);
      }
      if(ubicacion.toLowerCase().equals("otes les zones") && !tipo.toLowerCase().equals("tots els tipus")){
              inmuebles =  inmuebleDAORepository.getQueryCriteriaTres(pMin, pMax, anuncio, tipo);
          }
      if(!ubicacion.toLowerCase().equals("otes les zones") && tipo.toLowerCase().equals("ots els tipus")){
              inmuebles =  inmuebleDAORepository.getQueryCriteriaDos(pMin, pMax, anuncio, ubicacion);
          }
      if(!ubicacion.toLowerCase().equals("otes les zones") && !tipo.toLowerCase().equals("tots els tipus")){
              inmuebles =  inmuebleDAORepository.getQueryCriteriaCuatro(pMin, pMax, anuncio, ubicacion, tipo );
          }
           
      if ( nHab >1){
            for (Inmueble inmuebleItem : inmuebles){
                if (inmuebleItem.getNumHabitaciones() >= 2){
                    inmuebles2.add(inmuebleItem);
                }
            }
        }
      
      return inmuebles2; 
    }
    
    
}