package model;

import db.GestorDatos;

public class Tecnico extends Usuario{
	protected int pendientes;
	
	public Tecnico(String user, String passw, String nombre, String apllidos, String email, String tipo,
			Equipo equipo, Centro centro, int pendientes) {
		super(user, passw, nombre, apllidos, email, tipo, equipo, centro);
		this.pendientes = pendientes;
	}

	public int getPendientes() {
		return pendientes;
	}

	public void setPendientes(int pendientes) {
		this.pendientes = pendientes;		
	}

	@Override
	public String toString() {
		return "Tecnico [pendientes=" + pendientes + ", user=" + user + ", passw=" + passw + ", nombre=" + nombre
				+ ", apellidos=" + apellidos + ", email=" + email + ", tipo=" + tipo 
				+ ", equipo=" + equipo + ", centro=" + centro + "]";
	}	

}
