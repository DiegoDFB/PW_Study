//Arthur de Jesus Lima e Diego Francischette Blanco

package etechoracio.pw_study.controller;

import etechoracio.pw_study.entity.Monitor;
import etechoracio.pw_study.repository.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/monitores")
public class MonitorController {

    @Autowired
    private MonitorRepository repository;

    @GetMapping
    public List<Monitor> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Monitor> buscarPorId(@PathVariable Long id){
        var resposta = repository.findById(id);
        if (!resposta.isEmpty()){
            return ResponseEntity.ok(resposta.get());
        }

        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Monitor> inserir(@RequestBody Monitor monitor){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(monitor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Monitor> atualizar(@PathVariable Long id, @RequestBody Monitor monitorEncontrado) {
        Optional<Monitor> optionalMonitor = repository.findById(id);
        if (optionalMonitor.isPresent()) {
            Monitor monitor = optionalMonitor.get();
            monitor.setNome(monitorEncontrado.getNome());
            monitor.setFoto(monitorEncontrado.getFoto());
            monitor.setWhatsapp(monitorEncontrado.getWhatsapp());
            monitor.setEmail(monitorEncontrado.getEmail());
            monitor.setConteudo(monitorEncontrado.getConteudo());
            monitor.setDisciplina(monitorEncontrado.getDisciplina());
            monitor.setDisponibilidades(monitorEncontrado.getDisponibilidades());
            return ResponseEntity.ok(repository.save(monitor));
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
