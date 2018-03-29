package cat.xtec.ioc.repository.impl;

import cat.xtec.ioc.domain.Inmueble;
import cat.xtec.ioc.domain.Vendedor;
import cat.xtec.ioc.repository.VendedorDAORepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import org.hibernate.Query;
/**
 *
 * @author root
 */
@Transactional
@Repository
public class VendedorDAOHibernate implements VendedorDAORepository {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public List<Vendedor> getAllVendedor() {
       Query query =  getSession().createQuery("Select p from Vendedor p");
       List<Vendedor> vendedores = query.list();
       return vendedores;
      
    }

    @Override
    public Vendedor getVendedorByIdVendedor(Integer idVendedor) {
       return getSession().get(Vendedor.class, idVendedor);
    }
    
    @Override
    public Vendedor getVendedorByNombre(String nombre){
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("nombre", nombre));
        return (Vendedor) criteria.uniqueResult();
    }
    
    @Override
    public Vendedor validarVendedor(String email) {
    /*    String consulta = "Select p.email from Vendedor p where p.email = " + email;
        Query query = getSession().createQuery(consulta);
        String respuesta = query.getQueryString();
        
        Query query2 = getSession().createQuery(consulta);
        List<Vendedor> vendedores = query.list();
        Vendedor vendedor = vendedores.get(0); */
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("email", email));
        
       List<Vendedor> vendedores= (List<Vendedor>) criteria.list();
       
       System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
        System.out.println(vendedores.size());
       Vendedor vendedor = new Vendedor();
        
        
        return vendedor;
    }

    @Override
    public void addVendedor(Vendedor vendedor) {
        getSession().saveOrUpdate(vendedor);
    }

    @Override
    public void updateVendedor(Vendedor vendedor) {
        getSession().merge(vendedor);
    }
    
    @Override
    public void deleteVendedor(Vendedor vendedor){
        getSession().delete(vendedor);
    }
    
    private Criteria createEntityCriteria(){
       return getSession().createCriteria(Vendedor.class);
    }
    
    protected Session getSession(){
       return sessionFactory.getCurrentSession();
    }

    

}
