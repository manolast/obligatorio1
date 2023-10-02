package solli;

import java.util.Scanner;

public class Interfaz {
    
    public static void start() { // Changed from non-static to static
        Scanner in = new Scanner(System.in);
        System.out.println("Desea jugar? (y/n)");
        String answer = in.nextLine();
        answer = answer.toUpperCase(); // Corrected the assignment of the uppercase value
        switch(answer){
            case "Y":
                imprimirMenu();
                break;
            case "N":
                System.exit(0);
                break;
            default:
                System.out.println("Opcion no valida. Inserte opcion valida");
                start(); // Recursive call to start() method
                break;
        }
    }
    
    public static void imprimirMenu() {
        System.out.println("Ingrese una opcion");
        System.out.println("a) Tomar datos del archivo “datos.txt”");
        System.out.println("b) Usar el tablero predefinido");
        System.out.println("c) Usar un tablero al azar");

        Scanner in = new Scanner(System.in);
        String opcion = in.nextLine();
        switch(opcion){
            case "a":
                // Call jugarDatosArchivo() or implement this method
                break;
            case "b":
                // Call jugarPredefinido() or implement this method
                break;
            case "c":
                // Call jugarAzar() or implement this method
                break;
            default:
                System.out.println("Opcion no valida. Inserte opcion valida");
                imprimirMenu();
                break;
        }
    }
    
    public static void main(String[] args) {
        start(); // Call the static start() method
    }
}
