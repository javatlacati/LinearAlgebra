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
public class MatrixInvertExample {

    public MatrixInvertExample () {
         /*EquationSystem es = new EquationSystem();
        Polynomial p =new Polynomial();
        p.addTerm(new SimpleTerm(3.5, 'x', 2.0));
        p.addConstant ( 4.6);
        es.addPolynomial(p);
        Polynomial p1 =new Polynomial();
        p1.addTerm(new SimpleTerm(5.0, 'x', 2.0)); //FIXME print????
        p1.addTerm(new SimpleTerm(5.63, 'y', 2.0));
        p1.addConstant ( 4);
        es.addPolynomial(p1);
        Polynomial p3 = new Polynomial ();
        p3.addTerm(new SimpleTerm(10.0, 'x', 2.0)); //FIXME print????
        p3.addTerm(new SimpleTerm(3.3, 'y', 2.0));
        p3.addConstant ( 9);
        es.addPolynomial ( p3 );
        es.simplify ();
        Matrix m = new Matrix ( es );
        m.InverseByGaussJordan ();*/
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
        Matrix m= new Matrix ( es );
        m.InverseByGaussJordan ();
    }
    
    public static void main ( String[] args ) {
        MatrixInvertExample mie = new MatrixInvertExample ();
    }
}
