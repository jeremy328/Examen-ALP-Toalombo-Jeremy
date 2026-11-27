import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Guarda y recupera los estudiantes desde datos/estudiantes.txt.
 */
public class GestorArchivos {

    private String ruta;

    public GestorArchivos(String ruta) {
        this.ruta = ruta;
    }

    /** Sobrescribe el archivo completo, por lo que nunca duplica registros. */
    public boolean guardar(ArrayList<Estudiante> estudiantes) {
        File archivo = new File(ruta);
        File carpeta = archivo.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, StandardCharsets.UTF_8, false))) {
            for (Estudiante e : estudiantes) {
                bw.write(e.aLineaArchivo());
                bw.newLine();
            }
            return true;
        } catch (IOException ex) {
            System.out.println("Error al guardar el archivo: " + ex.getMessage());
            return false;
        }
    }

    /** Lee el archivo. Si no existe, retorna una lista vacia. */
    public ArrayList<Estudiante> cargar() {
        ArrayList<Estudiante> lista = new ArrayList<>();
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            System.out.println("Archivo de datos no encontrado. Se iniciara con la lista vacia.");
            return lista;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivo, StandardCharsets.UTF_8))) {
            String linea;
            int numeroLinea = 0;
            while ((linea = br.readLine()) != null) {
                numeroLinea++;
                if (linea.trim().isEmpty()) {
                    continue;
                }
                Estudiante e = convertirLinea(linea);
                if (e == null) {
                    System.out.println("Linea " + numeroLinea + " invalida, se omite: " + linea);
                } else if (existe(lista, e.getCodigo())) {
                    System.out.println("Linea " + numeroLinea + " con codigo repetido, se omite: " + linea);
                } else {
                    lista.add(e);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo: " + ex.getMessage());
        }
        return lista;
    }

    /** Convierte "E001;Ana;15;18;12" en un Estudiante. Retorna null si el formato es incorrecto. */
    private Estudiante convertirLinea(String linea) {
        String[] partes = linea.split(";");
        if (partes.length != 5) {
            return null;
        }
        try {
            int[] notas = new int[3];
            for (int i = 0; i < 3; i++) {
                notas[i] = Integer.parseInt(partes[2 + i].trim());
                if (notas[i] < 0 || notas[i] > 20) {
                    return null;
                }
            }
            String codigo = partes[0].trim();
            String nombre = partes[1].trim();
            if (codigo.isEmpty() || nombre.isEmpty()) {
                return null;
            }
            return new Estudiante(codigo, nombre, notas);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private boolean existe(ArrayList<Estudiante> lista, String codigo) {
        for (Estudiante e : lista) {
            if (e.getCodigo().equalsIgnoreCase(codigo)) {
                return true;
            }
        }
        return false;
    }
}