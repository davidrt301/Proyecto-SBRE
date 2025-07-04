import java.util.Scanner;

public class App {

    // Variables estáticas para almacenar los datos del estudiante actual
    private static String nombreEstudiante;
    private static double nota1;
    private static double nota2;
    private static double nota3;

    // Método principal. Inicia el programa.
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
                    5. Limpiar datos del estudiante
                    0. Salir
                    """);

            // Lee la opción del usuario
            var obcion =validacionObciones(input, "Ingrese su opción:");
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
                    validarNota(input, "Ingrese la nota a validar:");
                    break;
                case 5:
                    limpiarDatosEstudiante(input);
                    break;
                case 0:
                    System.out.print("Hasta pronto.");
                    break;
                default:
                    // Opción no válida
                    System.out.print("Opción incorrecta, vuelva e ingrese una opción válida.\n");
                    break;
            }
            System.out.println("\nPresione ENTER para continuar");
            input.nextLine();
            // Si el usuario elige salir, termina el ciclo
            if (obcion == 0) {
                break;
            }
        }
    }

    

    /**
     * Lee un dato del usuario mostrando un mensaje.
     * 
     * parameto input Scanner para leer datos
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
     * Calcula y muestra el promedio de las notas del estudiante actual. Indica si
     * el
     * estudiante aprobó o reprobó.
     * 
     * return El promedio calculado
     */
    private static double calcularPromedioNotasEA() {
        if (nombreEstudiante == null || nombreEstudiante.isEmpty()
                || nota1 == 0.0 && nota2 == 0.0 && nota3 == 0.0) {
            System.out.println("Por favor primero ingrese los datos del estudiante y las notas.");
            return -1;
        }
        var siAoR = "";
        var promedio = (nota1 + nota2 + nota3) / 3;
        System.out.printf("El promedio es %.2f:%n%n", promedio);
        if (promedio <= 60) {
            siAoR = "reprobado";
        } else {
            siAoR = "aprobado";
        }
        System.out.printf("El estudiante %s ha %s con un promedio de %.2f%n",nombreEstudiante, siAoR, promedio);
        return promedio;
    }

    /**
     * Solicita y valida que la nota ingresada sea un número y esté en el rango
     * válido.
     * 
     * parametro input Scanner para leer datos
     * parametro mensaje Mensaje a mostrar al usuario
     * return La nota válida ingresada
     */
    private static double validarNota(Scanner input, String mensaje) {
        double nota;
        do {
            try {
                nota = Double.parseDouble(leerDatos(input, mensaje));
                if (nota >= 0 && nota <= 100) {
                    System.out.println("La nota es válida.");
                    break;
                } else {
                    System.out.println("La nota es inválida. Debe estar entre 0 y 100.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        } while (true);
        return nota;
    }

    /**
     * Solicita y valida que el nombre no esté vacío y tenga más de 3 caracteres.
     * 
     * parametro input Scanner para leer datos
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

    /**
     * Limpia los datos del estudiante actual si el usuario confirma la acción.
     * 
     * parametro input Scanner para leer datos del usuario
     */
    private static void limpiarDatosEstudiante(Scanner input) {
        var obcion = leerDatos(input, "escriba eliminar para eliminar los datos:");
        if (obcion.equals("eliminar")) {
            nombreEstudiante = null;
            nota1 = 0.0;
            nota2 = 0.0;
            nota3 = 0.0;
            System.out.println("Datos eliminados.");
        } else {
            System.out.println("No se eliminaron los datos. Regresando al menú...");
        }
    }

    /**
     * Valida que la opción ingresada por el usuario sea un número dentro del rango permitido.
     * 
     * parametro input Scanner para leer datos del usuario
     * parametro mensaje Mensaje a mostrar al usuario
     * return La opción válida seleccionada por el usuario
     */
    private static int validacionObciones(Scanner input, String mensaje) {
        int obcion = -1;
        while (true) {
            var key = leerDatos(input, mensaje);
            try {
                obcion = Integer.parseInt(key);
                if (obcion >= 0 && obcion <= 5) {
                    break;
                } else {
                    System.out.println("Opción fuera de rango. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
        return obcion;
    }
}
