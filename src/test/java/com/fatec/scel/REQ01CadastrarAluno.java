package com.fatec.scel;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;

import com.fatec.scel.model.Alunos;
import com.fatec.scel.model.AlunosRepository;

@SpringBootTest
class REQ01CadastrarAluno {
	@Autowired
	AlunoRepository repository;

	private Validator validator;
	private ValidatorFactory validatorFactory;

	@Test
	void ct01_cadastrar_aluno_sucesso () {
		// Dado – que o atendente tem que cadastar um aluno
		repository.deleteAll();

		// Quando – o atendente cadastra o aluno
		Aluno aluno = new Aluno("99999", "John Doe", "mail@mail.com", "00000000", "Wall Street");
		repository.save(aluno);

		// Então – o sistema verifica os dados E confirma a operação
		assertEquals(1, repository.count());
	}

	@Test
	void ct02_validacao_dados_entrada () {
		// Dado – que o atendente tem que cadastar um aluno
		repository.deleteAll();

		// Quando – o atendente cadastra o aluno
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		Aluno aluno = new Aluno("99999", "John Doe", "mail@mail.com", "00000000", "Wall Street");

		// Então – o sistema verifica os dados E confirma a operação
		Set<ConstraintViolation<Aluno>> violations = validator.validate(aluno);
		assertTrue(violations.isEmpty());
	}

	@Test
	void ct03_validacao_dados_branco () {
		// Dado – que o atendente tem que cadastar um aluno
		repository.deleteAll();

		// Quando – o atendente cadastra o aluno com dados inválidos
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		Aluno aluno = new Aluno();
		Set<ConstraintViolation<Aluno>> violations = validator.validate(Aluno);

		// Então – o sistema verifica os dados E retorna mensagem nome invalido
		assertEquals("RA deve ter 5 caracteres", violations.iterator().next().getMessage());

	}

	@Test
	void ct04_ra_cadastrado_deve_retornar_1_resultado () {
		// Dado – que o RA do aluno ja está cadastrado
		repository.deleteAll();
		Aluno aluno = new Aluno("99999", "John Doe", "mail@mail.com", "00000000", "Wall Street");
		repository.save(aluno);

		// Quando – o atendente registra as informações do aluno e confirma a operação
		repository.save(aluno);
		
		// Então – o sistema valida as informações E retorna a quantidade de registros
		// inseridos igual a 1.
		assertEquals(1, repository.count());
	}
}
