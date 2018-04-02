/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.xtec.ioc.service;

import cat.xtec.ioc.domain.Vendedor;
import java.util.List;

/**
 *
 * @author root
 */
public interface VendedorDAOService {
    List<Vendedor> getAllVendedor();
    Vendedor getVendedorByIdVendedor(Integer idVendedor);
    Vendedor getVendedorByNombre(String nombre);
    Integer validarVendedor(String email);
    Vendedor loginVendedor(String email);
    void addVendedor(Vendedor vendedor);
    String updateVendedor(Vendedor vendedor);
    void deleteVendedor(Vendedor vendedor);
}
