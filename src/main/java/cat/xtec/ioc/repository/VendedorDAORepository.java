
package cat.xtec.ioc.repository;

import cat.xtec.ioc.domain.Vendedor;
import java.util.List;

/**
 *
 * @author root
 */
public interface VendedorDAORepository {
    List<Vendedor> getAllVendedor();
    Vendedor getVendedorByIdVendedor(Integer idVendedor);
    Vendedor getVendedorByNombre(String nombre);
    Integer validarVendedor(String email);
    Vendedor loginVendedor(String email);
    void addVendedor(Vendedor vendedor);
    void updateVendedor(Vendedor vendedor);
    void deleteVendedor(Vendedor vendedor);
}
