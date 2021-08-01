package model;

public class Peticion {
	private int idPeticion;
	private AlmacenProveedores comonenteAlmacen;
	private Tecnico tecnico;
	private Administrador administrador;
	private String descripcion;
	private int cantidad;
	private String estado;
	
	public Peticion(int idPeticion, AlmacenProveedores comonenteAlmacen, Tecnico tecnico, Administrador administrador,
			String descripcion, int cantidad, String estado) {
		super();
		this.idPeticion = idPeticion;
		this.comonenteAlmacen = comonenteAlmacen;
		this.tecnico = tecnico;
		this.administrador = administrador;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.estado = estado;
	}
	public int getIdPeticion() {
		return idPeticion;
	}
	public void setIdPeticion(int idPeticion) {
		this.idPeticion = idPeticion;
	}
	public AlmacenProveedores getComonenteAlmacen() {
		return comonenteAlmacen;
	}
	public void setComonenteAlmacen(AlmacenProveedores comonenteAlmacen) {
		this.comonenteAlmacen = comonenteAlmacen;
	}
	public Tecnico getTecnico() {
		return tecnico;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad (int cantidad) {
		this.cantidad = cantidad;
	}
	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}
	public String getEstado() {
		return this.estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Administrador getAdministrador() {
		return administrador;
	}
	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}
