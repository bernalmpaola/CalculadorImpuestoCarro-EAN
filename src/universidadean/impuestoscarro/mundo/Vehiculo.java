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

/**
 * Clase que representa un vehículo.
 */
public class Vehiculo {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Nombre de la línea.
     */
    private String linea;

    /**
     * Nombre de la marca.
     */
    private String marca;

    /**
     * Año del modelo.
     */
    private String anio;

    /**
     * Precio del vehículo de este modelo.
     */
    private double precio;

    /**
     * Ruta de la imagen del vehículo.
     */
    private String rutaImagen;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un vehículo con la información dada por parámetro.
     * <b> post: </b> La marca, la línea, el año, el precio y la ruta fueron inicializados con el valor dado.
     * @param pLinea Nombre de la línea. pLinea != null && pLinea != "".
     * @param pMarca Nombre de la marca. pMarca != null && pMarca != "".
     * @param pAnio Año del modelo. pAnio > 0.
     * @param pPrecio Precio del modelo. pPrecio > 0.
     * @param pRuta Ruta de la imagen. pRuta != null && pRuta != "".
     */
    public Vehiculo(String pMarca, String pLinea, String pAnio, double pPrecio, String pRuta) {
        marca = pMarca;
        linea = pLinea;
        anio = pAnio;
        precio = pPrecio;
        rutaImagen = pRuta;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre de la línea.
     * @return Nombre de la línea.
     */
    public String darLinea() {
        return linea;
    }

    /**
     * Retorna el nombre de la marca.
     * @return Nombre de la marca.
     */
    public String darMarca() {
        return marca;
    }

    /**
     * Retorna el año del modelo.
     * @return Año.
     */
    public String darAnio() {
        return anio;
    }

    /**
     * Retorna el precio del modelo.
     * @return Precio.
     */
    public double darPrecio() {
        return precio;
    }

    /**
     * Retorna la ruta de la imagen.
     * @return Ruta de la imagen.
     */
    public String darRutaImagen() {
        return rutaImagen;
    }
}
