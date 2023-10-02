package solli;

import java.util.Scanner;
import solli.Dominio.*;

public class Interfaz {
    
    public static void jugarAzar(){
        Scanner in = new Scanner(System.in);
        System.out.println("ingrese filas");
        int filas = in.nextInt();
        System.out.println("ingrese columnas");
        int columnas = in.nextInt();
        System.out.println("ingrese nivel");
        int nivel = in.nextInt();
        
        Juego juego = new Juego(filas, columnas, nivel);
        for (int i = 0; i < 4; i++) {
            System.out.println("ingrese movimiento");
            int x = in.nextInt();
            int y = in.nextInt();
            String resp = juego.hacerCambiosTablero(x,y);
            System.out.println(resp);
            
        }
        
    }
    
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
                jugarAzar();
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
