package co.edu.uniquindio.concesionariouq.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import co.edu.uniquindio.concesionariouq.exceptions.ConcesionarioException;
import co.edu.uniquindio.concesionariouq.exceptions.LoginFailedException;
import co.edu.uniquindio.concesionariouq.exceptions.NullException;
import co.edu.uniquindio.concesionariouq.exceptions.TransaccionNoExisteException;
import co.edu.uniquindio.concesionariouq.exceptions.TransaccionYaExisteException;
import co.edu.uniquindio.concesionariouq.exceptions.UsuarioEncontradoException;
import co.edu.uniquindio.concesionariouq.exceptions.UsuarioNoEncontradoException;
import co.edu.uniquindio.concesionariouq.exceptions.VehiculoNoExisteException;
import co.edu.uniquindio.concesionariouq.exceptions.VehiculoYaExisteException;

public class Concesionario implements GestionableVehiculo, GestionableUsuario, GestionableTransaccion, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ATRIBUTOS
	private String nombre;
	private String direccion;
	private Map<String, Vehiculo> listaVehiculos;
	private Map<String, Usuario> listaUsuarios;
	private Map<String, Transaccion> listaTransacciones;

	/**
	 * Es el metodo constructor vacio de la clase
	 */
	public Concesionario() {
	}

	/**
	 * Es el metodo constructor de la clase
	 */
	public Concesionario(final String nombre, final String direccion) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.listaVehiculos = new HashMap<String, Vehiculo>();
		this.listaUsuarios = new HashMap<String, Usuario>();
		this.listaTransacciones = new HashMap<String, Transaccion>();
	}

	@Override
	public Vehiculo buscarVehiculo(String id) {
		return listaVehiculos.getOrDefault(id, null);
	}

	@Override
	public boolean validarVehiculo(String id) {
		return buscarVehiculo(id) != null;
	}

	@Override
	public void agregarVehiculo(String id, Vehiculo vehiculo) throws NullException, VehiculoYaExisteException {
		if (id == null)
			throw new NullException("La identificacion enviada es null");
		if (vehiculo == null)
			throw new NullException("El vehiculo enviado es null");
		if (validarVehiculo(id))
			throw new VehiculoYaExisteException("El vehiculo con la identificacion " + id + " ya existe");
		listaVehiculos.put(id, vehiculo);
	}

	/**
	 * Elimina un vehiculo por medio de la identificacion, si no se encuentra se
	 * muestra una excepcion
	 * 
	 * @param id
	 * @throws ConcesionarioException
	 */
	public void eliminarVehiculo(String id) throws VehiculoNoExisteException {
		if (!validarVehiculo(id))
			throw new VehiculoNoExisteException("El vehiculo con la placa " + id + " no existe");
		listaVehiculos.remove(id);
	}

	/**
	 * Lista los vehiculos del concesionario, como la lista de vehiculos es en
	 * realidad un map, toca convertirlo a un set, y luego hacer un map para cambiar
	 * el tipo de variable a vehiculo
	 * 
	 * @return
	 */
	@Override
	public List<Vehiculo> listarVehiculos() {
		return listaVehiculos.entrySet().stream().map(Entry<String, Vehiculo>::getValue).collect(Collectors.toList());
	}

	/**
	 * Valida si una transaccion existe o no a partir de su código
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public boolean validarTransaccion(String id) {
		return buscarTransaccion(id) != null;
	}

	public Transaccion buscarTransaccion(String codigoTransaccion) {
		return listaTransacciones.getOrDefault(codigoTransaccion, null);
	}

	@Override
	public void agregarTransaccion(Transaccion transaccion) throws TransaccionYaExisteException, NullException {
		if (validarTransaccion(transaccion.getCodigo()))
			throw new TransaccionYaExisteException("La transaccion ya existe");
		listaTransacciones.put(transaccion.getCodigo(), transaccion);
	}

	/**
	 * Elimina una transaccion, muestra un error si no se encuentra
	 * 
	 * @param codigo
	 * @throws ConcesionarioException
	 */
	public void eliminarTransaccion(String codigo) throws TransaccionNoExisteException {
		if (!validarTransaccion(codigo))
			throw new TransaccionNoExisteException("La transaccion no existe, no se puede eliminar");
		listaTransacciones.remove(codigo);
	}

	@Override
	public List<Transaccion> listarTransacciones() {
		return listaTransacciones.entrySet().stream().map(Entry<String, Transaccion>::getValue)
				.collect(Collectors.toList());
	}

	/**
	 * Busca un usuario a partir de su identificacion y contraseña
	 *
	 * @param identificacion
	 * @param contrasena
	 * @return
	 */
	public Usuario buscarUsuarioLogin(String identificacion, String contrasena) {
		Usuario usuario = buscarUsuario(identificacion);
		if (usuario != null && usuario.getContrasena().equals(contrasena))
			return usuario;
		return null;
	}

	/**
	 * Intenta hacer login al usuario, si no se puede marca una excepcion
	 * 
	 * @param contrasena
	 * @param identificacion
	 * @throws LoginFailedException
	 * @return el usuario al obtenido al hacer login
	 */
	public Usuario hacerLogin(String identificacion, String contrasena) throws LoginFailedException {
		Usuario usuario = buscarUsuarioLogin(identificacion, contrasena);
		if (usuario == null) {
			throw new LoginFailedException(
					"La id o contraseña especificada no coinciden con tus datos, intenta nuevamente");
		}
		return usuario;
	}

	/**
	 * Busca un usuario basandose en su identificacion
	 *
	 * @param identificacion
	 * @return
	 */
	public Usuario buscarUsuario(String identificacion) {
		return listaUsuarios.getOrDefault(identificacion, null);
	}

	/**
	 * 
	 * @param identificacion
	 * @return
	 */
	public boolean validarUsuario(String identificacion) {
		return buscarUsuario(identificacion) != null;
	}

	public void agregarUsuario(Usuario usuario) throws UsuarioEncontradoException, NullException {
		if (usuario == null)
			throw new NullException("El usuario enviado es null");
		if (validarUsuario(usuario.getId()))
			throw new UsuarioEncontradoException("El usuario ya se encuentra agregado");
		listaUsuarios.put(usuario.getId(), usuario);

	}

	@Override
	public void eliminarUsuario(String id) throws UsuarioNoEncontradoException, NullException {
		if (id == null)
			throw new NullException("La identificacion enviada es null");
		if (!validarUsuario(id))
			throw new UsuarioNoEncontradoException("El usuario no fue encontrado, no se puede eliminar");
		listaUsuarios.remove(id);
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return listaUsuarios.entrySet().stream().map(Entry<String, Usuario>::getValue).collect(Collectors.toList());
	}

	/**
	 * Obtiene el nombre del concesionario
	 *
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Cambia el nombre del concesionario
	 *
	 * @param nombre
	 */
	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene la direccion del concesionario
	 *
	 * @return direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Cambia la direccion del concesionario
	 *
	 * @param direccion
	 */
	public void setDireccion(final String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return String.format(
				"Concesionario [nombre=%s, direccion=%s, listaVehiculos=%s, listaUsuarios=%s, listaTransacciones=%s]",
				nombre, direccion, listaVehiculos, listaUsuarios, listaTransacciones);
	}

}
