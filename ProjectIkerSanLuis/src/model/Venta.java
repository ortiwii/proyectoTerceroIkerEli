package model;

import java.util.Calendar;

public class Venta {

	private int idVenta;
	private Proveedor proveedor;
	private Calendar fechaVenta;
	private Calendar fechaCierre;
	private String estado;
	private Peticion peticion;
	private String informacion;
	public Venta(int idVenta, Proveedor proveedor, Calendar fechaVenta, Calendar fechaCierre, String estado,
			Peticion peticion, String informacion) {
		super();
		this.idVenta = idVenta;
		this.proveedor = proveedor;
		this.fechaVenta = fechaVenta;
		this.fechaCierre = fechaCierre;
		this.estado = estado;
		this.peticion = peticion;
		this.informacion = informacion;
	}
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public Calendar getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(Calendar fechaVenta) {
		this.fechaVenta = fechaVenta;
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
	public Peticion getPeticion() {
		return peticion;
	}
	public void setPeticion(Peticion peticion) {
		this.peticion = peticion;
	}
	public String getInformacion() {
		return informacion;
	}
	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}
	
	
}
