package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JEditorPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;

import controller.Criptografia;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField textSenha;
	JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JLogin frame = new JLogin();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(21, 11, 572, 255);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(318, 66, 46, 14);
		panel.add(lblNewLabel);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(318, 80, 169, 20);
		panel.add(textUsuario);
		textUsuario.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(318, 122, 46, 14);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Criptografia criptografia = new Criptografia(textSenha.getText(), Criptografia.MD5);				
				
				if(textUsuario.getText()!=null && !textUsuario.getText().isEmpty() && textSenha.getText()!=null &&  !textSenha.getText().isEmpty()) {
					if(criptografia.criptografar().equals("E10ADC3949BA59ABBE56E057F20F883E")) {
						JOptionPane.showMessageDialog(btnNewButton, "Informacoes Validas", "AVISO",JOptionPane.INFORMATION_MESSAGE);
						dispose();
						JPrincipal jPrincipal = new JPrincipal();
						jPrincipal.setLocationRelativeTo(jPrincipal);
						jPrincipal.setVisible(true);
					}				
				}else {
					JOptionPane.showMessageDialog(btnNewButton, "Informacoes Invalida", "AVISO",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(358, 181, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Bem Vindo");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(272, 11, 277, 23);
		panel.add(lblNewLabel_2);
		
		textSenha = new JPasswordField();
		textSenha.setBounds(318, 136, 169, 20);
		panel.add(textSenha);
		
		lblNewLabel_3 = new JLabel("");		
		lblNewLabel_3.setIcon(new ImageIcon(JLogin.class.getResource("/view/tes.png")));	
		lblNewLabel_3.setBounds(10, 11, 256, 233);
		panel.add(lblNewLabel_3);
	}
}
