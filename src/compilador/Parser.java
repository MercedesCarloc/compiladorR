/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compilador;

import java.util.List;

/**
 *
 * @author Pc
 */
public class Parser
{

    private final List<Token> tokens;  // Lista de tokens generada por el lexer
    private int posicionActual = 0;    // Posición del token actual en la lista

    public Parser(List<Token> tokens)
    {
        this.tokens = tokens;
    }

    // Método principal para iniciar el análisis
    public void parse()
    {
        while (!estaEnElFinal())
        {
            ejecutarDeclaracion();
        }
    }

    // Método para procesar una declaración y ejecutar la acción
    private void ejecutarDeclaracion()
    {
        if (coincide(TipoToken.PRINT))
        {
            ejecutarPrint();
        } else if (coincide(TipoToken.IDENTIFICADOR))
        {
            ejecutarAsignacion();
        } else
        {
            throw error("Se esperaba una declaración válida.");
        }
    }

    // Método para procesar y ejecutar una declaración "print"
    private void ejecutarPrint()
    {
        consumir(TipoToken.PARENTESIS_IZQUIERDO, "Se esperaba '(' después de 'print'.");
        Object valor = evaluarExpresion();
        consumir(TipoToken.PARENTESIS_DERECHO, "Se esperaba ')' después de la expresión.");
        consumir(TipoToken.PUNTO_Y_COMA, "Se esperaba ';' al final de la declaración.");

        // Ejecutamos el comando print (imprimimos el valor)
        System.out.println(valor);
    }

    // Método para procesar y ejecutar una declaración de asignación
    private void ejecutarAsignacion()
    {
        Token identificador = anterior();
        consumir(TipoToken.ASIGNACION, "Se esperaba '<-' después del identificador.");
        Object valor = evaluarExpresion();
        consumir(TipoToken.PUNTO_Y_COMA, "Se esperaba ';' al final de la declaración.");

        // Aquí puedes agregar la lógica para almacenar la variable si lo necesitas
        // Por ejemplo, en una tabla de símbolos
        System.out.println("Asignación: " + identificador.lexema + " = " + valor);
    }

    // Método para evaluar una expresión
    private Object evaluarExpresion()
    {
        Object izquierda = evaluarTermino();

        while (coincide(TipoToken.MAS, TipoToken.MENOS))
        {
            Token operador = anterior();
            Object derecha = evaluarTermino();
            izquierda = calcularOperacion(izquierda, operador, derecha);
        }

        return izquierda;
    }

    // Método para evaluar un término (números, variables, etc.)
    private Object evaluarTermino()
    {
        if (coincide(TipoToken.NUMERO))
        {
            return anterior().literal;  // Devuelve el valor literal del número
        }

        if (coincide(TipoToken.IDENTIFICADOR))
        {
            // Aquí podrías agregar la lógica para obtener el valor de una variable
            return anterior().lexema;  // Simplemente lo devolvemos como ejemplo
        }

        if (coincide(TipoToken.PARENTESIS_IZQUIERDO))
        {
            Object valor = evaluarExpresion();
            consumir(TipoToken.PARENTESIS_DERECHO, "Se esperaba ')'.");
            return valor;
        }

        throw error("Se esperaba un valor o expresión válida.");
    }

    // Método para realizar la operación de suma o resta
    private Object calcularOperacion(Object izquierda, Token operador, Object derecha)
    {
        if (operador.tipo == TipoToken.MAS)
        {
            return (int) izquierda + (int) derecha;
        } else if (operador.tipo == TipoToken.MENOS)
        {
            return (int) izquierda - (int) derecha;
        }
        throw error("Operador no soportado.");
    }

    // Métodos auxiliares
    private boolean coincide(TipoToken... tipos)
    {
        for (TipoToken tipo : tipos)
        {
            if (verificar(tipo))
            {
                avanzar();
                return true;
            }
        }
        return false;
    }

    private boolean verificar(TipoToken tipo)
    {
        if (estaEnElFinal())
        {
            return false;
        }
        return tokens.get(posicionActual).tipo == tipo;
    }

    private Token avanzar()
    {
        if (!estaEnElFinal())
        {
            posicionActual++;
        }
        return anterior();
    }

    private boolean estaEnElFinal()
    {
        return tokens.get(posicionActual).tipo == TipoToken.EOF;
    }

    private Token anterior()
    {
        return tokens.get(posicionActual - 1);
    }

    private Token consumir(TipoToken tipoEsperado, String mensajeError)
    {
        if (verificar(tipoEsperado))
        {
            return avanzar();
        }
        throw error(mensajeError);
    }

    private RuntimeException error(String mensaje)
    {
        return new RuntimeException("Error sintáctico: " + mensaje);
    }
}
