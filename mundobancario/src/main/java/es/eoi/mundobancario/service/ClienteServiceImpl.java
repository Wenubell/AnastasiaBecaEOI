package es.eoi.mundobancario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	ClienteRepository repository;
	
	public Cliente findClienteById(Integer id) {
		return repository.findById(id).get();
	}

}
