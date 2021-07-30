package model;

public class Centro {
	protected String nombre;
	protected String informacion;
	public Centro(String nombre, String informacion) {
		super();
		this.nombre = nombre;
		this.informacion = informacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getInformacion() {
		return informacion;
	}
	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}
	@Override
	public String toString() {
		return this.nombre;
	}
	
	
}
