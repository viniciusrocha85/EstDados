import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InterfaceSwing {
    private Planilha planilha;
    private JFrame frame;
    private JPanel yearPanel;
    private JPanel displayPanel;
    private JPanel buttonPanel;
    private JScrollPane scrollPane;

    public InterfaceSwing() {
        planilha = new Planilha();
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Planilha Financeira");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        yearPanel = new JPanel(new FlowLayout());
        yearPanel.setBackground(Color.LIGHT_GRAY);
        frame.add(yearPanel, BorderLayout.NORTH);

        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(displayPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        buttonPanel = new JPanel(new FlowLayout());
        frame.add(buttonPanel, BorderLayout.SOUTH);


        addButton("Adicionar Ano", buttonPanel, e -> adicionarAno());
        addButton("Adicionar Categoria", buttonPanel, e -> adicionarCategoria());
        addButton("Adicionar Ramo", buttonPanel, e -> adicionarRamo());
        addButton("Remover Ano", buttonPanel, e -> removerAno());
        addButton("Remover Categoria", buttonPanel, e -> removerCategoria());
        addButton("Remover Ramo", buttonPanel, e -> removerRamo());

        frame.setVisible(true);
    }

    private void addButton(String label, JPanel panel, ActionListener action) {
        JButton button = new JButton(label);
        button.addActionListener(action);
        panel.add(button);
    }

    private void adicionarAno() {
        String input = JOptionPane.showInputDialog(frame, "Digite o ano:");
        if (input != null) {
            try {
                int ano = Integer.parseInt(input);
                planilha.adicionarAno(ano);
                updateYearPanel();
                JOptionPane.showMessageDialog(frame, "Ano adicionado com sucesso!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Por favor, digite um número válido.");
            }
        }
    }

    private void adicionarCategoria() {
        String anoInput = JOptionPane.showInputDialog(frame, "Digite o ano:");
        if (anoInput != null) {
            int ano = Integer.parseInt(anoInput);
            Ano anoSelecionado = planilha.buscarAno(ano);

            if (anoSelecionado != null) {
                String nomeCategoria = JOptionPane.showInputDialog(frame, "Digite o nome da categoria:");
                if (nomeCategoria != null) {
                    anoSelecionado.adicionarCategoria(new Categoria(nomeCategoria));
                    updateDisplayPanel(anoSelecionado);
                    JOptionPane.showMessageDialog(frame, "Categoria adicionada com sucesso!");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Ano não encontrado.");
            }
        }
    }

    private void adicionarRamo() {
        String anoInput = JOptionPane.showInputDialog(frame, "Digite o ano:");
        if (anoInput != null) {
            int ano = Integer.parseInt(anoInput);
            Ano anoSelecionado = planilha.buscarAno(ano);

            if (anoSelecionado != null) {
                String nomeCategoria = JOptionPane.showInputDialog(frame, "Digite o nome da categoria:");
                Categoria categoriaSelecionada = planilha.buscarCategoria(anoSelecionado, nomeCategoria);

                if (categoriaSelecionada != null) {
                    String nomeRamo = JOptionPane.showInputDialog(frame, "Digite o nome do ramo:");
                    String valorInput = JOptionPane.showInputDialog(frame, "Digite o valor do ramo:");
                    if (nomeRamo != null && valorInput != null) {
                        double valor = Double.parseDouble(valorInput);
                        categoriaSelecionada.adicionarFilho(nomeRamo, valor);
                        updateDisplayPanel(anoSelecionado);
                        JOptionPane.showMessageDialog(frame, "Ramo adicionado com sucesso!");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Categoria não encontrada.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Ano não encontrado.");
            }
        }
    }

    private void removerAno() {
        String input = JOptionPane.showInputDialog(frame, "Digite o ano a remover:");
        if (input != null) {
            int ano = Integer.parseInt(input);
            if (planilha.removerAno(ano)) {
                updateYearPanel();
                displayPanel.removeAll();
                displayPanel.revalidate();
                displayPanel.repaint();
                JOptionPane.showMessageDialog(frame, "Ano removido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(frame, "Ano não encontrado.");
            }
        }
    }

    private void removerCategoria() {
        String anoInput = JOptionPane.showInputDialog(frame, "Digite o ano:");
        if (anoInput != null) {
            int ano = Integer.parseInt(anoInput);
            Ano anoSelecionado = planilha.buscarAno(ano);

            if (anoSelecionado != null) {
                String nomeCategoria = JOptionPane.showInputDialog(frame, "Digite o nome da categoria a remover:");
                if (planilha.removerCategoria(anoSelecionado, nomeCategoria)) {
                    updateDisplayPanel(anoSelecionado);
                    JOptionPane.showMessageDialog(frame, "Categoria removida com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Categoria não encontrada.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Ano não encontrado.");
            }
        }
    }

    private void removerRamo() {
        String anoInput = JOptionPane.showInputDialog(frame, "Digite o ano:");
        if (anoInput != null) {
            int ano = Integer.parseInt(anoInput);
            Ano anoSelecionado = planilha.buscarAno(ano);

            if (anoSelecionado != null) {
                String nomeCategoria = JOptionPane.showInputDialog(frame, "Digite o nome da categoria:");
                Categoria categoriaSelecionada = planilha.buscarCategoria(anoSelecionado, nomeCategoria);

                if (categoriaSelecionada != null) {
                    String nomeRamo = JOptionPane.showInputDialog(frame, "Digite o nome do ramo a remover:");
                    if (categoriaSelecionada.removerFilho(nomeRamo)) {
                        updateDisplayPanel(anoSelecionado);
                        JOptionPane.showMessageDialog(frame, "Ramo removido com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Ramo não encontrado.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Categoria não encontrada.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Ano não encontrado.");
            }
        }
    }

    private void updateYearPanel() {
        yearPanel.removeAll();
        for (int i = 0; i < planilha.getNumAnos(); i++) {
            Ano ano = planilha.getAno(i);
            JButton anoButton = new JButton(String.valueOf(ano.getAno()));
            anoButton.addActionListener(e -> updateDisplayPanel(ano));
            yearPanel.add(anoButton);
        }
        yearPanel.revalidate();
        yearPanel.repaint();
    }

    private void updateDisplayPanel(Ano anoSelecionado) {
        displayPanel.removeAll();
        for (Categoria categoria : anoSelecionado.getCategorias()) {
            if (categoria != null) {
                JLabel categoriaLabel = new JLabel(categoria.getNome() + " - Valor Total: R$ " + categoria.getValor());
                displayPanel.add(categoriaLabel);

                for (Categoria ramo : categoria.getFilhos()) {
                    if (ramo != null) {
                        JLabel ramoLabel = new JLabel("  Subcategoria: " + ramo.getNome() + " - R$ " + ramo.getValor());
                        displayPanel.add(ramoLabel);
                    }
                }
            }
        }
        displayPanel.revalidate();
        displayPanel.repaint();
    }

    public static void main(String[] args) {
        new InterfaceSwing();
    }
}
