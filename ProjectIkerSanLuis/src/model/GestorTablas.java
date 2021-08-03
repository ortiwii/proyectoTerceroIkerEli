package model;

import java.util.Iterator;
import java.util.Vector;

import db.GestorDB;
import db.GestorDatos;

public class GestorTablas {

	public GestorTablas() {	}
	
	// SOLICITUD
	public Vector <String> obtenerCabecerasSolicitudes(){
		Vector<String> columnas=new Vector<String>();
		columnas.add("idSolicitud");
		columnas.add("fechaInicio");
		columnas.add("fechaFin");
		columnas.add("estado");
		columnas.add("descripcion");
		columnas.add("user");
		columnas.add("tecnico");
		columnas.add("componente");
		return columnas;
	}
	public Vector<Vector<String>> obtenerCuerpoSolicitudes(Usuario usuario){

		//Cada elemento del vector es un vector con los datos de cada solicitud
		Vector<Vector<String>> vCuerpo=new Vector<Vector<String>>();
		
		//Contiene datos de cada solicitud (atributos idSolicitud, fechaInicio, fechaFin, estado, descripcion, user, tecnuico, componente)
		Vector<String> fila=new Vector<String>();
		Vector<Solicitud> solicitudes = GestorDatos.getInstance().getSolicitudesUsuario(usuario); 
		
		//Recorrer colección de solicitudes
		
		Iterator<Solicitud> itr = solicitudes.iterator();
		
		while (itr.hasNext()){
			
			Solicitud act = itr.next();
			fila.add(Integer.toString(act.getIdSolInc()));
			
			if (act.fechaInicio != null) {
				fila.add(act.fechaInicio.getTime().toString());
			}else {
				fila.add(" ");
			}
			
			if (act.fechaCierre != null) {
				fila.add(act.fechaCierre.getTime().toString());
			}else {
				fila.add(" ");
			}
			fila.add(act.getEstado());
			fila.add(act.getDescripcion());
			fila.add(act.getUser().getUser());
			fila.add(act.getTecnico().getUser());
			if (act.getComponente() != null) {
				fila.add(Integer.toString(act.getComponente().getIdComponente()));
			}else {
				fila.add("");
			}
			
			vCuerpo.add(fila);
			
			fila=new Vector<String>();
		}
		
		return vCuerpo;
	}
	public Vector<Vector<String>> obtenerCuerpoSolicitudesTecnico(Tecnico tecnico){

		//Cada elemento del vector es un vector con los datos de cada solicitud
		Vector<Vector<String>> vCuerpo=new Vector<Vector<String>>();
		
		//Contiene datos de cada solicitud (atributos idSolicitud, fechaInicio, fechaFin, estado, descripcion, user, tecnuico, componente)
		Vector<String> fila=new Vector<String>();
		Vector<Solicitud> solicitudes = GestorDatos.getInstance().getSolicitudesTecnico(tecnico); 
		
		//Recorrer colección de solicitudes
		
		Iterator<Solicitud> itr = solicitudes.iterator();
		
		while (itr.hasNext()){
			
			Solicitud act = itr.next();
			fila.add(Integer.toString(act.getIdSolInc()));
			
			if (act.fechaInicio != null) {
				fila.add(act.fechaInicio.getTime().toString());
			}else {
				fila.add(" ");
			}
			
			if (act.fechaCierre != null) {
				fila.add(act.fechaCierre.getTime().toString());
			}else {
				fila.add(" ");
			}
			fila.add(act.getEstado());
			fila.add(act.getDescripcion());
			fila.add(act.getUser().getUser());
			fila.add(act.getTecnico().getUser());
			if (act.getComponente() != null) {
				fila.add(Integer.toString(act.getComponente().getIdComponente()));
			}else {
				fila.add("");
			}
			
			vCuerpo.add(fila);
			
			fila=new Vector<String>();
		}
		
		return vCuerpo;
	}
	
	// USUARIO
	public Vector<String> obtenerCabecerasUsuariosPublico(){
		Vector<String> columnas=new Vector<String>();
		columnas.add("user");		
		columnas.add("nombre");
		columnas.add("equipo");
		columnas.add("centro");
		return columnas;
	}
	public Vector<Vector<String>> obtenerCuerpoUsuariosPublico(){

		//Cada elemento del vector es un vector con los datos de cada usuario
		Vector<Vector<String>> vCuerpo=new Vector<Vector<String>>();
		
		//Contiene datos de cada usuario
		Vector<String> fila=new Vector<String>();
		Vector<Usuario> usuarios = GestorDatos.getInstance().getUsuarios(); 
		
		//Recorrer colección de usuarios
		
		Iterator<Usuario> itr = usuarios.iterator();
		
		while (itr.hasNext()){
			
			Usuario act = itr.next();			
			fila.add(act.getUser());
			fila.add(act.getNombre());
			fila.add(Integer.toString(act.getEquipo().getIdEquipo()));
			fila.add(act.getCentro().getNombre());
			vCuerpo.add(fila);
			fila=new Vector<String>();
		}
				
		return vCuerpo;
	}
	public Vector<String> obtenerCabecerasUsuarios(){
		Vector<String> columnas=new Vector<String>();
		columnas.add("Usuario");		
		columnas.add("Contraseña");
		columnas.add("Nombre");
		columnas.add("Apellidos");
		columnas.add("Email");
		columnas.add("Tipo");
		columnas.add("Equipo");
		columnas.add("Centro");
		return columnas;
	}
	
	public Vector<Vector<String>> obtenerCuerpoUsuariosEncriptado(){

		//Cada elemento del vector es un vector con los datos de cada usuario
		Vector<Vector<String>> vCuerpo=new Vector<Vector<String>>();
		
		//Contiene datos de cada usuario
		Vector<String> fila=new Vector<String>();
		Vector<Usuario> usuarios = GestorDatos.getInstance().getUsuarios(); 
		
		//Recorrer colección de usuarios
		
		Iterator<Usuario> itr = usuarios.iterator();
		
		while (itr.hasNext()){
			
			Usuario act = itr.next();			
			fila.add(act.getUser());
			
			String passw = "";
			for(int i = 0; i < act.getPassw().length(); i ++) {
				passw = passw + "*";
			}
			fila.add(passw);
			
			fila.add(act.getNombre());
			fila.add(act.getApellidos());
			fila.add(act.getEmail());
			fila.add(act.getTipo());
			
			if (act.getEquipo() != null) {
				fila.add(Integer.toString(act.getEquipo().getIdEquipo()));
			}else {
				fila.add("");
			}
			fila.add(act.getCentro().getNombre());

			vCuerpo.add(fila);
			fila=new Vector<String>();
		}
				
		return vCuerpo;
	}
	
	// PROVEEDOR
	public Vector<String> obtenerCabecerasProveedoresPublico(){
		Vector<String> columnas=new Vector<String>();
		columnas.add("idProveedor");		
		columnas.add("user");
		columnas.add("email");
		columnas.add("centro");
		return columnas;
	}
	public Vector<Vector<String>> obtenerCuerpoProveedoresPublico(){

		//Cada elemento del vector es un vector con los datos de cada proveedor
		Vector<Vector<String>> vCuerpo=new Vector<Vector<String>>();
		
		//Contiene datos de cada proveedor
		Vector<String> fila = new Vector<String>();
		Vector<Proveedor> proveedor = GestorDatos.getInstance().getProveedores(); 
		
		//Recorrer colección de proveedores
		
		Iterator<Proveedor> itr = proveedor.iterator();
		
		while (itr.hasNext()){
			
			Proveedor act = itr.next();			
			fila.add(act.getIdProveedor());
			fila.add(act.getUser());
			fila.add(act.getEmail());
			fila.add(act.getCentro().getNombre());
			
			vCuerpo.add(fila);
			fila=new Vector<String>();
		}
				
		return vCuerpo;
	}
	
	// ALMACEN
	public Vector <String> obtenerCabecerasAlmacen(){		
		Vector<String> columnas=new Vector<String>();
		columnas.add("idComponente");
		columnas.add("Nombre");
		columnas.add("Lote");
		columnas.add("Clase");
		columnas.add("Cantidad");
		columnas.add("Informacion");
		return columnas;
	}
	public Vector<Vector<String>> obtenerCuerpoAlmacen (Centro centro){
		if (centro != null) {
			//Cada elemento del vector es un vector con los datos de cada componente del almacen
			Vector<Vector<String>> vCuerpo=new Vector<Vector<String>>();
			
			//Contiene datos de cada aula componente del almacen
			Vector<String> fila=new Vector<String>();
			Vector<AlmacenProveedores> almacen = GestorDatos.getInstance().getAlmacen(centro); 
			
			//Recorrer colección de solicitudes componente del almacen
			
			Iterator<AlmacenProveedores> itr = almacen.iterator();
			
			while (itr.hasNext()){
				
				AlmacenProveedores act = itr.next();
				fila.add(Integer.toString(act.getIdComponente()));
				fila.add(act.getNombre());
				fila.add(Integer.toString(act.getLote().getIdStock()));
				fila.add(act.getClase());
				fila.add(Integer.toString(act.getCantidad()));
				fila.add(act.getInformacion());						
				
				vCuerpo.add(fila);
				
				fila=new Vector<String>();
			}
			return vCuerpo;
		}else {
			return new Vector<>();
		}
					

	}
//	public Vector<Vector<String>> obtenerCuerpoAlmacen (Proveedor proveedor){
//		if (proveedor != null) {
//			//Cada elemento del vector es un vector con los datos de cada componente del almacen
//			Vector<Vector<String>> vCuerpo=new Vector<Vector<String>>();
//			
//			//Contiene datos de cada aula componente del almacen
//			Vector<String> fila=new Vector<String>();
//			Vector<AlmacenProveedores> almacen = GestorDatos.getInstance().getAlmacen(proveedor); 
//			
//			//Recorrer colección de solicitudes componente del almacen
//			
//			Iterator<AlmacenProveedores> itr = almacen.iterator();
//			
//			while (itr.hasNext()){
//				
//				AlmacenProveedores act = itr.next();
//				fila.add(Integer.toString(act.getIdComponente()));
//				fila.add(act.getNombre());
//				fila.add(Integer.toString(act.getLote().getIdStock()));
//				fila.add(act.getClase());
//				fila.add(Integer.toString(act.getCantidad()));
//				fila.add(act.getInformacion());						
//				
//				vCuerpo.add(fila);
//				
//				fila=new Vector<String>();
//			}
//			return vCuerpo;
//		}else {
//			return new Vector<>();
//		}
//					
//
//	}
	public Vector<Vector<String>> obtenerCuerpoAlmacen (Proveedor proveedor, String field, String content){
		if (proveedor != null) {
			//Cada elemento del vector es un vector con los datos de cada componente del almacen
			Vector<Vector<String>> vCuerpo=new Vector<Vector<String>>();
			
			//Contiene datos de cada aula componente del almacen
			Vector<String> fila=new Vector<String>();
			Vector<AlmacenProveedores> almacen = GestorDatos.getInstance().getAlmacen(proveedor, field, content); 
			
			//Recorrer colección de solicitudes componente del almacen
			
			Iterator<AlmacenProveedores> itr = almacen.iterator();
			
			while (itr.hasNext()){
				
				AlmacenProveedores act = itr.next();
				fila.add(Integer.toString(act.getIdComponente()));
				fila.add(act.getNombre());
				fila.add(Integer.toString(act.getLote().getIdStock()));
				fila.add(act.getClase());
				fila.add(Integer.toString(act.getCantidad()));
				fila.add(act.getInformacion());						
				
				vCuerpo.add(fila);
				
				fila=new Vector<String>();
			}
			return vCuerpo;
		}else {
			return new Vector<>();
		}
					

	}

	// AULA
	public Vector <String> obtenerCabecerasAulas(){		
		Vector<String> columnas=new Vector<String>();
		columnas.add("codigo");
		columnas.add("tamaño");
		columnas.add("disponibles");
		columnas.add("centro");		
		return columnas;
	}
	public Vector<Vector<String>> obtenerCuerpoAulas(Centro centro){

		//Cada elemento del vector es un vector con los datos de cada aula
		Vector<Vector<String>> vCuerpo=new Vector<Vector<String>>();
		
		//Contiene datos de cada aula
		Vector<String> fila=new Vector<String>();
		Vector<Aula> aulas = GestorDatos.getInstance().getAulas(centro); 
		
		//Recorrer colección de solicitudes
		
		Iterator<Aula> itr = aulas.iterator();
		
		while (itr.hasNext()){
			
			Aula act = itr.next();
			fila.add(act.getCodigo());			
			fila.add(Integer.toString(act.getTamaño()));
			fila.add(Integer.toString(act.getDisponibles()));
			fila.add(centro.getNombre());			
			
			vCuerpo.add(fila);
			
			fila=new Vector<String>();
		}
				
		return vCuerpo;
	}

	// EQUIPO
	public Vector <String> obtenerCabecerasEquipos(){		
		Vector<String> columnas=new Vector<String>();
		columnas.add("idEquipo");
		columnas.add("nombre");
		columnas.add("almacenamiento");
		columnas.add("ram");
		columnas.add("ip");
		columnas.add("aula");		
		return columnas;
	}
	public Vector<Vector<String>> obtenerCuerpoEquipos(Aula aula){

		//Cada elemento del vector es un vector con los datos de cada equipo
		Vector<Vector<String>> vCuerpo=new Vector<Vector<String>>();
		
		//Contiene datos de cada equipo
		Vector<String> fila=new Vector<String>();
		Vector<Equipo> equipos = GestorDatos.getInstance().getEquiposAula(aula); 
		
		//Recorrer colección de solicitudes
		
		Iterator<Equipo> itr = equipos.iterator();
		
		while (itr.hasNext()){
			
			Equipo act = itr.next();
			fila.add(Integer.toString(act.getIdEquipo()));;				
			fila.add(act.getNombre());
			fila.add(Integer.toString(act.getAlmacenamiento()));
			fila.add(Integer.toString(act.getRam()));
			fila.add(act.getIp());
			fila.add(act.getAula().codigo);
			
			vCuerpo.add(fila);
			
			fila=new Vector<String>();
		}
				
		return vCuerpo;
	}
	public Vector<Vector<String>> obtenerCuerpoEquipos(){

		//Cada elemento del vector es un vector con los datos de cada equipo
		Vector<Vector<String>> vCuerpo=new Vector<Vector<String>>();
		
		//Contiene datos de cada equipo
		Vector<String> fila=new Vector<String>();
		Vector<Equipo> equipos = GestorDatos.getInstance().getEquipos(); 
		
		//Recorrer colección de equipos
		
		Iterator<Equipo> itr = equipos.iterator();
		
		while (itr.hasNext()){
			
			Equipo act = itr.next();
			fila.add(Integer.toString(act.getIdEquipo()));;				
			fila.add(act.getNombre());
			fila.add(Integer.toString(act.getAlmacenamiento()));
			fila.add(Integer.toString(act.getRam()));
			fila.add(act.getIp());
			if(act.getAula() != null) {
				fila.add(act.getAula().codigo);
			}else {
				fila.add("");
			}
			
			vCuerpo.add(fila);
			
			fila=new Vector<String>();
		}
				
		return vCuerpo;
	}
		public Vector obtenerCuerpoEquiposCondicionado(String atributo, String valor){		
			
			//Cada elemento del vector es un vector con los datos de cada equipo
		Vector<Vector<String>> vCuerpo=new Vector<Vector<String>>();
		
		//Contiene datos de cada equipo
		Vector<String> fila=new Vector<String>();
		Vector<Equipo> equipos = GestorDatos.getInstance().obtenerEquiposCondicionado(atributo, valor); 
		
		//Recorrer colección de equipos
		
		Iterator<Equipo> itr = equipos.iterator();
		
		while (itr.hasNext()){
			
			Equipo act = itr.next();
			fila.add(Integer.toString(act.getIdEquipo()));;				
			fila.add(act.getNombre());
			fila.add(Integer.toString(act.getAlmacenamiento()));
			fila.add(Integer.toString(act.getRam()));
			fila.add(act.getIp());
			if(act.getAula() != null) {
				fila.add(act.getAula().codigo);
			}else {
				fila.add("");
			}
			
			vCuerpo.add(fila);
			
			fila=new Vector<String>();
		}
				
		return vCuerpo;		
	}		
		
	// COMPONENTE
	public Vector <String> obtenerCabecerasComponentes(){		
		Vector<String> columnas=new Vector<String>();
		columnas.add("idComponente");
		columnas.add("nombre");
		columnas.add("stock");
		columnas.add("clase");
		columnas.add("equipo");
		columnas.add("centro");
		columnas.add("informacion");
		
		return columnas;
	}
	public Vector<Vector<String>> obtenerCuerpoComponentesEquipo(Equipo equipo){

		//Cada elemento del vector es un vector con los datos de cada componente
		Vector<Vector<String>> vCuerpo=new Vector<Vector<String>>();
		
		//Contiene datos de cada componente
		Vector<String> fila=new Vector<String>();
		
		Vector <Componente> componentes;
		componentes = GestorDatos.getInstance().getComponentesEquipo(equipo); 
		
		//Recorrer colección de solicitudes
		
		Iterator<Componente> itr = componentes.iterator();
		
		while (itr.hasNext()){
			
			Componente act = itr.next();
			fila.add(Integer.toString(act.getIdComponente()));
			fila.add(act.getNombre());
			fila.add(Integer.toString(act.getStock().getIdStock()));
			fila.add(act.getClase());
			fila.add(Integer.toString(act.getEquipo().getIdEquipo()));
			fila.add(act.getCentro().getNombre());
			fila.add(act.getInformacion());
				
			vCuerpo.add(fila);
			
			fila=new Vector<String>();
		}
				
		return vCuerpo;
	}
	public Vector<Vector<String>> obtenerCuerpoComponentesSinCentro(){

		//Cada elemento del vector es un vector con los datos de cada componente
		Vector<Vector<String>> vCuerpo=new Vector<Vector<String>>();
		
		//Contiene datos de cada componente
		Vector<String> fila=new Vector<String>();
		
		Vector <Componente> componentes;
		//TODO no se si esto esta bien, comprobar
//		componentes = GestorDatos.getInstance().getComponentesCentroLibres(centro);
		componentes = GestorDatos.getInstance().getComponentesCentro(null);
		
		//Recorrer colección de solicitudes
		
		Iterator<Componente> itr = componentes.iterator();
		
		while (itr.hasNext()){
			
			Componente act = itr.next();
			fila.add(Integer.toString(act.getIdComponente()));
			fila.add(act.getNombre());
			fila.add(Integer.toString(act.getStock().getIdStock()));
			fila.add(act.getClase());
			if (act.getEquipo() != null) {
				fila.add(Integer.toString(act.getEquipo().getIdEquipo()));
			}else {
				fila.add(" ");
			}
			if(act.getCentro() != null) {
				fila.add(act.getCentro().getNombre());
			}else {
				fila.add(" ");
			}
			fila.add(act.getInformacion());
				
			vCuerpo.add(fila);
			
			fila=new Vector<String>();
		}
				
		return vCuerpo;
	}
	public Vector<Vector<String>> obtenerCuerpoComponentesCentro(Centro centro){

		//Cada elemento del vector es un vector con los datos de cada componente
		Vector<Vector<String>> vCuerpo=new Vector<Vector<String>>();
		
		//Contiene datos de cada componente
		Vector<String> fila=new Vector<String>();
		
		Vector <Componente> componentes;
		//TODO no se si esto esta bien, comprobar
//		componentes = GestorDatos.getInstance().getComponentesCentroLibres(centro);
		componentes = GestorDatos.getInstance().getComponentesCentro(centro);
		
		//Recorrer colección de solicitudes
		
		Iterator<Componente> itr = componentes.iterator();
		
		while (itr.hasNext()){
			
			Componente act = itr.next();
			fila.add(Integer.toString(act.getIdComponente()));
			fila.add(act.getNombre());
			fila.add(Integer.toString(act.getStock().getIdStock()));
			fila.add(act.getClase());
			if (act.getEquipo() != null) {
				fila.add(Integer.toString(act.getEquipo().getIdEquipo()));
			}else {
				fila.add(" ");
			}
			fila.add(act.getCentro().getNombre());
			fila.add(act.getInformacion());
				
			vCuerpo.add(fila);
			
			fila=new Vector<String>();
		}
				
		return vCuerpo;
	}
	public Vector<Vector<String>> obtenerCuerpoComponentesCentroLibres(Centro centro){

		//Cada elemento del vector es un vector con los datos de cada componente
		Vector<Vector<String>> vCuerpo=new Vector<Vector<String>>();
		
		//Contiene datos de cada componente
		Vector<String> fila=new Vector<String>();
		
		Vector <Componente> componentes;
		componentes = GestorDatos.getInstance().getComponentesCentroLibres(centro); 
		
		//Recorrer colección de solicitudes
		
		Iterator<Componente> itr = componentes.iterator();
		
		while (itr.hasNext()){
			
			Componente act = itr.next();
			fila.add(Integer.toString(act.getIdComponente()));
			fila.add(act.getNombre());
			fila.add(Integer.toString(act.getStock().getIdStock()));
			fila.add(act.getClase());
			if (act.getEquipo() != null) {
				fila.add(Integer.toString(act.getEquipo().getIdEquipo()));
			}else {
				fila.add("");
			}
			fila.add(act.getCentro().getNombre());
			fila.add(act.getInformacion());
				
			vCuerpo.add(fila);
			
			fila=new Vector<String>();
		}
				
		return vCuerpo;
	}

	// INCIDENCIA
	public Vector <String> obtenerCabecerasIncidencias(){
		Vector<String> columnas=new Vector<String>();
		columnas.add("idIncidencia");
		columnas.add("fechaInicio");
		columnas.add("fechaFin");
		columnas.add("estado");
		columnas.add("descripcion");
		columnas.add("user");
		columnas.add("tecnico");
		return columnas;
	}
	public Vector<Vector<String>> obtenerCuerpoIncidencias(Usuario usuario){
	
		//Cada elemento del vector es un vector con los datos de cada incidencia
		Vector<Vector<String>> vCuerpo=new Vector<Vector<String>>();
		
		//Contiene datos de cada incidencia
		Vector<String> fila=new Vector<String>();
		Vector<Incidencia> incidencias = GestorDatos.getInstance().getIncidenciasUsuario(usuario);				
		
		//Recorrer colección de Incidencias
		
		Iterator<Incidencia> itr = incidencias.iterator();
		
		while (itr.hasNext()){
			
			Incidencia act = itr.next();
			fila.add(Integer.toString(act.getIdSolInc()));
			
			if (act.fechaInicio != null) {
				fila.add(act.fechaInicio.getTime().toString());
			}else {
				fila.add(" ");
			}
			
			if (act.fechaCierre != null) {
				fila.add(act.fechaCierre.getTime().toString());
			}else {
				fila.add(" ");
			}
			fila.add(act.getEstado());
			fila.add(act.getDescripcion());
			fila.add(act.getUser().getUser());
			fila.add(act.getTecnico().getUser());
			
			vCuerpo.add(fila);
			
			fila=new Vector<String>();
		}
				
		return vCuerpo;
	}
	public Vector<Vector<String>> obtenerCuerpoIncidenciasTecnico(Tecnico tecnico){
		
		//Cada elemento del vector es un vector con los datos de cada incidencia
		Vector<Vector<String>> vCuerpo=new Vector<Vector<String>>();
		
		//Contiene datos de cada incidencia
		Vector<String> fila=new Vector<String>();
		Vector<Incidencia> incidencias = GestorDatos.getInstance().getIncidenciasTecnico(tecnico);				
		
		//Recorrer colección de Incidencias
		
		Iterator<Incidencia> itr = incidencias.iterator();
		
		while (itr.hasNext()){
			
			Incidencia act = itr.next();
			fila.add(Integer.toString(act.getIdSolInc()));
			
			if (act.fechaInicio != null) {
				fila.add(act.fechaInicio.getTime().toString());
			}else {
				fila.add(" ");
			}
			
			if (act.fechaCierre != null) {
				fila.add(act.fechaCierre.getTime().toString());
			}else {
				fila.add(" ");
			}
			fila.add(act.getEstado());
			fila.add(act.getDescripcion());
			fila.add(act.getUser().getUser());
			fila.add(act.getTecnico().getUser());
			
			vCuerpo.add(fila);
			
			fila=new Vector<String>();
		}
				
		return vCuerpo;
	}
	
	// PETICION
	public Vector <String> obtenerCabecerasPeticiones(){
		Vector<String> columnas=new Vector<String>();
		columnas.add("idPeticion");
		columnas.add("Componente");
		columnas.add("Tecnico");
		columnas.add("Administrador");
		columnas.add("Concepto");
		columnas.add("Cantidad");
		columnas.add("Estado");
		columnas.add("Centro");
		return columnas;
	}
	public Vector<Vector<String>> obtenerCuerpoPeticionesProveedor (Proveedor proveedor){
		//Cada elemento del vector es un vector con los datos de cada peticion
		Vector<Vector<String>> vCuerpo=new Vector<Vector<String>>();
		
		//Contiene datos de cada peticion
		Vector<String> fila=new Vector<String>();
		Vector<Peticion> peticiones = GestorDatos.getInstance().getPeticionesProveedor(proveedor);				
		
		//Recorrer colección de Incidencias
		
		Iterator<Peticion> itr = peticiones.iterator();
		
		while (itr.hasNext()){
			
			Peticion act = itr.next();
			fila.add(Integer.toString(act.getIdPeticion()));
			fila.add(Integer.toString(act.getComonenteAlmacen().getIdComponente()));
			fila.add(act.getTecnico().getUser());
			fila.add(act.getAdministrador().getUser());
			fila.add(act.getDescripcion());
			fila.add(Integer.toString(act.getCantidad()));
			fila.add(act.getEstado());
			fila.add(act.getCentro().getNombre());
			
			vCuerpo.add(fila);
			
			fila=new Vector<String>();
		}
		
		return vCuerpo;
	}
	
	public Vector<Vector<String>> obtenerCuerpoPeticionesAdminNoAtendidas(Administrador administrador){
		
		//Cada elemento del vector es un vector con los datos de cada peticion
		Vector<Vector<String>> vCuerpo=new Vector<Vector<String>>();
		
		//Contiene datos de cada peticion
		Vector<String> fila=new Vector<String>();
		Vector<Peticion> peticiones = GestorDatos.getInstance().getPeticionesAdminNoAtendidas(administrador);				
		
		//Recorrer colección de Incidencias
		
		Iterator<Peticion> itr = peticiones.iterator();
		
		while (itr.hasNext()){
			
			Peticion act = itr.next();
			fila.add(Integer.toString(act.getIdPeticion()));
			fila.add(Integer.toString(act.getComonenteAlmacen().getIdComponente()));
			fila.add(act.getTecnico().getUser());
			fila.add(act.getAdministrador().getUser());
			fila.add(act.getDescripcion());
			fila.add(Integer.toString(act.getCantidad()));
			fila.add(act.getEstado());
			fila.add(act.getCentro().getNombre());
			
			vCuerpo.add(fila);
			
			fila=new Vector<String>();
		}
				
		return vCuerpo;
	}
	
	// VENTAS
	public Vector <String> obtenerCabecerasVentas(){
		Vector<String> columnas=new Vector<String>();
		columnas.add("idVenta");
		columnas.add("Proveedor");
		columnas.add("fechaVenta");
		columnas.add("fechaCierre");
		columnas.add("Estado");
		columnas.add("Peticion");
		columnas.add("Informacion");
		return columnas;
	}
	public Vector<Vector<String>> obtenerCuerpoVentas (Proveedor proveedor){
		//Cada elemento del vector es un vector con los datos de cada venta
		Vector<Vector<String>> vCuerpo=new Vector<Vector<String>>();
		
		//Contiene datos de cada venta
		Vector<String> fila=new Vector<String>();
		Vector<Venta> ventas = GestorDatos.getInstance().getVentasProveedor(proveedor);				
		
		//Recorrer colección de ventas
		
		Iterator<Venta> itr = ventas.iterator();
		
		while (itr.hasNext()){
			
			Venta act = itr.next();
			fila.add(Integer.toString(act.getIdVenta()));
			fila.add(act.getProveedor().getIdProveedor());
			
			if (act.getFechaVenta() != null) {
				fila.add(act.getFechaVenta().getTime().toString());
			}else {
				fila.add("");
			}
			
			if (act.getFechaCierre() != null) {
				fila.add(act.getFechaCierre().getTime().toString());
			}else {
				fila.add("");
			}
			
			fila.add(act.getEstado());
			fila.add(Integer.toString(act.getPeticion().getIdPeticion()));
			fila.add(act.getInformacion());
			vCuerpo.add(fila);
			
			fila=new Vector<String>();
		}
		
		return vCuerpo;
	}
	
	
}

