
package cat.xtec.ioc.repository;

import cat.xtec.ioc.domain.Vendedor;
import java.util.List;

/**
 * Interface VendedorDAORepository define los métodos que implementará la clase VendedorDAOHibernate
 * Mediante esta interface podemos separar la especificación de los métodos de la implementación de ellos
 * @author: root
 */

public interface VendedorDAORepository {
    List<Vendedor> getAllVendedor();
    Vendedor getVendedorByIdVendedor(Integer idVendedor);
    Vendedor getVendedorByNombre(String nombre);
    Vendedor getVendedorByEmail(String email);
    Vendedor validarVendedor(String email, String password);
    Vendedor loginVendedor(String email);
    void addVendedor(Vendedor vendedor);
    void updateVendedor(Vendedor vendedor);
    void deleteVendedor(Vendedor vendedor);
}
