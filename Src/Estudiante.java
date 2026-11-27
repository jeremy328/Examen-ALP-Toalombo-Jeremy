/**
 * Representa a un estudiante y su informacion academica.
 */
public class Estudiante {

    // Variante C: promedio minimo de aprobacion. Cambiar segun la variante asignada.
    public static final double LIMITE_APROBACION = 13.0;
    public static final int CANTIDAD_NOTAS = 3;

    private String codigo;
    private String nombre;
    private int[] notas;

    public Estudiante(String codigo, String nombre, int[] notas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.notas = notas;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int[] getNotas() {
        return notas;
    }

    /** Promedio sin division entera: la suma se divide entre un double. */
    public double calcularPromedio() {
        int suma = 0;
        for (int i = 0; i < notas.length; i++) {
            suma += notas[i];
        }
        return (double) suma / notas.length;
    }

    public int obtenerNotaMaxima() {
        int max = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] > max) {
                max = notas[i];
            }
        }
        return max;
    }

    public int obtenerNotaMinima() {
        int min = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] < min) {
                min = notas[i];
            }
        }
        return min;
    }

    /** Promedio igual al limite se considera APROBADO (>=). */
    public String obtenerEstado() {
        if (calcularPromedio() >= LIMITE_APROBACION) {
            return "APROBADO";
        }
        return "REPROBADO";
    }

    /** Formato de archivo: codigo;nombre;n1;n2;n3 */
    public String aLineaArchivo() {
        return codigo + ";" + nombre + ";" + notas[0] + ";" + notas[1] + ";" + notas[2];
    }

    public void mostrar() {
        System.out.println("Codigo   : " + codigo);
        System.out.println("Nombre   : " + nombre);
        System.out.println("Notas    : " + notas[0] + " | " + notas[1] + " | " + notas[2]);
        System.out.printf("Promedio : %.2f%n", calcularPromedio());
        System.out.println("Nota max : " + obtenerNotaMaxima());
        System.out.println("Nota min : " + obtenerNotaMinima());
        System.out.println("Estado   : " + obtenerEstado());
    }
}