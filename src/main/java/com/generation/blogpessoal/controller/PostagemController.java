package com.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

// receberá requisições URL 
@RestController 
@RequestMapping("/postagens")
// de onde aceito requisições com links final /postagem 
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class PostagemController {
	
	//injeção de dependência, inversão de controle de uma classe "estância" 
	//não precisa colocar o "new" para instanciar 
	//Autowired fecha e abre sozinho se precisar o Spring que controla isso 
	@Autowired
	private PostagemRepository postagemRepository;
	
	//respota vim no padrão JASON, lista de postagem
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll() {
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getByid(@PathVariable Long id) {
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
}
