
public class Usuario {
	
	private String nombre;
	private int edad;
	private String DNI;
	
	public Usuario() {
		this.nombre= "nombre";
		this.edad=0;
		this.DNI= "DNI";
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getDNI() {
		return DNI;
	}
	
	public boolean setDNI (String DNI) {
		
	if(DNI.matches("^[0-9]{8}[a-zA-Z]$")
			|| DNI.matches("^[0-9]{8}[-][a-z,A-Z]$")){
		this.DNI=DNI;
		return true;
	}
	else {
		return false;
	}
}
	
	public String toString() {
		
		return "Nombre: " + this.nombre + "\n" + "Edad: " + this.edad + "\n" + "DNI: " + this.DNI;
	}

}
