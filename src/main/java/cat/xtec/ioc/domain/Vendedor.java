
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
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Definimos la clase Vendedor
 * Los atributos de la clase vendedor son: idVendedor, nombre, mail, password, telefono, rol 
 * La anotación @Entity para decirle al JPA que es una entidad y deberá ser administrda por el EntityManager
 * La anotación @Table indica el nombre de la tabla a la que está ligada la clase 
 */

@Entity
@Table(name = "vendedor")
public class Vendedor implements Serializable {   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Vendedor")
    private Integer idVendedor;
   
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<Inmueble> inmuebles;
   
    @NotNull
    @NotBlank
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    
    @NotNull
    @Size(max=100)
    @Email
    @NotBlank
    @Column(name = "email")
    private String email;
    
    @NotNull
    @Column(name = "telefono")
    private Integer telefono;
    
    @NotNull
    @NotBlank
    @Column(name = "password")
    private String password;
    
    @NotNull
    @Column(name = "rol")
    private String rol;
    
/**
* Constructor de la clase de vendedor, no recibe parámetros
*/
    public Vendedor(){}
    

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

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the rol
     */
    public String getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    public String vendedorInString(Vendedor vendedor){
        return "\"idVendedor\" : \""+vendedor.getIdVendedor()+"\", \"nombre\" : \""+vendedor.getNombre()+"\", \"email\" : \""+vendedor.getEmail()+"\", \"telefono\" : \""+vendedor.getTelefono()+"\", \"rol\" : \""+vendedor.getRol();
    }
}
