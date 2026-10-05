
import autenticacao.ValidacaoSenhaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidacaoSenhaServiceTest {

    private ValidacaoSenhaService service;

    @BeforeEach
    void setup() {
        service = new ValidacaoSenhaService();
    }

    @Test
    void ct01_deveAceitarSenhaValida() {

        String senha = "Java@12345";
        boolean resultado = service.validarSenha(senha);
        Assertions.assertTrue(resultado);
    }

    @Test
    void ct02_deveRejeitarSenhaMenorQue10Caracteres() {

        boolean resultado = service.validarSenha("Java@1234");
        Assertions.assertFalse(resultado);
    }

    @Test
    void ct03_deveRejeitarSenhaMaiorQue12Caracteres() {

        boolean resultado = service.validarSenha("Java@12345678");
        Assertions.assertFalse(resultado);
    }

    @Test
    void ct04_deveRejeitarSenhaSemNumero() {

        boolean resultado = service.validarSenha("Java@Teste");
        Assertions.assertFalse(resultado);
    }

    @Test
    void ct05_deveRejeitarSenhaSemLetra() {

        boolean resultado = service.validarSenha("123456@789");
        Assertions.assertFalse(resultado);
    }

    @Test
    void ct06_deveRejeitarSenhaSemEspecial() {

        boolean resultado = service.validarSenha("Java123456");
        Assertions.assertFalse(resultado);
    }

    @Test
    void ct07_deveRejeitarSenhaNula() {

        boolean resultado = service.validarSenha(null);
        Assertions.assertFalse(resultado);
    }

    @Test
    void ct08_deveRejeitarSenhaVazia() {

        boolean resultado = service.validarSenha("");
        Assertions.assertFalse(resultado);
    }

    @Test
    void ct09_deveAceitarSenhaExatamente10Caracteres() {

        boolean resultado = service.validarSenha("Java@12345");
        Assertions.assertTrue(resultado);
    }

    @Test
    void ct10_deveAceitarSenhaExatamente12Caracteres() {

        boolean resultado = service.validarSenha("Java@1234567");
        Assertions.assertTrue(resultado);
    }

    @Test
    void testeFronteira_deveAceitarSenhaCom11Caracteres() {

        boolean resultado = service.validarSenha("Java@123456");
        Assertions.assertTrue(resultado);
    }
}