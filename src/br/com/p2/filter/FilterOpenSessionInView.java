package br.com.p2.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.filter.DelegatingFilterProxy;

import br.com.p2.hibernate.HibernateUtil;
import br.com.p2.listener.ContextLoaderListenerP2App;

//intercepta todas as requisicoes fazendo commit e roolback

@WebFilter(filterName="conexaoFilter")
public class FilterOpenSessionInView extends DelegatingFilterProxy implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	public static SessionFactory sf;
	
	@Override
	public void initFilterBean() throws ServletException {
		sf = HibernateUtil.getSessionFactory();
	}
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		BasicDataSource springDataSource = (BasicDataSource) ContextLoaderListenerP2App.getBean("springDataSource");
		DefaultTransactionDefinition df = new DefaultTransactionDefinition();
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(springDataSource);
		TransactionStatus status = transactionManager.getTransaction(df);
		
		try {
			
			request.setCharacterEncoding("UTF-8");
			//inicia a transacao
			sf.getCurrentSession().beginTransaction();
			//executa a operacao
			filterChain.doFilter(request, response);
			//commita a transacao
			transactionManager.commit(status);
			
			if (sf.getCurrentSession().getTransaction().isActive()) {
				sf.getCurrentSession().flush();
				sf.getCurrentSession().getTransaction().commit();
			}
			//se tiver aberta fecha
			if (sf.getCurrentSession().isOpen()) {
				sf.getCurrentSession().close();	
			}
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			
			
			
		} catch (Exception e) {
			
			transactionManager.rollback(status);
			e.printStackTrace();
			
			if (sf.getCurrentSession().getTransaction().isActive()) {
				sf.getCurrentSession().getTransaction().rollback();
			}
			//se tiver aberta fecha
			if (sf.getCurrentSession().isOpen()) {
				sf.getCurrentSession().close();	
			}
			
			
		} finally {
			if (sf.getCurrentSession().isOpen()) {
				if (sf.getCurrentSession().getTransaction().isActive()) {
					sf.getCurrentSession().flush();
					sf.getCurrentSession().clear();
				}
			}
			
			//se tiver aberta fecha
			if (sf.getCurrentSession().isOpen()) {
				sf.getCurrentSession().close();	
			}
			
		}
		
		
		
	}

}
