package br.com.academy.camelmicroservicea.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.academy.camelmicroservicea.documents.Arquivo;

@Repository
public interface ArquivoRepository extends MongoRepository<Arquivo, String> {

}
