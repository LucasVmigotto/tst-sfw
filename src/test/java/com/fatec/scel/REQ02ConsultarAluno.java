package com.fatec.scel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fatec.scel.model.Aluno;
import com.fatec.scel.model.AlunoRepository;
@SpringBootTest
class REQ02ConsultarAluno {
	@Autowired
	AlunoRepository repository;

	@Test
	void ct01_consulta_aluno_sucesso () {
		// Dado – que o aluno está cadastrado
		repository.deleteAll();
		Aluno aluno = new Aluno("99999", "John Doe", "mail@mail.com", "00000000", "Wall Street");
		repository.save(aluno);
		
		// Quando – o usuário consulta o aluno pelo nome
		Aluno result = repository.findByRA("99999");

		// Então – o resultado obtido da consulta ao db deve ser igual a do objeto java cadastrado
		assertThat(result).isEqualTo(aluno);
	}

	@Test
	void ct02_consulta_aluno_nome_parcial () {
		// Dado – que existem 3 alunos cadastrados com nome John e 1 com nome Joene
		repository.deleteAll();
		
		Aluno aluno = new Aluno(99999, "John Doe", "mail@mail.com", "00000000", "Wall Street");
		repository.save(aluno);
		
		aluno = new Aluno(88888, "John Doe", "mail@mail.com", "00000000", "Wall Street");
		repository.save(aluno);
		
		aluno = new Aluno(77777, "John Doe", "mail@mail.com", "00000000", "Wall Street");
		repository.save(aluno);
		
		aluno = new Aluno(66666, "Joene Doe", "mail@mail.com", "00000000", "Wall Street");
		repository.save(aluno);

		// Quando – o usuário consulta pelo nome John de forma parcial 'Joh-'
		List<Livro> result = repository.findAllByNomeIgnoreCaseContaining("Joh");

		// Então – retorna 3 alunos
		assertThat(result.size()).isEqualTo(3);
	}
}
