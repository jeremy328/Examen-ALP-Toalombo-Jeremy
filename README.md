# Examen Práctico Integrador – AulaLogic

## Identificación
- **Estudiante:** Apellido Nombre  *(completar)*
- **Asignatura:** Algoritmos y Lógica de Programación – Primer nivel
- **Carrera:** Ingeniería de Software – FISEI, Universidad Técnica de Ambato
- **Variante asignada:** C (promedio mínimo de aprobación = **13**)

## Descripción del problema
Sistema de consola en Java para registrar, consultar, procesar y almacenar las calificaciones
(3 notas por estudiante) de una asignatura. Conserva los datos en `datos/estudiantes.txt`
y genera indicadores del rendimiento del curso.

## Compilación y ejecución
Desde la raíz del repositorio (necesita JDK 11 o superior):

```bash
# Compilar
javac -encoding UTF-8 -d bin src/*.java

# Ejecutar (debe lanzarse desde la raíz para que encuentre datos/estudiantes.txt)
java -cp bin Main
```
En Windows, si las tildes se ven mal en consola, ejecutar antes `chcp 65001`.

## Funcionalidades implementadas
- Registro con validación (código único, sin vacíos ni `;`, notas entre 0 y 20 con reintento).
- Cálculo de promedio (sin división entera), nota máxima, mínima y estado (APROBADO si promedio ≥ 13).
- Listado y búsqueda por código (mensaje si no existe).
- Reporte: totales, aprobados/reprobados, promedio general, % de aprobados, mejor y peor
  promedio, estudiantes bajo el promedio general y listado ordenado de mayor a menor.
- Persistencia con `FileWriter`/`BufferedWriter` y `FileReader`/`BufferedReader`;
  recuperación automática al iniciar; archivo inexistente y líneas inválidas controlados.
- Guardado automático al salir (opción 6). Menú repetitivo con control de opciones incorrectas.

## Estructura de clases
| Clase | Responsabilidad |
|---|---|
| `Estudiante` | Datos del estudiante y cálculos individuales |
| `GestionAcademica` | Lista (`ArrayList`), validaciones, búsqueda, indicadores, ordenamiento |
| `GestorArchivos` | Guardar y recuperar registros, manejo de excepciones de E/S |
| `Main` | Menú, lectura por consola y coordinación |

## Limitaciones
- Ordenamiento por selección (suficiente para el tamaño del problema, no óptimo para miles de registros).
- Si dos estudiantes empatan en máximo o mínimo, se muestra el primero registrado.
- Interfaz solo de consola.
