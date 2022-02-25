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


import universidadean.impuestoscarro.mundo.CalculadorImpuestos;
import universidadean.impuestoscarro.mundo.Vehiculo;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class InterfazImpuestosCarro extends JFrame {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Ruta de la imagen del banner.
     */
    public final static String RUTA_IMAGEN = "./data/banner.jpg";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Calculador de impuestos.
     */
    private CalculadorImpuestos calculador;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel de vehículos.
     */
    private PanelVehiculo panelVehiculo;

    /**
     * Panel de descuentos.
     */
    private PanelDescuentos panelDescuentos;

    /**
     * Panel de opciones
     */
    private PanelOpciones panelOpciones;

    /**
     * Panel de búsquedas.
     */
    private PanelBusquedas panelBusquedas;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Crea la interfaz de impuestos de carros.
     *
     * @throws Exception Error al cargar los archivos.
     */
    public InterfazImpuestosCarro() throws Exception {
        setTitle("Cálculo impuestos");
        setSize(600, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Crea el calculador de impuestos
        calculador = new CalculadorImpuestos();

        setLayout(new BorderLayout());

        // Crea los paneles y los agrega a la ventana

        // Imagen del título
        JLabel labImagen = new JLabel();
        labImagen.setIcon(new ImageIcon(RUTA_IMAGEN));
        add(labImagen, BorderLayout.NORTH);

        JPanel centro = new JPanel();
        centro.setLayout(new BorderLayout());
        add(centro, BorderLayout.CENTER);

        // Panel de vehículos: panel activo
        panelVehiculo = new PanelVehiculo(this);
        centro.add(panelVehiculo, BorderLayout.CENTER);

        panelBusquedas = new PanelBusquedas(this);
        centro.add(panelBusquedas, BorderLayout.SOUTH);

        JPanel sur = new JPanel();
        sur.setLayout(new BorderLayout());
        add(sur, BorderLayout.SOUTH);

        // Panel de descuentos: panel pasivo
        panelDescuentos = new PanelDescuentos();
        sur.add(panelDescuentos, BorderLayout.CENTER);

        // Panel de opciones: panel activo
        panelOpciones = new PanelOpciones(this);
        sur.add(panelOpciones, BorderLayout.SOUTH);

        Vehiculo actual = calculador.darVehiculoActual();
        panelVehiculo.actualizar(actual.darMarca(), actual.darLinea(), actual.darAnio(), actual.darPrecio(), actual.darRutaImagen());

    }

    // -----------------------------------------------------------------
    // Requerimientos funcionales
    // -----------------------------------------------------------------

    /**
     * Calcula el pago del impuesto según el vehículo.
     */
    public void calcularImpuestos() {
        // Pide al panel respectivo la información de los descuentos
        boolean descProntoPago = panelDescuentos.hayDescuentoProntoPago();
        boolean descServicioPublico = panelDescuentos.hayDescuentoServicioPublico();
        boolean descTrasladoCuenta = panelDescuentos.hayDescuentoTrasladoCuenta();

        // Calcula el valor de los impuestos
        double pago = calculador.calcularPago(descProntoPago, descServicioPublico, descTrasladoCuenta);
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
        df.applyPattern("$ ###,###.##");

        JOptionPane.showMessageDialog(this, "El valor a pagar es: " + df.format(pago), "Cálcular impuestos", JOptionPane.INFORMATION_MESSAGE);

    }

    // ----------------------------------------------------------------
    // Puntos de Extensión
    // ----------------------------------------------------------------

    /**
     * Muestra el anterior vehículo en la lista.
     */
    public void verAnterior() {
        try {
            Vehiculo anterior = calculador.darAnterior();
            panelVehiculo.actualizar(anterior.darMarca(), anterior.darLinea(), anterior.darAnio(), anterior.darPrecio(), anterior.darRutaImagen());

        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Ver vehículo anterior", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Muestra el siguiente vehículo en la lista.
     */
    public void verSiguiente() {
        try {
            Vehiculo siguiente = calculador.darSiguiente();
            panelVehiculo.actualizar(siguiente.darMarca(), siguiente.darLinea(), siguiente.darAnio(), siguiente.darPrecio(), siguiente.darRutaImagen());
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Ver vehículo siguiente", JOptionPane.WARNING_MESSAGE);
        }

    }

    /**
     * Muestra el primer vehículo en la lista.
     */
    public void verPrimero() {
        try {
            Vehiculo primero = calculador.darPrimero();
            panelVehiculo.actualizar(primero.darMarca(), primero.darLinea(), primero.darAnio(), primero.darPrecio(), primero.darRutaImagen());
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Ver primer vehículo", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Muestra el último vehículo en la lista.
     */
    public void verUltimo() {
        try {
            Vehiculo ultimo = calculador.darUltimo();
            panelVehiculo.actualizar(ultimo.darMarca(), ultimo.darLinea(), ultimo.darAnio(), ultimo.darPrecio(), ultimo.darRutaImagen());
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Ver último vehículo", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Busca el vehículo más caro y lo muestra.
     */
    public void buscarMasCaro() {
        Vehiculo masCaro = calculador.buscarVehiculoMasCaro();
        panelVehiculo.actualizar(masCaro.darMarca(), masCaro.darLinea(), masCaro.darAnio(), masCaro.darPrecio(), masCaro.darRutaImagen());

    }

    /**
     * Busca el vehículo de la línea y lo muestra. Si no hay ninguno le informa al usuario.
     *
     * @param pLinea Línea del vehículo. pLinea != null && pLinea != "".
     */
    public void buscarPorLinea(String pLinea) {
        Vehiculo respuesta = calculador.buscarVehiculoPorLinea(pLinea);
        if (respuesta == null) {
            JOptionPane.showMessageDialog(this, "No se encontró ningún vehículo de esta línea", "Buscar por línea", JOptionPane.ERROR_MESSAGE);
        }
        else {
            panelVehiculo.actualizar(respuesta.darMarca(), respuesta.darLinea(), respuesta.darAnio(), respuesta.darPrecio(), respuesta.darRutaImagen());

        }
    }

    /**
     * Busca el primer vehículo de la marca y lo muestra. Si no hay ninguno informa al usuario.
     *
     * @param pMarca Marca del vehículo. pMarca != null && pMarca != "".
     */
    public void buscarPorMarca(String pMarca) {
        Vehiculo respuesta = calculador.buscarVehiculoPorMarca(pMarca);
        if (respuesta == null) {
            JOptionPane.showMessageDialog(this, "No se encontró ningún vehículo de esta marca", "Buscar por marca", JOptionPane.ERROR_MESSAGE);
        }
        else {
            panelVehiculo.actualizar(respuesta.darMarca(), respuesta.darLinea(), respuesta.darAnio(), respuesta.darPrecio(), respuesta.darRutaImagen());
        }
    }

    // ----------------------------------------------------------------
    // Puntos de Extensión
    // ----------------------------------------------------------------

    /**
     * Llamado para realizar el método de extensión 1.
     */
    public void reqFuncOpcion1() {
        Vehiculo respuesta = calculador.buscarVehiculoMasAntiguo();
        JOptionPane.showMessageDialog(this, String.format("%s - %s", respuesta.darMarca(), respuesta.darLinea()),
                "Vehículo más antiguo", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Llamado para realizar el método de extensión 2.
     */
    public void reqFuncOpcion2() {
        double respuesta = calculador.promedioPreciosVehiculos();
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
        df.applyPattern("$ ###,###.##");
        String resp = df.format(respuesta);
        JOptionPane.showMessageDialog(this, resp, "Respuesta", JOptionPane.INFORMATION_MESSAGE);
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Ejecuta la aplicación.
     *
     * @param pArgs Parámetros de la ejecución. No son necesarios.
     */
    public static void main(String[] pArgs) {
        try {
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

            InterfazImpuestosCarro interfaz = new InterfazImpuestosCarro();
            interfaz.setVisible(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
