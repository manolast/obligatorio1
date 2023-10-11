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
        int filas = pedirNumero(3, 9);
        System.out.println("ingrese columnas");
        int columnas = pedirNumero(3, 9);
        System.out.println("ingrese nivel");
        int nivel = pedirNumero(1, 8);

        Juego juego = new Juego(filas, columnas, nivel);
        System.out.print(juego.toString());

        empezarJuego(juego);

    }

    public static int pedirNumero(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int numero = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                numero = scanner.nextInt();
                if (numero >= min && numero <= max) {
                    entradaValida = true;
                } else {
                    System.out.println("No válido. Debe ser un entero entre " + min + " y " + max + " inclusive.");
                }
            } catch (InputMismatchException e) {
                System.out.println("ingrese un número entero válido.");
                scanner.nextLine();
            }
        }

        return numero;
    }

    public static int pedirColumna() {
        Scanner scanner = new Scanner(System.in);
        int numero = 0;
        System.out.println("ingrese columna");
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                numero = scanner.nextInt();
                entradaValida = true;
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("ingrese un número entero válido.");
                scanner.nextLine();
            }
        }
        return numero;
    }

    public static void empezarJuego(Juego juego) {
        boolean solucionada = juego.estaSolucionada();
        boolean terminada = false;
        while (!solucionada && !terminada) {
            String[] movimiento1 = pedirComandoOFila();
            if (movimiento1.length == 1) {
                switch (movimiento1[0]) {
                    case "X":
                        System.out.println("terminado el juego");
                        terminada = true;
                        break;
                    case "H":
                        String hist = juego.devolverHistorial();
                        System.out.println(hist);
                        break;
                    case "S":
                        String solucion = juego.solucionar();
                        System.out.println(solucion);
                        break;
                    default:
                        System.out.println("PASO ALGO RARISIMO.");
                        break;
                }
            } else {
                int numeroX = Integer.parseInt(movimiento1[0]);
                int numeroY = pedirColumna();
                String resp = juego.hacerCambiosTablero(numeroX, numeroY, false);
                System.out.println(resp);
                solucionada = juego.estaSolucionada();
            }
        }
        if (solucionada) {
            long fin = System.currentTimeMillis();
            long inicio = juego.getTiempoInicio();
            long tiempoTranscurrido = fin - inicio;
            double tiempoSegundos = tiempoTranscurrido / 1000.0;
            System.out.println("Juego terminado! Tiempo de resolución: " + tiempoSegundos + " segundos.");

        }
        start();
    }

    public static String[] pedirComandoOFila() {
        Scanner in = new Scanner(System.in);
        String[] ret;
        System.out.println("Ingrese comando o fila");
        String input = in.nextLine();
        try {
            int numero1 = Integer.parseInt(input);
            ret = new String[2];
            ret[0] = "" + numero1;
            ret[1] = "placeholder";
        } catch (NumberFormatException e) {
            if (input.length() > 1) {
                System.out.println("opcion no valida");
                ret = pedirComandoOFila();
            } else {
                switch (input) {
                    case "X":
                        ret = new String[1];
                        ret[0] = "X";
                        break;
                    case "H":
                        ret = new String[1];
                        ret[0] = "H";
                        break;
                    case "S":
                        ret = new String[1];
                        ret[0] = "S";
                        break;
                    default:
                        System.out.println("opcion no valida");
                        ret = pedirComandoOFila();
                        break;
                }
            }
        }
        return ret;
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
                    ret = new String[1];
                    ret[0] = "X";
                    break;
                case "H":
                    ret = new String[1];
                    ret[0] = "H";
                    break;
                case "S":
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
        System.out.println("Bienvenido a Solliflips! El mejor juego solitario del planeta");
        start(); // Call the static start() method
    }
}
