package br.furb.aed.trabalho.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.furb.aed.trabalho.Calculadora;
import br.furb.aed.trabalho.facade.CalculadoraFacade;

import java.awt.FlowLayout;
import javax.swing.JButton;

/**
 * TrabalhoView tem por objetivo definir a classe principal da camada View do projeto. Esta classe permite fazer uso da {@link Calculadora}
 * 
 * @author Ariel
 * @author Gabriel Garcia
 * @author Sidnei
 *
 */
public class TrabalhoView {
	
	private final CalculadoraFacade calculadoraFacade = new CalculadoraFacade();

	private JFrame frmTrabalho;
	private JTextField txtExpressao;
	private JLabel lblNewLabel;
	private JTextField txtResultado;


	/**
	 * Create the application.
	 */
	public TrabalhoView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTrabalho = new JFrame();
		frmTrabalho.setResizable(false);
		frmTrabalho.setTitle("Trabalho 1 - AED");
		frmTrabalho.setBounds(100, 100, 602, 154);
		frmTrabalho.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmTrabalho.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNewLabel = new JLabel("Expressão");
		panel.add(lblNewLabel);
		
		txtExpressao = new JTextField();
		panel.add(txtExpressao);
		txtExpressao.setColumns(50);
		
		JLabel lblResultado = new JLabel("Resultado");
		panel.add(lblResultado);
		
		txtResultado = new JTextField();
		txtResultado.setColumns(50);
		panel.add(txtResultado);
		
		JPanel panel_1 = new JPanel();
		frmTrabalho.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JLabel lblArielAdonaiSouza = new JLabel("Ariel Adonai Souza | Gabriel Garcia Salvador | Sidnei Lanser");
		panel_1.add(lblArielAdonaiSouza);
		
		JPanel panel_2 = new JPanel();
		frmTrabalho.getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Calcular");
		btnNewButton.addActionListener((a) -> calcularClick());
		panel_2.add(btnNewButton);
	}
	
	
	private void calcularClick() {
		double valor = calculadoraFacade.calcularExpressao(txtExpressao.getText());
		txtResultado.setText(String.valueOf(valor));
	}

	public void mostrar() {
		frmTrabalho.setVisible(true);
	}
}
