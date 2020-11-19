package com.fatec.scel.ts2;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.fatec.scel.dd.DriverFactory;
import com.fatec.scel.dd.ManipulaExcel;
import com.fatec.scel.model.LivroRepository;
import com.fatec.scel.po.PageCadastrarLivro;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class REQ01CadastrarLivroDD {
    static private WebDriver driver;
    static private Map<String, Object> vars;
    static JavascriptExecutor js;
    private PageCadastrarLivro pageCadastrarLivro;
    @Autowired
    LivroRepository repository;
    
    @BeforeAll
    public static void inicializa() {
        driver = DriverFactory.getDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        try {
            ManipulaExcel.setExcelFile("D:\\dd\\cadastrarUsuario.xlsx", "Planilha1");
        } catch (Exception e) {
            System.out.println("erro =============>" + e.getMessage());
        }
    }
    
    @AfterAll
    public static void tearDown() {
        DriverFactory.finaliza();
    }
    
    @Test
    public void cadastrarLivro() throws Exception {
        int linha = 1;
        while (!ManipulaExcel.getCellData(linha, 0).equals("final".trim())) {
            System.out.println("linha ==> " + linha);
            pageCadastrarLivro = new PageCadastrarLivro(driver);
            driver.get("http://localhost:8080/livros/cadastrar");
            try {
                pageCadastrarLivro.cadastrar(
                    ManipulaExcel.getCellData(linha,0),
                    ManipulaExcel.getCellData(linha, 1),
                    ManipulaExcel.getCellData(linha, 2)
                );
                assertEquals(ManipulaExcel.getCellData(linha, 3), pageCadastrarLivro.getMessageText());
            } catch (Exception e) {
                fail("Erro nao esperado - " + e.getMessage());
                System.out.println(e.getMessage());
            }
            linha = linha + 1;
        }
    }
}