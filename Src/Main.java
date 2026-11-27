import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        GestionAcademica gestion = new GestionAcademica();
        GestorArchivos archivos = new GestorArchivos("datos/estudiantes.txt");

        // Recuperacion automatica al iniciar
        gestion.setEstudiantes(archivos.cargar());
        System.out.println("Registros recuperados: " + gestion.getEstudiantes().size());

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero();
            System.out.println();
            switch (opcion) {
                case 1:
                    registrarEstudiante(gestion);
                    break;
                case 2:
                    gestion.listar();
                    break;
                case 3:
                    buscarEstudiante(gestion);
                    break;
                case 4:
                    gestion.imprimirReporte();
                    break;
                case 5:
                    if (archivos.guardar(gestion.getEstudiantes())) {
                        System.out.println("Registros guardados correctamente.");
                    }
                    break;
                case 6:
                    if (archivos.guardar(gestion.getEstudiantes())) {
                        System.out.println("Registros guardados. Hasta pronto.");
                    }
                    break;
                default:
                    System.out.println("Opcion incorrecta. Elija un numero del 1 al 6.");
            }
            System.out.println();
        } while (opcion != 6);
    }

    private static void mostrarMenu() {
        System.out.println("=====================================");
        System.out.println(" SISTEMA AULALOGIC");
        System.out.println("=====================================");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Listar estudiantes");
        System.out.println("3. Buscar estudiante");
        System.out.println("4. Generar reporte academico");
        System.out.println("5. Guardar registros");
        System.out.println("6. Salir");
        System.out.println("=====================================");
        System.out.print("Seleccione una opcion: ");
    }

    /** Lee un entero; si el texto no es numerico retorna -1 (opcion invalida). */
    private static int leerEntero() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    private static void registrarEstudiante(GestionAcademica gestion) {
        String codigo;
        while (true) {
            System.out.print("Codigo: ");
            codigo = sc.nextLine().trim();
            if (!gestion.textoValido(codigo)) {
                System.out.println("Codigo invalido: no puede estar vacio ni contener ';'.");
            } else if (gestion.existeCodigo(codigo)) {
                System.out.println("Error: el codigo ya esta registrado.");
                return; // CP02
            } else {
                break;
            }
        }

        String nombre;
        do {
            System.out.print("Nombre completo: ");
            nombre = sc.nextLine().trim();
            if (!gestion.textoValido(nombre)) {
                System.out.println("Nombre invalido: no puede estar vacio ni contener ';'.");
            }
        } while (!gestion.textoValido(nombre));

        int[] notas = new int[Estudiante.CANTIDAD_NOTAS];
        for (int i = 0; i < notas.length; i++) {
            int nota;
            boolean valida;
            do {
                System.out.print("Calificacion " + (i + 1) + " (0-20): ");
                try {
                    nota = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException ex) {
                    nota = -1;
                }
                valida = gestion.notaValida(nota);
                if (!valida) {
                    System.out.println("Calificacion invalida. Debe ser un entero entre 0 y 20.");
                }
            } while (!valida);
            notas[i] = nota;
        }

        if (gestion.registrar(codigo, nombre, notas)) {
            System.out.println("Estudiante registrado correctamente.");
        }
    }

    private static void buscarEstudiante(GestionAcademica gestion) {
        System.out.print("Codigo a buscar: ");
        String codigo = sc.nextLine().trim();
        Estudiante e = gestion.buscarPorCodigo(codigo);
        if (e == null) {
            System.out.println("No existe un estudiante con el codigo " + codigo + ".");
        } else {
            e.mostrar();
        }
    }
}