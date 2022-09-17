
public class Ingreso extends Dinero {

	public Ingreso(double ingreso, String description) {
		this.dinero=ingreso;
		this.description=description;
	}
	
	public String toString() {
		return "Ingreso en concepto de : " + this.description + "\n" + "Importe ingresado: " +this.dinero;
	}
}
