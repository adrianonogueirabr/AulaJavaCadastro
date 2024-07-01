package view;

import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import DAO.DAO;
import model.Cliente;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nome;
	private JTextField cpfcnpj;
	private JTextField telefone;
	private JTextField email;
	private JTextArea endereco;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCadastro frame = new JCadastro(null,null);
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
	public JCadastro(Cliente clienteSelecionado, JPrincipal jPrincipal) {
		DAO dao = new DAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 365);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NOME*");
		lblNewLabel.setBounds(32, 41, 46, 20);
		contentPane.add(lblNewLabel);
		
		nome = new JTextField();
		nome.setBounds(32, 62, 447, 22);
		contentPane.add(nome);
		nome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CPFCNPJ*");
		lblNewLabel_1.setBounds(32, 100, 72, 14);
		contentPane.add(lblNewLabel_1);
		
		cpfcnpj = new JTextField();
		cpfcnpj.setBounds(32, 115, 222, 22);
		contentPane.add(cpfcnpj);
		cpfcnpj.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("TELEFONE");
		lblNewLabel_2.setBounds(269, 100, 72, 14);
		contentPane.add(lblNewLabel_2);
		
		telefone = new JTextField();
		telefone.setBounds(269, 115, 210, 22);
		contentPane.add(telefone);
		telefone.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("EMAIL");
		lblNewLabel_3.setBounds(32, 158, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		email = new JTextField();
		email.setBounds(32, 173, 447, 22);
		contentPane.add(email);
		email.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("ENDERECO");
		lblNewLabel_4.setBounds(32, 215, 100, 14);
		contentPane.add(lblNewLabel_4);
		
		endereco = new JTextArea();
		endereco.setBorder(new LineBorder(new Color(0, 0, 0)));
		endereco.setBounds(32, 229, 447, 49);
		contentPane.add(endereco);
		
		JButton btnNewButton = new JButton(clienteSelecionado==null?"INCLUIR":"ALTERAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cliente cliente = new Cliente(null, nome.getText(), cpfcnpj.getText(), email.getText(),telefone.getText(), endereco.getText());
					if(clienteSelecionado==null) {
						if(!"".equalsIgnoreCase(nome.getText()) && !"".equalsIgnoreCase(cpfcnpj.getText())){
							dao.cadastrarCliente(cliente);
							abrirTelaPrincipal(jPrincipal);
						}else {
							JOptionPane.showMessageDialog(null, "Necessario preencher todos campos obrigatorios","",JOptionPane.WARNING_MESSAGE);
						}
				}else {					
						if(!"".equalsIgnoreCase(nome.getText()) && !"".equalsIgnoreCase(cpfcnpj.getText())){
							dao.alterarCliente(clienteSelecionado.getId(), cliente);
							abrirTelaPrincipal(jPrincipal);
						}else {
							JOptionPane.showMessageDialog(null, "Necessario preencher todos campos obrigatorios","",JOptionPane.WARNING_MESSAGE);
						}					
				}
					
			}
		});
		btnNewButton.setBounds(390, 289, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("EXCLUIR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dao.excluirCliente(clienteSelecionado.getId());
				abrirTelaPrincipal(jPrincipal);
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.setBounds(32, 289, 89, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.setVisible(false);
		
		if(clienteSelecionado!=null) {
			preencherCampos(clienteSelecionado);
			btnNewButton_1.setVisible(true);

		}
	}
	
	private void preencherCampos(Cliente clienteSelecionado) {
		nome.setText(clienteSelecionado.getNome());
		cpfcnpj.setText(clienteSelecionado.getCpfcnpj());
		email.setText(clienteSelecionado.getEmail());
		telefone.setText(clienteSelecionado.getTelefone());
		endereco.setText(clienteSelecionado.getEndereco());
		
	}
	
	private void abrirTelaPrincipal(JPrincipal jPrincipal) {
		jPrincipal.dispose();
		dispose();
		jPrincipal = new JPrincipal();
		jPrincipal.setLocationRelativeTo(jPrincipal);
		jPrincipal.setVisible(true);
	}
}