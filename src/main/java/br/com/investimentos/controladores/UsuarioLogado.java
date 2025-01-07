package br.com.investimentos.controladores;

import br.com.investimentos.usuarios.UsuarioAdministrador;
import br.com.investimentos.usuarios.UsuarioComum;

public class UsuarioLogado {
    private static UsuarioLogado instancia;
    private UsuarioComum usuarioComum;
    private UsuarioAdministrador usuarioAdministrador;

    private UsuarioLogado() {}

    public static UsuarioLogado getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioLogado();
        }
        return instancia;
    }

    public UsuarioComum getUsuarioComum() {
        return usuarioComum;
    }

    public void setUsuarioComum(UsuarioComum usuarioComum) {
        this.usuarioComum = usuarioComum;
    }

    public UsuarioAdministrador getUsuarioAdministrador() {
        return usuarioAdministrador;
    }

    public void setUsuarioAdministrador(UsuarioAdministrador usuarioAdministrador) {
        this.usuarioAdministrador = usuarioAdministrador;
    }
}
