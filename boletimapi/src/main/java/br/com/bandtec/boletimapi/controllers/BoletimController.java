package br.com.bandtec.boletimapi.controllers;

import br.com.bandtec.boletimapi.entity.Boletim;
import br.com.bandtec.boletimapi.repository.BoletimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author José Yoshiriro
 */
@RestController
@RequestMapping("/boletinsruim")
public class BoletimController {

    @Autowired
    private BoletimRepository repository;

    @GetMapping
    public ResponseEntity getVarios(
            @RequestParam(value = "aluno", required = false) String aluno,
            @RequestHeader("token") String token) {
        if (aluno == null) {
            return ResponseEntity.ok(repository.findByTokenOrderByAluno(token));
        } else {
            return ResponseEntity.ok(repository.findByAlunoLikeAndTokenOrderByAluno(aluno, token));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getUm(@PathVariable("id") Long id, @RequestHeader("token") String token) {
        Boletim boletim =repository.findByIdAndToken(id, token);
        if(boletim != null)
            return ResponseEntity.ok(boletim);
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity criar(@RequestBody Boletim boletim, @RequestHeader("token") String token) {
       try{
           boletim.setToken(token);
           repository.save(boletim);
           return ResponseEntity.status(201).build();
       }catch (Exception e){
           return ResponseEntity.status(400).body(e.getCause().getMessage());
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id, @RequestHeader("token") String token) {
        repository.deleteByIdAndToken(id, token);
        return ResponseEntity.ok().build();
    }
}
