package Usuarios;

public class Persona {
protected String login;
protected String password;
public Persona(String login, String password) {
	super();
	this.login = login;
	this.password = password;
}
public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}
