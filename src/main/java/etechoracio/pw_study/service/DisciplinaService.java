package etechoracio.pw_study.service;

import dto.DisciplinaResponseDTO;
import etechoracio.pw_study.entity.Disciplina;
import etechoracio.pw_study.repository.DisciplinaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository repository;

    private ModelMapper modelMapper = new ModelMapper();
    public List<DisciplinaResponseDTO> listar(){
        var disciplinas = repository.findAll();
        var resultado = disciplinas.stream().map(
                e-> modelMapper.map(e, DisciplinaResponseDTO.class)).collect(Collectors.toList());

        return resultado;
    }
}
