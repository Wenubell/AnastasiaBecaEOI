package es.eoi.mundobancario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
	
	@Query("select c from Cuenta c where c.saldo<0")
	List<Cuenta> findDeudoras();
	
}
