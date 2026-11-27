# Análisis del problema

## Entrada
- Opción del menú (entero 1–6).
- Por estudiante: código (String), nombre completo (String), 3 calificaciones (int, 0–20).
- Código a buscar (String).
- Archivo `datos/estudiantes.txt` al iniciar.

## Proceso
1. Validar código (no vacío, sin `;`, no repetido), nombre (no vacío, sin `;`) y notas (0–20, reintentar).
2. Almacenar las notas en `int[]` y los estudiantes en `ArrayList<Estudiante>`.
3. Promedio = suma de notas / 3 usando `double` (evita división entera).
4. Máximo y mínimo mediante recorrido del arreglo.
5. Estado: `promedio >= 13` → APROBADO; en caso contrario REPROBADO.
6. Reporte: contadores (aprobados), acumulador (suma de promedios), búsqueda de mayor/menor,
   filtro `promedio < promedioGeneral`, ordenamiento por selección descendente.
7. Si la lista está vacía no se calcula nada (evita división entre cero).
8. Persistencia: se reescribe todo el archivo en cada guardado (sin duplicados);
   al iniciar se lee línea por línea, se separa con `split(";")` y se convierte con `Integer.parseInt`.

## Salida
- Menú, mensajes de validación y confirmación.
- Ficha del estudiante (código, nombre, notas, promedio, máx., mín., estado).
- Tabla de estudiantes y reporte académico.
- Archivo `datos/estudiantes.txt` con formato `E001;Ana Torres;15;18;12`.

## Diseño de clases
- **Estudiante**: `codigo`, `nombre`, `notas[]`; `calcularPromedio()`, `obtenerNotaMaxima()`,
  `obtenerNotaMinima()`, `obtenerEstado()`.
- **GestionAcademica**: `registrar`, `buscarPorCodigo`, `listar`, `promedioGeneral`,
  `mejorPromedio`, `peorPromedio`, `ordenadosPorPromedio`, `imprimirReporte`.
- **GestorArchivos**: `guardar(lista)`, `cargar()`.
- **Main**: menú con `do-while` y `switch`.

## Prueba de escritorio (E004: 13, 13, 13)
suma = 39 → 39 / 3 = 13.0 → máx = 13, mín = 13 → 13.0 ≥ 13 → **APROBADO** (caso límite).
