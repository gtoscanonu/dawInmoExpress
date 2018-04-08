package cat.xtec.ioc.repository.impl;

import cat.xtec.ioc.domain.Inmueble;
import cat.xtec.ioc.domain.Vendedor;
import cat.xtec.ioc.repository.InmuebleDAORepository;
import cat.xtec.ioc.repository.VendedorDAORepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;

@Transactional
@Repository
public class InmuebleDAOHibernate implements InmuebleDAORepository {

   @Autowired 
   private VendedorDAORepository vendedorDAORepository;
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Inmueble getInmuebleById(Integer id_vivienda) {
        return getSession().get(Inmueble.class, id_vivienda);
    }

    @Override
    public Set<Inmueble> getAllInmueblesByVendedor(Integer idVendedor) {
       Vendedor vendedor = vendedorDAORepository.getVendedorByIdVendedor(idVendedor);
        return vendedor.getInmuebles();
    }

    @Override
    public void addInmueble(Inmueble inmueble, Integer idVendedor) {        
        Vendedor vendedor = vendedorDAORepository.getVendedorByIdVendedor(idVendedor);
        Set<Inmueble> inmuebles = vendedor.getInmuebles();
        if (inmuebles != null){
            inmuebles.add(inmueble);
        } else{
            inmuebles = new HashSet<>();
            inmuebles.add(inmueble);
            vendedor.setInmuebles(inmuebles);
        }
    }

    @Override
    public void updateInmueble(Inmueble inmueble) {
        getSession().merge(inmueble);
    }
    
   @Override
    public void deleteInmueble(Inmueble inmueble, Integer idVendedor) {

       
       Vendedor vendedor = vendedorDAORepository.getVendedorByIdVendedor(idVendedor);

        Set<Inmueble> inmuebles = vendedor.getInmuebles();
            
        Set<Inmueble> inmuebles2 = new HashSet<Inmueble>();
        
        for (Inmueble inmuebleItem : inmuebles ){
            if (inmuebleItem.getIdVivienda() != inmueble.getIdVivienda()){  
                inmuebles2.add(inmuebleItem);
            }
        }
            
        inmuebles.clear();

        vendedor.setInmuebles(inmuebles2);
        vendedorDAORepository.updateVendedor(vendedor);   
        
        getSession().delete(inmueble);
    }
    
    @Override
    public List<Inmueble> getInmueblesByAnuncio(String anuncio) {

        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("anuncio", anuncio));
        
        return (List<Inmueble>) criteria.list();
    }
    
    @Override 
    public List<Inmueble> getAllInmuebles(){
        
        Criteria criteria = createEntityCriteria();
        return (List<Inmueble>) criteria.list();
     
    }
    
    @Override

    public List<Inmueble> getQueryCriteria(float pMin, float pMax, String anuncio) {
       Criteria criteria = createEntityCriteria();
       criteria.add(Restrictions.between("precio", pMin, pMax));
       if(!anuncio.equalsIgnoreCase("tots els tipus")){
            criteria.add(Restrictions.eq("anuncio", anuncio));
      }
       return (List<Inmueble>) criteria.list();
    }

   

    @Override
    public List<Inmueble> getQueryCriteriaDos(float pMin, float pMax, String anuncio, String ubicacion) {
       Criteria criteria = createEntityCriteria();
       criteria.add(Restrictions.between("precio", pMin, pMax));
       criteria.add(Restrictions.eq("ubicacion", ubicacion));
       if(!anuncio.equalsIgnoreCase("tots els tipus")){
            criteria.add(Restrictions.eq("anuncio", anuncio));
       }
       return (List<Inmueble>) criteria.list();
    }

   

    @Override

    public List<Inmueble> getQueryCriteriaTres(float pMin, float pMax, String anuncio, String tipo) {
       Criteria criteria = createEntityCriteria();
       criteria.add(Restrictions.between("precio", pMin, pMax));
       criteria.add(Restrictions.eq("tipo", tipo));
       if(!anuncio.equalsIgnoreCase("tots els tipus")){
            criteria.add(Restrictions.eq("anuncio", anuncio));
       }
       return (List<Inmueble>) criteria.list();
    }

   

    @Override
    public List<Inmueble> getQueryCriteriaCuatro(float pMin, float pMax, String anuncio, String ubicacion, String tipo) { 
       Criteria criteria = createEntityCriteria();
       criteria.add(Restrictions.between("precio", pMin, pMax));
       criteria.add(Restrictions.eq("ubicacion", ubicacion));
       criteria.add(Restrictions.eq("tipo", tipo));
       if(!anuncio.equalsIgnoreCase("tots els tipus")){
            criteria.add(Restrictions.eq("anuncio", anuncio));
       }
       return (List<Inmueble>) criteria.list();
    }
    
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    
    private Criteria createEntityCriteria() {
        return getSession().createCriteria(Inmueble.class);
    }

}
