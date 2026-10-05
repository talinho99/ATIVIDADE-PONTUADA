
import autenticacao.AutenticacaoService;
import autenticacao.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AutenticacaoServiceTest {

    private AutenticacaoService service;
    private Usuario usuarioValido;

    @BeforeEach
    void setup() {
        service = new AutenticacaoService();

        usuarioValido = new Usuario("joao.silva", "Java@12345", "CLIENTE");
        service.cadastrarUsuario(usuarioValido);
    }

    @Test
    void aut01_deveAutenticarUsuarioValido() {

        boolean resultado = service.autenticar("joao.silva", "Java@12345");
        Assertions.assertTrue(resultado);
    }

    @Test
    void aut02_deveRejeitarUsuarioInexistente() {

        RuntimeException erro = Assertions.assertThrows(RuntimeException.class, () -> {
            service.autenticar("usuario.fantasma", "Java@12345");
        });
        Assertions.assertEquals("Usuário inexistente", erro.getMessage());
    }

    @Test
    void aut03_deveRejeitarSenhaInvalida() {

        RuntimeException erro = Assertions.assertThrows(RuntimeException.class, () -> {
            service.autenticar("joao.silva", "SenhaErrada");
        });
        Assertions.assertEquals("Senha inválida", erro.getMessage());
    }

    @Test
    void aut04_deveRejeitarUsuarioNulo() {
        // AUT04 - Usuário nulo
        IllegalArgumentException erro = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.autenticar(null, "Java@12345");
        });
        Assertions.assertEquals("Usuário ou senha não podem ser nulos ou vazios", erro.getMessage());
    }

    @Test
    void aut05_deveRejeitarSenhaNula() {

        IllegalArgumentException erro = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.autenticar("joao.silva", null);
        });
        Assertions.assertEquals("Usuário ou senha não podem ser nulos ou vazios", erro.getMessage());
    }

    @Test
    void aut06_deveRejeitarUsuarioVazio() {

        IllegalArgumentException erro = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.autenticar("", "Java@12345");
        });
        Assertions.assertEquals("Usuário ou senha não podem ser nulos ou vazios", erro.getMessage());
    }

    @Test
    void aut07_deveRejeitarSenhaVazia() {

        IllegalArgumentException erro = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.autenticar("joao.silva", "");
        });
        Assertions.assertEquals("Usuário ou senha não podem ser nulos ou vazios", erro.getMessage());
    }

    @Test
    void aut09_aut10_aut11_deveBloquearAposTresTentativasInvalidas() {



        Assertions.assertThrows(RuntimeException.class, () -> service.autenticar("joao.silva", "Erro1"));
        Assertions.assertFalse(usuarioValido.isBloqueado(), "Após 1 falha, NÃO deve estar bloqueado");

        Assertions.assertThrows(RuntimeException.class, () -> service.autenticar("joao.silva", "Erro2"));
        Assertions.assertFalse(usuarioValido.isBloqueado(), "Após 2 falhas, NÃO deve estar bloqueado");


        Assertions.assertThrows(RuntimeException.class, () -> service.autenticar("joao.silva", "Erro3"));
        Assertions.assertTrue(usuarioValido.isBloqueado(), "Após 3 falhas, DEVE estar bloqueado");
    }

    @Test
    void aut08_aut12_deveRejeitarTentativaAposBloqueio() {

        usuarioValido.setBloqueado(true);

        RuntimeException erro = Assertions.assertThrows(RuntimeException.class, () -> {
            // Mesmo com a palavra-passe correta, deve rejeitar se estiver bloqueado
            service.autenticar("joao.silva", "Java@12345");
        });
        Assertions.assertEquals("Usuário bloqueado", erro.getMessage());
    }
}