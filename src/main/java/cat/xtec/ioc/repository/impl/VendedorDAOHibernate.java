package cat.xtec.ioc.repository.impl;

import cat.xtec.ioc.domain.Vendedor;
import cat.xtec.ioc.repository.VendedorDAORepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
    public Vendedor getVendedorByEmail(String email){
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("email", email));
        return (Vendedor) criteria.uniqueResult();
    }
    
    @Override 
    public Vendedor loginVendedor(String email){
       Criteria criteria = createEntityCriteria();
       criteria.add(Restrictions.eq("email", email));
       
       List<Vendedor> vendedores= (List<Vendedor>) criteria.list();
        
       Vendedor vendedor = vendedores.get(0);
       return vendedor;
    }
    
    @Override
    public Vendedor validarVendedor(String email, String password) {
       Criteria criteria = createEntityCriteria();
       criteria.add(Restrictions.eq("email", email));
       criteria.add(Restrictions.eq("password", password));
        
       return (Vendedor) criteria.uniqueResult();
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
