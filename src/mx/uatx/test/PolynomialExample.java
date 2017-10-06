package mx.uatx.test;

import mx.uatx.algebra.Polynomial;
import mx.uatx.algebra.SimpleTerm;

public class PolynomialExample{

    public PolynomialExample() {
        Polynomial p =new Polynomial();
        p.addTerm(new SimpleTerm(7.0, 'x', -2.0));
        p.addTerm(new SimpleTerm(2.3, 'x', -2.0));
        p.addTerm(new SimpleTerm('y'));
        p.addConstant(3.5);
        System.out.println("p="+p);
        System.out.println("p grade=>"+p.getGrade());
        Polynomial p1=new Polynomial();
        p1.addTerm(new SimpleTerm(7.0, 'y', 3.0));
        p1.addTerm(new SimpleTerm('y'));
        System.out.println("p1="+p1);
        System.out.println("p+p1="+p.add(p1));
        double[] val={2.0,3.0};
        char[] vars={'x','y'};
        System.out.println("p if x=2 and y=3 =>"+p.solve(val,vars));
       // System.out.println("p simplified="+p);
    }
    
     public static void main( String[] args ) {
         PolynomialExample example = new PolynomialExample();
    }
}