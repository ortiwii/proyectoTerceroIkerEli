package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	public Utils() {}
	
	public static boolean ValidarMail(String email) {
		// Patrón para validar el email
		Pattern pattern = Pattern
				.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
						+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		// El email a validar

		Matcher mather = pattern.matcher(email);

		return mather.find();
    }
}
