
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gabri
 */
public class Expressao {
    
    private char[] expressao;
    
    public Expressao(String s)
    {
        this.expressao = s.replaceAll("\\s","").toCharArray();
    }
    
    
    
}
