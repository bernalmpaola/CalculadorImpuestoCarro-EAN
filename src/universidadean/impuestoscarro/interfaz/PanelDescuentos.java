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

/**
 * Panel para presentar la información de descuentos.
 */
public class PanelDescuentos extends JPanel {
    // -----------------------------------------------------------------
    // Elementos de la interfaz
    // -----------------------------------------------------------------

    /**
     * CheckBox para elegir si se quiere liquidar con pronto pago.
     */
    private JCheckBox cbPPago;

    /**
     * CheckBox para elegir si se quiere liquidar con descuento por servicio público.
     */
    private JCheckBox cbSPublico;

    /**
     * CheckBox para elegir si se quiere liquidar con descuento por traslado de cuenta.
     */
    private JCheckBox cbTCuenta;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el panel de descuentos.
     */
    public PanelDescuentos() {
        // Establece el layout como una grilla de 2 filas y 2 columnas
        setLayout(new GridLayout(2, 2));

        // Adiciona un marco con título
        TitledBorder border = new TitledBorder("Descuentos");
        border.setTitleColor(Color.BLUE);
        setBorder(border);

        // Crea los objetos del panel
        cbPPago = new JCheckBox("Pronto pago");
        cbSPublico = new JCheckBox("Servicio público");
        cbTCuenta = new JCheckBox("Traslado de cuenta");

        // Adiciona los objetos del panel
        add(cbPPago);
        add(cbTCuenta);
        add(cbSPublico);
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Indica si aplica el descuento pronto pago.
     *
     * @return Retorna true si aplica el descuento, false en caso contrario.
     */
    public boolean hayDescuentoProntoPago() {
        return cbPPago.isSelected();
    }

    /**
     * Indica si aplica el descuento por servicio público.
     *
     * @return Retorna true si aplica el descuento, false en caso contrario.
     */
    public boolean hayDescuentoServicioPublico() {
        return cbSPublico.isSelected();
    }

    /**
     * Indica si aplica el descuento por traslado de cuenta.
     *
     * @return Retorna true si aplica el descuento, false en caso contrario.
     */
    public boolean hayDescuentoTrasladoCuenta() {
        return cbTCuenta.isSelected();
    }

    /**
     * Limpia los campos del panel.
     */
    public void limpiar() {
        cbPPago.setSelected(false);
        cbSPublico.setSelected(false);
        cbTCuenta.setSelected(false);
    }
}
