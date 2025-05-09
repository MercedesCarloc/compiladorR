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
public class AnalizadorSintactico
{

    private List<Token> tokens; // Lista de tokens del analizador léxico
    private int actual = 0;     // Índice del token actual

    public AnalizadorSintactico(List<Token> tokens)
    {
        this.tokens = tokens;
    }

    public void analizar()
    {
        while (!estaAlFinal())
        {
            declaracion();
        }
    }

    // Analiza una declaración
    private void declaracion()
    {
        if (coincide(TipoToken.PRINT))
        {
            declaracionImprimir();
        } else
        {
            throw error("Declaración no valida");
        }
    }

    private void declaracionImprimir()
    {
        consumir(TipoToken.PARENTESIS_DERECHO, "Se esperaba '(' después de 'imprimir'.");
        expresion(); // llama al metodo para analizar expresiones
        consumir(TipoToken.PARENTESIS_DERECHO, "Se esperaba ')' después de la expresion.");
        consumir(TipoToken.PUNTO_Y_COMA, "Se esperaba ';' al final de la declaracion.");
    }

    // Analiza expresiones
    private void expresion()
    {
        termino();
        while (coincide(TipoToken.MAS, TipoToken.MENOS))
        {
            termino(); // Procesa los términos que siguen
        }
    }

    private void termino()
    {
        factor();
        while (coincide(TipoToken.POR, TipoToken.DIVIDIDO))
        {
            factor(); // Procesa multiplicaciones y divisiones
        }
    }

    private void factor()
    {
        if (coincide(TipoToken.NUMERO, TipoToken.IDENTIFICADOR))
        {
            // Si es un número o un identificador avanza
        } else if (coincide(TipoToken.PARENTESIS_IZQUIERDO))
        {
            expresion();
            consumir(TipoToken.PARENTESIS_DERECHO, "Se esperaba ')'.");
        } else
        {
            throw error("Expresion invalida.");
        }
    }

    // Métodos auxiliares para manejar tokens
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
        if (estaAlFinal())
        {
            return false;
        }
        return mirar().tipo == tipo;
    }

    private Token avanzar()
    {
        if (!estaAlFinal())
        {
            actual++;
        }
        return anterior();
    }

    private boolean estaAlFinal()
    {
        return mirar().tipo == TipoToken.EOF;
    }

    private Token mirar()
    {
        return tokens.get(actual);
    }

    private Token anterior()
    {
        return tokens.get(actual - 1);
    }

    private Token consumir(TipoToken tipo, String mensaje)
    {
        if (verificar(tipo))
        {
            return avanzar();
        }
        throw error(mensaje);
    }

    private RuntimeException error(String mensaje)
    {
        return new RuntimeException("Error sintáctico: " + mensaje);
    }
}
