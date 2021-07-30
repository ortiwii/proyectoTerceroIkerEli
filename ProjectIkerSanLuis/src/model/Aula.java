package model;

public class Aula {
	protected String codigo;
	protected int tama�o;
	protected int disponibles;
	protected Centro centro;
	public Aula(String codigo, int tama�o, int disponibles, Centro centro) {
		super();
		this.codigo = codigo;
		this.tama�o = tama�o;
		this.disponibles = disponibles;
		this.centro = centro;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int getTama�o() {
		return tama�o;
	}
	public void setTama�o(int tama�o) {
		this.tama�o = tama�o;
	}
	public int getDisponibles() {
		return disponibles;
	}
	public void setDisponibles(int disponibles) {
		this.disponibles = disponibles;
	}
	public Centro getCentro() {
		return centro;
	}
	public void setCentro(Centro centro) {
		this.centro = centro;
	}
	@Override
	public String toString() {
		return this.codigo;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Aula(codigo, tama�o, disponibles, centro);
	}
	
	
	
}
