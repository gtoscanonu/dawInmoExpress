package cat.xtec.ioc.service.impl;

import cat.xtec.ioc.domain.Vendedor;
import cat.xtec.ioc.repository.InmuebleDAORepository;
import cat.xtec.ioc.repository.VendedorDAORepository;
import cat.xtec.ioc.service.VendedorDAOService;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


@Service

public class VendedorDAOServiceImpl implements VendedorDAOService {
    
    @Autowired
    private VendedorDAORepository vendedorDAORepository;
    
    @Autowired
    private InmuebleDAORepository inmuebleDAORepository;
/*    
    public VendedorDAOServiceImpl(VendedorDAORepository vendedorDAORepository){
        this.vendedorDAORepository= vendedorDAORepository;
    }
*/
    
     @Override
    public List<Vendedor> getAllVendedor() {
        return vendedorDAORepository.getAllVendedor();
    }
    @Override
    public Vendedor getVendedorByIdVendedor(Integer idVendedor) {
       Vendedor vendedor = vendedorDAORepository.getVendedorByIdVendedor(idVendedor);
        //vendedor.setPassword("******");
       return vendedor;
    }

    @Override
    public Vendedor getVendedorByNombre(String nombre) {
        return vendedorDAORepository.getVendedorByNombre(nombre);
    }

    @Override
    public void addVendedor(Vendedor vendedor) {
        String password = vendedor.getPassword();
        vendedorDAORepository.addVendedor(vendedor);
    }

    @Override
    public String updateVendedor(Vendedor vendedor) {        
        if (vendedorDAORepository.validarVendedor(vendedor.getEmail()) == 0 ) {
             vendedor.setInmuebles(inmuebleDAORepository.getAllInmueblesByVendedor(vendedor.getIdVendedor()));
             vendedorDAORepository.updateVendedor(vendedor);
             return "actualitzat correctament";
        }else{
            Vendedor vendedorEmail = vendedorDAORepository.loginVendedor(vendedor.getEmail());
            if (vendedorEmail.getIdVendedor().equals(vendedor.getIdVendedor())){
                vendedor.setInmuebles(inmuebleDAORepository.getAllInmueblesByVendedor(vendedor.getIdVendedor()));
                vendedorDAORepository.updateVendedor(vendedor);
                return "actualitzat correctament";
            } else {
                return "El mail ja està en ús";
            }
        }
    }

    @Override
    public void deleteVendedor(Vendedor vendedor) {
        vendedorDAORepository.deleteVendedor(vendedor);
    }

    @Override
    public Integer validarVendedor(String email) {
        return vendedorDAORepository.validarVendedor(email);
    }

    @Override
    public Vendedor loginVendedor(String email) {
        return vendedorDAORepository.loginVendedor(email);
    }
    
}
