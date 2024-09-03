//Arthur de Jesus Lima e Diego Francischette Blanco

package com.etechoracio.etechoracio.controller;

import com.etechoracio.etechoracio.entity.Conexao;
import com.etechoracio.etechoracio.entity.Disciplina;
import com.etechoracio.etechoracio.entity.Disponibilidade;
import com.etechoracio.etechoracio.repository.ConexaoRepository;
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
@RequestMapping("/conexao")
public class ConexaoController {

    @Autowired
    private ConexaoRepository repository;

    @GetMapping
    public List<Conexao> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conexao> buscarPorId(@PathVariable Long id){
        var resposta = repository.findById(id);
        if (!resposta.isEmpty()){
            return ResponseEntity.ok(resposta.get());
        }

        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Conexao> inserir(@RequestBody Conexao conexao){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(conexao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conexao> atualizar(@PathVariable Long id, @RequestBody Conexao conexaoEncontrada) {
        Optional<Conexao> optionalConexao = repository.findById(id);
        if (optionalConexao.isPresent()) {
            Conexao conexao = optionalConexao.get();
            conexao.setDataCriacao(conexaoEncontrada.getDataCriacao());
            conexao.setMonitor(conexaoEncontrada.getMonitor());
            return ResponseEntity.ok(repository.save(conexao));
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
