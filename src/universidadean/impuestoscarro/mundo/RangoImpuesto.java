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
 * Rango de impuesto de vehículos.
 */
public class RangoImpuesto {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Inicio del rango.
     */
    private double inicio;

    /**
     * Fin del rango.
     */
    private double fin;

    /**
     * Porcentaje.
     */
    private double porcentaje;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un rango de impuesto.<br>
     * <b> post:</b> Se inicializó el inicio, el fin y el porcentaje con la información dada por parámetro.
     *
     * @param pInicio     Inicio del rango. pInicio  > 0.
     * @param pFin        Fin del rango. pFin > pInicio
     * @param pPorcentaje Porcentaje de impuesto que aplica para el rango. pPorcentaje > 0
     */
    public RangoImpuesto(double pInicio, double pFin, double pPorcentaje) {
        inicio = pInicio;
        fin = pFin;
        porcentaje = pPorcentaje;
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el porcentaje.
     *
     * @return Porcentaje del rango.
     */
    public double darPorcentaje() {
        return porcentaje;
    }

    /**
     * Indica si el valor está en el rango.
     *
     * @param pValor Valor que se va a buscar en el rango.
     * @return True si el valor está en el rango, false en caso contrario.
     */
    public boolean contieneA(double pValor) {
        return (pValor >= inicio && pValor < fin);
    }
}
