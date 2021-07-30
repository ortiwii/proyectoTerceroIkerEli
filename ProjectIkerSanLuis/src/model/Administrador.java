package model;

public class Administrador extends Tecnico {

	private boolean permisos;

	public Administrador(String user, String passw, String nombre, String apllidos, String email, String tipo,
			Boolean permisos, Equipo equipo, Centro centro, int pendientes) {
		super(user, passw, nombre, apllidos, email, tipo, equipo, centro, pendientes);
		permisos = permisos;
	}
	public boolean isPermisos() {
		return permisos;
	}
	public void setPermisos(boolean permisos) {
		this.permisos = permisos;
	}		
	
}
