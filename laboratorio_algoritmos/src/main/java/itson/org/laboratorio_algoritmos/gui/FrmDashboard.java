package itson.org.laboratorio_algoritmos.gui;

import itson.org.laboratorio_algoritmos.datos.GeneradorDeArreglos;
import itson.org.laboratorio_algoritmos.datos.PasoAnimacion;
import itson.org.laboratorio_algoritmos.datos.ResultadoOrdenamiento;
import itson.org.laboratorio_algoritmos.datos.TipoAlgoritmo;
import itson.org.laboratorio_algoritmos.gui.utils.BotonRedondeado;
import itson.org.laboratorio_algoritmos.gui.utils.PanelBarras;
import itson.org.laboratorio_algoritmos.gui.utils.PanelRedondeado;
import itson.org.laboratorio_algoritmos.ordenamientos.VisualizadorOrdenamiento;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Rectangle;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class FrmDashboard extends javax.swing.JFrame {

    private static final Color FONDO_GENERAL = new Color(238, 241, 246);
    private static final Color LATERAL = new Color(238, 241, 246);
    private static final Color TARJETA = new Color(255, 255, 255);
    private static final Color ACENTO = new Color(99, 102, 241);
    private static final Color ACENTO_BORDE = new Color(199, 210, 254);
    private static final Color TEXTO_OSCURO = new Color(28, 43, 69);
    private static final Color TEXTO_MUTED = new Color(91, 107, 133);
    private static final Color TEXTO_CLARO = new Color(255, 255, 255);
    private static final Color BORDE = new Color(199, 208, 222);
    private static final Color HEADER_DARK = new Color(37, 48, 67);
    private static final Color TAB_SELECCIONADO = new Color(255, 255, 255);
    private static final Color TAB_NO_SELECCIONADO = new Color(241, 245, 249);
    private static final Color MEJOR_FONDO = new Color(255, 246, 230);
    private static final Color MEJOR_TEXTO = new Color(201, 130, 14);
    private static final Color TRACK_BG = new Color(229, 231, 235);

    private int lastTamano = 100;
    private int[] arregloActual;
    private int ultimoTamano = -1;
    private GeneradorDeArreglos.TipoArreglo ultimoTipoArreglo = null;
    private ImageIcon starIcon;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmDashboard.class.getName());

    public FrmDashboard() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        UIManager.put("Table.background", TARJETA);
        UIManager.put("Table.foreground", TEXTO_OSCURO);
        UIManager.put("Table.selectionBackground", ACENTO);
        UIManager.put("Table.selectionForeground", TEXTO_CLARO);
        UIManager.put("Table.gridColor", BORDE);

        initComponents();
        restructurarLayout();
        aplicarEstiloModerno();
        configurarControles();
        cargarIconosTabs();
        cargarStarIcon();
    }

    private void cargarStarIcon() {
        Image img = cargarImagen("/itson/org/laboratorio_algoritmos/gui/utils/star.png");
        if (img != null) {
            starIcon = new ImageIcon(img.getScaledInstance(14, 14, Image.SCALE_SMOOTH));
        }
    }

    private void restructurarLayout() {
        getContentPane().setLayout(new BorderLayout(15, 0));
        getContentPane().setBackground(FONDO_GENERAL);

        jPanel5.removeAll();
        jPanel5.setBackground(LATERAL);
        jPanel5.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jPanel5.setLayout(new BorderLayout());

        JPanel panelScrollContent = new JPanel();
        panelScrollContent.setBackground(LATERAL);

        javax.swing.GroupLayout scrollLayout = new javax.swing.GroupLayout(panelScrollContent);
        panelScrollContent.setLayout(scrollLayout);
        scrollLayout.setHorizontalGroup(
            scrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scrollLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(scrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        scrollLayout.setVerticalGroup(
            scrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scrollLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JScrollPane scrollControles = new JScrollPane(panelScrollContent);
        scrollControles.setBorder(BorderFactory.createEmptyBorder());
        scrollControles.getViewport().setBackground(LATERAL);
        scrollControles.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollControles.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        scrollControles.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override protected JButton createDecreaseButton(int o) { return createZeroButton(); }
            @Override protected JButton createIncreaseButton(int o) { return createZeroButton(); }
            private javax.swing.JButton createZeroButton() {
                javax.swing.JButton btn = new javax.swing.JButton();
                btn.setPreferredSize(new Dimension(0, 0)); btn.setMinimumSize(new Dimension(0, 0)); btn.setMaximumSize(new Dimension(0, 0));
                return btn;
            }
            @Override public Dimension getPreferredSize(JComponent c) {
                return new Dimension(10, super.getPreferredSize(c).height);
            }
            @Override protected void paintThumb(Graphics g, JComponent c, Rectangle b) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                JScrollBar sb = (JScrollBar) c;
                if (sb.getMaximum() - sb.getMinimum() > sb.getVisibleAmount()) {
                    g2.setColor(new Color(160, 165, 175));
                    g2.fillRoundRect(b.x + 2, b.y + 1, b.width - 4, b.height - 2, 6, 6);
                }
                g2.dispose();
            }
            @Override protected void paintTrack(Graphics g, JComponent c, Rectangle b) {
                g.setColor(new Color(238, 241, 246));
                g.fillRect(b.x, b.y, b.width, b.height);
            }
        });

        jPanel5.add(scrollControles, BorderLayout.CENTER);
        jPanel5.add(jButton5, BorderLayout.SOUTH);

        getContentPane().add(jPanel5, BorderLayout.WEST);

        JPanel panelContenido = new JPanel(new BorderLayout(0, 12));
        panelContenido.setBackground(FONDO_GENERAL);
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 20));

        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBackground(FONDO_GENERAL);
        panelTabla.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));
        panelTabla.add(jScrollPane1, BorderLayout.CENTER);
        panelContenido.add(panelTabla, BorderLayout.CENTER);
        panelContenido.add(jTabbedPane1, BorderLayout.SOUTH);

        getContentPane().add(panelContenido, BorderLayout.CENTER);
    }

    private void cargarIconosTabs() {
        Image imgTiempo = cargarImagen("/itson/org/laboratorio_algoritmos/gui/utils/tiempo.png");
        Image imgComparacion = cargarImagen("/itson/org/laboratorio_algoritmos/gui/utils/comparacion.png");
        Image imgIntercambio = cargarImagen("/itson/org/laboratorio_algoritmos/gui/utils/intercambio.png");

        if (imgTiempo != null) jTabbedPane1.setIconAt(0, new ImageIcon(imgTiempo.getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
        if (imgComparacion != null) jTabbedPane1.setIconAt(1, new ImageIcon(imgComparacion.getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
        if (imgIntercambio != null) jTabbedPane1.setIconAt(2, new ImageIcon(imgIntercambio.getScaledInstance(18, 18, Image.SCALE_SMOOTH)));
    }

    private Image cargarImagen(String ruta) {
        try (InputStream is = getClass().getResourceAsStream(ruta)) {
            if (is != null) {
                return javax.imageio.ImageIO.read(is);
            }
        } catch (Exception e) {
            logger.log(java.util.logging.Level.WARNING, "No se pudo cargar icono: " + ruta, e);
        }
        return null;
    }

    private void configurarControles() {
        rbtnAleatorio.setSelected(true);

        jSlider1.setMinimum(10);
        jSlider1.setMaximum(10000);
        jSlider1.setValue(100);
        txtTamanio.setText("100");

        jSlider1.addChangeListener(e -> {
            txtTamanio.setText(String.valueOf(jSlider1.getValue()));
        });

        txtTamanio.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) e.consume();
            }
        });

        txtTamanio.addActionListener(e -> aplicarValorTamano());
        txtTamanio.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent e) { aplicarValorTamano(); }
        });

        jButton4.addActionListener(e -> jSlider1.setValue(10));
        jButton3.addActionListener(e -> jSlider1.setValue(100));
        jButton2.addActionListener(e -> jSlider1.setValue(1000));
        jButton1.addActionListener(e -> jSlider1.setValue(10000));

        jButton5.addActionListener(e -> ejecutar());

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
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

    private void aplicarValorTamano() {
        String text = txtTamanio.getText().trim();
        if (text.isEmpty()) { txtTamanio.setText(String.valueOf(jSlider1.getValue())); return; }
        int val = Integer.parseInt(text);
        val = Math.max(10, Math.min(10000, val));
        txtTamanio.setText(String.valueOf(val));
        jSlider1.setValue(val);
    }

    private void aplicarEstiloModerno() {
        setTitle("Laboratorio de Algoritmos de Ordenamiento");
        getContentPane().setBackground(FONDO_GENERAL);

        estiloTarjeta(jPanel1);
        estiloTarjeta(jPanel2);
        estiloTarjeta(jPanel4);

        Font fuenteSeccion = new Font("IBM Plex Mono", Font.BOLD, 11);
        Font fuenteHint = new Font("Segoe UI", Font.ITALIC, 10);

        jLabel5.setText("TAMA\u00d1O DEL ARREGLO");
        jLabel5.setFont(fuenteSeccion);
        jLabel5.setForeground(TEXTO_MUTED);
        jLabel5.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));

        jLabel2.setText("TIPO DE DATOS");
        jLabel2.setFont(fuenteSeccion);
        jLabel2.setForeground(TEXTO_MUTED);
        jLabel2.setBorder(BorderFactory.createEmptyBorder(0, 2, 6, 0));

        jLabel3.setText("ALGORITMOS A EJECUTAR");
        jLabel3.setFont(fuenteSeccion);
        jLabel3.setForeground(TEXTO_MUTED);
        jLabel3.setBorder(BorderFactory.createEmptyBorder(0, 2, 6, 0));

        jLabel4.setFont(fuenteHint);
        jLabel4.setForeground(TEXTO_MUTED);

        txtTamanio.setBackground(new Color(248, 250, 252));
        txtTamanio.setForeground(TEXTO_OSCURO);
        txtTamanio.setFont(new Font("IBM Plex Mono", Font.BOLD, 22));
        txtTamanio.setHorizontalAlignment(SwingConstants.CENTER);
        txtTamanio.setCaretColor(ACENTO);
        txtTamanio.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        txtTamanio.setSelectionColor(ACENTO_BORDE);
        txtTamanio.setSelectedTextColor(TEXTO_OSCURO);

        estiloRadio(rbtnAleatorio);
        estiloRadio(rbtnOrdenados);
        estiloRadio(rbtnCasiOrdenados);
        estiloRadio(rbtnInverso);

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

        jButton5.setBackground(TEXTO_OSCURO);
        jButton5.setForeground(TEXTO_CLARO);
        jButton5.setFont(new Font("IBM Plex Mono", Font.BOLD, 13));
        jButton5.setBorder(BorderFactory.createEmptyBorder(14, 0, 14, 0));
        jButton5.setFocusPainted(false);
        jButton5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        jSlider1.setBackground(LATERAL);
        jSlider1.setForeground(ACENTO);
        jSlider1.setPaintTicks(false);
        jSlider1.setPaintLabels(false);

        jTable1.setRowHeight(40);
        jTable1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        jTable1.setShowHorizontalLines(true);
        jTable1.setShowVerticalLines(true);
        jTable1.setIntercellSpacing(new Dimension(1, 1));
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setGridColor(BORDE);
        jTable1.setSelectionBackground(ACENTO);
        jTable1.setSelectionForeground(TEXTO_CLARO);
        jTable1.setFocusable(false);

        JTableHeader header = jTable1.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);
                String texto = value != null ? value.toString().toUpperCase() : "";
                lbl.setText("  " + texto);
                lbl.setFont(new Font("IBM Plex Mono", Font.BOLD, 12));
                lbl.setBackground(HEADER_DARK);
                lbl.setForeground(TEXTO_CLARO);
                lbl.setHorizontalAlignment(SwingConstants.LEFT);
                lbl.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 0));
                lbl.setOpaque(true);
                return lbl;
            }
        });
        header.setFont(new Font("IBM Plex Mono", Font.BOLD, 12));
        header.setBackground(HEADER_DARK);
        header.setForeground(TEXTO_CLARO);
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);
        header.setBorder(null);

        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane1.getViewport().setBackground(TARJETA);

        jScrollPane1.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override protected JButton createDecreaseButton(int o) { return createZeroButton(); }
            @Override protected JButton createIncreaseButton(int o) { return createZeroButton(); }
            private javax.swing.JButton createZeroButton() {
                javax.swing.JButton btn = new javax.swing.JButton();
                btn.setPreferredSize(new Dimension(0, 0)); btn.setMinimumSize(new Dimension(0, 0)); btn.setMaximumSize(new Dimension(0, 0));
                return btn;
            }
            @Override public Dimension getPreferredSize(JComponent c) {
                return new Dimension(10, super.getPreferredSize(c).height);
            }
            @Override protected void paintThumb(Graphics g, JComponent c, Rectangle b) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                JScrollBar sb = (JScrollBar) c;
                if (sb.getMaximum() - sb.getMinimum() > sb.getVisibleAmount()) {
                    g2.setColor(new Color(160, 165, 175));
                    g2.fillRoundRect(b.x + 2, b.y + 1, b.width - 4, b.height - 2, 6, 6);
                }
                g2.dispose();
            }
            @Override protected void paintTrack(Graphics g, JComponent c, Rectangle b) {
                g.setColor(new Color(238, 241, 246));
                g.fillRect(b.x, b.y, b.width, b.height);
            }
        });

        configurarTabbedPane();

        jPanel6.setBackground(TARJETA);
        jPanel7.setBackground(TARJETA);
        jPanel8.setBackground(TARJETA);

        configurarRenderizadorTabla();
    }

    private void configurarTabbedPane() {
        jTabbedPane1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        jTabbedPane1.setBackground(TAB_NO_SELECCIONADO);
        jTabbedPane1.setForeground(TEXTO_MUTED);
        jTabbedPane1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        jTabbedPane1.setUI(new BasicTabbedPaneUI() {
            @Override
            protected void installDefaults() {
                super.installDefaults();
                tabAreaInsets = new javax.swing.plaf.InsetsUIResource(0, 0, 0, 0);
                selectedTabPadInsets = new javax.swing.plaf.InsetsUIResource(0, 0, 0, 0);
                contentBorderInsets = new javax.swing.plaf.InsetsUIResource(0, 0, 0, 0);
                tabRunOverlay = 0;
                highlight = TAB_NO_SELECCIONADO;
            }

            @Override
            protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(TAB_NO_SELECCIONADO);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
                super.paintTabArea(g, tabPlacement, selectedIndex);
            }

            @Override
            protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex,
                    int x, int y, int w, int h, boolean isSelected) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (isSelected) {
                    g2.setColor(TAB_SELECCIONADO);
                    g2.fillRect(x, y, w, h);
                } else {
                    g2.setColor(TAB_NO_SELECCIONADO);
                    g2.fillRect(x, y, w, h);
                }
                g2.dispose();
            }

            @Override
            protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex,
                    int x, int y, int w, int h, boolean isSelected) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (isSelected) {
                    g2.setColor(TEXTO_OSCURO);
                    g2.fillRoundRect(x + 2, y + h - 3, w - 4, 3, 2, 2);
                }
                g2.dispose();
            }

            @Override
            protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
                if (selectedIndex < 0 || selectedIndex >= rects.length) return;
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int tabBottom = rects[selectedIndex].y + rects[selectedIndex].height;
                g2.setColor(BORDE);
                g2.setStroke(new BasicStroke(1));
                g2.drawLine(0, tabBottom, getWidth(), tabBottom);
                g2.dispose();
            }
        });
    }

    private void configurarRenderizadorTabla() {
        DefaultTableCellRenderer nameRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);
                int modelRow = table.convertRowIndexToModel(row);
                Integer mejorIdx = (Integer) table.getClientProperty("mejorIdx");
                boolean esMejor = mejorIdx != null && modelRow == mejorIdx;
                String nombre = ((String) table.getValueAt(row, 0)).trim();
                Color algColor = obtenerColorAlgoritmo(nombre);

                lbl.setText("   " + nombre);
                lbl.setIcon(esMejor && starIcon != null
                        ? new CompositeIcon(new ColorSquareIcon(algColor), starIcon)
                        : new ColorSquareIcon(algColor));
                lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
                lbl.setHorizontalAlignment(SwingConstants.LEFT);

                Font baseFont = new Font("Segoe UI", Font.BOLD, 13);
                if (!isSelected) {
                    if (esMejor) {
                        lbl.setBackground(MEJOR_FONDO);
                        lbl.setForeground(MEJOR_TEXTO);
                        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
                    } else {
                        lbl.setBackground(TARJETA);
                        lbl.setForeground(algColor);
                        lbl.setFont(baseFont);
                    }
                } else {
                    lbl.setBackground(ACENTO);
                    lbl.setForeground(TEXTO_CLARO);
                    lbl.setFont(baseFont);
                }
                lbl.setOpaque(true);
                lbl.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
                return lbl;
            }
        };

        DefaultTableCellRenderer valueRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);
                int modelRow = table.convertRowIndexToModel(row);
                Integer mejorIdx = (Integer) table.getClientProperty("mejorIdx");
                boolean esMejor = mejorIdx != null && modelRow == mejorIdx;

                lbl.setHorizontalAlignment(SwingConstants.RIGHT);
                lbl.setFont(new Font("IBM Plex Mono", Font.PLAIN, 12));

                if (!isSelected) {
                    if (esMejor) {
                        lbl.setBackground(MEJOR_FONDO);
                        lbl.setForeground(MEJOR_TEXTO);
                        lbl.setFont(new Font("IBM Plex Mono", Font.BOLD, 12));
                    } else {
                        lbl.setBackground(TARJETA);
                        lbl.setForeground(TEXTO_OSCURO);
                    }
                } else {
                    lbl.setBackground(ACENTO);
                    lbl.setForeground(TEXTO_CLARO);
                }
                lbl.setOpaque(true);
                lbl.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
                return lbl;
            }
        };

        int cols = jTable1.getColumnModel().getColumnCount();
        for (int i = 0; i < cols; i++) {
            if (i == 0) {
                jTable1.getColumnModel().getColumn(i).setCellRenderer(nameRenderer);
            } else {
                jTable1.getColumnModel().getColumn(i).setCellRenderer(valueRenderer);
            }
        }
    }

    private void estiloTarjeta(javax.swing.JPanel panel) {
        panel.setBackground(TARJETA);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                BorderFactory.createEmptyBorder(12, 14, 12, 14)));
    }

    private void estiloRadioOrden(javax.swing.JRadioButton rb) {
        rb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        rb.setForeground(TEXTO_OSCURO);
        rb.setBackground(TARJETA);
        rb.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                BorderFactory.createEmptyBorder(7, 12, 7, 12)));
        rb.setFocusPainted(false);
        rb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        rb.setOpaque(true);
        rb.setIcon(new CircleRadioButtonIcon(false));
        rb.setSelectedIcon(new CircleRadioButtonIcon(true));
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
        btn.setFont(new Font("IBM Plex Mono", Font.BOLD, 11));
        btn.setBackground(FONDO_GENERAL);
        btn.setForeground(TEXTO_MUTED);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private GeneradorDeArreglos.TipoArreglo obtenerTipoArreglo() {
        if (rbtnAleatorio.isSelected()) return GeneradorDeArreglos.TipoArreglo.ALEATORIO;
        if (rbtnOrdenados.isSelected()) return GeneradorDeArreglos.TipoArreglo.ORDENADO;
        if (rbtnCasiOrdenados.isSelected()) return GeneradorDeArreglos.TipoArreglo.CASI_ORDENADO;
        if (rbtnInverso.isSelected()) return GeneradorDeArreglos.TipoArreglo.INVERSO;
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
                    "Sin selecci\u00f3n", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int tamano = jSlider1.getValue();
        GeneradorDeArreglos.TipoArreglo tipoArreglo = obtenerTipoArreglo();

        if (arregloActual == null || tamano != ultimoTamano || tipoArreglo != ultimoTipoArreglo) {
            arregloActual = GeneradorDeArreglos.generar(tamano, tipoArreglo);
            ultimoTamano = tamano;
            ultimoTipoArreglo = tipoArreglo;
        }
        lastTamano = tamano;

        List<ResultadoOrdenamiento> resultados =
                new ControladorLaboratorio().analizar(arregloActual, seleccionados);

        llenarTabla(resultados);
        generarGraficas(resultados);
    }

    private void llenarTabla(List<ResultadoOrdenamiento> resultados) {
        long minTiempo = resultados.stream()
                .mapToLong(ResultadoOrdenamiento::tiempoEjecucionNs)
                .min().orElse(Long.MAX_VALUE);
        int mejorIdx = -1;
        for (int i = 0; i < resultados.size(); i++) {
            if (resultados.get(i).tiempoEjecucionNs() == minTiempo) {
                mejorIdx = i;
                break;
            }
        }

        String[] columnas = {"Algoritmo", "Tiempo de ejecuci\u00f3n", "No. de comparaciones", "No. de intercambios"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };

        for (int i = 0; i < resultados.size(); i++) {
            ResultadoOrdenamiento r = resultados.get(i);
            modelo.addRow(new Object[]{
                r.nombreAlgoritmo(),
                formatTiempo(r.tiempoEjecucionNs()),
                formatNumber(r.comparaciones()),
                formatNumber(r.intercambios())
            });
        }
        jTable1.setModel(modelo);
        jTable1.putClientProperty("mejorIdx", mejorIdx);

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(160);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(110);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(120);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(120);

        configurarRenderizadorTabla();
    }

    private String formatTiempo(long nanos) {
        if (nanos < 1000) return nanos + " ns";
        if (nanos < 1_000_000) return String.format("%.2f \u00b5s", nanos / 1000.0);
        if (nanos < 1_000_000_000) return String.format("%.2f ms", nanos / 1_000_000.0);
        return String.format("%.2f s", nanos / 1_000_000_000.0);
    }

    private String formatNumber(long n) {
        return String.format("%,d", n);
    }

    private Color obtenerColorAlgoritmo(String nombre) {
        Map<String, Color> colores = new HashMap<>();
        colores.put("Bubble Sort", new Color(225, 88, 75));
        colores.put("Selection Sort", new Color(242, 169, 59));
        colores.put("Insertion Sort", new Color(78, 125, 187));
        colores.put("Merge Sort", new Color(46, 125, 107));
        colores.put("Quick Sort", new Color(139, 95, 163));
        colores.put("Heap Sort", new Color(86, 101, 115));
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

        List<String> fmtTiempos = tiempos.stream().map(this::formatTiempo).collect(Collectors.toList());
        List<String> fmtComp = comparaciones.stream().map(this::formatNumber).collect(Collectors.toList());
        List<String> fmtInterc = intercambios.stream().map(this::formatNumber).collect(Collectors.toList());

        ponerGrafica(jPanel6, nombres, tiempos, fmtTiempos, findBestIndex(tiempos));
        ponerGrafica(jPanel7, nombres, comparaciones, fmtComp, findBestIndex(comparaciones));
        ponerGrafica(jPanel8, nombres, intercambios, fmtInterc, findBestIndex(intercambios));
    }

    private int findBestIndex(List<Long> valores) {
        long min = Long.MAX_VALUE;
        int idx = 0;
        for (int i = 0; i < valores.size(); i++) {
            if (valores.get(i) < min) { min = valores.get(i); idx = i; }
        }
        return idx;
    }

    private void ponerGrafica(javax.swing.JPanel panel,
                              List<String> nombres, List<Long> valores,
                              List<String> fmtValores, int bestIdx) {
        long maxVal = valores.stream().mapToLong(Long::longValue).max().orElse(1);

        panel.removeAll();
        panel.setLayout(new BorderLayout());
        panel.setBackground(TARJETA);

        JPanel contenedor = new JPanel();
        contenedor.setLayout(new javax.swing.BoxLayout(contenedor, javax.swing.BoxLayout.Y_AXIS));
        contenedor.setBackground(TARJETA);
        contenedor.setBorder(BorderFactory.createEmptyBorder(16, 24, 16, 24));
        contenedor.setPreferredSize(new Dimension(800, nombres.size() * 60 + 32));

        for (int i = 0; i < nombres.size(); i++) {
            String nombre = nombres.get(i);
            long valor = valores.get(i);
            Color color = obtenerColorAlgoritmo(nombre);
            double fraccion = maxVal > 0 ? (double) valor / maxVal : 0;
            boolean esMejor = (i == bestIdx);

            contenedor.add(new BarraProgreso(nombre, fraccion, color, fmtValores.get(i), esMejor, starIcon));
            if (i < nombres.size() - 1) {
                contenedor.add(javax.swing.Box.createVerticalStrut(10));
            }
        }

        panel.add(contenedor, BorderLayout.NORTH);
        panel.revalidate();
        panel.repaint();
    }

    private void mostrarAnimacion(String algoritmo) {
        if (lastTamano > 200) {
            int opcion = JOptionPane.showConfirmDialog(this,
                    "El arreglo tiene " + lastTamano + " elementos.\n"
                    + "La animaci\u00f3n con m\u00e1s de 200 elementos puede ser lenta y poco visible.\n"
                    + "\u00bfDesea continuar?",
                    "Advertencia - Muchos elementos",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (opcion != JOptionPane.YES_OPTION) return;
        }

        int tamano = lastTamano;
        GeneradorDeArreglos.TipoArreglo tipo = obtenerTipoArreglo();
        int[] arreglo = GeneradorDeArreglos.generar(tamano, tipo);

        JDialog dialog = new JDialog(this, "Animaci\u00f3n - " + algoritmo, true);
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
            new String[]{"Instant\u00e1nea (1 ms)", "R\u00e1pida (25 ms)", "Media (100 ms)", "Lenta (250 ms)", "Muy lenta (500 ms)"});
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
        JLabel titulo = new JLabel("Animaci\u00f3n - " + algoritmo + "  (" + tamano + " elementos)", JLabel.CENTER);
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
                    if (vel == null || vel.startsWith("Instant")) delay = 1;
                    else if (vel.startsWith("R")) delay = 25;
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
        dialog.setMinimumSize(new Dimension(500, 350));
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        bgOrder = new ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jSlider1 = new javax.swing.JSlider();
        txtTamanio = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        rbtnAleatorio = new javax.swing.JRadioButton();
        rbtnOrdenados = new javax.swing.JRadioButton();
        rbtnCasiOrdenados = new javax.swing.JRadioButton();
        rbtnInverso = new javax.swing.JRadioButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(TARJETA);
        jPanel1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                BorderFactory.createEmptyBorder(12, 14, 12, 14)));

        jLabel5.setText("TAMA\u00d1O DEL ARREGLO");

        txtTamanio.setText("100");

        jButton4.setText("10");

        jButton3.setText("100");

        jButton2.setText("1,000");

        jButton1.setText("10,000");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSlider1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTamanio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(txtTamanio, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        jPanel2.setBackground(TARJETA);
        jPanel2.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                BorderFactory.createEmptyBorder(12, 14, 12, 14)));

        jLabel2.setText("TIPO DE DATOS");

        bgOrder.add(rbtnAleatorio);
        rbtnAleatorio.setSelected(true);
        rbtnAleatorio.setText("Totalmente aleatorios");

        bgOrder.add(rbtnOrdenados);
        rbtnOrdenados.setText("Ordenados");

        bgOrder.add(rbtnCasiOrdenados);
        rbtnCasiOrdenados.setText("Casi ordenados");

        bgOrder.add(rbtnInverso);
        rbtnInverso.setText("Orden inverso");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtnAleatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbtnOrdenados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbtnCasiOrdenados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rbtnInverso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtnAleatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtnOrdenados, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtnCasiOrdenados, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtnInverso, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(TARJETA);
        jPanel4.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                BorderFactory.createEmptyBorder(12, 14, 12, 14)));

        jLabel3.setText("ALGORITMOS A EJECUTAR");

        jRadioButton1.setText("Bubble Sort");

        jRadioButton6.setText("Selection Sort");

        jRadioButton5.setText("Insertion Sort");

        jRadioButton3.setText("Merge Sort");

        jRadioButton4.setText("Quick Sort");

        jRadioButton2.setText("Heap Sort");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 10));
        jLabel4.setForeground(new java.awt.Color(100, 116, 139));
        jLabel4.setText("Selecciona al menos 2");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton5.setText("EJECUTAR");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Algoritmo", "Tiempo de ejecuci\u00f3n", "No. de comparaciones", "No. de intercambios"}
        ) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 851, Short.MAX_VALUE));
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));
        jTabbedPane1.addTab("Tiempo de Ejecuci\u00f3n", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 851, Short.MAX_VALUE));
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));
        jTabbedPane1.addTab("Comparaciones", jPanel7);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 851, Short.MAX_VALUE));
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));
        jTabbedPane1.addTab("Intercambios", jPanel8);

        pack();
        setMinimumSize(new Dimension(900, 600));
        setSize(1200, 800);
        setLocationRelativeTo(null);
    }

    // Variables declaration - do not modify
    private javax.swing.ButtonGroup bgOrder;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
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
    private javax.swing.JRadioButton rbtnAleatorio;
    private javax.swing.JRadioButton rbtnCasiOrdenados;
    private javax.swing.JRadioButton rbtnInverso;
    private javax.swing.JRadioButton rbtnOrdenados;
    private javax.swing.JTextField txtTamanio;

    private static class ColorSquareIcon implements javax.swing.Icon {
        private final Color color;
        ColorSquareIcon(Color color) { this.color = color; }
        @Override public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fillRoundRect(x, y + 2, 12, 12, 3, 3);
            g2.dispose();
        }
        @Override public int getIconWidth() { return 16; }
        @Override public int getIconHeight() { return 16; }
    }

    private static class CompositeIcon implements javax.swing.Icon {
        private final javax.swing.Icon left;
        private final javax.swing.Icon right;
        CompositeIcon(javax.swing.Icon left, javax.swing.Icon right) { this.left = left; this.right = right; }
        @Override public void paintIcon(Component c, Graphics g, int x, int y) {
            left.paintIcon(c, g, x, y);
            right.paintIcon(c, g, x + left.getIconWidth() + 2, y);
        }
        @Override public int getIconWidth() { return left.getIconWidth() + 2 + right.getIconWidth(); }
        @Override public int getIconHeight() { return Math.max(left.getIconHeight(), right.getIconHeight()); }
    }

    private static class CircleRadioButtonIcon implements javax.swing.Icon {
        private final boolean selected;
        CircleRadioButtonIcon(boolean selected) { this.selected = selected; }
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int cx = x + getIconWidth() / 2;
            int cy = y + getIconHeight() / 2;
            int radius = 6;
            if (selected) {
                g2.setColor(new Color(28, 43, 69));
                g2.fillOval(cx - radius, cy - radius, radius * 2, radius * 2);
                g2.setColor(new Color(255, 255, 255));
                g2.fillOval(cx - 3, cy - 3, 6, 6);
            } else {
                g2.setColor(new Color(199, 208, 222));
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawOval(cx - radius, cy - radius, radius * 2, radius * 2);
            }
            g2.dispose();
        }
        @Override public int getIconWidth() { return 16; }
        @Override public int getIconHeight() { return 16; }
    }

    private static class BarraProgreso extends JComponent {
        private final String nombre;
        private final double fraccion;
        private final Color color;
        private final String valorTexto;
        private final boolean esMejor;
        private final ImageIcon starIcon;

        BarraProgreso(String nombre, double fraccion, Color color, String valorTexto,
                      boolean esMejor, ImageIcon starIcon) {
            this.nombre = nombre;
            this.fraccion = Math.max(0, Math.min(1, fraccion));
            this.color = color;
            this.valorTexto = valorTexto;
            this.esMejor = esMejor;
            this.starIcon = starIcon;
            setPreferredSize(new Dimension(800, 50));
            setMinimumSize(new Dimension(200, 50));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            int barHeight = 22;
            int barY = h - barHeight;
            int labelY = barY - 4;

            g2.setFont(new Font("Segoe UI", Font.BOLD, 13));
            g2.setColor(TEXTO_OSCURO);
            FontMetrics nameFm = g2.getFontMetrics();
            g2.drawString(nombre, 0, labelY);
            int nameWidth = nameFm.stringWidth(nombre);

            if (esMejor && starIcon != null) {
                starIcon.paintIcon(null, g2, nameWidth + 4, labelY - starIcon.getIconHeight() + 2);
            }

            Font monoFont = new Font("IBM Plex Mono", Font.PLAIN, 12);
            g2.setFont(monoFont);
            g2.setColor(TEXTO_MUTED);
            FontMetrics fm = g2.getFontMetrics();
            int valueWidth = fm.stringWidth(valorTexto);
            g2.drawString(valorTexto, w - valueWidth, labelY);

            int barWidth = w - valueWidth - 12;
            g2.setColor(TRACK_BG);
            g2.fillRect(0, barY, barWidth, barHeight);

            int fillWidth = (int) (barWidth * fraccion);
            if (fillWidth > 0) {
                g2.setColor(color);
                g2.fillRect(0, barY, fillWidth, barHeight);
            }

            g2.dispose();
        }
    }
}
