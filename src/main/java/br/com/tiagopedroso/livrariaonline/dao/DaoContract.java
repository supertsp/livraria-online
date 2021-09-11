package br.com.tiagopedroso.livrariaonline.dao;

import java.util.List;

public interface DaoContract<T> {

	void salvar(T t);

	T procurarPorId(Long id);

	List<T> listar();
	
	void remover(T t);

}
