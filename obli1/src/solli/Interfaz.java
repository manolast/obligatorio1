package solli;

import java.util.Scanner;
import solli.Dominio.*;

public class Interfaz {
    
    public static void jugarDatosArchivo(){
        Juego juego = new Juego(true);
        empezarJuego(juego);
    }
    
    public static void jugarPredefinido(){
        Juego juego = new Juego();
        empezarJuego(juego);
    }
    
    public static void jugarAzar(){
        Scanner in = new Scanner(System.in);
        System.out.println("ingrese filas");
        int filas = in.nextInt();
        System.out.println("ingrese columnas");
        int columnas = in.nextInt();
        System.out.println("ingrese nivel");
        int nivel = in.nextInt();
    
        Juego juego = new Juego(filas, columnas, nivel);
        empezarJuego(juego);

    }
    public static void empezarJuego(Juego juego){
        System.out.print(juego.toString());
        Scanner in = new Scanner(System.in);
        
        boolean solucionada = false;
        while(!solucionada){
            System.out.println("ingrese movimiento");
            int x = in.nextInt();
            int y = in.nextInt();
            String resp = juego.hacerCambiosTablero(x-1,y-1);
            System.out.println(resp);
            System.out.println(juego.toString());
            solucionada = juego.estaSolucionada();
        }
    }

    public static void start() {
        Scanner in = new Scanner(System.in);
        System.out.println("Desea jugar? (y/n)");
        String answer = in.nextLine();
        answer = answer.toUpperCase();
        switch(answer){
            case "Y":
                imprimirMenu();
                break;
            case "N":
                System.exit(0);
                break;
            default:
                System.out.println("Opcion no valida. Inserte opcion valida");
                start();
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
                jugarDatosArchivo();
                break;
            case "b":
                jugarPredefinido();
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
