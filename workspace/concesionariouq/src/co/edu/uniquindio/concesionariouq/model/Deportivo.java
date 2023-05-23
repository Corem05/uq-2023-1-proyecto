package co.edu.uniquindio.concesionariouq.model;

public class Deportivo extends Automovil {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Integer numeroCaballosFuerza;
	protected Integer tiempoAlcanza100Kmh; // podria ser Double por los segundos

	/**
	 * Es el constructor del Deportivo
	 * 
	 * @param marca
	 * @param modelo
	 * @param cilindraje
	 * @param velocidadMaxima
	 * @param combustible
	 * @param estado
	 * @param tipo
	 * @param numeroPasajeros
	 * @param numeroBolsasAire
	 * @param numeroPuertas
	 * @param numeroCaballosFuerza
	 * @param tiempoAlcanza100Kmh
	 */
	public Deportivo(String marca, String modelo, Double cilindraje, Double velocidadMaxima, Combustible combustible,
			EstadoVehiculo estado, TipoCambio tipo, Integer numeroPasajeros, Integer numeroBolsasAire,
			Integer numeroPuertas, Integer numeroCaballosFuerza, Integer tiempoAlcanza100Kmh) {
		super(marca, modelo, cilindraje, velocidadMaxima, combustible, estado, tipo, numeroPasajeros, numeroBolsasAire,
				numeroPuertas);
		this.numeroCaballosFuerza = numeroCaballosFuerza;
		this.tiempoAlcanza100Kmh = tiempoAlcanza100Kmh;
	}

	@Override
	public boolean atributosLlenos() {
		return super.atributosLlenos() && numeroCaballosFuerza != null && tiempoAlcanza100Kmh != null;
	}

	/**
	 * Obtiene el numero de caballos de fuerza del deportivo
	 *
	 * @return numeroCaballosFuerza
	 */
	public Integer getNumeroCaballosFuerza() {
		return numeroCaballosFuerza;
	}

	/**
	 * Cambia el numero de caballos de fuerza del deportivo
	 *
	 * @param numeroCaballosFuerza
	 */
	public void setNumeroCaballosFuerza(final Integer numeroCaballosFuerza) {
		this.numeroCaballosFuerza = numeroCaballosFuerza;
	}

	/**
	 * Obtiene el tiempo en el que el deportivo alcanza los 100 Kmh
	 *
	 * @return
	 */
	public Integer getTiempoAlcanza100Kmh() {
		return tiempoAlcanza100Kmh;
	}

	/**
	 * Cambia el tiempo en el que el deportivo alcanza los 100 Kmh
	 * 
	 * @param tiempoAlcanza100Kmh
	 */
	public void setTiempoAlcanza100Kmh(final Integer tiempoAlcanza100Kmh) {
		this.tiempoAlcanza100Kmh = tiempoAlcanza100Kmh;
	}

	@Override
	public TipoVehiculo getTipoVehiculo() {
		return TipoVehiculo.DEPORTIVO;
	}

	@Override
	public String toString() {
		return String.format(
				"Deportivo [numeroPasajeros=%s, numeroBolsasAire=%s, numeroPuertas=%s, marca=%s, modelo=%s, cilindraje=%s, velocidadMaxima=%s, combustible=%s, estado=%s, tipo=%s, numeroCaballosFuerza=%s, tiempoAlcanza100Kmh=%s]",
				numeroPasajeros, numeroBolsasAire, numeroPuertas, marca, modelo, cilindraje, velocidadMaxima,
				combustible, estado, tipo, numeroCaballosFuerza, tiempoAlcanza100Kmh);
	}

}