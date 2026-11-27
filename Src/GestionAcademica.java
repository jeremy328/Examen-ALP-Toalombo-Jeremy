import java.util.ArrayList;

/**
 * Administra la lista de estudiantes y genera los indicadores academicos.
 */
public class GestionAcademica {

    private ArrayList<Estudiante> estudiantes;

    public GestionAcademica() {
        estudiantes = new ArrayList<>();
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    /** Reemplaza la lista (usado al recuperar desde archivo). */
    public void setEstudiantes(ArrayList<Estudiante> lista) {
        this.estudiantes = lista;
    }

    public boolean existeCodigo(String codigo) {
        return buscarPorCodigo(codigo) != null;
    }

    /** Valida que el texto no este vacio ni contenga punto y coma. */
    public boolean textoValido(String texto) {
        return texto != null && !texto.trim().isEmpty() && !texto.contains(";");
    }

    public boolean notaValida(int nota) {
        return nota >= 0 && nota <= 20;
    }

    /** Registra al estudiante. Retorna false si el codigo ya existe. */
    public boolean registrar(String codigo, String nombre, int[] notas) {
        if (existeCodigo(codigo)) {
            return false;
        }
        estudiantes.add(new Estudiante(codigo, nombre, notas));
        return true;
    }

    public Estudiante buscarPorCodigo(String codigo) {
        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                return estudiantes.get(i);
            }
        }
        return null;
    }

    public void listar() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        System.out.printf("%-8s %-25s %4s %4s %4s %9s %-10s%n",
                "CODIGO", "NOMBRE", "N1", "N2", "N3", "PROMEDIO", "ESTADO");
        for (Estudiante e : estudiantes) {
            int[] n = e.getNotas();
            System.out.printf("%-8s %-25s %4d %4d %4d %9.2f %-10s%n",
                    e.getCodigo(), e.getNombre(), n[0], n[1], n[2],
                    e.calcularPromedio(), e.obtenerEstado());
        }
    }

    public int contarAprobados() {
        int contador = 0;
        for (Estudiante e : estudiantes) {
            if (e.obtenerEstado().equals("APROBADO")) {
                contador++;
            }
        }
        return contador;
    }

    public double promedioGeneral() {
        if (estudiantes.isEmpty()) {
            return 0; // evita division entre cero
        }
        double acumulador = 0;
        for (Estudiante e : estudiantes) {
            acumulador += e.calcularPromedio();
        }
        return acumulador / estudiantes.size();
    }

    /** Estudiante con mayor promedio (en empate, el primero registrado). */
    public Estudiante mejorPromedio() {
        if (estudiantes.isEmpty()) return null;
        Estudiante mejor = estudiantes.get(0);
        for (int i = 1; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).calcularPromedio() > mejor.calcularPromedio()) {
                mejor = estudiantes.get(i);
            }
        }
        return mejor;
    }

    /** Estudiante con menor promedio (en empate, el primero registrado). */
    public Estudiante peorPromedio() {
        if (estudiantes.isEmpty()) return null;
        Estudiante peor = estudiantes.get(0);
        for (int i = 1; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).calcularPromedio() < peor.calcularPromedio()) {
                peor = estudiantes.get(i);
            }
        }
        return peor;
    }

    /** Copia de la lista ordenada de mayor a menor promedio (ordenamiento por seleccion). */
    public ArrayList<Estudiante> ordenadosPorPromedio() {
        ArrayList<Estudiante> copia = new ArrayList<>(estudiantes);
        for (int i = 0; i < copia.size() - 1; i++) {
            int posMayor = i;
            for (int j = i + 1; j < copia.size(); j++) {
                if (copia.get(j).calcularPromedio() > copia.get(posMayor).calcularPromedio()) {
                    posMayor = j;
                }
            }
            Estudiante temp = copia.get(i);
            copia.set(i, copia.get(posMayor));
            copia.set(posMayor, temp);
        }
        return copia;
    }

    public void imprimirReporte() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados. No es posible generar el reporte.");
            return;
        }
        int total = estudiantes.size();
        int aprobados = contarAprobados();
        int reprobados = total - aprobados;
        double general = promedioGeneral();
        double porcentaje = (aprobados * 100.0) / total;

        System.out.println("------------- REPORTE ACADEMICO -------------");
        System.out.println("Limite de aprobacion : " + Estudiante.LIMITE_APROBACION);
        System.out.println("Total de estudiantes : " + total);
        System.out.println("Aprobados            : " + aprobados);
        System.out.println("Reprobados           : " + reprobados);
        System.out.printf("Promedio general     : %.2f%n", general);
        System.out.printf("Porcentaje aprobados : %.2f%%%n", porcentaje);

        Estudiante mejor = mejorPromedio();
        Estudiante peor = peorPromedio();
        System.out.printf("Promedio mas alto    : %s (%.2f)%n", mejor.getNombre(), mejor.calcularPromedio());
        System.out.printf("Promedio mas bajo    : %s (%.2f)%n", peor.getNombre(), peor.calcularPromedio());

        System.out.println("\nEstudiantes con promedio inferior al general:");
        boolean hay = false;
        for (Estudiante e : estudiantes) {
            if (e.calcularPromedio() < general) {
                System.out.printf("  - %s %s (%.2f)%n", e.getCodigo(), e.getNombre(), e.calcularPromedio());
                hay = true;
            }
        }
        if (!hay) {
            System.out.println("  (ninguno)");
        }

        System.out.println("\nListado ordenado de mayor a menor promedio:");
        int pos = 1;
        for (Estudiante e : ordenadosPorPromedio()) {
            System.out.printf("  %d. %s %s - %.2f - %s%n", pos++, e.getCodigo(),
                    e.getNombre(), e.calcularPromedio(), e.obtenerEstado());
        }
        System.out.println("---------------------------------------------");
    }
}