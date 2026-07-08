package itson.org.laboratorio_algoritmos.gui.utils;

import itson.org.laboratorio_algoritmos.datos.PasoAnimacion;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Panel personalizado que dibuja un conjunto de barras proporcionales a los
 * valores de un arreglo, con colores que cambian según el paso de la
 * animación (comparación, intercambio, completado).
 * 
 * @author andres
 */
public class PanelBarras extends JPanel {

    private int[] arreglo;
    private int idxA = -1;
    private int idxB = -1;
    private PasoAnimacion.TipoOperacion tipoActual;
    private boolean ordenado = false;

    private static final Color COLOR_BARRA = new Color(70, 130, 200);
    private static final Color COLOR_COMPARACION = new Color(255, 200, 50);
    private static final Color COLOR_INTERCAMBIO = new Color(220, 80, 80);
    private static final Color COLOR_ORDENADO = new Color(80, 200, 120);
    private static final Color COLOR_FONDO = new Color(237, 248, 255);

    public PanelBarras() {
        setBackground(COLOR_FONDO);
    }

    /**
     * Establece un nuevo arreglo para graficar y reinicia los indices
     * resaltados.
     */
    public void setArreglo(int[] arreglo) {
        this.arreglo = arreglo;
        this.idxA = -1;
        this.idxB = -1;
        this.ordenado = false;
        repaint();
    }

    /**
     * Actualiza los índices resaltados segun el paso de animación recibido.
     */
    public void setPaso(PasoAnimacion paso) {
        this.idxA = paso.indiceA();
        this.idxB = paso.indiceB();
        this.tipoActual = paso.tipo();
        if (paso.tipo() == PasoAnimacion.TipoOperacion.COMPLETADO) {
            this.ordenado = true;
            this.idxA = -1;
            this.idxB = -1;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (arreglo == null || arreglo.length == 0) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();
        int n = arreglo.length;
        int padding = 2;
        int barWidth = Math.max(1, (ancho - padding * (n + 1)) / n);

        int max = 0;
        for (int v : arreglo) {
            if (v > max) max = v;
        }
        if (max == 0) max = 1;

        for (int i = 0; i < n; i++) {
            int barHeight = (int) ((double) arreglo[i] / max * (alto - 10));
            int x = padding + i * (barWidth + padding);
            int y = alto - 10 - barHeight;

            if (ordenado) {
                g2.setColor(COLOR_ORDENADO);
            } else if (i == idxA || i == idxB) {
                if (tipoActual == PasoAnimacion.TipoOperacion.INTERCAMBIO) {
                    g2.setColor(COLOR_INTERCAMBIO);
                } else {
                    g2.setColor(COLOR_COMPARACION);
                }
            } else {
                g2.setColor(COLOR_BARRA);
            }

            g2.fillRect(x, y, barWidth, barHeight);
            g2.setColor(new Color(50, 50, 50, 30));
            g2.drawRect(x, y, barWidth, barHeight);
        }

        g2.dispose();
    }
}
