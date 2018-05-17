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

/**
 * La clase VendedorDAOServiceImpl es la implementación de la interface VendedorDAORepository
 * En esta clase se establece la lógica del programa, 
 * Primero se consultan los datos de la BD llamando a las funciones de la interface VendedorDAORepository.
 * Con los datos obtenidos realizamos la lógica y tratamos los datos
 * @author: root
 */

@Service
public class VendedorDAOServiceImpl implements VendedorDAOService {
    
    @Autowired
    private VendedorDAORepository vendedorDAORepository;
    
    @Autowired
    private InmuebleDAORepository inmuebleDAORepository;

     @Override
    public List<Vendedor> getAllVendedor() {
        return vendedorDAORepository.getAllVendedor();
    }
    @Override
    public Vendedor getVendedorByIdVendedor(Integer idVendedor) {
       Vendedor vendedor = vendedorDAORepository.getVendedorByIdVendedor(idVendedor);
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

    @Override
    public Vendedor getVendedorByEmail(String email) {
        return vendedorDAORepository.getVendedorByEmail(email);
    }
}
