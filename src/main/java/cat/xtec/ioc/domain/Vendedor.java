
package cat.xtec.ioc.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
/**
 *
 * @author root
 */
@Entity
@Table(name = "Vendedor")
public class Vendedor implements Serializable {   
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Vendedor")
    private Integer idVendedor;
   
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<Inmueble> inmuebles;
   
    @NotNull
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    
    @NotNull
    @Size(max=100)
    @Column(name = "email")
    private String email;
    
    @NotNull
    @Column(name = "telefono")
    private Integer telefono;
    
    public Vendedor(){}
    
 //   public Vendedor(Integer idVendedor, String nombre, String email, Integer telefono){
 //       this.idVendedor=idVendedor;
 //       this.nombre=nombre;
 //       this.email=email;
 //       this.telefono=telefono;
 //   }


    /**
     * @return the idUser
     */
    public Integer getIdVendedor() {
        return idVendedor;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdVendedor(Integer idUser) {
        this.idVendedor = idUser;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telefono
     */
    public Integer getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }


    public Set<Inmueble> getInmuebles() {
        return inmuebles;
    }

    public void setInmuebles(Set<Inmueble> inmuebles) {
        this.inmuebles = inmuebles;
    }

}
