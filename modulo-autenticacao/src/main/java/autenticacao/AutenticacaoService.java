package autenticacao;

import java.util.HashMap;
import java.util.Map;

public class AutenticacaoService {


    private Map<String, Usuario> bancoDeDados = new HashMap<>();

    public void cadastrarUsuario(Usuario usuario) {
        bancoDeDados.put(usuario.getLogin(), usuario);
    }

    public boolean autenticar(String login, String senha) {

        if (login == null || login.isBlank() || senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Usuário ou senha não podem ser nulos ou vazios");
        }

        Usuario usuario = bancoDeDados.get(login);

        if (usuario == null) {
            throw new RuntimeException("Usuário inexistente");
        }


        if (usuario.isBloqueado()) {
            throw new RuntimeException("Usuário bloqueado");
        }


        if (!usuario.getSenha().equals(senha)) {
            usuario.registrarTentativaFalha();
            throw new RuntimeException("Senha inválida");
        }


        usuario.resetarTentativas();
        return true;
    }
}