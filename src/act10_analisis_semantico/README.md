# Actividad 10 - Análisis semántico: verificación de tipos

Traductores de Lenguajes I. Esta carpeta (`act10_analisis_semantico`) contiene el trabajo de la
Actividad 10. Conserva todo lo de las actividades anteriores (análisis léxico, sintáctico, métodos y
Tabla de Símbolos) y agrega la **verificación de tipos** en expresiones aritméticas, relacionales y lógicas.

Integrantes: Cervantes Mora Juan Pablo, Muñoz Madriz Nadya Janneth, Ramirez Torres Jesus Adrian.

## Qué se agregó en esta actividad

- Cada expresión devuelve su **tipo** (`int`, `float`, `double`, `char`, `boolean`, `string`).
- Aritmética `+ - * / %`: solo con números; `int` con `float` da `float` (promoción). `+` también concatena cadenas.
- Relacionales `> < >= <=`: solo con números y siempre dan `boolean`. `==` y `!=` comparan tipos iguales o números.
- Lógicos `&& || !`: solo con `boolean` y siempre dan `boolean`.
- Los identificadores se buscan en la **Tabla de Símbolos** (ámbito del método y luego el global) para saber su tipo.
- Las **constantes** (`final` / `const`) se pueden leer, pero no reasignar (ni con `=` ni con `leer`).
- Las asignaciones y las inicializaciones revisan que el tipo del valor sea compatible con el tipo declarado.
- Las condiciones de `si`, `mientras`, `hacer-mientras` y `para` deben ser `boolean`.
- Nuevas declaraciones locales dentro de un método (`int x;`, `float y = 2.5;`).
- Los errores semánticos muestran línea, columna, token y causa, y el análisis **sigue** después de cada error.
- Un símbolo con error (valor incompatible, declarado dos veces, constante sin valor) **no se registra** en la Tabla de Símbolos.
- Los nombres que no siguen PascalCase (clases) o camelCase (atributos y métodos) dan una **advertencia**, no un error, y el símbolo sí se registra.
- Al final se imprime el número de errores y la Tabla de Símbolos.

## Archivos

| Archivo | Para qué sirve |
|---|---|
| `Lexer10.jflex` | Analizador léxico (acepta también `clase`, `const`, `verdadero`, `falso` y comentarios `//` y `/* */`) |
| `parser10.cup` | Gramática con las acciones semánticas |
| `ManejadorTipos.java` | Reglas de compatibilidad de tipos y mensajes de error de tipos |
| `ReportarError.java` | Imprime los errores semánticos y los cuenta |
| `Simbolo.java`, `TablaSimbolos.java` | Tabla de Símbolos |
| `ValidadorSemantico.java` | Convenciones de nombres (PascalCase / camelCase) |
| `GeneradorAct10.java` | Genera `Lexer10.java`, `parser10.java` y `sym.java` |
| `Main.java` | Programa principal |
| `prueba_valida.txt` | Expresiones correctas |
| `prueba_errores.txt` | Los 5 errores semánticos de la práctica |
| `prueba_mixta.txt` | Código correcto e incorrecto en el mismo archivo |
| `prueba_registro.txt` | Advertencias de nombres y símbolos con error que no se registran |

`Lexer10.java`, `parser10.java` y `sym.java` se generan solos; no se editan a mano.

## Cómo compilar y ejecutar

Requisitos: JDK 17 o superior y, dentro de `lib`, los `.jar` de JFlex y de Java CUP (con su runtime).
Todos los comandos se ejecutan desde la raíz del proyecto (PowerShell).

1. Generar el lexer y el parser:

```
javac -encoding UTF-8 -cp "lib/*" -d bin src/act10_analisis_semantico/GeneradorAct10.java
java -cp "bin;lib/*" act10_analisis_semantico.GeneradorAct10
```

2. Compilar todo:

```
javac -encoding UTF-8 -cp "lib/*" -d bin src/act10_analisis_semantico/*.java
```

3. Ejecutar las pruebas:

```
java -cp "bin;lib/*" act10_analisis_semantico.Main prueba_valida.txt
java -cp "bin;lib/*" act10_analisis_semantico.Main prueba_errores.txt
java -cp "bin;lib/*" act10_analisis_semantico.Main prueba_mixta.txt
java -cp "bin;lib/*" act10_analisis_semantico.Main prueba_registro.txt
```

Si cambias algún archivo, vuelve a hacer los pasos 1 y 2.

## Resultado esperado

- `prueba_valida.txt`: ningún error semántico; se ven los tipos resultantes (`int * int -> int`, `int + float -> float`, `boolean && boolean -> boolean`).
- `prueba_errores.txt`: 5 errores semánticos (asignación incompatible, operador con booleano, condición no booleana, constante reasignada, variable no declarada).
- `prueba_mixta.txt`: 5 errores entre sentencias correctas; el análisis llega hasta el final.
- `prueba_registro.txt`: 2 advertencias (nombres) y 4 errores; en la tabla quedan solo `AtributoIncorrecto`, `proceso`, `x` (la primera), `z` y la clase.
