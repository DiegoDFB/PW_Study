//Arthur de Jesus Lima e Diego Francischette Blanco

package etechoracio.pw_study.controller;

import dto.DisciplinaResponseDTO;
import etechoracio.pw_study.entity.Disciplina;
import etechoracio.pw_study.repository.DisciplinaRepository;
import etechoracio.pw_study.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService service;

    @GetMapping
    public List<DisciplinaResponseDTO> listarDisciplina() {
        return service.listar();
    }
}
