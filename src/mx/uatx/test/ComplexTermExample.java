package mx.uatx.test;

import mx.uatx.algebra.ComplexTerm;
import mx.uatx.algebra.numbers.Complex;


/**
 * @author javatlacati
 *
 */
public class ComplexTermExample{

	public ComplexTermExample() {
		ComplexTerm myTerm = new ComplexTerm('x');
		boolean x = myTerm.equals(new ComplexTerm(1.0,'x',1.0));//be careful not to insert int's
		System.out.println("the term "+myTerm+ " is equal to 1x^1?=>"+x);
                System.out.println("the grade of that is=>"+myTerm.getExponent());
                ComplexTerm ct = new ComplexTerm(7.0, 'x', 2.0);
                System.out.println(ct+" if x=2 "+ct.solve(new Complex(2.0)));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ComplexTermExample example = new ComplexTermExample();
	}

}
