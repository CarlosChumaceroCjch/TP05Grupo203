package ar.edu.unju.fi.service;


import ar.edu.unju.fi.model.Materia;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.DTO.MateriaDTO;

@Service
public interface MateriaService {
    List<MateriaDTO> listar();
    void guardar(Materia materia);
    MateriaDTO obtenerPorId(Long codigo);
    public void modificarMateria(Materia m);
    void eliminar(Long codigo);
}