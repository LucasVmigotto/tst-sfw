package com.fatec.scel;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

public class REQ01CadastrarLivroE2E {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  
  @Before
  public void setUp() {
	System.setProperty("webdriver.chrome.driver", "browserDriver/chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  
  @After
  public void tearDown() {
    driver.quit();
  }
  
  @Test
  public void cT01CadastroDeLivroComSucesso () {
    driver.get("http://localhost:8080/livros/cadastrar");
    driver.manage().window().setSize(new Dimension(1003, 681));
    
    // ISBN
    driver.findElement(By.name("isbn")).click();
    driver.findElement(By.name("isbn")).sendKeys("1111");

    // Titulo
    driver.findElement(By.name("titulo")).click();
    driver.findElement(By.name("titulo")).sendKeys("Testes");

    // Autor
    driver.findElement(By.name("autor")).click();
    driver.findElement(By.name("autor")).sendKeys("Sommerville");
    
    driver.findElement(By.id("submitButton")).click();
    
    assertThat(driver.findElement(By.cssSelector("text-danger")).getText(), is("Livro cadastrado."));
  }
}
