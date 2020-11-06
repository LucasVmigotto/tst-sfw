package com.fatec.scel.service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scel.model.Aluno;
import com.fatec.scel.model.AlunoRepository;

@Service
public class AlunoServico {
    Logger logger = LogManager.getLogger(AlunoServico.class);
    
    @Autowired
    private AlunoRepository repository;

    public Iterable<Aluno> findAll () {
        return repository.findAll();
    }
    
    public void save (Aluno aluno) {
        repository.save(aluno);
    }
}