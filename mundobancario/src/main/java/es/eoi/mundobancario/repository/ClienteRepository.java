package es.eoi.mundobancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	@Query("select c from Cliente c where c.usuario = :usuario")
	Cliente findClienteByUsuario(@Param("usuario") String usuario);

}
