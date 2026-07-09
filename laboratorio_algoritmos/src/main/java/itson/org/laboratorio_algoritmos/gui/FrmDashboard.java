package itson.org.laboratorio_algoritmos.gui;

import itson.org.laboratorio_algoritmos.datos.GeneradorDeArreglos;
import itson.org.laboratorio_algoritmos.datos.PasoAnimacion;
import itson.org.laboratorio_algoritmos.datos.ResultadoOrdenamiento;
import itson.org.laboratorio_algoritmos.datos.TipoAlgoritmo;
import itson.org.laboratorio_algoritmos.gui.utils.BotonRedondeado;
import itson.org.laboratorio_algoritmos.gui.utils.PanelBarras;
import itson.org.laboratorio_algoritmos.gui.utils.PanelRedondeado;
import itson.org.laboratorio_algoritmos.ordenamientos.VisualizadorOrdenamiento;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.CategorySeries;
import org.knowm.xchart.XChartPanel;

public class FrmDashboard extends javax.swing.JFrame {

    private static final Color FONDO_GENERAL = new Color(248, 250, 252);
    private static final Color LATERAL = new Color(238, 242, 255);
    private static final Color TARJETA = new Color(255, 255, 255);
    private static final Color ACENTO = new Color(99, 102, 241);
    private static final Color ACENTO_BORDE = new Color(199, 210, 254);
    private static final Color TEXTO_OSCURO = new Color(30, 41, 59);
    private static final Color TEXTO_MUTED = new Color(100, 116, 139);
    private static final Color TEXTO_CLARO = new Color(255, 255, 255);
    private static final Color BORDE = new Color(226, 232, 240);

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmDashboard.class.getName());

    public FrmDashboard() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        javax.swing.UIManager.put("Table.background", TARJETA);
        javax.swing.UIManager.put("Table.foreground", TEXTO_OSCURO);
        javax.swing.UIManager.put("Table.selectionBackground", ACENTO);
        javax.swing.UIManager.put("Table.selectionForeground", TEXTO_CLARO);
        javax.swing.UIManager.put("Table.gridColor", BORDE);
        javax.swing.UIManager.put("TableHeader.background", FONDO_GENERAL);
        javax.swing.UIManager.put("TableHeader.foreground", TEXTO_OSCURO);
        javax.swing.UIManager.put("nimbusSelectionBackground", ACENTO);

        initComponents();
        aplicarEstiloModerno();
        configurarControles();
    }

    private void configurarControles() {
        jToggleButton2.setSelected(true);

        jSlider1.setMinimum(10);
        jSlider1.setMaximum(10000);
        jSlider1.setValue(100);
        jLabel1.setText("100");
        jSlider1.addChangeListener(e -> {
            jLabel1.setText(String.valueOf(jSlider1.getValue()));
        });

        jButton4.addActionListener(e -> {
            jSlider1.setValue(10);
            jLabel1.setText("10");
        });
        jButton3.addActionListener(e -> {
            jSlider1.setValue(100);
            jLabel1.setText("100");
        });
        jButton2.addActionListener(e -> {
            jSlider1.setValue(1000);
            jLabel1.setText("1,000");
        });
        jButton1.addActionListener(e -> {
            jSlider1.setValue(10000);
            jLabel1.setText("10,000");
        });

        jButton5.addActionListener(e -> ejecutar());

        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = jTable1.rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        String algoritmo = (String) jTable1.getValueAt(row, 0);
                        mostrarAnimacion(algoritmo);
                    }
                }
            }
        });
    }

    private void aplicarEstiloModerno() {
        setTitle("Laboratorio de Algoritmos de Ordenamiento");
        setMinimumSize(new java.awt.Dimension(1000, 650));
        setSize(Math.max(getWidth(), 1000), Math.max(getHeight(), 650));
        getContentPane().setBackground(FONDO_GENERAL);

        jPanel3.setBackground(LATERAL);
        jPanel3.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 1, BORDE),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)));

        Font fuenteLabel = new Font("Segoe UI", Font.BOLD, 13);
        Font fuenteTabla = new Font("Segoe UI", Font.PLAIN, 13);
        Font fuenteTab = new Font("Segoe UI", Font.BOLD, 14);

        estiloTarjeta(jPanel1);
        estiloTarjeta(jPanel2);
        estiloTarjeta(jPanel4);

        jLabel5.setForeground(TEXTO_MUTED);
        jLabel5.setFont(fuenteLabel);
        jLabel2.setForeground(TEXTO_MUTED);
        jLabel2.setFont(fuenteLabel);
        jLabel3.setForeground(TEXTO_MUTED);
        jLabel3.setFont(fuenteLabel);
        jLabel4.setForeground(TEXTO_MUTED);
        jLabel4.setFont(new Font("Segoe UI", Font.ITALIC, 11));

        jLabel1.setBackground(TARJETA);
        jLabel1.setForeground(ACENTO);
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 22));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setOpaque(true);
        jLabel1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACENTO_BORDE),
                BorderFactory.createEmptyBorder(4, 12, 4, 12)));

        estiloToggle(jToggleButton1);
        estiloToggle(jToggleButton2);
        estiloToggle(jToggleButton3);
        estiloToggle(jToggleButton4);

        estiloRadio(jRadioButton1);
        estiloRadio(jRadioButton2);
        estiloRadio(jRadioButton3);
        estiloRadio(jRadioButton4);
        estiloRadio(jRadioButton5);
        estiloRadio(jRadioButton6);

        estiloBotonPreset(jButton1);
        estiloBotonPreset(jButton2);
        estiloBotonPreset(jButton3);
        estiloBotonPreset(jButton4);

        jButton5.setBackground(ACENTO);
        jButton5.setForeground(TEXTO_CLARO);
        jButton5.setFont(new Font("Segoe UI", Font.BOLD, 22));
        jButton5.setBorder(BorderFactory.createEmptyBorder(14, 0, 14, 0));
        jButton5.setFocusPainted(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jTable1.setRowHeight(38);
        jTable1.setFont(fuenteTabla);
        jTable1.setShowHorizontalLines(true);
        jTable1.setShowVerticalLines(false);
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 1));
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        jTable1.getTableHeader().setForeground(TEXTO_OSCURO);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.getTableHeader().setResizingAllowed(false);
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane1.getViewport().setBackground(TARJETA);

        jTabbedPane1.setFont(fuenteTab);
        jTabbedPane1.setBorder(BorderFactory.createEmptyBorder());

        jSlider1.setBackground(LATERAL);
        jSlider1.setForeground(ACENTO);

        jPanel6.setBackground(TARJETA);
        jPanel6.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)));
        jPanel7.setBackground(TARJETA);
        jPanel7.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)));
        jPanel8.setBackground(TARJETA);
        jPanel8.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)));
    }

    private void estiloTarjeta(javax.swing.JPanel panel) {
        panel.setBackground(TARJETA);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)));
    }

    private void estiloToggle(javax.swing.JToggleButton btn) {
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btn.setBackground(new Color(241, 245, 249));
        btn.setForeground(TEXTO_OSCURO);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                BorderFactory.createEmptyBorder(6, 14, 6, 14)));
        btn.setFocusPainted(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn.setOpaque(true);
    }

    private void estiloRadio(javax.swing.JRadioButton rb) {
        rb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        rb.setForeground(TEXTO_OSCURO);
        rb.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        rb.setFocusPainted(false);
        rb.setOpaque(false);
        rb.setContentAreaFilled(false);
        rb.setBorderPainted(true);
    }

    private void estiloBotonPreset(javax.swing.JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btn.setBackground(FONDO_GENERAL);
        btn.setForeground(TEXTO_OSCURO);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        btn.setFocusPainted(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private GeneradorDeArreglos.TipoArreglo obtenerTipoArreglo() {
        if (jToggleButton2.isSelected()) return GeneradorDeArreglos.TipoArreglo.ALEATORIO;
        if (jToggleButton1.isSelected()) return GeneradorDeArreglos.TipoArreglo.ORDENADO;
        if (jToggleButton4.isSelected()) return GeneradorDeArreglos.TipoArreglo.CASI_ORDENADO;
        if (jToggleButton3.isSelected()) return GeneradorDeArreglos.TipoArreglo.INVERSO;
        return GeneradorDeArreglos.TipoArreglo.ALEATORIO;
    }

    private List<TipoAlgoritmo> obtenerAlgoritmosSeleccionados() {
        List<TipoAlgoritmo> seleccionados = new ArrayList<>();
        if (jRadioButton1.isSelected()) seleccionados.add(TipoAlgoritmo.BUBBLE_SORT);
        if (jRadioButton6.isSelected()) seleccionados.add(TipoAlgoritmo.SELECTION_SORT);
        if (jRadioButton5.isSelected()) seleccionados.add(TipoAlgoritmo.INSERTION_SORT);
        if (jRadioButton3.isSelected()) seleccionados.add(TipoAlgoritmo.MERGE_SORT);
        if (jRadioButton4.isSelected()) seleccionados.add(TipoAlgoritmo.QUICK_SORT);
        if (jRadioButton2.isSelected()) seleccionados.add(TipoAlgoritmo.HEAP_SORT);
        return seleccionados;
    }

    private void ejecutar() {
        List<TipoAlgoritmo> seleccionados = obtenerAlgoritmosSeleccionados();
        if (seleccionados.size() < 2) {
            JOptionPane.showMessageDialog(this,
                    "Selecciona al menos 2 algoritmos.",
                    "Sin selección", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int tamano = jSlider1.getValue();
        GeneradorDeArreglos.TipoArreglo tipoArreglo = obtenerTipoArreglo();

        List<ResultadoOrdenamiento> resultados =
                new ControladorLaboratorio().analizar(tamano, tipoArreglo, seleccionados);

        llenarTabla(resultados);
        generarGraficas(resultados);
    }

    private void llenarTabla(List<ResultadoOrdenamiento> resultados) {
        long minTiempo = resultados.stream()
                .mapToLong(ResultadoOrdenamiento::tiempoEjecucionNs)
                .min().orElse(Long.MAX_VALUE);
        String mejorAlgoritmo = resultados.stream()
                .filter(r -> r.tiempoEjecucionNs() == minTiempo)
                .findFirst().map(ResultadoOrdenamiento::nombreAlgoritmo)
                .orElse("");

        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"Algoritmo", "Tiempo", "Comparaciones", "Intercambios"}, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        for (ResultadoOrdenamiento r : resultados) {
            modelo.addRow(new Object[]{
                r.nombreAlgoritmo(),
                r.tiempoEjecucionNs() + " ns",
                formatNumber(r.comparaciones()),
                formatNumber(r.intercambios())
            });
        }
        jTable1.setModel(modelo);

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(140);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(90);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(110);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(110);

        jTable1.getTableHeader().setResizingAllowed(false);

        jTable1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);
                String algoNombre = (String) table.getValueAt(row, 0);
                boolean esMejor = algoNombre.equals(mejorAlgoritmo);
                if (!isSelected) {
                    if (esMejor) {
                        c.setBackground(new Color(254, 243, 199));
                        c.setForeground(new Color(180, 130, 0));
                    } else {
                        Color algColor = obtenerColorAlgoritmo(algoNombre);
                        c.setBackground(new Color(
                                Math.min(255, algColor.getRed() / 6 + 240),
                                Math.min(255, algColor.getGreen() / 6 + 240),
                                Math.min(255, algColor.getBlue() / 6 + 240)));
                        c.setForeground(TEXTO_OSCURO);
                    }
                }
                if (column == 0 && c instanceof JLabel && !isSelected) {
                    ((JLabel) c).setForeground(obtenerColorAlgoritmo(algoNombre));
                }
                return c;
            }
        });

        int rowH = jTable1.getRowHeight();
        int headerH = jTable1.getTableHeader().getPreferredSize().height;
        int totalH = resultados.size() * rowH + headerH + 4;
        jScrollPane1.setPreferredSize(new java.awt.Dimension(
                jScrollPane1.getWidth(), Math.min(Math.max(totalH, 100), 280)));
        jScrollPane1.revalidate();
    }

    private String formatNumber(long n) {
        if (n >= 1_000_000_000) return String.format("%.1fB", n / 1_000_000_000.0);
        if (n >= 1_000_000) return String.format("%.1fM", n / 1_000_000.0);
        if (n >= 1_000) return String.format("%.1fK", n / 1_000.0);
        return String.valueOf(n);
    }

    private Color obtenerColorAlgoritmo(String nombre) {
        Map<String, Color> colores = new HashMap<>();
        colores.put("Bubble Sort", new Color(239, 68, 68));
        colores.put("Selection Sort", new Color(249, 115, 22));
        colores.put("Insertion Sort", new Color(234, 179, 8));
        colores.put("Merge Sort", new Color(34, 197, 94));
        colores.put("Quick Sort", new Color(59, 130, 246));
        colores.put("Heap Sort", new Color(168, 85, 247));
        return colores.getOrDefault(nombre, Color.GRAY);
    }

    private void generarGraficas(List<ResultadoOrdenamiento> resultados) {
        List<String> nombres = resultados.stream()
                .map(ResultadoOrdenamiento::nombreAlgoritmo)
                .collect(Collectors.toList());

        List<Long> tiempos = resultados.stream()
                .map(ResultadoOrdenamiento::tiempoEjecucionNs)
                .collect(Collectors.toList());
        List<Long> comparaciones = resultados.stream()
                .map(ResultadoOrdenamiento::comparaciones)
                .collect(Collectors.toList());
        List<Long> intercambios = resultados.stream()
                .map(ResultadoOrdenamiento::intercambios)
                .collect(Collectors.toList());

        ponerGrafica(jPanel6, "Tiempo de Ejecución (ns)", nombres, tiempos);
        ponerGrafica(jPanel7, "Comparaciones", nombres, comparaciones);
        ponerGrafica(jPanel8, "Intercambios", nombres, intercambios);
    }

    private void ponerGrafica(javax.swing.JPanel panel, String titulo,
                              List<String> nombres, List<Long> valores) {
        CategoryChart chart = new CategoryChartBuilder()
                .width(800).height(350)
                .title(titulo)
                .xAxisTitle("")
                .yAxisTitle("")
                .build();

        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setAvailableSpaceFill(0.9);
        chart.getStyler().setPlotContentSize(0.95);
        chart.getStyler().setChartBackgroundColor(TARJETA);
        chart.getStyler().setPlotBackgroundColor(TARJETA);
        chart.getStyler().setPlotBorderVisible(false);
        chart.getStyler().setXAxisLabelRotation(0);
        chart.getStyler().setAxisTickLabelsFont(new Font("Segoe UI", Font.PLAIN, 12));
        chart.getStyler().setChartTitleBoxVisible(false);
        chart.getStyler().setChartPadding(10);
        chart.getStyler().setPlotGridVerticalLinesVisible(false);
        chart.getStyler().setStacked(true);
        chart.getStyler().setYAxisTickLabelsFormattingFunction(
            val -> {
                if (val >= 1_000_000_000)
                    return String.format("%.1fB", val / 1_000_000_000);
                if (val >= 1_000_000)
                    return String.format("%.1fM", val / 1_000_000);
                if (val >= 1_000)
                    return String.format("%.1fK", val / 1_000);
                return String.format("%.0f", val);
            });

        for (int i = 0; i < nombres.size(); i++) {
            String nombre = nombres.get(i);
            Long valor = valores.get(i);
            Color color = obtenerColorAlgoritmo(nombre);

            List<Long> ceros = new ArrayList<>();
            for (int k = 0; k < nombres.size(); k++) {
                ceros.add(0L);
            }
            ceros.set(i, valor);

            CategorySeries serie = chart.addSeries(nombre, nombres, ceros);
            serie.setFillColor(color);
        }

        panel.removeAll();
        panel.setLayout(new BorderLayout());
        panel.add(new XChartPanel<>(chart), BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }

    private void mostrarAnimacion(String algoritmo) {
        int tamano = Math.min(Math.max(jSlider1.getValue(), 10), 200);
        GeneradorDeArreglos.TipoArreglo tipo = obtenerTipoArreglo();
        int[] arreglo = GeneradorDeArreglos.generar(tamano, tipo);

        JDialog dialog = new JDialog(this, "Animación - " + algoritmo, true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().setBackground(TARJETA);

        PanelBarras panelBarras = new PanelBarras();
        panelBarras.setArreglo(arreglo);
        panelBarras.setBackground(TARJETA);

        PanelRedondeado contenedorBarras = new PanelRedondeado();
        contenedorBarras.setBackground(LATERAL);
        contenedorBarras.setLayout(new BorderLayout());
        contenedorBarras.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        contenedorBarras.add(panelBarras, BorderLayout.CENTER);

        JComboBox<String> cboVelocidad = new JComboBox<>(
            new String[]{"Instantánea (1 ms)", "Rápida (25 ms)", "Media (100 ms)", "Lenta (250 ms)", "Muy lenta (500 ms)"});
        cboVelocidad.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        BotonRedondeado btnIniciar = new BotonRedondeado();
        btnIniciar.setText("Iniciar");
        btnIniciar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnIniciar.setBackground(ACENTO);
        btnIniciar.setForeground(TEXTO_CLARO);

        JLabel lblEstado = new JLabel("Listo", JLabel.CENTER);
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblEstado.setForeground(TEXTO_MUTED);

        JPanel controles = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        controles.setBackground(TARJETA);
        JLabel lblVel = new JLabel("Velocidad:");
        lblVel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblVel.setForeground(TEXTO_OSCURO);
        controles.add(lblVel);
        controles.add(cboVelocidad);
        controles.add(btnIniciar);
        controles.add(lblEstado);

        JPanel main = new JPanel(new BorderLayout(10, 10));
        main.setBackground(TARJETA);
        main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(TARJETA);
        JLabel titulo = new JLabel("Animación - " + algoritmo + "  (" + tamano + " elementos)", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(ACENTO);
        header.add(titulo, BorderLayout.CENTER);

        JLabel lblSize = new JLabel("Tipo: " + tipo.name());
        lblSize.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSize.setForeground(TEXTO_MUTED);
        lblSize.setHorizontalAlignment(JLabel.CENTER);
        header.add(lblSize, BorderLayout.SOUTH);

        main.add(header, BorderLayout.NORTH);
        main.add(contenedorBarras, BorderLayout.CENTER);
        main.add(controles, BorderLayout.SOUTH);

        btnIniciar.addActionListener(e -> {
            btnIniciar.setEnabled(false);
            cboVelocidad.setEnabled(false);
            lblEstado.setText("Ordenando...");
            new SwingWorker<Void, PasoAnimacion>() {
                @Override
                protected Void doInBackground() {
                    String vel = (String) cboVelocidad.getSelectedItem();
                    int delay;
                    if (vel == null || vel.startsWith("Instantánea")) delay = 1;
                    else if (vel.startsWith("Rápida")) delay = 25;
                    else if (vel.startsWith("Media")) delay = 100;
                    else if (vel.startsWith("Lenta")) delay = 250;
                    else delay = 500;
                    java.util.function.Consumer<PasoAnimacion> callback = paso -> {
                        publish(paso);
                        try { Thread.sleep(delay); }
                        catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
                    };
                    switch (algoritmo) {
                        case "Bubble Sort" -> VisualizadorOrdenamiento.bubbleSort(arreglo, callback);
                        case "Selection Sort" -> VisualizadorOrdenamiento.selectionSort(arreglo, callback);
                        case "Insertion Sort" -> VisualizadorOrdenamiento.insertionSort(arreglo, callback);
                        case "Merge Sort" -> VisualizadorOrdenamiento.mergeSort(arreglo, callback);
                        case "Quick Sort" -> VisualizadorOrdenamiento.quickSort(arreglo, callback);
                        case "Heap Sort" -> VisualizadorOrdenamiento.heapSort(arreglo, callback);
                    }
                    return null;
                }
                @Override
                protected void process(List<PasoAnimacion> pasos) {
                    panelBarras.setPaso(pasos.get(pasos.size() - 1));
                }
                @Override
                protected void done() {
                    btnIniciar.setEnabled(true);
                    cboVelocidad.setEnabled(true);
                    lblEstado.setText("Ordenado");
                }
            }.execute();
        });

        dialog.add(main);
        dialog.pack();
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        jSlider1 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("PANEL DE CONTROL"));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setMinimumSize(new java.awt.Dimension(300, 800));
        jPanel2.setName("");

        jLabel5.setText("Tipo de Datos:");

        buttonGroup1.add(jToggleButton1);
        jToggleButton1.setText("Ordenados");

        buttonGroup1.add(jToggleButton2);
        jToggleButton2.setText("Totalmente Aleatorios");

        buttonGroup1.add(jToggleButton3);
        jToggleButton3.setText("Orden Inverso");

        buttonGroup1.add(jToggleButton4);
        jToggleButton4.setText("Casi Ordenados");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setMinimumSize(new java.awt.Dimension(300, 800));
        jPanel1.setName("");
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 800));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel1.setText("000");
        jLabel1.setAlignmentX(0.5F);
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel2.setText("Tama\u00f1o del Arreglo:");

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 10));
        jButton1.setText("10,000");
        jButton1.setAlignmentY(0.0F);

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 10));
        jButton2.setText("1,000");
        jButton2.setAlignmentY(0.0F);

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 10));
        jButton3.setText("100");
        jButton3.setAlignmentY(0.0F);

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 10));
        jButton4.setText("10");
        jButton4.setAlignmentY(0.0F);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSlider1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3, jButton4});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setText("Algoritmos a Ejecutar");

        jRadioButton1.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jRadioButton1.setText("Bubble Sort");
        jRadioButton1.setBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("Button.borderColor"), 1, true));
        jRadioButton1.setBorderPainted(true);
        jRadioButton1.setSelected(true);

        jRadioButton2.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jRadioButton2.setText("Heap Sort");
        jRadioButton2.setBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("Button.borderColor"), 1, true));
        jRadioButton2.setBorderPainted(true);
        jRadioButton2.setSelected(true);

        jRadioButton3.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jRadioButton3.setText("Merge Sort");
        jRadioButton3.setBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("Button.borderColor"), 1, true));
        jRadioButton3.setBorderPainted(true);
        jRadioButton3.setSelected(true);

        jRadioButton4.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jRadioButton4.setText("Quick Sort");
        jRadioButton4.setBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("Button.borderColor"), 1, true));
        jRadioButton4.setBorderPainted(true);
        jRadioButton4.setSelected(true);

        jRadioButton5.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jRadioButton5.setText("Insertion Sort");
        jRadioButton5.setBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("Button.borderColor"), 1, true));
        jRadioButton5.setBorderPainted(true);

        jRadioButton6.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jRadioButton6.setText("Selection Sort");
        jRadioButton6.setBorder(new javax.swing.border.LineBorder(javax.swing.UIManager.getDefaults().getColor("Button.borderColor"), 1, true));
        jRadioButton6.setBorderPainted(true);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 10));
        jLabel4.setForeground(new java.awt.Color(51, 102, 255));
        jLabel4.setText("Selecciona al menos 2 algoritmos.");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4))
                    .addComponent(jRadioButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jButton5.setBackground(new java.awt.Color(0, 51, 153));
        jButton5.setFont(new java.awt.Font("sansserif", 1, 24));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("EJECUTAR");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Algoritmo", "Tiempo de ejecuci\u00f3n", "No. Comparaciones", "No. Intercambios"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 851, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 375, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Tiempo de Ejecuci\u00f3n", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 851, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 375, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Comparaciones", jPanel7);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 851, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 375, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Intercambios", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    // End of variables declaration//GEN-END:variables
}
