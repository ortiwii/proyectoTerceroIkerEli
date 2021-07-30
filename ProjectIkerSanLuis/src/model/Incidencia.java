package model;

import java.util.Calendar;

public class Incidencia {
	
	protected int idSolInc;
	protected Calendar fechaInicio;
	protected Calendar fechaCierre;
	protected String estado;
	protected Usuario user; 
	protected Tecnico tecnico;
	protected String descripcion;
//	protected String tipo;
	
	public Incidencia(int idSolInc, Calendar fechaInicio, Calendar fechaCierre, String estado, Usuario user,
			Tecnico tecnico, String descripcion) {
		super();
		this.idSolInc = idSolInc;
		this.fechaInicio = fechaInicio;
		this.fechaCierre = fechaCierre;
		this.estado = estado;
		this.user = user;
		this.tecnico = tecnico;
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdSolInc() {
		return idSolInc;
	}

	public Calendar getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Calendar getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Calendar fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	@Override
	public String toString() {
		return "Incidencia [idSolInc=" + idSolInc + ", fechaInicio=" + fechaInicio + ", fechaCierre=" + fechaCierre
				+ ", estado=" + estado + ", user=" + user + ", tecnico=" + tecnico + ", descripcion=" + descripcion
				+ "]";
	}
	
	
	
	
}
