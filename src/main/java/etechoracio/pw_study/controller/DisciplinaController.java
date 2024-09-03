//Arthur de Jesus Lima e Diego Francischette Blanco

package com.etechoracio.etechoracio.controller;

import com.etechoracio.etechoracio.entity.Disciplina;
import com.etechoracio.etechoracio.repository.DisciplinaRepository;
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
    private DisciplinaRepository repository;

    @GetMapping
    public List<Disciplina> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscarPorId(@PathVariable Long id){
        var resposta = repository.findById(id);
        if (!resposta.isEmpty()){
            return ResponseEntity.ok(resposta.get());
        }

        else{
            return ResponseEntity.notFound().build();
        }
    }

        @PostMapping
        public ResponseEntity<Disciplina> inserir(@RequestBody Disciplina disciplina){
            return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(disciplina));
        }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> atualizar(@PathVariable Long id, @RequestBody Disciplina disciplinaEncontrada) {
        Optional<Disciplina> optionalDisciplina = repository.findById(id);
        if (optionalDisciplina.isPresent()) {
            Disciplina disciplina = optionalDisciplina.get();
            disciplina.setNome(disciplinaEncontrada.getNome());
            return ResponseEntity.ok(repository.save(disciplina));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
