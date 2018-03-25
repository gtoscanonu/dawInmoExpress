package cat.xtec.ioc.service.impl;

import cat.xtec.ioc.domain.Vendedor;
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


@Service

public class VendedorDAOServiceImpl implements VendedorDAOService {
    
    @Autowired
    private VendedorDAORepository vendedorDAORepository;
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
        return vendedorDAORepository.getVendedorByIdVendedor(idVendedor);
    }

    @Override
    public Vendedor getVendedorByNombre(String nombre) {
        return vendedorDAORepository.getVendedorByNombre(nombre);
    }

    @Override
    public void addVendedor(Vendedor vendedor) {
        vendedorDAORepository.addVendedor(vendedor);
    }

    @Override
    public void updateVendedor(Vendedor vendedor) {
        vendedorDAORepository.updateVendedor(vendedor);
    }

    @Override
    public void deleteVendedor(Vendedor vendedor) {
        vendedorDAORepository.deleteVendedor(vendedor);
    }

    @Override
    public Vendedor validarVendedor(String email) {
        return vendedorDAORepository.validarVendedor(email);
    }
    
}
