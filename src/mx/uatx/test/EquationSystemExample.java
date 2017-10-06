/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.uatx.test;

import mx.uatx.algebra.EquationSystem;
import mx.uatx.algebra.Polynomial;
import mx.uatx.algebra.SimpleTerm;
import mx.uatx.algebra.utils.Matrix;

/**
 *
 * @author javatlacati
 */
public class EquationSystemExample {

    public EquationSystemExample() {
        EquationSystem es = new EquationSystem();
        Polynomial p =new Polynomial();
        p.addTerm(new SimpleTerm(1.0, 'x', 1.0));
        p.addTerm ( new SimpleTerm ( 'y'));
        p.addConstant ( 3.0);
        es.addPolynomial(p);
        Polynomial p1 =new Polynomial();
        p1.addTerm(new SimpleTerm(1.0, 'x', 1.0)); //FIXME print????
        p1.addTerm(new SimpleTerm(2.0, 'y', 1.0));
        p1.addConstant ( 5.0);
        es.addPolynomial(p1);
        es.simplify ();
        System.out.println("Our equation System is: \n"+es);
        System.out.println ( "Contains "+es.getTermsNumber () +" Differing Terms");
     //   System.out.println ( "Contains Constants=>"+es.constants () ); //TODO reform es.constants method
        es.solveGaussJordan ();
        
    }
    
    public static void main(String[] args) {
        EquationSystemExample ese = new EquationSystemExample();
    }
}
