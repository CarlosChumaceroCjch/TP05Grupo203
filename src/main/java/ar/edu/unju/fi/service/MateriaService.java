package ar.edu.unju.fi.service;

import ar.edu.unju.fi.model.Alumno;
import ar.edu.unju.fi.model.Materia;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.MateriaDTO;

@Service
public interface MateriaService {
    List<Materia> listar();
    void guardar(MateriaDTO materiaDTO);
    MateriaDTO obtenerPorId(Long codigo);
    void eliminar(Long codigo);
}