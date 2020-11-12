package com.fatec.scel;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.fatec.scel.model.Aluno;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class REQ01CadastrarAlunoTSTests {
    private WebDriver driver;
    
    @Test
    public void ct01_cadastro_de_aluno_com_sucesso () {
        System.setProperty("webdriver.chrome.driver", "browserDriver/chromedriver.exe");
        driver = new ChromeDriver();
        
        driver.get("http://localhost:8080/alunos/cadastrar");
        
        driver.findElement(By.id("ra")).sendKeys("99999");
        driver.findElement(By.id("nome")).sendKeys("John Doe");
        driver.findElement(By.id("email")).sendKeys("mail@mail.com");
        driver.findElement(By.id("cep")).sendKeys("00000000");
        driver.findElement(By.id("endereco")).sendKeys("Rua Direita");

        driver.findElement(By.cssSelector(".btn-primary:nth-child(1)")).click();
        
        assertEquals("Aluno cadastrado", driver.findElement(By.cssSelector(".text-danger")).getText());
        
        driver.quit();
    }

    @Test
    public void ct02_cadastro_de_aluno_ja_cadastrado () {
        System.setProperty("webdriver.chrome.driver", "browserDriver/chromedriver.exe");
        driver = new ChromeDriver();
        
        driver.get("http://localhost:8080/alunos/cadastrar");
        
        driver.findElement(By.id("ra")).sendKeys("99999");
        driver.findElement(By.id("nome")).sendKeys("John Doe");
        driver.findElement(By.id("email")).sendKeys("mail@mail.com");
        driver.findElement(By.id("cep")).sendKeys("00000000");
        driver.findElement(By.id("endereco")).sendKeys("Rua Direita");

        driver.findElement(By.cssSelector(".btn-primary:nth-child(1)")).click();
        
        assertEquals("Aluno ja cadastrado", driver.findElement(By.cssSelector(".text-danger")).getText());
        
        driver.quit();
    }
}