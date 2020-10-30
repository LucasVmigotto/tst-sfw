package com.fatec.scel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.fatec.scel.model.Aluno;
import com.fatec.scel.model.AlunoRepository;

@SpringBootTest
class REQ03ExcluirAluno {
	@Autowired
	AlunoRepository repository;

	@Test
	void ct01_excluir_aluno_sucesso () {
		// Dado – que o aluno está cadastrado
		repository.deleteAll();
		Aluno aluno = new Aluno("99999", "John Doe", "mail@mail.com", "00000000", "Wall Street");
		repository.save(aluno);

		// Quando – o usuário solicita exclusao
		Aluno excluido = repository.findByRA("99999");
		repository.deleteById(excluido.getId());

		// Então – o resultado obtido da consulta deve ser null
		assertThat(repository.findByRA("99999")).isEqualTo(null);
	}
}
