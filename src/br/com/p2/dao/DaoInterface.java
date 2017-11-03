package br.com.p2.dao;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(noRollbackFor = Exception.class)
@Service
public interface DaoInterface<T>  {
	
	void salvar (T objeto) throws Exception;
	
	void deletar (T objeto) throws Exception;
	
	void atualizar (T objeto) throws Exception;
	
	void salvarAtualizar (T objeto) throws Exception;
	
	T merge(T objeto) throws Exception;
	
	List<T> listar() throws Exception;
	
	T loadObjeto(Long idObj) throws Exception;
	
	
	
}
