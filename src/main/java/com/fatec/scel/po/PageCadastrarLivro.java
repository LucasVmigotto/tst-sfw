package com.fatec.scel.po;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import com.fatec.scel.controller.LivroController;
import com.fatec.scel.model.LivroRepository;

public class PageCadastrarLivro {
    Logger logger = LogManager.getLogger(LivroController.class);
    @Autowired LivroRepository livroRepository;
    private WebDriver driver;
    private By isbnBy = By.id("isbn");
    private By autorBy = By.id("autor");
    private By tituloBy = By.id("titulo");
    private By btnCadastrarBy = By.cssSelector(".btn-primary:nth-child(1)");
    private By mensagemBy = By.cssSelector(".text-danger");
    
    public PageCadastrarLivro(WebDriver driver) {
        this.driver = driver;
    }
    
    /**
     * Cadastrar livro
     * @param isbn
     * @param autor
     * @param titulo
     * @return
    */
    public PageCadastrarLivro cadastrar(String isbn, String autor, String titulo) { driver.findElement(isbnBy).sendKeys(isbn);
        driver.findElement(autorBy).sendKeys(autor);
        driver.findElement(tituloBy).sendKeys(titulo);
        driver.findElement(btnCadastrarBy).click();
        return new PageCadastrarLivro(driver);
    }
    
    public String getMessageText() {
        return driver.findElement(mensagemBy).getText();
    }
}