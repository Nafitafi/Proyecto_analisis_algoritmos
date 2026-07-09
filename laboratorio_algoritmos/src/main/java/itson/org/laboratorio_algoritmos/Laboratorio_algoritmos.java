package itson.org.laboratorio_algoritmos;

import itson.org.laboratorio_algoritmos.gui.FrmDashboard;
import javax.swing.JFrame;

public class Laboratorio_algoritmos {

    public static void main(String[] args) {
        FrmDashboard frame = new FrmDashboard();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
