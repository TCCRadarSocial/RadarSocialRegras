package tcc.radarsocialregras.service;

import java.text.ParseException;

import tcc.radarsocial.servico.IntegracaoFacebook;

public class ExecuteServices implements Runnable{

	private String nome;
	private IntegracaoFacebook login;

    public ExecuteServices(String nome,IntegracaoFacebook login) {
        this.nome = nome;
        this.login = login;
    }

    public void run() {
    	try {
			login.retornaJson(nome.toLowerCase());
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
}
