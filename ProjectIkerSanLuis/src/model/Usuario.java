package model;

public class Usuario {
	protected String user;
	protected String passw;
	protected String nombre;
	protected String apellidos;
	protected String email;
	protected String tipo;
	protected boolean permisos;
	protected Equipo equipo;
	protected Centro centro;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassw() {
		return passw;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Usuario (String user, String passw, String nombre, String apllidos,
			String email, String tipo, Boolean permisos, Equipo equipo, Centro centro) {
		this.user = user;
		this.passw = passw;
		this.nombre = nombre;
		this.apellidos = apllidos;
		this.email = email;
		this.tipo = tipo;
		this.permisos = permisos;		
		this.equipo = equipo;		
		this.centro = centro;
	}
	
	@Override
	public String toString() {
		return "Usuario [user=" + user + ", passw=" + passw + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", email=" + email + ", tipo=" + tipo + ", permisos=" + permisos + ", equipo=" + equipo + ", centro="
				+ centro + "]";
	}

	public Usuario (String pUser, String pPassw, String pTipo) {
		this.user = pUser;
		this.passw = pPassw;
		this.tipo = pTipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isPermisos() {
		return permisos;
	}

	public void setPermisos(boolean permisos) {
		this.permisos = permisos;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	public void setPassw(String passw) {
		this.passw = passw;
	}
	public boolean isPassw (String passw) {
		return this.passw.equals(passw);
	}
	
}
