package edu.es.eoi.user.repository;

import java.util.List;

import edu.es.eoi.user.entity.User;

public interface MyRepository<E> {
	
	public E findById(Integer idUsuario);
	
	public void create(E e);
	
	public void update(E e);
	
	public void deleteById(Integer id);
	
	public List<E> findAll();

}
