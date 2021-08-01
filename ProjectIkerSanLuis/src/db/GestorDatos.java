package db;

import java.sql.ResultSet;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import model.Administrador;
import model.AlmacenProveedores;
import model.Aula;
import model.Centro;
import model.Componente;
import model.Equipo;
import model.Incidencia;
import model.Lote;
import model.Peticion;
import model.Proveedor;
import model.Solicitud;
import model.Tecnico;
import model.Usuario;

public class GestorDatos {
	private static final GestorDatos instance = new GestorDatos();

    public static GestorDatos getInstance() {
        return instance;
    }

    private GestorDatos() {    }
    
    public Usuario getUsuarioCompleto (String user) {
    	
    	GestorDB gestorDB = GestorDB.getGestorDB();
    	String query = "SELECT * FROM sanluis.usuario WHERE user = '" + user + "';";
    	ResultSet rs = gestorDB.execSQL(query);
		Usuario usuario = null;
    	try {

    		int i = 0;
    		while (rs.next()) {
    			String userAct = rs.getString("user");
    			String passwAct = rs.getString("passw");
    			String nombreAct = rs.getString("nombre");
    			String apellidosAct = rs.getString("apellidos");
    			String emailAct = rs.getString("email");
    			String tipoAct = rs.getString("tipo");    			
    			
    			int equipoAct = rs.getInt("equipo");
    			Equipo equipo = this.getEquipo(equipoAct);
    			
    			String centroAct = rs.getString("centro");
    			Centro centro = this.getCentro(centroAct);
    			
//    			int solicitudesAct = rs.getInt("solicitudes");    			
    			usuario = new Usuario(userAct, passwAct, nombreAct, apellidosAct, emailAct, tipoAct, equipo, centro);    			
    			    		
    			i ++;
    		}
    		if (i == 1) {
    			return usuario;
    		}else {
    			return null;
    		}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}    	
    	return usuario;
    }
    public Vector<Usuario> getUsuarios(){
    	Vector<Usuario>lista = new Vector<>();
    	String query = "SELECT * FROM sanluis.usuario";
    	ResultSet rs = GestorDB.getGestorDB().execSQL(query);
    	try {
	    	while (rs.next()) {
				String userAct = rs.getString("user");
				String passwAct = rs.getString("passw");
				String nombreAct = rs.getString("nombre");
				String apellidosAct = rs.getString("apellidos");
				String emailAct = rs.getString("email");
				String tipoAct = rs.getString("tipo");
				
				int equipoAct = rs.getInt("equipo");
				Equipo equipo = this.getEquipo(equipoAct);
				
				String centroAct = rs.getString("centro");
				Centro centro = this.getCentro(centroAct);
				    			
				Usuario usuario = new Usuario(userAct, passwAct, nombreAct, apellidosAct, emailAct, tipoAct, equipo, centro);
				lista.add(usuario);
	    	}	
    	}catch (Exception ex) {
    		ex.printStackTrace();
		}
    	
    	return lista;
    }
    public Usuario getUsuarioEquipo (Equipo equipo) {
    	GestorDB gestorDB = GestorDB.getGestorDB();
    	String query = "SELECT * FROM sanluis.usuario WHERE equipo = '"+equipo.getIdEquipo()+"';";
    	ResultSet rs = gestorDB.execSQL(query);
		Usuario usuario = null;
    	try {

    		int i = 0;
    		while (rs.next()) {
    			String userAct = rs.getString("user");
    			String passwAct = rs.getString("passw");
    			String nombreAct = rs.getString("nombre");
    			String apellidosAct = rs.getString("apellidos");
    			String emailAct = rs.getString("email");
    			String tipoAct = rs.getString("tipo");			
    			
    			String centroAct = rs.getString("centro");
    			Centro centro = this.getCentro(centroAct);
    			
//    			int solicitudesAct = rs.getInt("solicitudes");    			
    			usuario = new Usuario(userAct, passwAct, nombreAct, apellidosAct, emailAct, tipoAct, equipo, centro);    			
    			
    			
    			i ++;
    		}
    		if (i == 1) {
    			return usuario;
    		}else {
    			return null;
    		}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}    	
    	return usuario;
    }
    public Tecnico getTecnicoCompleto (String user) {
    	
    	GestorDB gestorDB = GestorDB.getGestorDB();
    	String query = "SELECT * FROM sanluis.usuario WHERE user = '" + user + "';";
    	ResultSet rs = gestorDB.execSQL(query);
		Tecnico tecnico = null;
    	try {

    		int i = 0;
    		while (rs.next()) {
    			String userAct = rs.getString("user");
    			String passwAct = rs.getString("passw");
    			String nombreAct = rs.getString("nombre");
    			String apellidosAct = rs.getString("apellidos");
    			String emailAct = rs.getString("email");
    			String tipoAct = rs.getString("tipo");
    			
    			int equipoAct = rs.getInt("equipo");
    			Equipo equipo = this.getEquipo(equipoAct);
    			
    			String centroAct = rs.getString("centro");
    			Centro centro = this.getCentro(centroAct);
    			
    			int solicitudesAct = rs.getInt("solicitudes");    			
    			tecnico = new Tecnico(userAct, passwAct, nombreAct, apellidosAct, emailAct, tipoAct, equipo, centro, solicitudesAct);    			
    			    			    		
    			i ++;
    		}
    		if (i == 1) {
    			return tecnico;
    		}else {
    			return null;
    		}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}    	
    	return tecnico;
    }
    
public Administrador getAdministradorCompleto (String user) {
    	
    	GestorDB gestorDB = GestorDB.getGestorDB();
    	String query = "SELECT * FROM sanluis.usuario WHERE user = '" + user + "';";
    	ResultSet rs = gestorDB.execSQL(query);
		Administrador administrador = null;
    	try {

    		int i = 0;
    		while (rs.next()) {
    			String userAct = rs.getString("user");
    			String passwAct = rs.getString("passw");
    			String nombreAct = rs.getString("nombre");
    			String apellidosAct = rs.getString("apellidos");
    			String emailAct = rs.getString("email");
    			String tipoAct = rs.getString("tipo");
    			
    			int equipoAct = rs.getInt("equipo");
    			Equipo equipo = this.getEquipo(equipoAct);
    			
    			String centroAct = rs.getString("centro");
    			Centro centro = this.getCentro(centroAct);
    			
    			int solicitudesAct = rs.getInt("solicitudes");    			
    			Boolean permisosAct = rs.getBoolean("permisos");
    			System.out.println(permisosAct);
    			
    			administrador = new Administrador(userAct, passwAct, nombreAct, apellidosAct, emailAct, tipoAct, permisosAct, equipo, centro, solicitudesAct);    			
    			    			    		
    			i ++;
    		}
    		if (i == 1) {
    			return administrador;
    		}else {
    			return null;
    		}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}    	
    	return administrador;
    }
public Administrador getAdministradorMenosOcupadoConPermisos (Centro centro) {
	
	GestorDB gestorDB = GestorDB.getGestorDB();
	String query = "SELECT * FROM sanluis.usuario WHERE tipo = 'a' AND centro = '"+centro.getNombre()+"' and permisos = '1' order by solicitudes;";
	ResultSet rs = gestorDB.execSQL(query);
	Administrador administrador = null;
	try {

		int i = 0;
		while (rs.next() && i < 1) {
			String userAct = rs.getString("user");
			String passwAct = rs.getString("passw");
			String nombreAct = rs.getString("nombre");
			String apellidosAct = rs.getString("apellidos");
			String emailAct = rs.getString("email");
			String tipoAct = rs.getString("tipo");
			
			int equipoAct = rs.getInt("equipo");
			Equipo equipo = this.getEquipo(equipoAct);			
			
			int solicitudesAct = rs.getInt("solicitudes");    			
			Boolean permisosAct = rs.getBoolean("permisos");
			System.out.println(permisosAct);
			
			administrador = new Administrador(userAct, passwAct, nombreAct, apellidosAct, emailAct, tipoAct, permisosAct, equipo, centro, solicitudesAct);    			
			    			    		
			i ++;
		}
		if (i == 1) {
			return administrador;
		}else {
			return null;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}    	
	return administrador;
}
	public boolean insertarUsuario (Usuario usuario) {
		String query = "";
		if (usuario.getEquipo() != null) {
			query = "INSERT INTO `sanluis`.`usuario` (`user`, `passw`, `nombre`, `apellidos`, `email`, `tipo`, `equipo`, `centro`) VALUES ('"+usuario.getUser()+"', '"+usuario.getPassw()+"', '"+usuario.getNombre()+"', '"+usuario.getApellidos()+"', '"+usuario.getEmail()+"', '"+usuario.getTipo()+"', '"+usuario.getEquipo().getIdEquipo()+"', '"+usuario.getCentro()+"');";
		}else {
			query = "INSERT INTO `sanluis`.`usuario` (`user`, `passw`, `nombre`, `apellidos`, `email`, `tipo`, `equipo`, `centro`) VALUES ('"+usuario.getUser()+"', '"+usuario.getPassw()+"', '"+usuario.getNombre()+"', '"+usuario.getApellidos()+"', '"+usuario.getEmail()+"', '"+usuario.getTipo()+"', NULL, '"+usuario.getCentro()+"');";
		}
		try {
			GestorDB.getGestorDB().exeqSQLExc(query);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	public boolean actualizarUsuario (Usuario pUsuario, String userPrevio) {
		String query = "";
		if (pUsuario.getEquipo() != null) {
			query = "UPDATE `sanluis`.`usuario` SET `nombre` = '"+pUsuario.getNombre()+"', `apellidos` = '"+pUsuario.getApellidos()+"', `email` = '"+pUsuario.getEmail()+"', `passw` = '"+pUsuario.getPassw()+"', `equipo` = '"+pUsuario.getEquipo().getIdEquipo()+"', `centro` = '"+pUsuario.getCentro().getNombre()+"', `tipo` = '"+pUsuario.getTipo()+"' WHERE (`user` = '"+userPrevio+"');";
		}else {
			
		}
		try {
			GestorDB.getGestorDB().execSQL(query);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}
	public boolean eliminarUsuario(Usuario usuario) {
		try {
			String query = "DELETE FROM `sanluis`.`usuario` WHERE (`user` = '"+usuario.getUser()+"');";
			GestorDB.getGestorDB().exeqSQLExc(query);
			return true;
		}catch (Exception ex) {
			return false;
		}
	}
	public void actualizarContraseñaUsuario (Usuario pUsuario) {
		String query = "UPDATE `sanluis`.`usuario` SET `passw` = '"+pUsuario.getPassw()+"' WHERE (`user` = '"+pUsuario.getUser()+"');";
		System.out.println(query);
		GestorDB.getGestorDB().execSQL(query);
	}
	
	public void mostrarResultSet(ResultSet rs) throws java.sql.SQLException {

		ResultSetMetaData rsmeta = rs.getMetaData();
		int numCol = rsmeta.getColumnCount();
		String nombreCol = "";
		for (int i = 0; i < numCol; i++) {
			nombreCol += rsmeta.getColumnName(i + 1);
			if (i + 1 < numCol) {
				nombreCol += " | ";
			}
		}
		System.out.println(nombreCol);	
		while (rs.next()) {
			String act = "";
			for (int i = 0; i < numCol; i++) {
				act += rs.getString(i+1);
				if (i + 1 < numCol) {
					act += " | ";
				}
			}			
			System.out.println(act);
		}
	}
	public Incidencia generarIncidencia (String incidencia, Calendar fechaInicio, Usuario usuario) {
		Tecnico tecnico = this.listadoDeTecnicos().get(0);
		Incidencia incidenciaAct = null;
		System.out.println(tecnico.getUser());
		
		String query = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'sanluis' AND   TABLE_NAME   = 'solicitudincidencia';";
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		int idSolInc;
		try {
			while (rs.next()) {
				idSolInc = rs.getInt("AUTO_INCREMENT");
				
				query = "INSERT INTO `sanluis`.`solicitudincidencia` (`tipo`, `fechaInicio`, `estado`, `user`, `tecnico`, `descripcion`) VALUES ('i', '"+fechaInicio.getTime().toString()+"', 'n', '"+usuario.getUser()+"', '"+tecnico.getUser()+"', '"+incidencia+"');";
				GestorDB.getGestorDB().execSQL(query);
				
				// NO SE SI SIRVE PARA ALGO
				incidenciaAct = new Incidencia(idSolInc, fechaInicio, null, "n", usuario, tecnico, incidencia);
				tecnico.setPendientes(tecnico.getPendientes()+1);
				this.actualizarTecnico(tecnico);	
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return incidenciaAct;
		
	}
	public Solicitud generarSolicitud (String descripcion, Calendar fechaInicio, Usuario usuario, Componente componente) {
		
		Tecnico tecnico = this.listadoDeTecnicos().get(0);
		Solicitud solicitudAct = null;
		
		String query = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'sanluis' AND   TABLE_NAME   = 'solicitudincidencia';";
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		int idSolInc;
		try {
			while (rs.next()) {
				idSolInc = rs.getInt("AUTO_INCREMENT");
				
				query = "INSERT INTO `sanluis`.`solicitudincidencia` (`tipo`, `fechaInicio`, `estado`, `user`, `tecnico`, `componente`, `descripcion`) VALUES ('s', '"+fechaInicio.getTime().toString()+"', 'n', '"+usuario.getNombre()+"', '"+tecnico.getNombre()+"', '"+componente.getIdComponente()+"', '"+descripcion+"');";
				GestorDB.getGestorDB().execSQL(query);
				
				// NO SE SI SIRVE PARA ALGO
				solicitudAct = new Solicitud(idSolInc, fechaInicio, null, "n", usuario, tecnico, descripcion, componente);
				
				tecnico.setPendientes(tecnico.getPendientes()+1);
				this.actualizarTecnico(tecnico);	
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return solicitudAct;
		
	}
	private Vector<Tecnico> listadoDeTecnicos (){
		Vector<Tecnico>lista = new Vector<>();
		String query = "SELECT * FROM sanluis.usuario WHERE tipo = 't' order by solicitudes;";
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		try {
			while(rs.next()) {
				String userAct = rs.getString("user");
    			String passwAct = rs.getString("passw");
    			String nombreAct = rs.getString("nombre");
    			String apellidosAct = rs.getString("apellidos");
    			String emailAct = rs.getString("email");
    			String tipoAct = rs.getString("tipo");
    			
    			int equipoAct = rs.getInt("equipo");
    			Equipo equipo = this.getEquipo(equipoAct);
    			
    			String centroAct = rs.getString("centro");
    			Centro centro = this.getCentro(centroAct);
    			
    			int solicitudesAct = rs.getInt("solicitudes");    			
    			Tecnico tecnico = new Tecnico (userAct, passwAct, nombreAct, apellidosAct, emailAct, tipoAct, equipo, centro, solicitudesAct);
    			lista.add(tecnico);								
			}			
			
		}catch (Exception e) {
			e.printStackTrace();
		}					
		
		return lista;
	}
	public Tecnico getTecnico (String user){
		Tecnico tecnico = null;
		String query = "SELECT * FROM sanluis.usuario WHERE (tipo = 't' or tipo = 'a') and user = '"+user+"';";
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		try {			
			while (rs.next()) {
				String userAct = rs.getString("user");
				String passwAct = rs.getString("passw");
				String nombreAct = rs.getString("nombre");
				String apellidosAct = rs.getString("apellidos");
				String emailAct = rs.getString("email");
				String tipoAct = rs.getString("tipo");
    			int equipoAct = rs.getInt("equipo");
    			Equipo equipo = this.getEquipo(equipoAct);
    			
    			String centroAct = rs.getString("centro");
    			Centro centro = this.getCentro(centroAct);
    			
    			int solicitudesAct = rs.getInt("solicitudes");    			
    			tecnico = new Tecnico (userAct, passwAct, nombreAct, apellidosAct, emailAct, tipoAct, equipo, centro, solicitudesAct);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}					
		
		return tecnico;
	}
	public void actualizarTecnico(Tecnico tecnico) {
		String query = "UPDATE `sanluis`.`usuario` SET `nombre` = '"+tecnico.getNombre()+"', `apellidos` = '"+tecnico.getApellidos()+"', `email` = '"+tecnico.getEmail()+"', `passw` = '"+tecnico.getPassw()+"', `solicitudes`= '"+tecnico.getPendientes()+"' WHERE (`user` = '"+tecnico.getUser()+"');";
		System.out.println(query);
		GestorDB.getGestorDB().execSQL(query);
		System.out.println("Tecnico actualizado");
	}
	public Vector<Solicitud> getSolicitudesUsuario(Usuario usuario){
		String query = "";
		Vector<Solicitud>lista = new Vector<>();
		if (usuario == null) {
			query = "SELECT * FROM sanluis.solicitudincidencia WHERE tipo = 's';";
		}else {
			query = "SELECT * FROM sanluis.solicitudincidencia WHERE tipo = 's' and user = '"+usuario.getUser()+"';";
		}
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		try {
			while(rs.next()) {
				int idSolic = rs.getInt("idSolInc");
				String tipo = rs.getString("tipo");
				
				SimpleDateFormat f = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				
				String inicio = rs.getString("fechaInicio");
				Calendar fechaInicio = null;
				if (inicio != null) {
					fechaInicio = Calendar.getInstance();
					fechaInicio.setTime(f.parse(inicio));						
				}
				
				String fin = rs.getString("fechaFin");
				Calendar fechaFin = null;
				if (fin != null) {
					fechaFin = Calendar.getInstance();
					fechaFin.setTime(f.parse(fin));						
				}
				
				String estado = rs.getString("estado");
				String descripcion = rs.getString("descripcion");
				
				String userName = rs.getString("user");
				Usuario user =this.getUsuarioCompleto(userName);
				
				String tecnicoName = rs.getString("tecnico");
				Tecnico tecnico = GestorDatos.getInstance().getTecnico(tecnicoName);
				
				int componenteId = rs.getInt("componente");
				Componente componente = this.getComponente(componenteId);
				
				Solicitud solicitud = new Solicitud(idSolic, fechaInicio, fechaFin , estado, user, tecnico, descripcion, componente);
				lista.add(solicitud);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	public Vector<Solicitud> getSolicitudesTecnico(Tecnico tecnico){
		String query = "";
		Vector<Solicitud>lista = new Vector<>();		
		query = "SELECT * FROM sanluis.solicitudincidencia WHERE tipo = 's' and tecnico = '"+tecnico.getUser()+"';";
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		try {
			while(rs.next()) {
				int idSolic = rs.getInt("idSolInc");
				String tipo = rs.getString("tipo");
				
				SimpleDateFormat f = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				
				String inicio = rs.getString("fechaInicio");
				Calendar fechaInicio = null;
				if (inicio != null) {
					fechaInicio = Calendar.getInstance();
					fechaInicio.setTime(f.parse(inicio));						
				}
				
				String fin = rs.getString("fechaFin");
				Calendar fechaFin = null;
				if (fin != null) {
					fechaFin = Calendar.getInstance();
					fechaFin.setTime(f.parse(fin));						
				}
				
				String estado = rs.getString("estado");
				String descripcion = rs.getString("descripcion");
				
				String userName = rs.getString("user");
				Usuario user =this.getUsuarioCompleto(userName);
				
				String tecnicoName = rs.getString("tecnico");
				
				int componenteId = rs.getInt("componente");
				Componente componente = this.getComponente(componenteId);
				
				Solicitud solicitud = new Solicitud(idSolic, fechaInicio, fechaFin , estado, user, tecnico, descripcion, componente);
				lista.add(solicitud);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	public Solicitud getSolicitud(String idSol){
		String query = "";
		Solicitud solicitud = null;
		query = "SELECT * FROM sanluis.solicitudincidencia WHERE idSolInc = "+idSol+";";
		
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		try {
			while(rs.next()) {
				int idSolic = rs.getInt("idSolInc");
				String tipo = rs.getString("tipo");
				
				SimpleDateFormat f = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				
				String inicio = rs.getString("fechaInicio");
				Calendar fechaInicio = null;
				if (inicio != null) {
					fechaInicio = Calendar.getInstance();
					fechaInicio.setTime(f.parse(inicio));						
				}
				
				String fin = rs.getString("fechaFin");
				Calendar fechaFin = null;
				if (fin != null) {
					fechaFin = Calendar.getInstance();
					fechaFin.setTime(f.parse(fin));						
				}
				
				String estado = rs.getString("estado");
				String descripcion = rs.getString("descripcion");
				
				String userName = rs.getString("user");
				Usuario user =this.getUsuarioCompleto(userName);
				
				String tecnicoName = rs.getString("tecnico");
				Tecnico tecnico = GestorDatos.getInstance().getTecnico(tecnicoName);
				
				int componenteId = rs.getInt("componente");
				Componente componente = this.getComponente(componenteId);
				
				solicitud = new Solicitud(idSolic, fechaInicio, fechaFin , estado, user, tecnico, descripcion, componente);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return solicitud;
	}
	public boolean actualizarSolicitud(Solicitud solicitud, int idAnterior) {
		String query = "";
		if (solicitud.getEstado().equals("a")) {
			query = "UPDATE `sanluis`.`solicitudincidencia` SET `idSolInc` = '"+solicitud.getIdSolInc()+"', `tipo` = 's', `fechaInicio` = '"+solicitud.getFechaInicio().getTime().toString()+"', `fechaFin` = '"+solicitud.getFechaCierre().getTime().toString()+"', `estado` = '"+solicitud.getEstado()+"', `user` = '"+solicitud.getUser().getUser()+"', `tecnico` = '"+solicitud.getTecnico().getUser()+"', `componente` = '"+solicitud.getComponente().getIdComponente()+"', `descripcion` = '"+solicitud.getDescripcion()+"' WHERE (`idSolInc` = '"+idAnterior+"');";
		}else {
			query = "UPDATE `sanluis`.`solicitudincidencia` SET `idSolInc` = '"+solicitud.getIdSolInc()+"', `tipo` = 's', `fechaInicio` = '"+solicitud.getFechaInicio().getTime().toString()+"', `fechaFin` = NULL, `estado` = '"+solicitud.getEstado()+"', `user` = '"+solicitud.getUser().getUser()+"', `tecnico` = '"+solicitud.getTecnico().getUser()+"', `componente` = '"+solicitud.getComponente().getIdComponente()+"', `descripcion` = '"+solicitud.getDescripcion()+"' WHERE (`idSolInc` = '"+idAnterior+"');";
		}
		try {
			GestorDB.getGestorDB().exeqSQLExc(query);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	public boolean actualizarIncidencia(Incidencia incidencia, int idAnterior) {
		String query = "";
		if (incidencia.getEstado().equals("a")) {
			query = "UPDATE `sanluis`.`solicitudincidencia` SET `idSolInc` = '"+incidencia.getIdSolInc()+"', `tipo` = 'i', `fechaInicio` = '"+incidencia.getFechaInicio().getTime().toString()+"', `fechaFin` = '"+incidencia.getFechaCierre().getTime().toString()+"', `estado` = '"+incidencia.getEstado()+"', `user` = '"+incidencia.getUser().getUser()+"', `tecnico` = '"+incidencia.getTecnico().getUser()+"', `descripcion` = '"+incidencia.getDescripcion()+"' WHERE (`idSolInc` = '"+idAnterior+"');";
		}else {
			query = "UPDATE `sanluis`.`solicitudincidencia` SET `idSolInc` = '"+incidencia.getIdSolInc()+"', `tipo` = 'i', `fechaInicio` = '"+incidencia.getFechaInicio().getTime().toString()+"', `fechaFin` = NULL, `estado` = '"+incidencia.getEstado()+"', `user` = '"+incidencia.getUser().getUser()+"', `tecnico` = '"+incidencia.getTecnico().getUser()+"', `descripcion` = '"+incidencia.getDescripcion()+"' WHERE (`idSolInc` = '"+idAnterior+"');";
		}
		try {
			GestorDB.getGestorDB().exeqSQLExc(query);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	public Vector<Incidencia> getIncidenciasUsuario(Usuario usuario){
		String query = "";
		Vector<Incidencia>lista = new Vector<>();
		if (usuario == null) {
			query = "SELECT * FROM sanluis.solicitudincidencia WHERE tipo = 'i';";
		}else {
			query = "SELECT * FROM sanluis.solicitudincidencia WHERE tipo = 'i' and user = '"+usuario.getUser()+"';";
		}
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		try {
			while(rs.next()) {
				int idSolic = rs.getInt("idSolInc");
				String tipo = rs.getString("tipo");
				
				SimpleDateFormat f = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				
				String inicio = rs.getString("fechaInicio");
				Calendar fechaInicio = null;
				if (inicio != null) {
					fechaInicio = Calendar.getInstance();
					fechaInicio.setTime(f.parse(inicio));						
				}
				
				String fin = rs.getString("fechaFin");
				Calendar fechaFin = null;
				if (fin != null) {
					fechaFin = Calendar.getInstance();
					fechaFin.setTime(f.parse(fin));						
				}
				
				String estado = rs.getString("estado");
				String descripcion = rs.getString("descripcion");
				
				String userName = rs.getString("user");
				Usuario user =this.getUsuarioCompleto(userName);
				
				String tecnicoName = rs.getString("tecnico");
				Tecnico tecnico = GestorDatos.getInstance().getTecnico(tecnicoName);
				
//				int componenteId = rs.getInt("componente");
//				Componente componente = this.getComponente(componenteId);
				
				Incidencia incidencia = new Incidencia(idSolic, fechaInicio, fechaFin, estado, user, tecnico, descripcion);
				lista.add(incidencia);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	public Vector<Incidencia> getIncidenciasTecnico(Tecnico tecnico){
		String query = "";
		Vector<Incidencia>lista = new Vector<>();
		if (tecnico == null) {
			query = "SELECT * FROM sanluis.solicitudincidencia WHERE tipo = 'i';";
		}else {
			query = "SELECT * FROM sanluis.solicitudincidencia WHERE tipo = 'i' and tecnico = '"+tecnico.getUser()+"';";
		}
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		try {
			while(rs.next()) {
				int idSolic = rs.getInt("idSolInc");
				String tipo = rs.getString("tipo");
				
				SimpleDateFormat f = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				
				String inicio = rs.getString("fechaInicio");
				Calendar fechaInicio = null;
				if (inicio != null) {
					fechaInicio = Calendar.getInstance();
					fechaInicio.setTime(f.parse(inicio));						
				}
				
				String fin = rs.getString("fechaFin");
				Calendar fechaFin = null;
				if (fin != null) {
					fechaFin = Calendar.getInstance();
					fechaFin.setTime(f.parse(fin));						
				}
				
				String estado = rs.getString("estado");
				String descripcion = rs.getString("descripcion");
				
				String userName = rs.getString("user");
				Usuario user =this.getUsuarioCompleto(userName);
				
				String tecnicoName = rs.getString("tecnico");

				Incidencia incidencia = new Incidencia(idSolic, fechaInicio, fechaFin, estado, user, tecnico, descripcion);
				lista.add(incidencia);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	public Incidencia getIncidencia(String idIncidencia){
		
		Incidencia incidencia = null;
		String query = "SELECT * FROM sanluis.solicitudincidencia WHERE idSolInc = '"+idIncidencia+"';";
		
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		try {
			while(rs.next()) {
				int idSolic = rs.getInt("idSolInc");
				String tipo = rs.getString("tipo");
				
				SimpleDateFormat f = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				
				String inicio = rs.getString("fechaInicio");
				Calendar fechaInicio = null;
				if (inicio != null) {
					fechaInicio = Calendar.getInstance();
					fechaInicio.setTime(f.parse(inicio));						
				}
				
				String fin = rs.getString("fechaFin");
				Calendar fechaFin = null;
				if (fin != null) {
					fechaFin = Calendar.getInstance();
					fechaFin.setTime(f.parse(fin));						
				}
				
				String estado = rs.getString("estado");
				String descripcion = rs.getString("descripcion");
				
				String userName = rs.getString("user");
				Usuario user =this.getUsuarioCompleto(userName);
				
				String tecnicoName = rs.getString("tecnico");
				Tecnico tecnico = GestorDatos.getInstance().getTecnico(tecnicoName);
				
				incidencia = new Incidencia(idSolic, fechaInicio, fechaFin, estado, user, tecnico, descripcion);				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return incidencia;
	}
	public Componente getComponente(int id) {
		Componente componente = null;
		String query = "SELECT * FROM sanluis.componente WHERE idComponente = "+id+";";
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);		
		try {
			while (rs.next()) {
				int idComponente = rs.getInt("idComponente");
				String nombre = rs.getString("nombre");
	
				int stockId = rs.getInt("stock");
				Lote stock = this.getStock(stockId);
				
				String clase = rs.getString("clase");
				
				int equipoId = rs.getInt("equipo");
				Equipo equipo = this.getEquipo(equipoId);
				
				String centroName = rs.getString("centro");
				Centro centro = this.getCentro(centroName);
				
				String informacion = rs.getString("informacion");
				
				componente = new Componente(idComponente, nombre, stock, clase, equipo, centro, informacion);
			}	
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return componente;
	}
	public Equipo getEquipo (int equipoId) {
		Equipo equipo = null;
		String query = "SELECT * FROM sanluis.equipo WHERE idEquipo = "+equipoId+";";
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		try {
			while (rs.next()) {
				int idEquipo = rs.getInt("idEquipo");
				String nombre = rs.getString("nombre");
				int almacenamiento = rs.getInt("almacenamiento");
				int ram = rs.getInt("ram");
				String ip = rs.getString("ip");
				
				String aulaCod = rs.getString("aula");
				Aula aula = this.getAula(aulaCod);
				
				equipo = new Equipo(idEquipo, nombre, almacenamiento, ram, ip, aula);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return equipo;
	}
	public boolean actualizarEquipo (Equipo equipo, int idEquipoAnterior) {
		String query = "UPDATE `sanluis`.`equipo` SET `idEquipo` = '"+equipo.getIdEquipo()+"', `nombre` = '"+equipo.getNombre()+"', `almacenamiento` = '"+equipo.getAlmacenamiento()+"', `ram` = '"+equipo.getRam()+"', `ip` = '"+equipo.getIdEquipo()+"', `aula` = '"+equipo.getAula().getCodigo()+"' WHERE (`idEquipo` = '"+idEquipoAnterior+"');";
		try {
			GestorDB.getGestorDB().exeqSQLExc(query);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	public boolean crearEquipo (Equipo equipo) {
		String query = "INSERT INTO `sanluis`.`equipo` (`idEquipo`, `nombre`, `almacenamiento`, `ram`, `ip`, `aula`) VALUES ('"+equipo.getIdEquipo()+"', '"+equipo.getNombre()+"', '"+equipo.getAlmacenamiento()+"', '"+equipo.getRam()+"', '"+equipo.getIp()+"', '"+equipo.getAula().getCodigo()+"');";
		try {
			GestorDB.getGestorDB().exeqSQLExc(query);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	public boolean eliminarEquipo (Equipo equipo) {
		String query = "DELETE FROM `sanluis`.`equipo` WHERE (`idEquipo` = '"+equipo.getIdEquipo()+"');";
		try {
			GestorDB.getGestorDB().exeqSQLExc(query);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	public Aula getAula (String aulaCod) {
		Aula aula = null;
		String query = "SELECT * FROM sanluis.aula where codigo = '"+aulaCod+"';";
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		try {
			while (rs.next()) {
				String codigo = rs.getString("codigo");
				int tamaño = rs.getInt("tamaño");
				int disponibles = rs.getInt("disponibles");
				
				String centroName = rs.getString("centro");
				Centro centro = this.getCentro(centroName);
				
				aula = new Aula(codigo, tamaño, disponibles, centro);
			}
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return aula;
	}
	public boolean actualizarAula(Aula aula, String codigoAnterior) {
		String query = "UPDATE `sanluis`.`aula` SET `codigo` = '"+aula.getCodigo()+"', `tamaño` = '"+aula.getTamaño()+"', `disponibles` = '"+aula.getDisponibles()+"', `centro` = '"+aula.getCentro().getNombre()+"' WHERE `codigo` = '"+codigoAnterior+"';";
		System.out.println(query);
		try {
			GestorDB.getGestorDB().exeqSQLExc(query);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	public Centro getCentro (String centroName) {
		Centro centro = null;
		String query  = "SELECT * FROM sanluis.centro WHERE nombre = '"+centroName+"';";
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		try {
			while (rs.next()) {
				String name = rs.getString("nombre");
				String info = rs.getString("informacion");
				centro = new Centro(name, info);
			}			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return centro;
	}
	public Vector<Centro> getCentros () {
		Vector<Centro>centros = new Vector<>();
		String query  = "SELECT * FROM sanluis.centro ;";
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		try {
			while (rs.next()) {
				String name = rs.getString("nombre");
				String info = rs.getString("informacion");
				Centro centro = new Centro(name, info);
				centros.add(centro);
			}			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return centros;
	}
	public Lote getStock (int stockId) {
		Lote lote = null;
		String query = "SELECT * FROM sanluis.stock WHERE idStock = "+stockId+";";
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		try {
			while (rs.next()) {
				int idStock = rs.getInt("idStock");
				
				int proveedorId = rs.getInt("proveedor");
				Proveedor proveedor = this.getProveedor(proveedorId);
				
				lote = new Lote(idStock, proveedor);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return lote;
	}
	
	public Proveedor getProveedor(int proveedorId) {
		Proveedor proveedor = null;
		String query = "SELECT * FROM sanluis.proveedor WHERE idProveedor = '"+proveedorId+"';";
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		try {
			while (rs.next()) {
				String idProveedor = rs.getString("idProveedor");
				String user = rs.getString("user");
				String passw = rs.getString("passw");
				String email = rs.getString("email");
				String centroName = rs.getString("centro");
				Centro centro = this.getCentro(centroName);
				
				proveedor = new Proveedor(idProveedor, user, passw, email, centro);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return proveedor;
	}
	public Proveedor getProveedorUser(String proveedoruser) {
		Proveedor proveedor = null;
		String query = "SELECT * FROM sanluis.proveedor WHERE user = '"+proveedoruser+"';";
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		try {
			while (rs.next()) {
				String idProveedor = rs.getString("idProveedor");
				String user = rs.getString("user");
				String passw = rs.getString("passw");
				String email = rs.getString("email");
				String centroName = rs.getString("centro");
				Centro centro = this.getCentro(centroName);
				
				proveedor = new Proveedor(idProveedor, user, passw, email, centro);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return proveedor;
	}
	public Vector<Proveedor> getProveedores() {
		Vector<Proveedor> lista = new Vector<>();
		String query = "SELECT * FROM sanluis.proveedor;";
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		try {
			while (rs.next()) {
				String idProveedor = rs.getString("idProveedor");
				String user = rs.getString("user");
				String passw = rs.getString("passw");
				String email = rs.getString("email");
				String centroName = rs.getString("centro");
				Centro centro = this.getCentro(centroName);
				
				Proveedor proveedor = new Proveedor(idProveedor, user, passw, email, centro);
				lista.add(proveedor);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	public boolean actualizarProveedor (Proveedor proveedor, String codigo) {
		String query = "UPDATE `sanluis`.`proveedor` SET `idProveedor` = '"+proveedor.getIdProveedor()+"', `user` = '"+proveedor.getUser()+"', `passw` = '"+proveedor.getPassw()+"', `email` = '"+proveedor.getEmail()+"', `centro` = '"+proveedor.getCentro().getNombre()+"' WHERE (`idProveedor` = '"+codigo+"');";
		try {
			GestorDB.getGestorDB().exeqSQLExc(query);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	public boolean eliminarProveedor (Proveedor proveedor) {
		String query = "DELETE FROM `sanluis`.`proveedor` WHERE (`idProveedor` = '"+proveedor.getIdProveedor()+"');";
		try {
			GestorDB.getGestorDB().exeqSQLExc(query);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	public boolean insertarProveedor (Proveedor proveedor) {
		String query = "INSERT INTO `sanluis`.`proveedor` (`idProveedor`, `user`, `passw`, `email`, `centro`) VALUES ('"+proveedor.getIdProveedor()+"', '"+proveedor.getUser()+"', '"+proveedor.getPassw()+"', '"+proveedor.getEmail()+"', '"+proveedor.getCentro().getNombre()+"');";
		try {
			GestorDB.getGestorDB().exeqSQLExc(query);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	public Vector<Aula> getAulas (Centro centro){
		if (centro != null) {
			Vector<Aula> lista = new Vector<>();
			String query = "SELECT * FROM sanluis.aula WHERE centro = '"+centro.getNombre()+"';";
			ResultSet rs = GestorDB.getGestorDB().execSQL(query);
			try {
				while (rs.next()) {
					String codigo = rs.getString("codigo");
					int tamaño = rs.getInt("tamaño");
					int disponibles = rs.getInt("disponibles");
					
					Aula aula = new Aula(codigo, tamaño, disponibles, centro);
					lista.add(aula);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
				
			return lista;
		}else {
			return null;
		}
	}
	public Vector<Equipo> getEquiposAula (Aula aula){
		if (aula != null) {
			Vector<Equipo> lista = new Vector<>();
			String query = "SELECT * FROM sanluis.equipo WHERE aula = '"+aula.getCodigo()+"';";
			ResultSet rs = GestorDB.getGestorDB().execSQL(query);
			try {
				
				while(rs.next()) {
					int idEquipo = rs.getInt("idEquipo");
					String nombre = rs.getString("nombre");
					int almacenamiento = rs.getInt("almacenamiento");
					int ram = rs.getInt("ram");
					String ip = rs.getString("ip");				
					
					Equipo equipo = new Equipo(idEquipo, nombre, almacenamiento, ram, ip, aula);
					lista.add(equipo);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return lista;
		}else {
			return null;			
		}
	}
	public Vector<Equipo> getEquipos (){
	
		Vector<Equipo> lista = new Vector<>();
		String query = "SELECT * FROM sanluis.equipo ;";
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		try {
			
			while(rs.next()) {
				int idEquipo = rs.getInt("idEquipo");
				String nombre = rs.getString("nombre");
				int almacenamiento = rs.getInt("almacenamiento");
				int ram = rs.getInt("ram");
				String ip = rs.getString("ip");				
				
				String aulaCod = rs.getString("aula");
				Aula aula = this.getAula(aulaCod);
				Equipo equipo = new Equipo(idEquipo, nombre, almacenamiento, ram, ip, aula);
				lista.add(equipo);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	public Vector<Equipo> obtenerEquiposCondicionado(String atributo, String valor){
		
		Vector<Equipo> lista = new Vector<Equipo>();
		
		try {
			String query = "Select * from equipo where " + atributo + " like '%" + valor + "%'";
			ResultSet rs = GestorDB.getGestorDB().exeqSQLExc(query);			
			while(rs.next()) {
				int idEquipo = rs.getInt("idEquipo");
				String nombre = rs.getString("nombre");
				int almacenamiento = rs.getInt("almacenamiento");
				int ram = rs.getInt("ram");
				String ip = rs.getString("ip");				
				
				String aulaCod = rs.getString("aula");
				Aula aula = this.getAula(aulaCod);
				Equipo equipo = new Equipo(idEquipo, nombre, almacenamiento, ram, ip, aula);
				lista.add(equipo);
			}
			
		} catch (Exception e) {
			return null;
			
		}
		
		return lista;
		
	}

	
	public Vector<Componente> getComponentesEquipo (Equipo equipo){
		if (equipo != null) {
			Vector<Componente> lista = new Vector<>();
			String query = "SELECT * FROM sanluis.componente WHERE equipo = "+equipo.getIdEquipo()+";";
			ResultSet rs = GestorDB.getGestorDB().execSQL(query);
			try {
				while(rs.next()) {
					int idComponente = rs.getInt("idComponente");
					String nombre = rs.getString("nombre");
		
					int stockId = rs.getInt("stock");
					Lote stock = this.getStock(stockId);
					
					String clase = rs.getString("clase");				
					
					String centroName = rs.getString("centro");
					Centro centro = this.getCentro(centroName);
					
					String informacion = rs.getString("informacion");
					
					Componente componente = new Componente(idComponente, nombre, stock, clase, equipo, centro, informacion);
					lista.add(componente);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return lista;
		}else {
			return null;
		}
	}
	public Vector<Componente> getComponentesCentro (Centro centro){
		String query = "";
		if (centro != null) {
			query = "SELECT * FROM sanluis.componente WHERE centro = '"+centro.getNombre()+"';";
		}else {
			query = "SELECT * FROM sanluis.componente WHERE centro IS NULL;";
		}
		Vector<Componente> lista = new Vector<>();
		
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		try {
			while(rs.next()) {
				int idComponente = rs.getInt("idComponente");
				String nombre = rs.getString("nombre");
	
				int stockId = rs.getInt("stock");
				Lote stock = this.getStock(stockId);
				
				int equipoId = rs.getInt("equipo");
				Equipo equipo = GestorDatos.getInstance().getEquipo(equipoId);
				
				String clase = rs.getString("clase");								
				
				String informacion = rs.getString("informacion");
				
				Componente componente = null;
				if (centro != null) {
					componente = new Componente(idComponente, nombre, stock, clase, equipo, centro, informacion);
				}else {
					componente = new Componente(idComponente, nombre, stock, clase, equipo, null, informacion);
				}
				lista.add(componente);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;		
	}
	public Vector<Componente> getComponentesCentroLibres (Centro centro){
		if (centro != null) {
			Vector<Componente> lista = new Vector<>();
			String query = "SELECT * FROM sanluis.componente WHERE centro = '"+centro.getNombre()+"' AND equipo is null;";
			ResultSet rs = GestorDB.getGestorDB().execSQL(query);
			try {
				while(rs.next()) {
					int idComponente = rs.getInt("idComponente");
					String nombre = rs.getString("nombre");
		
					int stockId = rs.getInt("stock");
					Lote stock = this.getStock(stockId);
					
					int equipoId = rs.getInt("equipo");
					Equipo equipo = GestorDatos.getInstance().getEquipo(equipoId);
					
					String clase = rs.getString("clase");								
					
					String informacion = rs.getString("informacion");
					
					Componente componente = new Componente(idComponente, nombre, stock, clase, equipo, centro, informacion);
					lista.add(componente);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return lista;
		}else {
			return null;
		}
	}
	public boolean actualizarComponente (Componente componente, int idComponenteAnterior) {
		String query;
		if (componente.getEquipo() != null) {
			query = "UPDATE `sanluis`.`componente` SET `idComponente` = '"+componente.getIdComponente()+"', `nombre` = '"+componente.getNombre()+"', `stock` = '"+componente.getStock().getIdStock()+"', `clase` = '"+componente.getClase()+"', `equipo` = '"+componente.getEquipo().getIdEquipo()+"', `centro` = '"+componente.getCentro().getNombre()+"' WHERE (`idComponente` = '"+idComponenteAnterior+"');";
		}else {
			if (componente.getCentro() != null) {
				query = "UPDATE `sanluis`.`componente` SET `idComponente` = '"+componente.getIdComponente()+"', `nombre` = '"+componente.getNombre()+"', `stock` = '"+componente.getStock().getIdStock()+"', `clase` = '"+componente.getClase()+"', `equipo` = NULL, `centro` = '"+componente.getCentro().getNombre()+"' WHERE (`idComponente` = '"+idComponenteAnterior+"');";
			}else {
				query = "UPDATE `sanluis`.`componente` SET `idComponente` = '"+componente.getIdComponente()+"', `nombre` = '"+componente.getNombre()+"', `stock` = '"+componente.getStock().getIdStock()+"', `clase` = '"+componente.getClase()+"', `equipo` = NULL, `centro` = NULL WHERE (`idComponente` = '"+idComponenteAnterior+"');";
			}
		}
		
		try {
			GestorDB.getGestorDB().exeqSQLExc(query);
			return true;
		}catch (Exception e) {			
			return false;
		}
	}
	
	public void eliminarIncidencia (Incidencia incidencia) {
		String query = "DELETE FROM `sanluis`.`solicitudincidencia` WHERE (`idSolInc` = '"+incidencia.getIdSolInc()+"');";
		GestorDB.getGestorDB().execSQL(query);
		
		// TODO Actualizar Tecnico
		incidencia.getTecnico().setPendientes(incidencia.getTecnico().getPendientes()-1);
		GestorDatos.getInstance().actualizarTecnico(incidencia.getTecnico());
						
	}
	public void eliminarSolicitud (Solicitud solicitud) {
		String query = "DELETE FROM `sanluis`.`solicitudincidencia` WHERE (`idSolInc` = '"+solicitud.getIdSolInc()+"');";
		GestorDB.getGestorDB().execSQL(query);
		
		// TODO Actualizar Tecnico
		solicitud.getTecnico().setPendientes(solicitud.getTecnico().getPendientes()-1);
		GestorDatos.getInstance().actualizarTecnico(solicitud.getTecnico());
						
	}
	public boolean crearAula(Aula aula) {
		String query = "INSERT INTO `sanluis`.`aula` (`codigo`, `tamaño`, `disponibles`, `centro`) VALUES ('"+aula.getCodigo()+"', '"+aula.getTamaño()+"', '"+aula.getDisponibles()+"', '"+aula.getCentro().getNombre()+"');"; 
		try {
			GestorDB.getGestorDB().exeqSQLExc(query);
			return true;			
		}catch (Exception e) {
			return false;
		}
	}
	public boolean eliminarAula(Aula aula) {
		String query = "DELETE FROM `sanluis`.`aula` WHERE (`codigo` = '"+aula.getCodigo()+"');";
		try {
			GestorDB.getGestorDB().exeqSQLExc(query);
			return true;			
		}catch (Exception e) {
			return false;
		}
	}
	public Vector<AlmacenProveedores> getAlmacen(Centro centro){
		Vector<AlmacenProveedores>lista = new Vector<>();
		String query = "";
		if (centro != null) {
			query = "SELECT a.* FROM sanluis.proveedor p, sanluis.stock s, sanluis.almacen a WHERE p.centro = '"+centro.getNombre()+"' and s.proveedor = p.idProveedor and a.stock = s.idStock;";
		}else {
			query = "SELECT * FROM sanluis.almacen;";
		}	
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		try {
			while(rs.next()) {
				int idComponente = rs.getInt("idComponenteAlmacen");
				String nombre = rs.getString("nombre");
				
				int idStock = rs.getInt("stock");
				Lote stock = this.getStock(idStock);
				
				String clase = rs.getString("clase");
				int cantidad = rs.getInt("cantidad");
				String informacion = rs.getString("informacion");
				
				AlmacenProveedores almacenProveedores = new AlmacenProveedores(idComponente, stock, cantidad, nombre, clase, informacion);
				lista.add(almacenProveedores);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;							
	}
	public AlmacenProveedores getComponenteAlmacen(int idComponenteAlmacen){
		String query = "SELECT * FROM sanluis.almacen where idComponenteAlmacen = "+idComponenteAlmacen+";";
		AlmacenProveedores almacenProveedores = null;
		ResultSet rs = GestorDB.getGestorDB().execSQL(query);
		try {
			while(rs.next()) {
				int idComponente = rs.getInt("idComponenteAlmacen");
				String nombre = rs.getString("nombre");
				
				int idStock = rs.getInt("stock");
				Lote stock = this.getStock(idStock);
				
				String clase = rs.getString("clase");
				int cantidad = rs.getInt("cantidad");
				String informacion = rs.getString("informacion");
				
				almacenProveedores = new AlmacenProveedores(idComponente, stock, cantidad, nombre, clase, informacion);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return almacenProveedores;							
	}
	public Peticion getPeticion(int idPeticion) {
		Peticion peticion = null;
		String query = "SELECT * FROM sanluis.peticiones where idPeticion = '"+idPeticion+"';";
		try {
			ResultSet rs = GestorDB.getGestorDB().execSQL(query);
			while(rs.next()) {
				
				int idComponenteAlmacen = rs.getInt("componenteAlmacen");
				AlmacenProveedores componenteAlmacen = this.getComponenteAlmacen(idComponenteAlmacen);
				
				String tecnicoUser = rs.getString("tecnico");
				Tecnico tecnico = this.getTecnico(tecnicoUser);
				
				String adminUser = rs.getString("administrador");
				Administrador admin = this.getAdministradorCompleto(adminUser);
				
				String descripcion = rs.getString("descripcion");
				int cantidad = rs.getInt("cantidad");
				String estado = rs.getString("estado");
				
				peticion = new Peticion(idPeticion, componenteAlmacen, tecnico, admin, descripcion, cantidad, estado);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return peticion;
	}
	public Peticion generarPeticion(String concepto, AlmacenProveedores componente, Tecnico tecnico, Administrador administrador, int cantidad) {
		String query = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'sanluis' AND   TABLE_NAME   = 'peticiones';";
		Peticion peticion = null;
		int idPeticion = 0;
		try {
			ResultSet rs = GestorDB.getGestorDB().execSQL(query);
			while(rs.next()) {
				idPeticion = rs.getInt("AUTO_INCREMENT");
			}
			
			peticion = new Peticion(idPeticion, componente, tecnico, administrador, concepto, cantidad, "n");			
			query = "INSERT INTO `sanluis`.`peticiones` (`componenteAlmacen`, `tecnico`, `administrador`, `descripcion`, `cantidad`, `estado`) VALUES ('"+peticion.getComonenteAlmacen().getIdComponente()+"', '"+tecnico.getUser()+"', '"+administrador.getUser()+"', '"+concepto+"', '"+cantidad+"', 'n');";
			GestorDB.getGestorDB().exeqSQLExc(query);
			return peticion;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Vector<Peticion> getPeticionesAdminNoAtendidas (Administrador administrador){
		Vector<Peticion> lista = new Vector<>();
		String query = "SELECT * FROM sanluis.peticiones WHERE administrador = '"+administrador.getUser()+"' and estado = 'n';";
		try {
			ResultSet rs = GestorDB.getGestorDB().execSQL(query);
			while(rs.next()) {
				int idPeticion = rs.getInt("idPeticion");
				
				int idComponenteAlmacen = rs.getInt("componenteAlmacen");
				AlmacenProveedores componenteAlmacen = this.getComponenteAlmacen(idComponenteAlmacen);
				
				String tecnicoUser = rs.getString("tecnico");
				Tecnico tecnico = this.getTecnico(tecnicoUser);
				
//				String adminUser = rs.getString("administrador");
//				Administrador adminstrador = this.getAdministradorCompleto(adminUser);
				
				String descripcion = rs.getString("descripcion");
				int cantidad = rs.getInt("cantidad");
				String estado = rs.getString("estado");
				
				Peticion peticion = new Peticion(idPeticion, componenteAlmacen, tecnico, administrador, descripcion, cantidad, estado);
				lista.add(peticion);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	public boolean actualizarPeticion (Peticion peticion, int idPeticion) {
		try {
			String query = "UPDATE `sanluis`.`peticiones` SET `idPeticion` = '"+peticion.getIdPeticion()+"', `componenteAlmacen` = '"+peticion.getComonenteAlmacen().getIdComponente()+"', `tecnico` = '"+peticion.getTecnico().getUser()+"', `administrador` = '"+peticion.getAdministrador().getUser()+"', `descripcion` = '"+peticion.getDescripcion()+"', `cantidad` = '"+peticion.getCantidad()+"', `estado` = '"+peticion.getEstado()+"' WHERE (`idPeticion` = '"+idPeticion+"');";
			GestorDB.getGestorDB().exeqSQLExc(query);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}
