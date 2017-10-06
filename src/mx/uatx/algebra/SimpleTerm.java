package mx.uatx.algebra;

import mx.uatx.algebra.numbers.Rational;

/**
 * 
 */
/**
 * @author Ruslan López 'javatlacati'
 * 
 */
public class SimpleTerm {

    /** The coeficient as a in ax */
    private Rational coef;
    private char variable;
    private Rational exponent;

    /**
     * @param coef
     * @param variable
     * @param exponent
     */
    public SimpleTerm ( double coef, char variable, double exponent ) {
        this.coef = new Rational ( coef );
        this.variable = variable;
        this.exponent = new Rational ( exponent );
    }

    /**
     * @param variable
     */
    public SimpleTerm ( char variable ) {
        coef = new Rational ( 1 );
        exponent = new Rational ( 1 );
        this.variable = variable;
    }

    /**
     * @param term -
     *            {@link SimpleTerm}
     */
    public SimpleTerm ( SimpleTerm term ) {
        coef = term.coef;
        variable = term.variable;
        exponent = term.exponent;
    }
    //hope you'll take care here
    public SimpleTerm add ( SimpleTerm term ) {
        return new SimpleTerm ( coef.toDouble () + term.getCoef (), variable, exponent.toDouble () );
    }

    /**
     * @return the coef
     */
    public double getCoef () {
        return coef.toDouble ();
    }

    /**
     * @param coef
     *            the coef to set
     */
    public void setCoef ( double coef ) {
        this.coef = new Rational ( coef );
    }

    /**
     * @return the variable
     */
    public char getVariable () {
        return variable;
    }

    /**
     * @param variable
     *            the variable to set
     */
    public void setVariable ( char variable ) {
        this.variable = variable;
    }

    /**
     * @return the exponent
     */
    public double getExponent () {
        return exponent.toDouble ();
    }

    /**
     * @param exponent
     *            the exponent to set
     */
    public void setExponent ( double exponent ) {
        this.exponent = new Rational ( exponent );
    }

    /*
     *
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        String cadena = "";
        if ( coef.toDouble () != 1.0 ) {
            cadena += coef + "";
        }
        cadena += variable + "";
        if ( exponent.toDouble () != 1.0 ) {
            cadena += "^" + exponent;
        }
        return cadena;
    }

    /** Method to compare Simple Terms
     * @param t 
     */
    public boolean equals ( SimpleTerm t ) {
        return coef.toDouble () == t.coef.toDouble () && variable == t.variable && exponent.toDouble () == t.exponent.toDouble ();
    }

    public double solve ( double value ) {
        double result;
        double x = getExponent ();
        result = Math.pow ( value, x );
        result *= getCoef ();
        return result;
    }
}
