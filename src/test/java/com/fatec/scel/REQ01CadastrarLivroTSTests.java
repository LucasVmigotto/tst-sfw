package com.fatec.scel;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.fatec.scel.model.Livro;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class REQ01CadastrarLivroTSTests {
    private WebDriver driver;
    
    @Test
    public void ct01cadastrodelivrocomsucesso () {
        System.setProperty("webdriver.chrome.driver", "browserDriver/chromedriver.exe");
        driver = new ChromeDriver();
        
        driver.get("http://localhost:8080/livros/cadastrar");
        
        driver.findElement(By.id("isbn")).sendKeys("1111");
        driver.findElement(By.id("autor")).sendKeys("Pressman");
        driver.findElement(By.id("titulo")).sendKeys("Engenharia de Software");
        driver.findElement(By.cssSelector(".btn-primary:nth-child(1)")).click();
        
        assertEquals("Livro cadastrado", driver.findElement(By.cssSelector(".text-danger")).getText());
        
        driver.quit();
    }

    @Test
    public void ct02cadastrodelivro_ja_cadastrado () {
        System.setProperty("webdriver.chrome.driver", "browserDriver/chromedriver.exe");
        driver = new ChromeDriver();
        
        driver.get("http://localhost:8080/livros/cadastrar");
        
        driver.findElement(By.id("isbn")).sendKeys("1111");
        driver.findElement(By.id("autor")).sendKeys("Pressman");
        driver.findElement(By.id("titulo")).sendKeys("Engenharia de Software");
        driver.findElement(By.cssSelector(".btn-primary:nth-child(1)")).click();
        
        assertEquals("Livro ja cadastrado", driver.findElement(By.cssSelector(".text-danger")).getText());
        
        driver.quit();
    }
}