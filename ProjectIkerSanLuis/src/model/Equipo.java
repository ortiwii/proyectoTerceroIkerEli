package model;

public class Equipo {

	protected int idEquipo;
	protected String nombre;
	protected int almacenamiento;
	protected int ram;
	protected String ip;
	protected Aula aula;
	public Equipo(int idEquipo, String nombre, int almacenamiento, int ram, String ip, Aula aula) {
		super();
		this.idEquipo = idEquipo;
		this.nombre = nombre;
		this.almacenamiento = almacenamiento;
		this.ram = ram;
		this.ip = ip;
		this.aula = aula;
	}
	public int getIdEquipo() {
		return idEquipo;
	}
	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getAlmacenamiento() {
		return almacenamiento;
	}
	public void setAlmacenamiento(int almacenamiento) {
		this.almacenamiento = almacenamiento;
	}
	public int getRam() {
		return ram;
	}
	public void setRam(int ram) {
		this.ram = ram;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Aula getAula() {
		return aula;
	}
	public void setAula(Aula aula) {
		this.aula = aula;
	}
	@Override
	public String toString() {
		return "Equipo [idEquipo=" + idEquipo + ", nombre=" + nombre + ", almacenamiento=" + almacenamiento + ", ram="
				+ ram + ", ip=" + ip + ", aula=" + aula + "]";
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Equipo(idEquipo, nombre, almacenamiento, ram, ip, aula);
	}
	
	
}
