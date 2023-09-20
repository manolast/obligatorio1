/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

import java.util.Scanner;

/**
 *
 * @author manue
 */
public class start {
        public static void start(){
            Scanner in = new Scanner(System.in);
            System.out.println("Desea jugar? (y/n)");
            String answer = in.nextLine();
            answer.toUpperCase();
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
        public static void imprimirMenu(){
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
}
