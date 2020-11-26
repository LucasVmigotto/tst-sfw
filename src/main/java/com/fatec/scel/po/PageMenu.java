package com.fatec.scel.po;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageMenu {
    Logger logger = LogManager.getLogger(PageMenu.class);
    private WebDriver driver;
    private By byLinkTextLivros = By.linkText("Livros");
    private By byLinkTextUsuarios = By.linkText("Usuários");
    private By byLinkTextAlunos = By.linkText("Alunos");
    
    public PageMenu (WebDriver driver) {
        this.driver = driver;
    }
    
    public void click_na_opcao_cadastrarLivros() {
        logger.info("======================> chamada para a função cadastrar livro");
        driver.findElement(byLinkTextLivros).click();
    }
    
    public void click_na_opcao_cadastrarAlunos() {
        driver.findElement(byLinkTextAlunos).click();
    }
    
    public void sair() {
        driver.findElement(By.linkText("Sair")).click();
    }
}