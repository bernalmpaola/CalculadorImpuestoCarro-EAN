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

package universidadean.impuestoscarro.mundo;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * Calculador de impuestos.
 */
public class CalculadorImpuestos {
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Porcentaje de descuento por pronto pago.
     */
    public static final double PORC_DESC_PRONTO_PAGO = 10.0;

    /**
     * Valor de descuento por servicio público.
     */
    public static final double VALOR_DESC_SERVICIO_PUBLICO = 50000.0;

    /**
     * Porcentaje de descuento por traslado de cuenta.
     */
    public static final double PORC_DESC_TRASLADO_CUENTA = 5.0;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Vehículos que maneja el calculador.
     */
    private Vehiculo[] vehiculos;

    /**
     * Vehículo actual mostrado en la aplicación.
     */
    private int posVehiculoActual;

    /**
     * Rangos de los impuestos.
     */
    private RangoImpuesto[] rangosImpuesto;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un calculador de impuestos, cargando la información de dos archivos. <br>
     * <b>post: </b> Se inicializaron los arreglos de vehículos y rangos.<br>
     * Se cargaron los datos correctamente a partir de los archivos.
     *
     * @throws Exception Error al cargar los archivos.
     */
    public CalculadorImpuestos() throws Exception {
        cargarVehiculos("data/vehiculos.txt");
        cargarTablaImpuestos("data/impuestos.properties");
    }

    // ----------------------------------------------------------------
    // Métodos
    // ----------------------------------------------------------------

    /**
     * Carga los datos de los vehículos que maneja el calculador de impuestos. <br>
     * <b>post: </b> Se cargan todos los vehículos del archivo con sus datos.
     *
     * @param pArchivo Nombre del archivo donde se encuentran los datos de los vehículos. pArchivo != null.
     * @throws Exception Si ocurre algún error cargando los datos.
     */
    private void cargarVehiculos(String pArchivo) throws Exception {
        String texto, valores[], sMarca, sLinea, sModelo, sImagen;
        double precio;
        int cantidad = 0;
        Vehiculo vehiculo;
        try {
            File datos = new File(pArchivo);
            FileReader fr = new FileReader(datos);
            BufferedReader lector = new BufferedReader(fr);
            texto = lector.readLine();

            cantidad = Integer.parseInt(texto);
            vehiculos = new Vehiculo[cantidad];
            posVehiculoActual = 0;

            texto = lector.readLine();
            for (int i = 0; i < vehiculos.length; i++) {
                valores = texto.split(",");

                sMarca = valores[0];
                sLinea = valores[1];
                sModelo = valores[2];
                sImagen = valores[4];
                precio = Double.parseDouble(valores[3]);

                vehiculo = new Vehiculo(sMarca, sLinea, sModelo, precio, sImagen);
                vehiculos[i] = vehiculo;
                // Siguiente línea
                texto = lector.readLine();
            }
            lector.close();
        }
        catch (IOException e) {
            throw new Exception("Error al cargar los datos almacenados de vehículos.");
        }
        catch (NumberFormatException e) {
            throw new Exception("El archivo no tiene el formato esperado.");
        }
    }

    /**
     * Carga la tabla de impuestos por los rangos. <br>
     * <b>post: </b> Se cargan todos valores de impuestos según los rangos de valores.
     *
     * @param pArchivo Ubicación del archivo a leer. pArchivo != null.
     * @throws Exception Si ocurre un error al cargar los rangos.
     */
    private void cargarTablaImpuestos(String pArchivo) throws Exception {
        Properties datos = new Properties();
        int rangos = 0;
        String texto, valores[];
        double inicio, fin, porcentaje;
        RangoImpuesto rango;
        try {
            FileInputStream input = new FileInputStream(pArchivo);
            datos.load(input);
            rangos = Integer.parseInt(datos.getProperty("numero.rangos"));
            rangosImpuesto = new RangoImpuesto[rangos];

            for (int i = 0; i < rangos; i++) {
                texto = datos.getProperty("rango" + (i + 1));
                valores = texto.split(",");
                try {
                    inicio = Double.parseDouble(valores[0]);
                    fin = Double.parseDouble(valores[1]);
                    porcentaje = Double.parseDouble(valores[2]);
                }
                catch (Exception e) {
                    throw new Exception("Error en la definición de rango" + i);
                }

                rango = new RangoImpuesto(inicio, fin, porcentaje);
                rangosImpuesto[i] = rango;
            }
        }
        catch (IOException e) {
            throw new Exception("Error al cargar los rangos de impuestos.");
        }
        catch (NumberFormatException e) {
            throw new Exception("Error en el formato del archivo.");
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error en el formato del archivo.");
        }
    }

    /**
     * Busca, dado un valor, el rango de impuestos al que corresponde.
     *
     * @param valor Valor a buscar. valor > 0.
     * @return Rango de impuesto que contiene al valor o null si no lo encuentra.
     */
    private RangoImpuesto buscarRangoImpuesto(double valor) {
        RangoImpuesto rango = null;
        for (int i = 0; i < rangosImpuesto.length; i++) {
            if (rangosImpuesto[i].contieneA(valor)){
                rango = rangosImpuesto[i];
            }
        }
        return rango;
    }

    /**
     * Calcula el pago de impuesto que debe hacer el vehículo actual. <br>
     * <b>pre:</b> Las listas de rangos y vehículos están inicializadas.
     *
     * @param descProntoPago      Indica si aplica el descuento por pronto pago.
     * @param descServicioPublico Indica si aplica el descuento por servicio público.
     * @param descTrasladoCuenta  Indica si aplica el descuento por traslado de cuenta.
     * @return Valor a pagar de acuerdo con las características del vehículo y los descuentos que se pueden aplicar.
     */
    public double calcularPago(boolean descProntoPago, boolean descServicioPublico, boolean descTrasladoCuenta) {
        double pago = 0.0, precio = darVehiculoActual().darPrecio();
        RangoImpuesto rango = buscarRangoImpuesto(precio);
        pago = (precio*rango.darPorcentaje())/100;
        if (descProntoPago){
            pago = pago-((pago*PORC_DESC_PRONTO_PAGO)/100);
        }
        if (descServicioPublico){
            pago = pago-VALOR_DESC_SERVICIO_PUBLICO;
        }
        if (descTrasladoCuenta){
            pago = pago-((pago*PORC_DESC_TRASLADO_CUENTA)/100);
        }
        return pago;
    }

    /**
     * Retorna el primer vehículo. <br>
     * <b>post: </b> Se actualizó la posición del vehículo actual.
     *
     * @return El primer vehículo, que ahora es el vehículo actual.
     * @throws Exception Si ya se encuentra en el primer vehículo.
     */
    public Vehiculo darPrimero() throws Exception {
        if (posVehiculoActual == 0) {
            throw new Exception("Ya se encuentra en el primer vehículo.");
        }
        posVehiculoActual = 0;
        return darVehiculoActual();
    }

    /**
     * Retorna el vehículo anterior al actual. <br>
     * <b>post: </b> Se actualizó la posición del vehículo actual.
     *
     * @return El anterior vehículo, que ahora es el vehículo actual.
     * @throws Exception Si ya se encuentra en el primer vehículo.
     */
    public Vehiculo darAnterior() throws Exception {
        if (posVehiculoActual == 0) {
            throw new Exception("Se encuentra en el primer vehículo.");
        }
        posVehiculoActual--;
        return darVehiculoActual();
    }

    /**
     * Retorna el vehículo siguiente al actual. <br>
     * <b>post: </b> Se actualizó la posición del vehículo actual.
     *
     * @return El siguiente vehículo, que ahora es el vehículo actual.
     * @throws Exception Si ya se encuentra en el último vehículo
     */
    public Vehiculo darSiguiente() throws Exception {
        if (posVehiculoActual == vehiculos.length - 1) {
            throw new Exception("Se encuentra en el último vehículo.");
        }
        posVehiculoActual++;
        return darVehiculoActual();
    }

    /**
     * Retorna el último vehículo. <br>
     * <b>post: </b> Se actualizó la posición del vehículo actual.
     *
     * @return El último vehículo, que ahora es el vehículo actual.
     * @throws Exception Si ya se encuentra en el último vehículo
     */
    public Vehiculo darUltimo() throws Exception {
        if (posVehiculoActual == vehiculos.length - 1) {
            throw new Exception("Ya se encuentra en el último vehículo.");
        }
        posVehiculoActual = vehiculos.length - 1;
        return darVehiculoActual();
    }

    /**
     * Retorna el vehículo actual.
     *
     * @return El vehículo actual.
     */
    public Vehiculo darVehiculoActual() {
        return vehiculos[posVehiculoActual];
    }

    /**
     * Busca el vehículo más caro, lo asigna como actual y lo retorna.
     *
     * @return El vehículo más caro.
     */
    public Vehiculo buscarVehiculoMasCaro() {

        double precioVeh = 0 ;
        Vehiculo vehiculoCaro = null;

        for (Vehiculo v : vehiculos ){
            if (v.darPrecio() > precioVeh) {
                precioVeh = v.darPrecio();
                vehiculoCaro = v;
            }
        }
        return vehiculoCaro;
    }



    /**
     * Busca y retorna el primer vehículo que encuentra con la marca que llega por parámetro. <br>
     * <b>post: </b> Se asigna como vehículo actual al vehículo encontrado.
     *
     * @param marca Marca buscada.
     * @return El primer vehículo de la marca. Si no encuentra ninguno retorna null.
     */
    public Vehiculo buscarVehiculoPorMarca(String marca) {
        Vehiculo buscado = null;
        for (Vehiculo v : vehiculos ){
            if (v.darMarca().equalsIgnoreCase(marca)) {
                buscado = v;
            }
        }
        return buscado;
    }

    /**
     * Busca y retorna el vehículo de la línea buscada. <br>
     * <b>post: </b> Se asigna como vehículo actual al vehículo encontrado.
     *
     * @param linea Línea buscada. pLinea != null && pLinea != ""
     * @return El vehículo de la línea, null si no encuentra ninguno.
     */
    public Vehiculo buscarVehiculoPorLinea(String linea) {
        Vehiculo buscado = null;
        for (Vehiculo v : vehiculos ){
            if (v.darLinea().equalsIgnoreCase(linea)) {
                buscado = v;
            }
        } return buscado;
        }


    /**
     * Busca el vehículo más antiguo, lo asigna como actual y lo retorna.
     *
     * @return El vehículo más antiguo.
     */

    public Vehiculo buscarVehiculoMasAntiguo() {
        Vehiculo buscado = null;
        for (Vehiculo v : vehiculos ){
           if (Integer.parseInt(v.darAnio()) < Integer.parseInt(buscado.darAnio())) {
            buscado = v;
        } }


        return buscado;
    }

    /**
     * Calcula el promedio de los precios de todos los automóviles que están en el sistema
     *
     * @return Promedio de precios
     */
    public double promedioPreciosVehiculos() {
        double promedio = 0.0;
        for (int i = 0; i < vehiculos.length; i++) {
            promedio = promedio + vehiculos[i].darPrecio();
        }
        promedio = promedio/vehiculos.length;
        return promedio;
    }


}
