package com.fatec.scel;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.ViewResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class REQ01CadastrarAlunoMAVTests {
    @Autowired MockMvc mockMvc;
    
    @Test
    public void ct01_quando_seleciona_cadastrar_aluno_retorna_200 () throws Exception {
        //Dado – que o sistema está disponível
        //Quando – o usuário faz uma solicitação do tipo GET - localhost:8080/alunos/cadastrar
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/alunos/cadastrar"));
        //Então – retorna o HTTP 200
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ct02_quando_seleciona_cadastrar_aluno_retorna_view () throws Exception {
        // Dado – que o sistema está disponível
        // Quando – o usuário faz uma solicitação do tipo GET localhost:8080/alunos/cadastrar
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/alunos/cadastrar"));
        ViewResultMatchers view = MockMvcResultMatchers.view();
        // Então – retorna o nome logico da view de cadastro de aluno
        resultActions.andExpect(view.name(Matchers.is("cadastrarAluno")));
    }

    @Test
    public void ct03_quando_nome_branco_retorna_size () throws Exception {
        //Dado – que o aluno nao esta cadastrado
        //Quando - o usuario deixa o nome em branco
        mockMvc.perform(post("/alunos/save") //simula uma requisicao post entrada pelo usuario
            .param("ra", "99999")
            .param("nome", "")
            .param("email", "john@mail.com"))
            .param("cep", "99999999")
            .param("endereco", "Rua Direita")
        //Então - retorna erro 
            .andExpect(MockMvcResultMatchers.status().is(200)) //404
            .andExpect(MockMvcResultMatchers.view().name("cadastrarAluno"))
            .andExpect(MockMvcResultMatchers.model().attribute("aluno", Matchers.any(Aluno.class)))
            .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("aluno", "nome"))
            .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrorCode("aluno", "nome", "Size"));
    }
}
