package model;

public class Componente {
	
	protected int idComponente;
	protected String nombre;
	protected Lote stock;
	protected String clase;
	protected Equipo equipo;
	protected Centro centro;
	protected String informacion;
	public Componente(int idComponente, String nombre, Lote stock, String clase, Equipo equipo, Centro centro,
			String informacion) {
		super();
		this.idComponente = idComponente;
		this.nombre = nombre;
		this.stock = stock;
		this.clase = clase;
		this.equipo = equipo;
		this.centro = centro;
		this.informacion = informacion;
	}
	public int getIdComponente() {
		return idComponente;
	}
	public void setIdComponente(int idComponente) {
		this.idComponente = idComponente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Lote getStock() {
		return stock;
	}
	public void setStock(Lote stock) {
		this.stock = stock;
	}
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
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
	public String getInformacion() {
		return informacion;
	}
	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}
	@Override
	public String toString() {
		return idComponente+", "+nombre;
	}
	
	
	
}
