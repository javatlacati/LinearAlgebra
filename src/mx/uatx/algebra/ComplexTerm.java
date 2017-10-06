package mx.uatx.algebra;

import mx.uatx.algebra.numbers.Complex;
import mx.uatx.algebra.numbers.Rational;

/**
 * 
 */


/**
 * @author Ruslan López 'javatlacati'
 * 
 */
public class ComplexTerm {

	/** The coeficient as a in ax */
	private Complex coef;
	private char variable;
	private Complex exponent;

	/**
	 * @param coef
	 * @param variable
	 * @param exponent
	 */
	public ComplexTerm(double coef, char variable, double exponent) {
		this.coef = new Complex(coef);
		this.variable = variable;
		this.exponent = new Complex(exponent);
	}
	
	
	/**
	 * @param variable
	 */
	public ComplexTerm(char variable) {
		coef=new Complex(1.0);
                exponent=new Complex(1.0);
		this.variable = variable;
	}


	/**
	 * @param term -
	 *            {@link SimpleTerm}
	 */
	public ComplexTerm(ComplexTerm term) {
		coef = term.coef;
		variable = term.variable;
		exponent = term.exponent;
	}

    private ComplexTerm( Complex c, char var, Complex c1 ) {
        coef=c;
        variable=var;
        exponent=c1;
    }

	/**
	 * @return the coef
	 */
	public Complex getCoef() {
		return coef;
	}

	/**
	 * @param coef
	 *            the coef to set
	 */
	public void setCoef(double coef) {
		this.coef = new Complex(coef);
	}

	/**
	 * @return the variable
	 */
	public char getVariable() {
		return variable;
	}

	/**
	 * @param variable
	 *            the variable to set
	 */
	public void setVariable(char variable) {
		this.variable = variable;
	}

	/**
	 * @return the exponent
	 */
	public Complex getExponent() {
		return exponent;
	}

	/**
	 * @param exponent
	 *            the exponent to set
	 */
	public void setExponent(double exponent) {
		this.exponent = new Complex(exponent);
	}

	/*
	 *
	 * 
	 * @see java.lang.Object#toString()
	 */
    @Override
	public String toString() {
		String cadena = "";
		if (coef.getReal().toDouble()!=1.0)//||coef.getImaginary().toDouble()==0.0
			cadena += coef + "";
		cadena += variable + "";
		if (exponent.getReal().toDouble()!=1.0)
			cadena += "^" + exponent;
		return cadena;
	}

        /** Method to compare Simple Terms
         * @param t 
         */
	public boolean equals(ComplexTerm t)  {
		return coef.equals(t.coef)  && variable == t.variable && exponent.equals(t.exponent);
	}

    public ComplexTerm add( ComplexTerm complexTerm ) {
        return new ComplexTerm(coef.add(complexTerm.getCoef()), variable, exponent);
    }
    
    public Complex solve(Complex value)
    {
        Complex result;
        Double x=getExponent().getReal().toDouble();
                result=Complex.pow(value, x.intValue());
        result=result.mul(coef);
        return result;
    }

}
