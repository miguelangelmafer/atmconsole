import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	
	//Recogemos los datos que ha introducido el usuario por teclado
	
	private static Scanner sc = new Scanner(System.in);
	
	// Declaramos una variable para cuando el usuario seleccione la opcion del menu
	
	private static int opcion=-1;
	
	//Almacenamos objeto tipo usuario
	private static Usuario nuevoUsuario= new Usuario();
	
	//Declaramos e inicializamos las variables que nos permitirán crear el usuario.
	private static String username="";
	private static String years="";
	private static int yearsuser=0;
	private static String userDNI="";
	private static boolean validezDNI=false;
	
	//Almacenamos objeto tipo cuenta
	private static Cuenta nuevaCuenta=null;
	
	
	//Declaramos e inicializamos las variables para introducir los ingresos y los gastos
	private static String descripcion_movimiento="";
	private static String importe="";
	private static double importe_movimiento=-1;
	
	
	
	//Declaramos e inicializamos una variable que nos indica si el saldo de la cuenta es negativo
	private static boolean  saldoNegativo=false;
	
	
	//Declaramos las variables que nos permitirán almacenar los disntintos ingresos y gastos
	private static List <Ingreso> listaIngresos= new ArrayList<Ingreso>();
	private static List <Gasto> listaGastos= new ArrayList<Gasto>();
	
	
	//Con este método solicitamos al usuario la información necesaria para crear su usuario
	private static boolean introducirUsuario() {
		
		//Solicitamos el nombre por teclado y lo guardamos con un setter
		do{
			System.out.println("Introduce el nombre del usuario: ");
			username=sc.nextLine();
		} 
		while (username.isEmpty());
		
		//Solicitamos la edad por teclado y lo guardamos con un setter
		do{
			System.out.println("Introduce la edad del usuario: ");
			years=sc.nextLine();
			
			//Hacemos un try para tratar de tranformar el valor introducido por teclado a int
			try {
			yearsuser=Integer.parseInt(years);
			}
			//en caso de que no se pueda porque el usuario no haya metido un numero o sea inferior a 0 se lo indicamos por pantalla
			catch(Exception e) {
				System.out.println("La edad debe ser un número mayor a 0");
			}
		}
		while (years.isEmpty() || yearsuser<=0);
		
		//Solicitamos el DNI al usuario
		do {
			System.out.println("Introduce el DNI del usuario: ");
			userDNI=sc.nextLine();
			
			//Comprobamos que el formato del DNI es correcto y en caso contrario se lo indicamos por pantalla
			if(nuevoUsuario.setDNI(userDNI)==false) {
				validezDNI=false;
				System.out.println("El formato del DNI es incorrecto, ha de contener 8 números y 1 letra");
			}
			else {
				validezDNI=true;
			}
			
		}
		while(userDNI.isEmpty() || validezDNI==false);
		
		// Establecemos con lso setter tanto el nombre como la edad del usuario
		nuevoUsuario.setNombre(username);
		nuevoUsuario.setEdad(yearsuser);
		return true;
	}
	
	//TENEMOS QUE CREAR la cuenta
	
	//Metodo para visualizar el menu
	private static void show_menu() {
		
		System.out.println("Realiza una nueva acción");
		System.out.println("1 Introduce un nuevo gasto");
		System.out.println("2 Introduce un nuevo ingreso");
		System.out.println("3 Mostrar gastos");
		System.out.println("4 Mostrar ingresos");
		System.out.println("5 Mostrar saldo");
		System.out.println("0 Salir");
		
		//leemos por pantalla y guardamos en variable opcion
		opcion = sc.nextInt();
	}
	
	private static void introducirIngresos() {
		
		do {
			System.out.println("Introduce la descripcion del ingreso: ");
			descripcion_movimiento=sc.nextLine();
		
		}while(descripcion_movimiento.isEmpty());
		
		do {
			System.out.println("Introduce el importe del ingreso: ");
			importe=sc.nextLine();
			
			try {
				importe_movimiento=Double.parseDouble(importe);
			}catch(Exception e) {
				System.out.println("\n"+"Debes introducir un valor numérico para el importe del ingreso"+"\n");
			}
		}while(importe.isEmpty()|| importe_movimiento==-1);
		
		nuevaCuenta.addIngresos(descripcion_movimiento, importe_movimiento);
		System.out.println("\n"+"El saldo actual de tu cuenta es de: "+ nuevaCuenta.getSaldo() + "€"+"\n");
		
	}
	
	private static void introducirGastos() {
		do {
			System.out.println("Introduce la descripcion del gasto: ");
			descripcion_movimiento=sc.nextLine(); 
		}
		while(descripcion_movimiento.isEmpty());
		
		do {
			System.out.println("Introduce el importe del gasto: ");
			importe=sc.nextLine();
			
			try {
				importe_movimiento=Double.parseDouble(importe);
			}catch(Exception e) {
				System.out.println("Debes introducir un valor numérico para el importe del gasto");
			}
			if(nuevaCuenta.addGastos(descripcion_movimiento,importe_movimiento)<0) {
				System.out.println("\n"+"Saldo insuficiente para realiza la operación"+ "\n");
				saldoNegativo=true;
				break;
			}
			else {
				saldoNegativo=false;
				System.out.println("\n"+"El saldo actual de tu cuenta es de: "+ nuevaCuenta.getSaldo() + "€"+"\n");
			}
		}
		while(importe.isEmpty()|| importe_movimiento<0 || saldoNegativo==true);
	}
	
	
	/**
	 * ESTOS METODOS MOSTRARAN LISTA DE INGRESOS Y GASTOS
	 */
	
	private static void mostrarListaIngresos() {
		listaIngresos=nuevaCuenta.getIngresos();
		if(listaIngresos.isEmpty()) {
			System.out.println("No hay ingresos en la cuenta");
		}
		else {
			for(int i=0;i<listaIngresos.size();i++) {
				System.out.println(listaIngresos.get(i));
			}
		}
	}

	private static void mostrarListaGastos() {
		listaGastos=nuevaCuenta.getGastos();
		if(listaGastos.isEmpty()) {
			System.out.println("No hay gastos en la cuenta");
		}
		else {
			for(int i=0;i<listaGastos.size();i++) {
				System.out.println(listaGastos.get(i));
			}
		}
	}
	

	public static void main(String[] args) {

		
		//Si el metodo introducirUsuario devuelve true, porque todos los datos son correctos, creamos la cuenta con dichos datos
		if(introducirUsuario()) {
			nuevaCuenta=new Cuenta(nuevoUsuario);
		}
	
		//System.out.println(nuevoUsuario.toString());
		
		//Una vez creada la cuenta mostramos el menu con el método show_menu
		do {
		show_menu();
		
		switch (opcion) {
		case 1: //Introducir gasto
			sc.nextLine();
			introducirGastos();
			break;
		case 2: //Introducir ingreso
			sc.nextLine();
			introducirIngresos();
			break;
		case 3: //Mostrar gastos
			mostrarListaGastos();
			break;
		case 4: //Mostrar ingreso
			mostrarListaIngresos();
			break;
		case 5: //Mostrar saldo revisar
			System.out.println(nuevaCuenta.toString());
			break;
		case 0: 
		System.out.println("Fin del programa.\nGracias por utilizar la aplicación.");
			break;
			}
		}while (opcion !=0);
	}
	
}
