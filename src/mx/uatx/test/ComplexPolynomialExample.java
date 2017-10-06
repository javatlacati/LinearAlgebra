package mx.uatx.test;

import mx.uatx.algebra.ComplexPolynomial;
import mx.uatx.algebra.ComplexTerm;

public class ComplexPolynomialExample{

    public ComplexPolynomialExample() {
        ComplexPolynomial p =new ComplexPolynomial();
        p.addTerm(new ComplexTerm(7.0, 'x', -2.0));
        p.addTerm(new ComplexTerm(2.3, 'x', -2.0));
        p.addTerm(new ComplexTerm('y'));
        System.out.println("p="+p);
        p.simplify();
        System.out.println("p simplified="+p);
        System.out.println("p grade=>"+p.getGrade());
        ComplexPolynomial p1=new ComplexPolynomial();
        p1.addTerm(new ComplexTerm(7.0, 'y', 3.0));
        p1.addTerm(new ComplexTerm('y'));
        System.out.println("p1="+p1);
        System.out.println("p+p1="+p.add(p1));
    }
    
     public static void main( String[] args ) {
         ComplexPolynomialExample example = new ComplexPolynomialExample();
    }
}