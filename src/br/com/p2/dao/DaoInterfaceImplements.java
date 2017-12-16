package br.com.p2.dao;


import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.p2.hibernate.HibernateUtil;

@Transactional(noRollbackFor = Exception.class)
@Service
public abstract class DaoInterfaceImplements<T> implements DaoInterface<T>{
	
	
	private Class<T> persistenceClass;
	
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	
	public DaoInterfaceImplements(Class<T> persistenceClass) {
		
		super();
		this.persistenceClass = persistenceClass;
		
	}


	@Override
	public void salvar(T objeto) throws Exception {
		sessionFactory.getCurrentSession().save(objeto);
		sessionFactory.getCurrentSession().flush();
		
	}


	@Override
	public void deletar(T objeto) throws Exception {
		sessionFactory.getCurrentSession().delete(objeto);
		sessionFactory.getCurrentSession().flush();
		
	}


	@Override
	public void atualizar(T objeto) throws Exception {
		sessionFactory.getCurrentSession().update(objeto);
		sessionFactory.getCurrentSession().flush();
		
	}


	@Override
	public void salvarAtualizar(T objeto) throws Exception {
		sessionFactory.getCurrentSession().saveOrUpdate(objeto);
		sessionFactory.getCurrentSession().flush();
		
	}

    @SuppressWarnings("unchecked")
	@Override
	public T merge(T objeto) throws Exception {
		
		T t = (T) sessionFactory.getCurrentSession().merge(objeto);
		sessionFactory.getCurrentSession().flush();
		return t;
		
		
	}

    @SuppressWarnings("unchecked")
	@Override
	public List<T> listar() throws Exception {
	    
		return sessionFactory.getCurrentSession().createCriteria(persistenceClass).list();
	}
	
    
    @Override
    public T loadObjeto(Long idObj) throws Exception {
        return (T) sessionFactory.getCurrentSession().get(persistenceClass,idObj);
    }
    
    
    
   public SessionFactory getSessionFactory() {
	   return sessionFactory;
   }
   


   public Class<T> getPersistenceClass() {
	   return persistenceClass;
   }
	
  
	
}
