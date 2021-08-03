package br.com.academy.camelmicroservicea.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.academy.camelmicroservicea.model.ArquivoModel;
import br.com.academy.camelmicroservicea.service.ArquivoService;

@RestController
@RequestMapping(path = "/arquivos")
public class ArquivoController {

	@Autowired
	private ArquivoService service;
	
	@PostMapping
	public ResponseEntity<ArquivoModel> save(@RequestBody ArquivoModel model) {
		return new ResponseEntity<>(service.save(model), HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<ArquivoModel>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
}
