package com.fatec.scel.model;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long> {
	@Query("SELECT a FROM Aluno a WHERE a.ra = :ra")
	public Livro findByRA(@Param("ra") String ra);
	List<Aluno> findAllByNomeIgnoreCaseContaining (String nome);
	@Query(value = "SELECT a FROM Aluno a ORDER BY nome")
	List<Aluno> buscaTodosAlunosOrdenadosPorNome();	

}