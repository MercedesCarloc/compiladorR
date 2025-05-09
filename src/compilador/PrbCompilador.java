/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package compilador;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Pc
 */
public class PrbCompilador
{

    public static void main(String[] args)
    {
        // Lista de tokens simulada
        List<Token> tokens = Arrays.asList(
                new Token(TipoToken.PRINT, "print", null, 1),
                new Token(TipoToken.PARENTESIS_IZQUIERDO, "(", null, 1),
                new Token(TipoToken.NUMERO, "42", 42, 1),
                new Token(TipoToken.PARENTESIS_DERECHO, ")", null, 1),
                new Token(TipoToken.PUNTO_Y_COMA, ";", null, 1),
                new Token(TipoToken.EOF, "", null, 2)
        );

        for (Token token : tokens)
        {
            System.out.println(token);
        }
//        List<Token> tokens = Arrays.asList(
//                new Token(TipoToken.PRINT, "print", null, 1),
//                new Token(TipoToken.PARENTESIS_IZQUIERDO, "(", null, 1),
//                new Token(TipoToken.NUMERO, "5", 5, 1),
//                new Token(TipoToken.MAS, "+", null, 1),
//                new Token(TipoToken.NUMERO, "3", 3, 1),
//                new Token(TipoToken.PARENTESIS_DERECHO, ")", null, 1),
//                new Token(TipoToken.PUNTO_Y_COMA, ";", null, 1),
//                new Token(TipoToken.EOF, "", null, 2)
//        );
//
//        Parser parser = new Parser(tokens);
//        parser.parse();
    }
}
