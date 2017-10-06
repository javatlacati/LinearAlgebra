/**
 * 
 */
package mx.uatx.algebra.numbers;

/**
 * @author javatlacati
 *
 */
public class Complex {

    private Rational real;
    private Rational imaginary;

    public Complex() {
        real = new Rational();
        imaginary = new Rational();
    }

    /**
     * @param realPart 
     */
    public Complex( double realPart ) {
        this.real = new Rational(realPart);
        imaginary = new Rational();
    }

    public Complex( Rational real ) {
        this.real = real;
        imaginary = new Rational();
    }

    /**
     * @param real
     * @param complex
     */
    public Complex( Double real, Double complex ) {
        this.real = new Rational(real);
        this.imaginary = new Rational(complex);
    }

    public Complex( Rational real, Rational imaginary ) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * @return Absolute value or modulus
     */
    public double abs() {
        return Math.sqrt(Math.pow(real.toDouble(), 2) + Math.pow(imaginary.toDouble(), 2));
    }
    /*  
    public Rational abs() {
    return new Rational(Math.sqrt(Math.pow(real.toDouble(), 2) + Math.pow(imaginary.toDouble(), 2)));
    }
     */

    public Complex add( Complex c ) {
        return new Complex(c.real.toDouble() + real.toDouble(), c.imaginary.toDouble() + imaginary.toDouble());
    }

    /**
     * 
     * @return Alpha angle of complex number also called argument
     */
    public double argument() {
        return Math.atan(imaginary.toDouble() / real.toDouble());
    }

    @Override
    public boolean equals( Object arg0 ) {
        if ( arg0 instanceof Complex ) {
            Complex new_name = ( Complex ) arg0;
            if ( new_name.real.toDouble() == real.toDouble() && new_name.imaginary.toDouble() == imaginary.toDouble() ) {
                return true;
            }
        }
        return false;
    }
    /*
    public Rational argument() {
    return new Rational(Math.atan(imaginary.toDouble() / real.toDouble()));
    }
     */

    public Rational getImaginary() {
        return imaginary;
    }

    public Rational getReal() {
        return real;
    }

    public Complex mul( Complex c ) {
        return new Complex(real.toDouble() * c.real.toDouble() - imaginary.toDouble() * c.imaginary.toDouble(), real.toDouble() * c.imaginary.toDouble() + imaginary.toDouble() * c.real.toDouble());
    }

    /**
     * @param imaginary the imaginary to set
     */
    public void setImaginary( Double imaginary ) {
        this.imaginary = new Rational(imaginary);
    }

    /**
     * @param real the real to set
     */
    public void setReal( Double real ) {
        this.real = new Rational(real);
    }

    public Complex times( Complex c ) {
        return new Complex((real.toDouble() * c.real.toDouble()) / (2 * c.real.toDouble() + 2 * c.imaginary.toDouble()), (imaginary.toDouble() * c.real.toDouble()) / (2 * c.real.toDouble() + 2 * c.imaginary.toDouble()));
    }

    public static Complex pow( Complex c, int n ) {
        Complex x = c;
        for ( int i = 0; i < n-3; i++ ) {
            x = x.mul(c);
        }
        return x;
    }

    public String toBinomStr() {
        return "(" + real + "," + imaginary + ")";
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        if ( imaginary.toDouble() != 0.0 ) {
            return real + "+" + imaginary + "i";
        }
        return real + "";
    }
}
