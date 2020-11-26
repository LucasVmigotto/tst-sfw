package com.fatec.scel.ts2;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.fatec.scel.dd.DriverFactory;
import com.fatec.scel.dd.ManipulaExcel;
import com.fatec.scel.po.PageCadastrarLivro;
import com.fatec.scel.po.PageMenu;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class REQ01CadastrarLivroDD {
    static private WebDriver driver;
    static private Map<String, Object> vars;
    static JavascriptExecutor js;
    private PageMenu pageMenu;
    private PageCadastrarLivro pageCadastrarLivro;
    private static Logger logger;
    
    @BeforeAll
    public static void inicializa() {
        logger = LogManager.getLogger(REQ01CadastrarLivroDD.class);
        driver = DriverFactory.getDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        try {
            ManipulaExcel.setExcelFile("E:\\dd\\cadastrarUsuario.xlsx", "Planilha1");
        } catch (Exception e) {
            logger.info("Erro no path ou workbook =============>" + e.getMessage());
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
            logger.info("==============> linha = " + linha);
            pageMenu = new PageMenu(driver);
            driver.get("http://localhost:8080/");
            pageMenu.click_na_opcao_cadastrarLivros();
            pageCadastrarLivro = new PageCadastrarLivro(driver);
            try {
                pageCadastrarLivro.cadastrar(
                    ManipulaExcel.getCellData(linha, 0),
                    ManipulaExcel.getCellData(linha, 1),
                    ManipulaExcel.getCellData(linha, 2)
                );
                assertEquals(ManipulaExcel.getCellData(linha, 3), pageCadastrarLivro.getMessageText());
            } catch (Exception e) {
                logger.info("Erro nao esperado=" + e.getMessage());
                System.out.println(e.getMessage());
            }
            linha = linha + 1;
        }
    }
}