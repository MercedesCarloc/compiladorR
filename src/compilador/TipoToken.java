/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compilador;

/**
 *
 * @author Pc
 */
public enum TipoToken
{
    // Palabras clave
    PRINT, IF, ELSE,
    // Literales
    NUMERO, CADENA, IDENTIFICADOR,
    // Operadores
    MAS, MENOS, POR, DIVIDIDO,
    MENOR, MAYOR, IGUAL_IGUAL, ASIGNACION,
    // Símbolos
    PARENTESIS_IZQUIERDO, PARENTESIS_DERECHO,
    LLAVE_IZQUIERDA, LLAVE_DERECHA,
    PUNTO_Y_COMA,
    // Fin de archivo
    EOF
}
