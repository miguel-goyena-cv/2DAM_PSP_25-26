package org.cuatrovientos.dam.psp.io;

import java.util.Scanner;

public class Calculadora {

    private static final int MODE_ENTERO = 1;
    private static final int MODE_REAL = 2;

    public static void main( String[] args )
    {

        int modo = validarModo(args);

        // Creamos un objeto Scanner para leer desde consola
        Scanner scanner = new Scanner(System.in);

        // Preparamos el bucle para repetir la operación
        boolean continuar = true;
        while (continuar) {

            // Dependiendo del modo, llamamos a una función u otra
            if (modo == 1) {
                calculadoraEnteros(scanner);
            } else {
                calculadoraReales(scanner);
            }

            // Pregunto si quiero otra operacion
            System.out.print("Quiere realizar otra operación (S/n): ");
            String respuesta = scanner.nextLine();
            continuar = (respuesta.equalsIgnoreCase("s"));
            
        }

        // Cerramos el scanner, OJO que no se nos olvide
        scanner.close();
        
    }

    /**
     * Aqui realizo las validaciones iniciales
     * @param args
     * @return
     */
    private static int validarModo(String[] args) {
        // Tenemos 2 modos de funcionamiento, con enteros o con reales
        if (args.length != 1) {
            System.out.println("Uso: java Calculadora <modo>: El modo puede ser "+MODE_ENTERO+" (enteros) o "+MODE_REAL+" (reales)");
            System.out.println("Ejemplo: java Calculadora 1");
            System.exit(1);
        }
        int modo = Integer.parseInt(args[0]);
        if (modo != MODE_ENTERO && modo != MODE_REAL) {
            System.out.println("Modo no válido. Debe ser "+MODE_ENTERO+" (enteros) o "+MODE_REAL+" (reales)");
            System.exit(1);
        }
        return modo;
    }

    private static void calculadoraReales(Scanner scanner) {

        System.out.println("Modo de operación: Reales");
        
        // Pedimos dos números por consola, si no son numeros dará errores
        System.out.print("Introduce el primer número: ");
        double num1 = Double.parseDouble(scanner.nextLine());
        System.out.print("Introduce el segundo número: ");
        double num2 = Double.parseDouble(scanner.nextLine());
        System.out.print("Introduce la operación (+, -, *, /): ");
        String operacion = scanner.nextLine();

        realizarOperacion(num1, num2, operacion, MODE_REAL);

    }

    private static void calculadoraEnteros(Scanner scanner) {

        System.out.println("Modo de operación: Enteros");
        
        // Pedimos dos números por consola, si no son enteros daran errores
        System.out.print("Introduce el primer número: ");
        int num1 = Integer.parseInt(scanner.nextLine());
        System.out.print("Introduce el segundo número: ");
        int num2 = Integer.parseInt(scanner.nextLine());
        System.out.print("Introduce la operación (+, -, *, /): ");
        String operacion = scanner.nextLine();

        realizarOperacion(num1, num2, operacion, MODE_ENTERO);

    }

    private static void realizarOperacion(double num1, double num2, String operacion, int mode) {
        double resultado = 0;
        boolean errorOperacion = false;
        switch (operacion) {
            case "+":
                resultado = num1 + num2;
                break;
            case "-":
                resultado = num1 - num2;
                break;
            case "*":
                resultado = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    resultado = num1 / num2;
                } else {
                    System.out.println("Error: División por cero");
                    errorOperacion = true;
                }
                break;
            default:
                System.out.println("Operación no válida");
                errorOperacion = true;
        }
        if (!errorOperacion) {
            if (mode == 1){
                System.out.println("El resultado es: " + (int)resultado);
            }
            else{
                System.out.println("El resultado es: " + resultado);
            }
        }
    }
}
