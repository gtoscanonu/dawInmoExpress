package cat.xtec.ioc.domain;

import cat.xtec.ioc.domain.Inmueble;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.4.v20160829-rNA", date="2018-03-24T17:20:07")
@StaticMetamodel(Vendedor.class)
public class Vendedor_ { 

    public static volatile SingularAttribute<Vendedor, Integer> idVendedor;
    public static volatile SingularAttribute<Vendedor, Integer> telefono;
    public static volatile SingularAttribute<Vendedor, String> nombre;
    public static volatile SetAttribute<Vendedor, Inmueble> inmuebles;
    public static volatile SingularAttribute<Vendedor, String> email;

}