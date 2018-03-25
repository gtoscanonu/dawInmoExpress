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
    public List<Inmueble> getInmueblesByTipus(String tipo) {
        return inmuebleDAORepository.getInmueblesByTipus(tipo);
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
    public List<Inmueble> getQueryCriteria(float pMin, float pMax, Integer nHab, String ubicacion, String tipo) {
      return inmuebleDAORepository.getQueryCriteria(pMin, pMax, nHab, ubicacion, tipo);
    }
}