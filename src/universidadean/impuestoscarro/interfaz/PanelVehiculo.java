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
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Panel para mostrar la información de los vehículos.
 */
public class PanelVehiculo extends JPanel {
    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Campo de texto para visualizar la marca del vehículo.
     */
    private JTextField txtMarca;

    /**
     * Campo de texto para escribir y visualizar la línea del vehículo.
     */
    private JTextField txtLinea;

    /**
     * Campo de texto para escribir y visualizar el modelo del vehículo.
     */
    private JTextField txtModelo;

    /**
     * Campo de texto para visualizar el valor del vehículo.
     */
    private JTextField txtValor;

    /**
     * Etiqueta para mostrar la imagen del vehículo.
     */
    private JLabel labImagen;

    /**
     * Panel para la navegación del vehículo.
     */
    private PanelNavegacion panelNavegacion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el panel donde se mostrarán los vehículos.
     *
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public PanelVehiculo(InterfazImpuestosCarro pPrincipal) {
        setLayout(new BorderLayout());
        labImagen = new JLabel();
        labImagen.setBorder(new EmptyBorder(0, 0, 0, 10));
        add(labImagen, BorderLayout.WEST);

        JPanel informacion = new JPanel();
        informacion.setLayout(new GridLayout(4, 2, 10, 5));
        add(informacion, BorderLayout.CENTER);

        // Adiciona un marco con título
        TitledBorder border = new TitledBorder("Datos del vehículo");
        border.setTitleColor(Color.BLUE);
        setBorder(border);

        // Crea e inicializa los objetos del panel
        JLabel labMarca = new JLabel("Marca");
        informacion.add(labMarca);

        txtMarca = new JTextField();
        txtMarca.setEditable(false);
        informacion.add(txtMarca);

        JLabel labLinea = new JLabel("Línea");
        JLabel labModelo = new JLabel("Modelo");
        JLabel labValor = new JLabel("Valor");
        txtLinea = new JTextField();
        txtLinea.setEditable(false);
        txtModelo = new JTextField();
        txtModelo.setEditable(false);

        txtValor = new JTextField();
        txtValor.setEditable(false);
        txtValor.setForeground(Color.BLUE);
        txtValor.setBackground(Color.WHITE);

        // Adiciona los objetos al panel
        informacion.add(labLinea);
        informacion.add(txtLinea);
        informacion.add(labModelo);
        informacion.add(txtModelo);
        informacion.add(labValor);
        informacion.add(txtValor);

        panelNavegacion = new PanelNavegacion(pPrincipal);
        add(panelNavegacion, BorderLayout.SOUTH);
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza el panel con la información del vehículo que entra por parámetro.
     *
     * @param pMarca      Marca del vehículo a mostrar.
     * @param pLinea      Línea del vehículo a mostrar.
     * @param pAnio       Año del vehículo a mostrar.
     * @param pPrecio     Precio del vehículo a mostrar.
     * @param pRutaImagen Ruta del vehiculo a mostrar
     */
    public void actualizar(String pMarca, String pLinea, String pAnio, double pPrecio, String pRutaImagen) {
        txtMarca.setText(pMarca);
        txtLinea.setText(pLinea);
        txtModelo.setText(pAnio);
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
        df.applyPattern("$ ###,###.##");
        txtValor.setText(df.format(pPrecio));
        labImagen.setIcon(new ImageIcon(new ImageIcon("./data/imagenes/" + pRutaImagen).getImage().getScaledInstance(280, 170, Image.SCALE_DEFAULT)));
    }

}

