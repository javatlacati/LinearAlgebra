package mx.uatx.algebra;

import java.util.Vector;

public class Polynomial {

    public Vector<SimpleTerm> terms;
    public Vector<Double> constants;

    /***/
    public Polynomial () {
        terms = new Vector<SimpleTerm> ();
        constants = new Vector ();
        constants.add ( 0.0); //perhaps not much useful, but helps on print as ax^b+cy^d=e
    }

    /**
     * If we get an unorderet set of terms, order them an add it to the polynomial
     * @param unorderedTerms Simple terms
     * @see SimpleTerm
     */
    public Polynomial ( Vector<SimpleTerm> unorderedTerms ) {
        terms = new Vector<SimpleTerm> ();
        for ( int i = 0; i < unorderedTerms.size (); i ++ ) {
            addTerm ( unorderedTerms.elementAt ( i ));
        }
        constants = new Vector ();
        constants.add ( 0.0); //perhaps not much useful, but helps on print as ax^b+cy^d=e
    }
    
    
    /**
     * Method that adds a polynomial to another
     * @param p a Polynomial
     * @return a Polynomial
     */
    public Polynomial add ( Polynomial p ) {
        Polynomial polynomial = new Polynomial ();
        //adding terms
        for ( int i = 0; i < terms.size (); i ++ ) {
            polynomial.addTerm ( terms.elementAt ( i ) );
        }
        for ( int j = 0; j < p.terms.size (); j ++ ) {
            polynomial.addTerm ( p.terms.elementAt ( j ) );
        }
        //adding constants
        for ( int i = 0; i < constants.size (); i ++ ) {
            polynomial.addConstant ( constants.elementAt ( i ) );
        }
        for ( int j = 0; j < p.constants.size (); j ++ ) {
            polynomial.addConstant ( p.constants.elementAt ( j ) );
        }
        polynomial.simplify ();
        return polynomial;
    }

    /**
     * @param c The term addeded
     */
    public void addTerm ( SimpleTerm c ) {
        if ( terms.size () == 0 )//there are no elements
        {
            terms.add ( c );
        }
        else {
            char var = c.getVariable ();
            for ( int i = 0; i < terms.size (); i ++ ) {
                if ( terms.elementAt ( i ).getVariable () >= var ) //FIXME Possibly an error if there were two of the same variable, and must compare exponents
                {
                    terms.add ( i, c );
                    return;
                }
                else if ( i + 1 < terms.size () ) {
                    if ( terms.elementAt ( i + 1 ).getVariable () >= var )//FIXME It works only if there were no equal variables
                    {
                        terms.add ( i + 1, c );
                        return;
                    }
                }
                else if ( i + 1 == terms.size () ) {
                    terms.add ( c );//insert at the end
                    return;
                }
            }
        }
    }

    /*  private double[] getCoefs() //possibly better with hash table
    {
    double[] coefs=new double[terms.size()];
    for ( int i = 0; i < terms.size(); i++ ) {
    coefs[i]  =  terms.elementAt(i).getCoef();
    }
    return coefs;
    }*/
    /*public double[] reduceCoefMatrix(int mcd)
    { //by iterative mcd algorithm
    if(mcd>9)
    return null;
    double[] matrix = getCoefs();
    for ( int i = 0; i < matrix.length; i++ ) {
    double d = matrix[i];
    for ( int j = i+1; j < matrix.length; j++ ) {
    double e = matrix[j];
    if(d%mcd!=0&&e%mcd!=0)
    reduceCoefMatrix(mcd+1);
    }
    }
    //if we're here bingo: found the mcd
    for ( int i = 0; i < matrix.length; i++ ) {//divide all matrix elements by our mcd
    matrix[i]/=mcd;
    }
    return matrix;
    }*/
    
    /**
     * @return Polynomial grade as double
     */
    public double getGrade () {
        double max =  Double.MIN_VALUE;
        for ( int i = 0; i < terms.size (); i ++ ) {
            if ( terms.get ( i ).getExponent () > max ) {
                max = terms.get ( i ).getExponent ();
            }
        }
        return max;
    }

    /**Method that reduces the terms and constants*/
    public void simplify () {
        for ( int i = 0; i < terms.size (); i ++ ) {
            SimpleTerm complexTerm = terms.elementAt ( i );
            for ( int j = i + 1; j < terms.size (); j ++ ) {
                if ( complexTerm.getVariable () == terms.elementAt ( j ).getVariable () && complexTerm.getExponent () == terms.elementAt ( j ).getExponent () ) {
                    SimpleTerm ct = terms.elementAt ( j );
                    terms.remove ( i );
                    terms.remove ( j - 1 );
                    addTerm ( new SimpleTerm ( ct.add ( complexTerm ) ) );
                }
            }
        }
        for ( int i = 0; i < constants.size (); i ++ ) { //make that there will be only one constant
            Double constant = constants.elementAt ( i );
            for ( int j = i + 1; j < constants.size (); j ++ ) {
                Double ct = constants.elementAt ( j );
                constants.remove ( i );
                constants.remove ( j - 1 );
                addConstant ( constant + ct );
            }
        }

    }

    @Override
    public String toString () {
        String cadena = "";
        for ( int i = 0; i < terms.size (); i ++ ) { //lets print terms
            if ( i != 0 ) {
                if ( terms.get ( i ).getCoef () > 0.0 ) {
                    cadena += "+" + terms.get ( i );
                }
                else {
                    cadena += "-" + terms.get ( i );
                }
            }
            else {
                cadena += terms.get ( i );
            }
        }
        if ( constants.size () > 0 ) {
            cadena += "=";
            for ( int j = 0; j < constants.size (); j ++ ) {       //lets print constants
                if ( constants.get ( j ).doubleValue () > 0.0 ) {
                    if ( j != 0 ) {
                        cadena += "+";
                    }
                    cadena += constants.get ( j );
                }
                else if (j!=0){
                    cadena += "-" + constants.get ( j );
                }
                else
                    cadena += constants.get ( j );
            }
        }
        return cadena;
    }
      
    /**
     * Solves by sutitution the polynomial.
     * @param values the values for corresponding variables
     * @param variables the variables that actually are known
     * @return the value of Lim polynomial if variables -> values
     */
    public double solve ( double[] values, char[] variables ) {
        simplify (); //let's make things simple
        double ret_val = 0.0;
        if ( values.length == variables.length && values.length == terms.size () ) {
            for ( int i = 0; i < terms.size (); i ++ ) {
                SimpleTerm simpleTerm = terms.elementAt ( i );
                for ( int j = 0; j < variables.length; j ++ ) {//avoid mess with polynomials like 23/10x^-2+7x^-3+y, lets hope so
                    if ( variables[j] == simpleTerm.getVariable () ) {
                        ret_val += simpleTerm.solve ( values[j] );
                        break;
                    }
                }
            //else continue 'cause someone put a variable that actually can't be recognized as te same char in the term
            }
        }
        for ( int j = 0; j < constants.size (); j ++ ) //add the constants
        {
            ret_val += constants.get ( j ).doubleValue ();
        }
        return ret_val;
    }

    /**
     * @param d The double costant
     */
    public void addConstant ( double d ) {
        if ( d != 0.0 ) {
            constants.add ( new Double ( d ) );
        }
    }
}