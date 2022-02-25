/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Programa de Ingeniería de Sistemas
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Bloque de Estudios: Desarrollo de Software
 * Ejercicio: Cálculo de Impuestos de Carros
 * Adaptado de: Proyecto CUPI2 - UNIANDES
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package universidadean.impuestoscarro.interfaz;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelOpciones extends JPanel implements ActionListener {

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------


    /**
     * Comando de cálculo del valor de los impuestos.
     */
    public final static String CALCULAR = "calcular";

    /**
     * Constante OPCION_1, usada para la opción de la extensión 1.
     */
    private final static String OPCION_1 = "Más Antiguo";

    /**
     * Constante OPCION_2, usada para la opción de la extensión 2.
     */
    private final static String OPCION_2 = "Promedio";


    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal del programa.
     */
    private InterfazImpuestosCarro principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------
    /**
     * Botón para calcular el total de la liquidación.
     */
    private JButton btnCalcular;

    /**
     * Botón para hacer la extensión 1.
     */
    private JButton btnOpcion1;

    /**
     * Botón para hacer la extensión 2.
     */
    private JButton btnOpcion2;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel con todos sus componentes.
     *
     * @param pPrincipal Clase principal de la aplicación. pPrincipal != null.
     */
    public PanelOpciones(InterfazImpuestosCarro pPrincipal) {
        // Guarda la referencia a la ventana principal
        principal = pPrincipal;

        // Establece el layout como una grilla de 1 fila y 3 columnas
        setLayout(new GridLayout(1, 3));

        // Adiciona un marco con título
        TitledBorder border = new TitledBorder("Opciones");
        border.setTitleColor(Color.BLUE);
        setBorder(border);

        btnCalcular = new JButton("Calcular");
        btnCalcular.setActionCommand(CALCULAR);
        btnCalcular.addActionListener(this);
        add(btnCalcular);

        btnOpcion1 = new JButton("Opción 1");
        btnOpcion1.setActionCommand(OPCION_1);
        btnOpcion1.addActionListener(this);
        add(btnOpcion1);

        btnOpcion2 = new JButton("Opción 2");
        btnOpcion2.setActionCommand(OPCION_2);
        btnOpcion2.addActionListener(this);
        add(btnOpcion2);

    }

    /**
     * Respuesta a los eventos en los elementos de la interfaz.
     *
     * @param pEvento Evento generado. pEvento != null.
     */
    public void actionPerformed(ActionEvent pEvento) {
        String comando = pEvento.getActionCommand();

        if (comando.equals(CALCULAR)) {
            principal.calcularImpuestos();
        }
        else if (comando.equals(OPCION_1)) {
            principal.reqFuncOpcion1();
        }
        else if (comando.equals(OPCION_2)) {
            principal.reqFuncOpcion2();
        }

    }
}
