
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
        // CT01 - Senha válida
        // Ajustei a entrada do PDF "Java@1234" (9 caracteres) para "Java@12345" (10 caracteres)
        // para que ela realmente passe na validação de tamanho (10 a 12).
        String senha = "Java@12345";
        boolean resultado = service.validarSenha(senha);
        Assertions.assertTrue(resultado);
    }

    @Test
    void ct02_deveRejeitarSenhaMenorQue10Caracteres() {
        // CT02 - Menor que 10 caracteres (9 caracteres)
        boolean resultado = service.validarSenha("Java@1234");
        Assertions.assertFalse(resultado);
    }

    @Test
    void ct03_deveRejeitarSenhaMaiorQue12Caracteres() {
        // CT03 - Maior que 12 caracteres (13 caracteres)
        boolean resultado = service.validarSenha("Java@12345678");
        Assertions.assertFalse(resultado);
    }

    @Test
    void ct04_deveRejeitarSenhaSemNumero() {
        // CT04 - Sem número (ajustado para 10 caracteres para isolar a falha da regra de números)
        boolean resultado = service.validarSenha("Java@Teste");
        Assertions.assertFalse(resultado);
    }

    @Test
    void ct05_deveRejeitarSenhaSemLetra() {
        // CT05 - Sem letra (10 caracteres)
        boolean resultado = service.validarSenha("123456@789");
        Assertions.assertFalse(resultado);
    }

    @Test
    void ct06_deveRejeitarSenhaSemEspecial() {
        // CT06 - Sem especial (10 caracteres)
        boolean resultado = service.validarSenha("Java123456");
        Assertions.assertFalse(resultado);
    }

    @Test
    void ct07_deveRejeitarSenhaNula() {
        // CT07 - Senha nula
        boolean resultado = service.validarSenha(null);
        Assertions.assertFalse(resultado);
    }

    @Test
    void ct08_deveRejeitarSenhaVazia() {
        // CT08 - Senha vazia
        boolean resultado = service.validarSenha("");
        Assertions.assertFalse(resultado);
    }

    @Test
    void ct09_deveAceitarSenhaExatamente10Caracteres() {
        // CT09 - Exatamente 10 caracteres (limite mínimo)
        boolean resultado = service.validarSenha("Java@12345");
        Assertions.assertTrue(resultado);
    }

    @Test
    void ct10_deveAceitarSenhaExatamente12Caracteres() {
        // CT10 - Exatamente 12 caracteres (limite máximo)
        boolean resultado = service.validarSenha("Java@1234567");
        Assertions.assertTrue(resultado);
    }

    @Test
    void testeFronteira_deveAceitarSenhaCom11Caracteres() {
        // Atividade 4 - Valor dentro dos limites de fronteira (11 caracteres)
        boolean resultado = service.validarSenha("Java@123456");
        Assertions.assertTrue(resultado);
    }
}