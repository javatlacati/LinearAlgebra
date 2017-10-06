package mx.uatx.test;

import mx.uatx.algebra.SimpleTerm;
import mx.uatx.algebra.numbers.Rational;

/**
 * @author javatlacati
 *
 */
public class TermExample{

	public TermExample() {
		SimpleTerm myTerm = new SimpleTerm('x');
		boolean x = myTerm.equals(new SimpleTerm(1,'x',1.0));
		System.out.println("the term "+myTerm+ " is equal to 1x^1?=>"+x);
                SimpleTerm ct = new SimpleTerm(7.0, 'x', 2.0);
                System.out.println(ct+" if x=2 "+ct.solve(2.0));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TermExample example = new TermExample();
	}

}
