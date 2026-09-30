package autenticacao;

public class Usuario {
    private String login;
    private String senha;
    private boolean bloqueado;
    private int tentativasFalhas;
    private String nivel; // ADMIN, GERENTE, CLIENTE

    public Usuario(String login, String senha, String nivel) {
        this.login = login;
        this.senha = senha;
        this.nivel = nivel;
        this.bloqueado = false;
        this.tentativasFalhas = 0;
    }

    public String getLogin() { return login; }
    public String getSenha() { return senha; }

    public boolean isBloqueado() { return bloqueado; }
    public void setBloqueado(boolean bloqueado) { this.bloqueado = bloqueado; }

    public int getTentativasFalhas() { return tentativasFalhas; }

    public void registrarTentativaFalha() {
        this.tentativasFalhas++;
        if (this.tentativasFalhas >= 3) {
            this.bloqueado = true;
        }
    }

    public void resetarTentativas() {
        this.tentativasFalhas = 0;
    }
}