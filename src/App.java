import java.util.Scanner;

public class App {
    // Variables estáticas para almacenar los datos del estudiante actual
    private static String nombreEstudiante;
    private static double nota1;
    private static double nota2;
    private static double nota3;

    
    //Método principal. Inicia el programa.
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // Scanner para leer datos del usuario
        Menu(input); // Llama al menú principal
        input.close(); // Cierra el scanner al finalizar
    }

    /**
     * Muestra el menú principal y gestiona las opciones del usuario.
     * 
     * parameto (Scanner input) para leer datos del usuario
     */
    private static void Menu(Scanner input) {
        boolean condition = true;
        while (condition) {
            // Muestra las opciones del menú
            System.out.println("---Sistema de Registro de Estudiantes---\n");
            System.out.println("""
                    1. Registrar datos de un estudiante
                    2. Mostrar datos del estudiante actual
                    3. Calcular promedio de notas del estudiante actual
                    4. Validar una nota individual
                    0. Salir
                    """);

            // Lee la opción del usuario
            var key = leerDatos(input, "Ingrese su opción:");
            int obcion = Integer.parseInt(key);
            switch (obcion) {
                case 1:
                    // Registrar datos de un estudiante
                    registarInformacionEstudiante(input);
                    break;
                case 2:
                    // Mostrar datos del estudiante actual
                    mostrardatosEstudianteActual();
                    break;
                case 3:
                    // Calcular promedio de notas
                    calcularPromedioNotasEA();
                    break;
                case 4:
                    // Validar una nota individual ingresada por el usuario
                    double nota;
                    while (true) {
                        try {
                            nota = Double.parseDouble(leerDatos(input, "Ingrese la nota a validar:"));
                            if (validarNotaIndividual(nota)) {
                                System.out.println("La nota es válida.");
                                break;
                            } else {
                                System.out.println("La nota es inválida. Debe estar entre 0 y 100.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Debe4 ingresar un número válido.");
                        }
                    }
                    break;
                default:
                    // Opción no válida
                    System.out.print("Opción incorrecta, vuelva e ingrese una opción válida.\n");
                    break;
            }
            // Si el usuario elige salir, termina el ciclo
            if (obcion == 0) {
                break;
            }
        }
    }

    /**
     * Lee un dato del usuario mostrando un mensaje.
     * 
     * parameto input   Scanner para leer datos
     * parameto mensaje Mensaje a mostrar al usuario
     * return La entrada del usuario como String
     */
    private static String leerDatos(Scanner input, String mensaje) {
        System.out.print(mensaje);
        return input.nextLine();
    }

    /**
     * Registra la información de un estudiante: nombre y tres notas. Valida cada
     * dato antes de almacenarlo.
     * 
     * parametro input Scanner para leer datos
     */
    private static void registarInformacionEstudiante(Scanner input) {
        nombreEstudiante = validarNombre(input, "Ingrese el nombre del estudiante:");
        nota1 = validarNota(input, "Ingrese la nota 1:");
        nota2 = validarNota(input, "Ingrese la nota 2:");
        nota3 = validarNota(input, "Ingrese la nota 3:");
        System.out.println("Datos registrados correctamente.\n");
    }

    /**
     * Muestra los datos del estudiante actual.
     */
    private static void mostrardatosEstudianteActual() {
        System.out.println("El nombre del estudiante es: " + nombreEstudiante);
        System.out.print("La nota 1 es: " + nota1 + "\n");
        System.out.print("La nota 2 es: " + nota2 + "\n");
        System.out.println("La nota 3 es: " + nota3 + "\n");
    }

    /**
     * Calcula y muestra el promedio de las notas del estudiante actual. Indica si el
     * estudiante aprobó o reprobó.
     * 
     * return El promedio calculado
     */
    private static double calcularPromedioNotasEA() {
        var promedio = (nota1 + nota2 + nota3) / 3;
        System.out.printf("El promedio es %.3f:%n%n", promedio);
        if (promedio <= 60) {
            System.out.println("El estiduante " + nombreEstudiante + "ha reprobado");
        } else {
            System.out.println("El estiduante " + nombreEstudiante + "ha aprobado");
        }
        return promedio;
    }

    /**
     * Valida si una nota está en el rango de 0 a 100 .
     * 
     * parametro nota Nota a validar
     * return true si es válida, false si no
     */
    private static boolean validarNotaIndividual(double nota) {
        return nota >= 0 && nota <= 100;
    }

    /**
     * Solicita y valida que la nota ingresada sea un número y esté en el rango
     * válido.
     * 
     * parametro input   Scanner para leer datos
     * parametro mensaje Mensaje a mostrar al usuario
     * return La nota válida ingresada
     */
    private static double validarNota(Scanner input, String mensaje) {
        double nota;
        while (true) {
            try {
                nota = Double.parseDouble(leerDatos(input, mensaje));
                if (validarNotaIndividual(nota)) {
                    System.out.println("La nota es válida.");
                    break;
                } else {
                    System.out.println("La nota es inválida. Debe estar entre 0 y 100.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
        return nota;
    }

    /**
     * Solicita y valida que el nombre no esté vacío y tenga más de 3 caracteres.
     * 
     * parametro input   Scanner para leer datos
     * parametro mensaje Mensaje a mostrar al usuario
     * return El nombre válido ingresado
     */
    private static String validarNombre(Scanner input, String mensaje) {
        String name;
        do {
            name = leerDatos(input, mensaje).trim();
            if (name == null || name.isEmpty() || name.length() <= 3) {
                System.out.println(
                        "El nombre no puede estar vacío y debe tener más de 3 caracteres. Por favor, ingrese un nombre válido.");
            }
        } while (name == null || name.isEmpty() || name.length() <= 3);
        return name;
    }

}
