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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase para navegación entre los carros.
 */
public class PanelNavegacion extends JPanel implements ActionListener {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para ir al primer carro.
     */
    public final static String PRIMERO = "Primero";

    /**
     * Comando para ir al anterior carro.
     */
    public final static String ANTERIOR = "Anterior";

    /**
     * Comando para ir al siguiente carro.
     */
    public final static String SIGUIENTE = "Siguiente";

    /**
     * Comando para ir al último carro.
     */
    public final static String ULTIMO = "Ultimo";

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Botón para ir al primer carro.
     */
    private JButton btnPrimero;

    /**
     * Botón para ir al anterior carro.
     */
    private JButton btnAnterior;

    /**
     * Botón para ir al siguiente carro.
     */
    private JButton btnSiguiente;

    /**
     * Botón para ir al último carro.
     */
    private JButton btnUltimo;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal del programa.
     */
    private InterfazImpuestosCarro principal;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Crea el panel para la navegación entre carros.
     *
     * @param pPrincipal Instancia de la ventana principal.
     */
    public PanelNavegacion(InterfazImpuestosCarro pPrincipal) {
        principal = pPrincipal;
        setLayout(new GridLayout(1, 4));

        btnPrimero = new JButton("<<");
        btnPrimero.setActionCommand(PRIMERO);
        btnPrimero.addActionListener(this);
        add(btnPrimero);

        btnAnterior = new JButton("<");
        btnAnterior.setActionCommand(ANTERIOR);
        btnAnterior.addActionListener(this);
        add(btnAnterior);

        btnSiguiente = new JButton(">");
        btnSiguiente.setActionCommand(SIGUIENTE);
        btnSiguiente.addActionListener(this);
        add(btnSiguiente);

        btnUltimo = new JButton(">>");
        btnUltimo.setActionCommand(ULTIMO);
        btnUltimo.addActionListener(this);
        add(btnUltimo);

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Respuesta a los eventos en los elementos de la interfaz.
     *
     * @param pEvento Evento generado. pEvento != null.
     */
    public void actionPerformed(ActionEvent pEvento) {
        String comando = pEvento.getActionCommand();

        if (comando.equals(PRIMERO)) {
            principal.verPrimero();
        }
        else if (comando.equals(ANTERIOR)) {
            principal.verAnterior();
        }
        else if (comando.equals(SIGUIENTE)) {
            principal.verSiguiente();
        }
        else {
            principal.verUltimo();
        }

    }
}
