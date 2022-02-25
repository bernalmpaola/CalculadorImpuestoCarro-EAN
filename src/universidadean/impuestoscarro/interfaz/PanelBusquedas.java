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

public class PanelBusquedas extends JPanel implements ActionListener {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para buscar un vehículo por línea.
     */
    public final static String BUSCAR_POR_LINEA = "Buscar por línea";

    /**
     * Comando para buscar un vehículo por marca.
     */
    public final static String BUSCAR_POR_MARCA = "Buscar por marca";

    /**
     * Comando para buscar el vehículo más caro.
     */
    public final static String BUSCAR_MAS_CARO = "Buscar vehículo más Caro";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Instancia de la clase principal.
     */
    private InterfazImpuestosCarro principal;

    // -----------------------------------------------------------------
    // Elementos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Campo de texto para la línea.
     */
    private JTextField txtLinea;

    /**
     * Campo de texto para la marca.
     */
    private JTextField txtMarca;

    /**
     * Botón para buscar por línea.
     */
    private JButton btnBuscarLinea;

    /**
     * Botón para buscar por marca.
     */
    private JButton btnBuscarMarca;

    /**
     * Botón para buscar el más caro.
     */
    private JButton btnBuscarCaro;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel con los botones y campos de texto.
     *
     * @param pPrincipal Instancia principal de la aplicación.
     */
    public PanelBusquedas(InterfazImpuestosCarro pPrincipal) {
        principal = pPrincipal;
        setLayout(new GridLayout(3, 2));
        setPreferredSize(new Dimension(0, 100));
        TitledBorder border = new TitledBorder("Búsquedas");
        border.setTitleColor(Color.BLUE);
        setBorder(border);


        txtLinea = new JTextField();
        add(txtLinea);

        btnBuscarLinea = new JButton(BUSCAR_POR_LINEA);
        btnBuscarLinea.addActionListener(this);
        btnBuscarLinea.setActionCommand(BUSCAR_POR_LINEA);
        add(btnBuscarLinea);

        txtMarca = new JTextField();
        add(txtMarca);

        btnBuscarMarca = new JButton(BUSCAR_POR_MARCA);
        btnBuscarMarca.addActionListener(this);
        btnBuscarMarca.setActionCommand(BUSCAR_POR_MARCA);
        add(btnBuscarMarca);

        add(new JLabel());

        btnBuscarCaro = new JButton(BUSCAR_MAS_CARO);
        btnBuscarCaro.addActionListener(this);
        btnBuscarCaro.setActionCommand(BUSCAR_MAS_CARO);
        add(btnBuscarCaro);

    }

    /**
     * Respuesta a los eventos en los elementos de la interfaz.
     *
     * @param pEvento Evento generado. pEvento != null.
     */
    public void actionPerformed(ActionEvent pEvento) {
        String comando = pEvento.getActionCommand();

        if (comando.equals(BUSCAR_MAS_CARO)) {
            principal.buscarMasCaro();
        }
        else if (comando.equals(BUSCAR_POR_LINEA)) {
            String linea = txtLinea.getText();
            if (linea.isEmpty()) {
                JOptionPane.showMessageDialog(principal, "Debe ingresar una línea.", "Buscar por línea", JOptionPane.ERROR_MESSAGE);
            }
            else {
                principal.buscarPorLinea(linea);
                txtLinea.setText("");
            }
        }
        else {
            String marca = txtMarca.getText();
            if (marca.isEmpty()) {
                JOptionPane.showMessageDialog(principal, "Debe ingresar una marca.", "Buscar por marca", JOptionPane.ERROR_MESSAGE);
            }
            else {
                principal.buscarPorMarca(marca);
                txtMarca.setText("");
            }
        }
    }
}
