package model;

public class Lote {

	protected int idStock;
	protected Proveedor proveedor;
	public Lote(int idStock, Proveedor proveedor) {
		super();
		this.idStock = idStock;
		this.proveedor = proveedor;
	}
	public int getIdStock() {
		return idStock;
	}
	public void setIdStock(int idStock) {
		this.idStock = idStock;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	@Override
	public String toString() {
		return Integer.toString(idStock);
	}
	
}
