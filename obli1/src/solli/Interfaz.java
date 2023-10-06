package solli;

import java.util.InputMismatchException;
import java.util.Scanner;
import solli.Dominio.*;
import static solli.Interfaz.empezarJuego;

public class Interfaz {

    public static void jugarDatosArchivo() {
        Juego juego = new Juego(true);
        System.out.print(juego.toString());
        empezarJuego(juego);
    }

    public static void jugarPredefinido() {
        Juego juego = new Juego();
        System.out.print(juego.toString());
        empezarJuego(juego);
    }

    public static void jugarAzar() {
        Scanner in = new Scanner(System.in);
        System.out.println("ingrese filas");
        int filas = pedirNumero();
        System.out.println("ingrese columnas");
        int columnas = pedirNumero();
        System.out.println("ingrese nivel");
        int nivel = pedirNumero();

        Juego juego = new Juego(filas, columnas, nivel);
        System.out.print(juego.toString());

        empezarJuego(juego);

    }

    public static int pedirNumero() {
        Scanner scanner = new Scanner(System.in);
        int numero = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                numero = scanner.nextInt();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("ingrese un número entero válido.");
                scanner.nextLine();
            }
        }

        return numero;
    }

    public static void empezarJuego(Juego juego) {
        boolean solucionada = juego.estaSolucionada();
        while (!solucionada) {
            String[] movimiento = pedirMovimiento();
            if (movimiento.length == 1) {
                switch (movimiento[0]) {
                    case "X":
                        System.out.println("terminado el juego");
                        solucionada = true;
                        start();
                        break;
                    case "H":
                        System.out.println("mostrando historial");
//                        juego.mostrarHistorial();
                        break;
                    case "S":
                        System.out.println("Mostrando solucion");
//                        juego.solucionar();
                        break;
                    default:
                        System.out.println("paso algo raro");
                        break;
                }
            } else {
                int numeroX = Integer.parseInt(movimiento[0]);
                int numeroY = Integer.parseInt(movimiento[1]);
                String resp = juego.hacerCambiosTablero(numeroX - 1, numeroY - 1);
                System.out.println(resp);
                solucionada = juego.estaSolucionada();
            }
        }
        System.out.println("Juego terminado!");
        start();
    }

    public static String[] pedirMovimiento() {
        System.out.println("ingrese movimiento");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] ret = new String[2];
        String[] inputArray = input.split(" ");
        //si el array tiene largo 1, o es letras o esta mal
        //si largo 2, o es movimiento de numeros o esta mal
        //si es largo 3 esta mal
        if (inputArray.length == 1) {
            switch (inputArray[0]) {
                case "X":
                    System.out.println("terminado el juego");
                    ret = new String[1];
                    ret[0] = "X";
                    break;
                case "H":
                    System.out.println("mostrando historial");
                    ret = new String[1];
                    ret[0] = "H";
                    break;
                case "S":
                    System.out.println("Mostrando solucion");
                    ret = new String[1];
                    ret[0] = "S";
                    break;
                default:
                    System.out.println("opcion no valida");
                    ret = pedirMovimiento();
            }
        } else if (inputArray.length == 2) {
            try {
                int numero1 = Integer.parseInt(inputArray[0]);
                int numero2 = Integer.parseInt(inputArray[1]);
                ret = inputArray;
            } catch (NumberFormatException e) {
                System.out.println("opcion no valida");
                ret = pedirMovimiento();
            }
        } else {
            System.out.println("opcion no valida");
            ret = pedirMovimiento();
        }
        return ret;
    }

    public static void start() {
        Scanner in = new Scanner(System.in);
        System.out.println("Desea jugar? (y/n)");
        String answer = in.nextLine();
        answer = answer.toUpperCase();
        switch (answer) {
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
        switch (opcion) {
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
