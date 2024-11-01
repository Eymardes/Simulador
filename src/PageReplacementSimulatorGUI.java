import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class PageReplacementSimulatorGUI extends JFrame {
    private JTextField pageSequenceField;
    private JTextField frameCountField;
    private JTextArea resultArea;

    public PageReplacementSimulatorGUI() {
        setTitle("Simulador de Algoritmos de Substituição de Páginas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JLabel pageSequenceLabel = new JLabel("Sequência de Páginas (separadas por vírgula): ");
        pageSequenceField = new JTextField();

        JLabel frameCountLabel = new JLabel("Número de Frames: ");
        frameCountField = new JTextField();

        JButton simulateButton = new JButton("Simular");
        simulateButton.addActionListener(new SimulateButtonListener());

        inputPanel.add(pageSequenceLabel);
        inputPanel.add(pageSequenceField);
        inputPanel.add(frameCountLabel);
        inputPanel.add(frameCountField);
        inputPanel.add(new JLabel());
        inputPanel.add(simulateButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
    }

    private class SimulateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String[] pageSequenceStr = pageSequenceField.getText().split(",");
                int[] pageSequence = Arrays.stream(pageSequenceStr).mapToInt(Integer::parseInt).toArray();
                int frameCount = Integer.parseInt(frameCountField.getText());

                PageReplacementSimulator simulator = new PageReplacementSimulator(pageSequence, frameCount);
                resultArea.setText("Resultados da Simulação:\n");
                resultArea.append("Método FIFO - " + simulator.runFIFO() + " faltas de página\n");
                resultArea.append("Método LRU - " + simulator.runLRU() + " faltas de página\n");
                resultArea.append("Método Clock - " + simulator.runClock() + " faltas de página\n");
                resultArea.append("Método Ótimo - " + simulator.runOptimal() + " faltas de página\n");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro na entrada. Verifique os valores inseridos.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PageReplacementSimulatorGUI frame = new PageReplacementSimulatorGUI();
            frame.setVisible(true);
        });
    }
}
