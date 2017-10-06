/**
 * 
 */
package mx.uatx.test;

import mx.uatx.algebra.numbers.Rational;

/**
 * @author javatlacati
 *
 */
public class RationalExample {

	public RationalExample() {
		Rational myRational = new Rational(-2,4);
		Rational Racional2=new Rational(1,-2);
		boolean x = myRational.equals(Racional2);
		System.out.println("the term "+myRational.toString()+ " is equal to "+Racional2.toString()+"?=>"+x);
		System.out.println("-1/2 * 5/8 ="+Racional2.multiply(new Rational(5,8)));
		Racional2.simplify();   
		System.out.println("simplified is "+Racional2.toString()+" and it's value "+Racional2.toDouble());
		System.out.println("the casting of -.25 is"+new Rational(-0.25));
                System.out.println("let's cast -1.345 =>"+new Rational(-1.345));
                Rational r=new Rational(1.0);
                System.out.println("r1="+r);
                Rational r1= new Rational ( 0.45673686881502157);
                System.out.println ( "Let's try something harder:"+r1 );
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RationalExample example = new RationalExample();
	}

}
