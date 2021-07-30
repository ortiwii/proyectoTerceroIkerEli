package view;


public class AppMain {

	public static void main(String[] args) {
		try {
			
			LoginUI login = new LoginUI();
			login.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
