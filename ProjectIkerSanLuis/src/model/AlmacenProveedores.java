package model;

public class AlmacenProveedores {


	private int idComponente;
	private Lote lote;
	private int cantidad;
	private String nombre;
	private String clase;
	private String informacion;
	public AlmacenProveedores(int idComponente, Lote lote, int cantidad, String nombre, String clase,
			String informacion) {
		super();
		this.idComponente = idComponente;
		this.lote = lote;
		this.cantidad = cantidad;
		this.nombre = nombre;
		this.clase = clase;
		this.informacion = informacion;
	}
	public int getIdComponente() {
		return idComponente;
	}
	public void setIdComponente(int idComponente) {
		this.idComponente = idComponente;
	}
	public Lote getLote() {
		return lote;
	}
	public void setLote(Lote lote) {
		this.lote = lote;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	public String getInformacion() {
		return informacion;
	}
	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return new AlmacenProveedores(idComponente, lote, cantidad, nombre, clase, informacion);
	}
	
	
}
