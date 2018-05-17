/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.xtec.ioc.service;

import cat.xtec.ioc.domain.Vendedor;
import java.util.List;

/**
 * Interface VendedorDAOService define los métodos que implementará la clase VendedorDAOServiceImpl
 * Mediante esta interface podemos separar la especificación de los métodos de la implementación de ellos
 * @author: root
 */

public interface VendedorDAOService {
    List<Vendedor> getAllVendedor();
    Vendedor getVendedorByIdVendedor(Integer idVendedor);
    Vendedor getVendedorByNombre(String nombre);
    Vendedor getVendedorByEmail(String email);
    Vendedor validarVendedor(String email, String password);
    Vendedor loginVendedor(String email);
    void addVendedor(Vendedor vendedor);
    String updateVendedor(Vendedor vendedor);
    void deleteVendedor(Vendedor vendedor);
    Vendedor getVendedorInfomation(Integer idVivienda);
}
