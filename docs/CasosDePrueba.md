# Casos de prueba – Variante C (límite 13)

Datos base: E001 Ana Torres 15,18,12 · E002 Luis Pérez 8,10,12 · E003 Marta Ruiz 20,18,19 ·
E004 Carlos Mora 13,13,13 · E005 Sofía León 11,14,12

| Caso | Entrada | Resultado esperado | Resultado obtenido | Estado |
|---|---|---|---|---|
| CP01 Registro correcto | Opción 1; E006, Juan Vera, 14, 16, 12 | "Estudiante registrado correctamente"; promedio 14.00 APROBADO | Igual al esperado | ✅ Aprobado |
| CP02 Código duplicado | Opción 1; código E001 | "Error: el codigo ya esta registrado." sin crear el registro | Igual al esperado | ✅ Aprobado |
| CP03 Nota fuera de rango | Notas 25, -3, abc y luego 14 | Rechaza cada valor y vuelve a pedir la nota hasta recibir una válida | Igual al esperado | ✅ Aprobado |
| CP04 Promedio = límite | E004 con 13, 13, 13 | Promedio 13.00 y estado APROBADO | Promedio 13.00, APROBADO | ✅ Aprobado |
| CP05 Búsqueda existente | Opción 3; E004 | Muestra código, nombre, notas, promedio, máx., mín. y estado | Igual al esperado | ✅ Aprobado |
| CP06 Búsqueda inexistente | Opción 3; E999 | "No existe un estudiante con el codigo E999." | Igual al esperado | ✅ Aprobado |
| CP07 Reporte vacío | Sin registros; opción 4 | Mensaje informativo, sin error ni división entre cero | Igual al esperado | ✅ Aprobado |
| CP08 Guardado y recuperación | Registrar, opción 5 (o 6), cerrar y reiniciar | Al iniciar recupera los 6 registros; el archivo no duplica líneas | "Registros recuperados: 6"; archivo con 6 líneas | ✅ Aprobado |
| Extra: opción inválida | Opciones `x` y `9` | "Opcion incorrecta", el programa continúa | Igual al esperado | ✅ Aprobado |
