/**
 * 
 */
package mx.uatx.test;

import mx.uatx.algebra.numbers.Complex;
import mx.uatx.algebra.numbers.Rational;

/**
 * @author javatlacati
 *
 */
public class ComplexExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Complex c = new Complex(3.0, 2.0);
                System.out.println("c en forma binómica=>"+c.toBinomStr());
                System.out.println("c=>"+c);
                Complex c1 = new Complex(6.7, 0.0);
                System.out.println("c1=>"+c1);
                System.out.println("c+c1=>"+c.add(c1));
                System.out.println("c*c1=>"+c.mul(c1));
                System.out.println("c/c1=>"+c.times(c1));
                 System.out.println("c modulus =>"+c.abs());
                System.out.println("c argument =>"+c.argument());
                Complex c2=new Complex(6.7);
                System.out.println("c2=>"+c2);
                System.out.println("c1=6+4i? "+c1.equals(c2));
                Complex c3= new Complex(new Rational(0), new Rational(2));
                System.out.println(c3);
                c3=c3.mul(new Complex(2.0));
                System.out.println("(2*i)4=>"+Complex.pow(c3, 4));
                System.out.println(""+Math.pow(2.0, 4.0));
                Complex c4= new Complex(-3.0, -5.0);
                Complex c5= new Complex(4.0, -7.0);
                System.out.println(c4+"/"+c5+"="+c4.times(c5));
	}

}
