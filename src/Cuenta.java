import java.util.ArrayList;
import java.util.List;

public class Cuenta {
	
	private double saldo;
	private Usuario usuario;
	private List<Gasto> gastos;
	private List<Ingreso> ingresos;
	
	public Cuenta(Usuario usuario) {
		this.usuario=usuario;
		this.saldo=0;
		this.gastos = new ArrayList<Gasto>();
		this.ingresos= new ArrayList<Ingreso>();		
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double addIngresos(String description, double cantidad) {
		
	Ingreso nuevoIngreso=new Ingreso(cantidad,description);
	this.ingresos.add(nuevoIngreso);
	
	this.saldo=this.saldo + cantidad;
	return this.saldo;
	}
	
	
	public double addGastos (String description, double cantidad) {
	
		try{
			if(this.getSaldo()<cantidad) {
			throw new GastoException();
			}
			else {
				this.saldo=this.saldo-cantidad;
				Gasto nuevoGasto=new Gasto(cantidad,description);
				this.gastos.add(nuevoGasto);
				
			return this.saldo;
			}		
		}catch(GastoException ex) {
			return -1;
		}
		
	}

	public List<Ingreso> getIngresos() {
		return ingresos;
	} 

	public List<Gasto> getGastos() {
		return gastos;
	}	
	
	public String toString() {
		return "\n"+ "Hola " + usuario.getNombre()+" el saldo de tu cuenta es de: " + this.saldo +" €"+"\n";
	}

}
