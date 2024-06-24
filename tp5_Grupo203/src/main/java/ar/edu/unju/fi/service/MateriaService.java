package ar.edu.unju.fi.service;

import ar.edu.unju.fi.model.Alumno;
import java.util.List;
import ar.edu.unju.fi.dto.MateriaDTO;

public interface MateriaService {
    List<MateriaDTO> listar();
    MateriaDTO guardar(MateriaDTO materiaDTO);
    MateriaDTO obtenerPorId(Long codigo);
    void eliminar(Long codigo);
}