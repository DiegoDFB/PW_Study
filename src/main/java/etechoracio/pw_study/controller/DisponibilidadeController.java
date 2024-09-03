//Arthur de Jesus Lima e Diego Francischette Blanco

package com.etechoracio.etechoracio.controller;

import com.etechoracio.etechoracio.entity.Disciplina;
import com.etechoracio.etechoracio.entity.Disponibilidade;
import com.etechoracio.etechoracio.repository.DisciplinaRepository;
import com.etechoracio.etechoracio.repository.DisponibilidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disponibilidade")
public class DisponibilidadeController {

    @Autowired
    private DisponibilidadeRepository repository;

    @GetMapping
    public List<Disponibilidade> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disponibilidade> buscarPorId(@PathVariable Long id){
        var resposta = repository.findById(id);
        if (!resposta.isEmpty()){
            return ResponseEntity.ok(resposta.get());
        }

        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Disponibilidade> inserir(@RequestBody Disponibilidade disponibilidade){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(disponibilidade));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disponibilidade> atualizar(@PathVariable Long id, @RequestBody Disponibilidade disponibilidadeEncontrada) {
        Optional<Disponibilidade> optionalDisponibilidade = repository.findById(id);
        if (optionalDisponibilidade.isPresent()) {
            Disponibilidade disponibilidade = optionalDisponibilidade.get();
            disponibilidade.setDiaSemana(disponibilidadeEncontrada.getDiaSemana());
            disponibilidade.setDas(disponibilidadeEncontrada.getDas());
            disponibilidade.setAte(disponibilidadeEncontrada.getAte());
            return ResponseEntity.ok(repository.save(disponibilidade));
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
