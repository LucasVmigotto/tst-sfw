package com.fatec.scel.po;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import com.fatec.scel.controller.AlunoController;
import com.fatec.scel.model.AlunoRepository;

public class PageCadastrarAluno {
    Logger logger = LogManager.getLogger(AlunoController.class);
    @Autowired AlunoRepository alunoRepository;
    private WebDriver driver;
    private By raBy = By.id("ra");
    private By nomeBy = By.id("nome");
    private By emailBy = By.id("email");
    private By cepBy = By.id("cep");
    private By enderecoBy = By.id("endereco");
    private By btnCadastrarBy = By.cssSelector(".btn-primary:nth-child(1)");
    private By mensagemBy = By.cssSelector(".text-danger");
    
    public PageCadastrarAluno(WebDriver driver) {
        this.driver = driver;
    }
    
    /**
     * Cadastrar Aluno
     * @param ra
     * @param nome
     * @param email
     * @param cep
     * @param endereco
     * @return
    */
    public PageCadastrarAluno cadastrar(
        String ra,
        String nome,
        String email,
        String cep,
        String endereco
    ) {
        driver.findElement(raBy).sendKeys(ra);
        driver.findElement(nomeBy).sendKeys(nome);
        driver.findElement(emailBy).sendKeys(email);
        driver.findElement(cepBy).sendKeys(cep);
        driver.findElement(enderecoBy).sendKeys(endereco);
        driver.findElement(btnCadastrarBy).click();
        return new PageCadastrarAluno(driver);
    }
    
    public String getMessageText() {
        return driver.findElement(mensagemBy).getText();
    }
}