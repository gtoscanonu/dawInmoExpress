package cat.xtec.ioc.service.impl;

import cat.xtec.ioc.domain.Inmueble;
import cat.xtec.ioc.domain.Vendedor;
import cat.xtec.ioc.repository.InmuebleDAORepository;
import cat.xtec.ioc.repository.VendedorDAORepository;
import cat.xtec.ioc.service.VendedorDAOService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



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
        vendedorDAORepository.addVendedor(vendedor);
    }

    @Override
    public String updateVendedor(Vendedor vendedor) {        
        vendedor.setInmuebles(inmuebleDAORepository.getAllInmueblesByVendedor(vendedor.getIdVendedor()));
        vendedorDAORepository.updateVendedor(vendedor);
        return "{\"missatge\" : \"Usuari modificat correctament\"}";
    }

    @Override
    public void deleteVendedor(Vendedor vendedor) {
        vendedorDAORepository.deleteVendedor(vendedor);
    }

    @Override
    public Vendedor validarVendedor(String email, String password) {
        return vendedorDAORepository.validarVendedor(email, password);
    }

    @Override
    public Vendedor loginVendedor(String email) {
        return vendedorDAORepository.loginVendedor(email);
    }
    
    @Override
    public Vendedor getVendedorInfomation(Integer idVivienda) {
        Vendedor findvendedor = null;
        List<Vendedor> vendedores = vendedorDAORepository.getAllVendedor();
        for (Vendedor vendedor : vendedores){
            Set<Inmueble> inmuebles = vendedor.getInmuebles();
            for (Inmueble inmueble : inmuebles){
                if (inmueble.getIdVivienda()== idVivienda){
                    findvendedor = vendedor;
                }
            }
        }
        return findvendedor;
    }    
}
