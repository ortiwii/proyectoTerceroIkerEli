package model;

public class Proveedor {
	protected String idProveedor;
	protected String user;
	protected String passw;
	protected String email;
	protected Centro centro;
	public Proveedor(String idProveedor, String user, String passw, String email, Centro centro) {
		super();
		this.idProveedor = idProveedor;
		this.user = user;
		this.passw = passw;
		this.email = email;
		this.centro = centro;
	}
	public String getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(String idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassw() {
		return passw;
	}
	public void setPassw(String passw) {
		this.passw = passw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Centro getCentro() {
		return centro;
	}
	public void setCentro(Centro centro) {
		this.centro = centro;
	}
	@Override
	public String toString() {
		return "Proveedor [idProveedor=" + idProveedor + ", user=" + user + ", passw=" + passw + ", email=" + email
				+ ", centro=" + centro + "]";
	}
	
	
}
