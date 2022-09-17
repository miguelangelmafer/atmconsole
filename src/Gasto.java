
public class Gasto extends Dinero {
	
	public Gasto(double gasto, String description){
		this.dinero=gasto;
		this.description=description;
		
	}
	
	public String toString() {
		return "Gasto en concepto de : " + this.description + "\n" + "Importe gastado: " + this.dinero;
	}
}
