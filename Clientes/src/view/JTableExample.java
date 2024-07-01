package view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class JTableExample {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Exemplo de Tamanho de Células em JTable");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Dados da tabela (exemplo simples)
            Object[][] data = {
                    {"1", "João", "joao@example.com"},
                    {"2", "Maria", "maria@example.com"},
                    {"3", "Pedro", "pedro@example.com"}
            };
            String[] columnNames = {"ID", "Nome", "Email"};

            // Criando o modelo da tabela
            DefaultTableModel model = new DefaultTableModel(data, columnNames);

            // Criando a tabela com o modelo
            JTable table = new JTable(model);

            // Define a altura das linhas da tabela
            table.setRowHeight(30); // Define a altura das linhas para 30 pixels

            // Define a largura preferida da coluna ID
            TableColumnModel columnModel = table.getColumnModel();
            TableColumn column = columnModel.getColumn(0); // Coluna ID
            column.setPreferredWidth(100); // Define a largura da coluna ID para 100 pixels

            // Configurando a barra de rolagem
            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setPreferredSize(new Dimension(400, 200));

            // Adicionando a tabela ao JFrame
            frame.add(scrollPane, BorderLayout.CENTER);

            frame.pack();
            frame.setLocationRelativeTo(null); // Centraliza a janela na tela
            frame.setVisible(true);
        });
    }
}
