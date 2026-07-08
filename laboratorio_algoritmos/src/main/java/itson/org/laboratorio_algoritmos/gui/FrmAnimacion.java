package itson.org.laboratorio_algoritmos.gui;

import itson.org.laboratorio_algoritmos.datos.GeneradorDeArreglos;
import itson.org.laboratorio_algoritmos.datos.PasoAnimacion;
import itson.org.laboratorio_algoritmos.ordenamientos.VisualizadorOrdenamiento;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingWorker;

/**
 * Ventana de animacion visual de algoritmos de ordenamiento. Muestra
 * barras que cambian de color segun el paso actual y permite controlar
 * el algoritmo, el tamano del arreglo y la velocidad de animacion.
 * 
 * @author andres
 */
public class FrmAnimacion extends javax.swing.JFrame {

    private int[] arreglo;
    private AnimacionWorker worker;
    private final int tamanoInicial;
    private final GeneradorDeArreglos.TipoArreglo tipoInicial;

    public FrmAnimacion() {
        this(30, GeneradorDeArreglos.TipoArreglo.ALEATORIO);
    }

    public FrmAnimacion(int tamano, GeneradorDeArreglos.TipoArreglo tipo) {
        this.tamanoInicial = Math.min(Math.max(tamano, 10), 200);
        this.tipoInicial = tipo;
        initComponents();
        spnTamano.setValue(this.tamanoInicial);
        generarArreglo();
    }

    private void generarArreglo() {
        int tamano = (int) spnTamano.getValue();
        arreglo = GeneradorDeArreglos.generar(tamano, tipoInicial);
        panelBarras.setArreglo(arreglo);
        btnIniciar.setEnabled(true);
        lblEstado.setText("Listo");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        pnlHeader = new itson.org.laboratorio_algoritmos.gui.utils.PanelRedondeado();
        lblTitulo = new javax.swing.JLabel();
        panelBarrasContainer = new itson.org.laboratorio_algoritmos.gui.utils.PanelRedondeado();
        panelBarras = new itson.org.laboratorio_algoritmos.gui.utils.PanelBarras();
        pnlControles = new javax.swing.JPanel();
        lblAlgoritmo = new javax.swing.JLabel();
        cboAlgoritmo = new javax.swing.JComboBox<>();
        lblTamano = new javax.swing.JLabel();
        spnTamano = new javax.swing.JSpinner();
        lblVelocidad = new javax.swing.JLabel();
        cboVelocidad = new javax.swing.JComboBox<>();
        btnGenerar = new itson.org.laboratorio_algoritmos.gui.utils.BotonRedondeado();
        btnIniciar = new itson.org.laboratorio_algoritmos.gui.utils.BotonRedondeado();
        btnVolver = new itson.org.laboratorio_algoritmos.gui.utils.BotonRedondeado();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visualizador de ordenamiento");

        pnlPrincipal.setBackground(new java.awt.Color(237, 248, 255));

        pnlHeader.setBackground(new java.awt.Color(208, 220, 254));

        lblTitulo.setFont(new java.awt.Font("Corbel", 1, 24));
        lblTitulo.setForeground(new java.awt.Color(31, 0, 78));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Visualizador de ordenamiento");

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblTitulo)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        panelBarrasContainer.setBackground(new java.awt.Color(208, 220, 254));

        panelBarras.setBackground(new java.awt.Color(237, 248, 255));

        javax.swing.GroupLayout panelBarrasContainerLayout = new javax.swing.GroupLayout(panelBarrasContainer);
        panelBarrasContainer.setLayout(panelBarrasContainerLayout);
        panelBarrasContainerLayout.setHorizontalGroup(
            panelBarrasContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarrasContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBarras, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBarrasContainerLayout.setVerticalGroup(
            panelBarrasContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBarrasContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBarras, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlControles.setBackground(new java.awt.Color(237, 248, 255));

        lblAlgoritmo.setFont(new java.awt.Font("Corbel", 0, 16));
        lblAlgoritmo.setForeground(new java.awt.Color(31, 0, 78));
        lblAlgoritmo.setText("Algoritmo:");

        cboAlgoritmo.setFont(new java.awt.Font("Corbel", 0, 14));
        cboAlgoritmo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bubble Sort", "Selection Sort", "Insertion Sort" }));

        lblTamano.setFont(new java.awt.Font("Corbel", 0, 16));
        lblTamano.setForeground(new java.awt.Color(31, 0, 78));
        lblTamano.setText("Tama\u00f1o:");

        spnTamano.setFont(new java.awt.Font("Corbel", 0, 14));
        spnTamano.setModel(new javax.swing.SpinnerNumberModel(30, 10, 200, 5));

        lblVelocidad.setFont(new java.awt.Font("Corbel", 0, 16));
        lblVelocidad.setForeground(new java.awt.Color(31, 0, 78));
        lblVelocidad.setText("Velocidad:");

        cboVelocidad.setFont(new java.awt.Font("Corbel", 0, 14));
        cboVelocidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Instant\u00e1nea (1 ms)", "R\u00e1pida (25 ms)", "Media (100 ms)", "Lenta (250 ms)", "Muy lenta (500 ms)" }));

        btnGenerar.setBackground(new java.awt.Color(208, 220, 254));
        btnGenerar.setForeground(new java.awt.Color(31, 0, 78));
        btnGenerar.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnGenerar.setText("Generar");
        btnGenerar.addActionListener(this::btnGenerarActionPerformed);

        btnIniciar.setBackground(new java.awt.Color(208, 220, 254));
        btnIniciar.setForeground(new java.awt.Color(31, 0, 78));
        btnIniciar.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(this::btnIniciarActionPerformed);

        btnVolver.setBackground(new java.awt.Color(208, 220, 254));
        btnVolver.setForeground(new java.awt.Color(31, 0, 78));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnVolver.setText("Volver");
        btnVolver.addActionListener(e -> dispose());

        lblEstado = new javax.swing.JLabel();
        lblEstado.setFont(new java.awt.Font("Corbel", 1, 16));
        lblEstado.setForeground(new java.awt.Color(31, 0, 78));
        lblEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEstado.setText("Listo");

        javax.swing.GroupLayout pnlControlesLayout = new javax.swing.GroupLayout(pnlControles);
        pnlControles.setLayout(pnlControlesLayout);
        pnlControlesLayout.setHorizontalGroup(
            pnlControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlesLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(lblAlgoritmo)
                .addGap(5, 5, 5)
                .addComponent(cboAlgoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblTamano)
                .addGap(5, 5, 5)
                .addComponent(spnTamano, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblVelocidad)
                .addGap(5, 5, 5)
                .addComponent(cboVelocidad, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(pnlControlesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblEstado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlControlesLayout.setVerticalGroup(
            pnlControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblAlgoritmo)
                    .addComponent(cboAlgoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTamano)
                    .addComponent(spnTamano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVelocidad)
                    .addComponent(cboVelocidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEstado)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelBarrasContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlControles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(pnlHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelBarrasContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pnlControles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent e) {
        if (worker != null && !worker.isDone()) {
            worker.cancel(true);
        }
        generarArreglo();
    }

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent e) {
        if (worker != null && !worker.isDone()) {
            worker.cancel(true);
        }
        btnIniciar.setEnabled(false);
        btnGenerar.setEnabled(false);
        cboAlgoritmo.setEnabled(false);
        spnTamano.setEnabled(false);

        lblEstado.setText("Ordenando...");

        String seleccion = (String) cboAlgoritmo.getSelectedItem();
        worker = new AnimacionWorker(arreglo, seleccion);
        worker.execute();
    }

    private class AnimacionWorker extends SwingWorker<Void, PasoAnimacion> {

        private final int[] arr;
        private final String algoritmo;

        AnimacionWorker(int[] arr, String algoritmo) {
            this.arr = arr;
            this.algoritmo = algoritmo;
        }

        @Override
        protected Void doInBackground() throws Exception {
            int delay;
            String vel = (String) cboVelocidad.getSelectedItem();
            if (vel == null || vel.startsWith("Instant\u00e1nea")) delay = 1;
            else if (vel.startsWith("R\u00e1pida")) delay = 25;
            else if (vel.startsWith("Media")) delay = 100;
            else if (vel.startsWith("Lenta")) delay = 250;
            else delay = 500;

            java.util.function.Consumer<PasoAnimacion> callback = paso -> {
                publish(paso);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            };

            switch (algoritmo) {
                case "Bubble Sort" -> VisualizadorOrdenamiento.bubbleSort(arr, callback);
                case "Selection Sort" -> VisualizadorOrdenamiento.selectionSort(arr, callback);
                case "Insertion Sort" -> VisualizadorOrdenamiento.insertionSort(arr, callback);
            }

            return null;
        }

        @Override
        protected void process(List<PasoAnimacion> pasos) {
            PasoAnimacion ultimo = pasos.get(pasos.size() - 1);
            panelBarras.setPaso(ultimo);
        }

        @Override
        protected void done() {
            btnGenerar.setEnabled(true);
            cboAlgoritmo.setEnabled(true);
            spnTamano.setEnabled(true);
            lblEstado.setText("Ordenado");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private itson.org.laboratorio_algoritmos.gui.utils.BotonRedondeado btnGenerar;
    private itson.org.laboratorio_algoritmos.gui.utils.BotonRedondeado btnIniciar;
    private itson.org.laboratorio_algoritmos.gui.utils.BotonRedondeado btnVolver;
    private javax.swing.JComboBox<String> cboAlgoritmo;
    private javax.swing.JLabel lblAlgoritmo;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblTamano;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVelocidad;
    private itson.org.laboratorio_algoritmos.gui.utils.PanelBarras panelBarras;
    private itson.org.laboratorio_algoritmos.gui.utils.PanelRedondeado panelBarrasContainer;
    private itson.org.laboratorio_algoritmos.gui.utils.PanelRedondeado pnlHeader;
    private javax.swing.JPanel pnlControles;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JComboBox<String> cboVelocidad;
    private javax.swing.JSpinner spnTamano;
    // End of variables declaration//GEN-END:variables
}
