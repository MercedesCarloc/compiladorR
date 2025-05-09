/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compilador;

/**
 *
 * @author Pc
 */
public class Token
{

    public final TipoToken tipo;     // Tipo del token
    public final String lexema;      // Texto original del token
    public final Object literal;     // Valor literal (si aplica)
    public final int linea;          // Línea donde aparece el token

    public Token(TipoToken tipo, String lexema, Object literal, int linea)
    {
        this.tipo = tipo;
        this.lexema = lexema;
        this.literal = literal;
        this.linea = linea;
    }

    @Override
    public String toString()
    {
        return tipo + " " + lexema + " " + (literal != null ? literal : "") + " [linea: " + linea + "]";
    }
}
