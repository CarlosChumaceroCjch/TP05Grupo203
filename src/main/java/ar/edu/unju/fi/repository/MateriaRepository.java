package ar.edu.unju.fi.repository;

import ar.edu.unju.fi.model.Materia;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {
	List <Materia> findMateriaByEstado(Boolean estado);
}