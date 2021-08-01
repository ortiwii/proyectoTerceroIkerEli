package model;

public class Administrador extends Tecnico {

	private boolean permisos;

	public Administrador(String user, String passw, String nombre, String apllidos, String email, String tipo,
			boolean permisos, Equipo equipo, Centro centro, int pendientes) {
		super(user, passw, nombre, apllidos, email, tipo, equipo, centro, pendientes);
		this.permisos = permisos;
	}
	public boolean isPermisos() {
		return permisos;
	}
	public void setPermisos(boolean permisos) {
		this.permisos = permisos;
	}
	@Override
	public String toString() {
		return "Administrador [permisos=" + permisos + ", pendientes=" + pendientes + ", user=" + user + ", passw="
				+ passw + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + ", tipo=" + tipo
				+ ", equipo=" + equipo + ", centro=" + centro + "]";
	}		
	
}
