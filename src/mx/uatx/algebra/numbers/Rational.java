/*
 * Rational.java 
 * 07/10/2008 08:04:42
 */

package mx.uatx.algebra.numbers;

/**
 * This class models the rational numbers, and uses some algorithm approaches that are widely used on real life.
 * It's main purpose is avoid the presition problems inherents to computer cast, and model some teories for future code approaches
 * @author javatlacati
 * @see Rational#Rational(double) 
 * @see Rational#divide(mx.uatx.algebra.numbers.Rational) 
 */
public class Rational {
	private int num;
	private int den;
	
        /**Default constructor, creates the rational 0/1*/
	public Rational() {
		num=0;
		den=1;
	}
	
	/**
         * Constructor for representing intgers as Rationals. The widely common way used is add 1 as denominator
	 * @param num an int number
	 */
	public Rational(int num) {
		this.num = num;
		den=1;
	}



	/**
         * Constructor for creating any Rational, but not the zero denominator ones
	 * @param num numerator
	 * @param den denominator
	 */
	public Rational(int num, int den) {
		this.num = num;
		if(den!=0)
		this.den = den;
		else this.den=1;
	}
	
        /**
         * An easy way to cast double numbers to Rational ones.
         * The algorithm is simple:<BR> <ul><li>if the number is greater than |1| simplify
         * <li>if the number is a fraction, multiply the denominator by 10 until there will be no fractional parts or reached some number and
         * the denominator is put as numerator the denominator is the 10 potence</ul>
         * @param n a double presition number
         */
	public Rational(double n)//TODO create a rational by float number
	{
            int parteEntera=(int)n;
		double parteDecimal=n-(double)parteEntera;
            if(n>=1.0&&n<=-1.0)
            {
		int denom=0;
                if(n>=1.0)
		//buscar el denominador apropiado
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			if(parteDecimal%(double)i==0)
				denom=i;
		}
                else
		for (int i = 0; i > Integer.MIN_VALUE; i--) {
			if(parteDecimal%(double)i==0)
				denom=i;
		}
		//buscar el numerador en base al denominador y asignar al nuevo objeto
		if(denom!=0)
		{
			den=denom;
			num=parteEntera*denom;
		}
		//optional simplify
            }
            else //non greather than 1
            {
                //do it the easy way
                int deno=1;
                double copy=n;
                while(parteDecimal!=0)
                {
                    if(deno==1000000.0) //if the denominator is huge, round and avoid at the same time deal with irational #'s
                        break;
                    n=copy; //no sense the first time if you're performing desktop test
                    deno*=10.0;
                    n*=(double)deno;
                    parteEntera=(int)n;
                    parteDecimal=n-(double)parteEntera;
                }
                den=deno;
                num=parteEntera;
                //done now let's simplify
                simplify();//not really neccessary but make's a nicer Rational
            }
	}
        
        /**
         * This method adds an integer to a Rational. The algorithm is pretty easy 
         * the numerator is the Rationals, and numerator is the integer multiplied 
         * by the Rational denominator.
         * @param constant an integer
         */
        public void add(int constant) {
		int converted=den*constant;
		num+=converted;
	}
        
        public void add(Rational r){
            
        }
        
        /* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
    @Override
	public boolean equals(Object arg0) {
		
			if (arg0 instanceof Rational) {
				Rational new_name = (Rational) arg0;
				if(new_name.toDouble()==toDouble())
					return true;
			}
			return false;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * @return the den
	 */
	public int getDen() {
		return den;
	}
        
        /**
         * The easy way to divide a Rational by another is multiply by th inverse of the Rational
         * @param r Rational number
         * @return Multiplication by the inverse of r
         * @see Rational#multiply(mx.uatx.algebra.numbers.Rational) 
         */
        public Rational divide(Rational r){
            int aux=r.den;
            r.den=r.num;
            r.num=aux;
            return multiply ( r );
        }
        
        /**
         * Multiply numerator by numerator and denominator by denominator
         * @param r Rational
         * @return The Rational multiplied
         */
        public Rational multiply(Rational r) {
		return new Rational(num*r.num,den*r.den);
	}
	
        /**
         * To multiply a number bya constant, lets multiply its numerator by the constant.
         * @param constant 
         */
	public void multiply(int constant) {
		num*=constant;
	}

	/**
	 * @param den the den to set
	 */
	public void setDen(int den) {
		this.den = den;
	}
        
        /**Method that simplifies a Rational finding the Minimal Common Divider*/	
	public void simplify()
	{
		//MCD
		long mcd,temp,resto;
		mcd=Math.abs(num);
		temp=Math.abs(den);
		while (temp>0) {
			resto=mcd%temp;
			mcd=temp;
			temp=resto;
		}
		//simplificar
		if (mcd>1) {
			num/=mcd;
			den/=mcd;
		}
	}
	
        /**
         * @return The casting to double 
         */
	public double toDouble()
	{
		return (double)num/(double)den;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
	public String toString() {
        String cadena="";
            if(num<0||den<0){
            cadena+="-";
            }
		if(den!=1)
		cadena+=Math.abs(num)+"/"+Math.abs(den);
                else
		cadena+= Math.abs(num)+"";
            return cadena;
	}
	
}
