package br.furb.aed.trabalho.main;

import java.awt.EventQueue;

import br.furb.aed.trabalho.view.TrabalhoView;

/**
 * Trabalho tem por objetivo definir a classe Main do trabalho, instanciar a classe de View ({@link TrabalhoView}) e exibir o Frame.
 * 
 * @author Ariel
 * @author Gabriel Garcia
 * @author Sidnei
 *
 */
public class Trabalho {

	/**
	 * Classe main do projeto.
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrabalhoView trabalhoView = new TrabalhoView();
					trabalhoView.mostrar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
