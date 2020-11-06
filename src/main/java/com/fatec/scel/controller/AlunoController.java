package com.fatec.scel.controller;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scel.model.Aluno;
import com.fatec.scel.service.AlunoServico;

@Controller
@RequestMapping(path = "/alunos")
public class AlunoController {
    Logger logger = LogManager.getLogger(AlunoController.class);

    @Autowired AlunoServico servico;
    @GetMapping("/cadastrar")
    public ModelAndView retornaFormDeCadastroDeAluno (Aluno aluno) {
        ModelAndView mv = new ModelAndView("cadastrarAluno");
        mv.addObject("aluno", aluno);
        return mv;
    }
    
    @PostMapping("/save")
    public ModelAndView save (@Valid Aluno aluno, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("cadastrarAluno");
        try {
            servico.save(aluno);
            modelAndView.addObject("alunos", servico.findAll());
            modelAndView.addObject("message", "Aluno cadastrado");
        } catch (Exception e) { // captura validacoes na camada de persistencia
            if (result.hasErrors()) {
                logger.info("======================> entrada de dados invalida na pagina cadastrar aluno");
                modelAndView.addObject("message", "");
                return modelAndView;
            } else {
                modelAndView.addObject("message", "Aluno ja cadastrado");
            }
        }
    }
}