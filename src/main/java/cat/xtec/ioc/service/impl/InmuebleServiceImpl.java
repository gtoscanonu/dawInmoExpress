package cat.xtec.ioc.service.impl;

import cat.xtec.ioc.domain.Inmueble;
import cat.xtec.ioc.domain.Vendedor;
import cat.xtec.ioc.repository.InmuebleDAORepository;
import cat.xtec.ioc.repository.VendedorDAORepository;
import cat.xtec.ioc.service.InmuebleService;
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
    private InmuebleDAORepository inmuebleDAORepository;
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
        Vendedor vendedor = vendedorDAORepository.getVendedorByIdVendedor(idVendedor);
        return vendedor.getInmuebles();
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
        Vendedor vendedor = vendedorDAORepository.getVendedorByIdVendedor(idVendedor);
        Set<Inmueble> inmuebles = vendedor.getInmuebles();
        if (inmuebles != null){
            inmuebles.add(inmueble);
        } else{
            inmuebles = new HashSet<>();
            inmuebles.add(inmueble);
            vendedor.setInmuebles(inmuebles);
        }
    }
        

    @Override
    public void updateInmueble(Inmueble inmueble){
        inmuebleDAORepository.updateInmueble(inmueble);
    }

    @Override
    public void deleteInmueble(Inmueble inmueble, Integer idVendedor) {
        inmuebleDAORepository.deleteInmueble(inmueble, idVendedor);
    }
    
}
