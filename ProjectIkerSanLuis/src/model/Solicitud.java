package model;

import java.util.Calendar;


public class Solicitud extends Incidencia{

	//	protected Aula aula = null;
	protected Componente componente = null;
	
	public Solicitud(int idSolInc, Calendar fechaInicio, Calendar fechaCierre, String estado, Usuario user, Tecnico tecnico,
			String descripcion, Componente componente) {
		super(idSolInc, fechaInicio, fechaCierre, estado, user, tecnico, descripcion);
		
//		this.aula = aula;
		this.componente = componente;
	}

//	public Aula getAula() {
//		return aula;
//	}
//
//	public void setAula(Aula aula) {
//		this.aula = aula;
//	}

	public Componente getComponente() {
		return componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}
	
	@Override
	public String toString() {
		return "Solicitud [componente=" + componente + ", \nidSolInc=" + idSolInc + ", \nfechaInicio=" + fechaInicio
				+ ", \nfechaCierre=" + fechaCierre + ", \nestado=" + estado + ", \nuser=" + user + ", \ntecnico=" + tecnico
				+ ", \ndescripcion=" + descripcion + "]";
	}
}
