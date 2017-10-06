package mx.uatx.algebra;

import java.util.HashMap;
import java.util.Vector;
import mx.uatx.algebra.utils.Matrix;

public class EquationSystem {

    public Vector<Polynomial> polynomials; //TODO work with es balancing
//    public Vector<Polynomial> eqPoly;
    
    public EquationSystem () {
        polynomials = new Vector<Polynomial> ();
    }

    public EquationSystem ( Vector<Polynomial> polynomials ) {
        this.polynomials = polynomials;
    }

    public void addPolynomial ( Polynomial p ) {
        polynomials.add ( p );
    }
    
    /* not useful since all ES's have 0 at constructor
    public boolean constants()
    {
        Polynomial poly;
        for ( int i = 0; i < polynomials.size (); i ++ ) {
            poly = polynomials.elementAt ( i );
            if(poly.terms.size ()>0)
                return true;
        }
        return false;
    }*/
    
     public Vector<SimpleTerm> getAllTerms(){ //TODO Order implementing comparable
        Vector<SimpleTerm> allTerms = new Vector<SimpleTerm> ();
        Polynomial poly;
        for ( int i = 0; i < polynomials.size (); i ++ ) {
            poly = polynomials.elementAt ( i );
            for ( int j = 0; j < poly.terms.size (); j ++ ) {
                SimpleTerm simpleTerm = poly.terms.elementAt ( j );
                allTerms.add ( simpleTerm );
            }
        }
        return allTerms;
    }
    
    public Vector<SimpleTerm> getDifferingTerms(){ //TODO Order implementing comparable
        Vector<SimpleTerm> allTerms = new Vector<SimpleTerm> ();
        Polynomial poly;
        for ( int i = 0; i < polynomials.size (); i ++ ) {
            poly = polynomials.elementAt ( i );
            for ( int j = 0; j < poly.terms.size (); j ++ ) {
                SimpleTerm simpleTerm = poly.terms.elementAt ( j );
                if ( allTerms.size () == 0 ) {
                    allTerms.add ( simpleTerm );
                }
                else {
                    double exp = simpleTerm.getExponent ();
                    char var = simpleTerm.getVariable ();
                    for ( int k = 0; k < allTerms.size (); k ++ ) {
                        SimpleTerm simpleTerm1 = allTerms.elementAt ( k );
                        double exp1 = simpleTerm1.getExponent ();
                        char var1 = simpleTerm1.getVariable ();
                        if ( var != var1 | exp != exp1 ) {
                            allTerms.add ( simpleTerm );
                            //if(simpleTerm>simpleTerm1)return;
                        }
                    }
                }

            }
        }
        return allTerms;
    }
    
    public int getTermsNumber () {
        HashMap<Character,Double> h = new HashMap<Character, Double> ();
        Polynomial poly;
        for ( int i = 0; i < polynomials.size (); i ++ ) {
            poly = polynomials.elementAt ( i );
            for ( int j = 0; j < poly.terms.size (); j ++ ) {
                SimpleTerm simpleTerm = poly.terms.elementAt ( j );
                if(h.size ()==0)
                    h.put ( simpleTerm.getVariable (), simpleTerm.getExponent ());
               else 
               {
                double exp = simpleTerm.getExponent ();
                char var = simpleTerm.getVariable ();
                if(!h.containsKey ( var ))    
               //     if(!h.get ( var ).equals ( exp))
                        h.put ( var, exp );
                }
            }
        }
        return h.size ();
    }

    /*public int getTermsNumber () {
        Vector<SimpleTerm> allTerms = new Vector<SimpleTerm> ();
        Polynomial poly;
        for ( int i = 0; i < polynomials.size (); i ++ ) {
            poly = polynomials.elementAt ( i );
            for ( int j = 0; j < poly.terms.size (); j ++ ) {
                SimpleTerm simpleTerm = poly.terms.elementAt ( j );
                if ( allTerms.size () == 0 ) {
                    allTerms.add ( simpleTerm );
                }
                else 
                {
                    double exp = simpleTerm.getExponent ();
                    char var = simpleTerm.getVariable ();
                    
                    
                    for ( int k = 0; k < allTerms.size (); k ++ ) {
                        SimpleTerm simpleTerm1 = allTerms.elementAt ( k );
                        double exp1 = simpleTerm1.getExponent ();
                        char var1 = simpleTerm1.getVariable (); //FIXME
                        if ( var != var1 ) {
                            //if(exp != exp1)
                            allTerms.add ( simpleTerm );
                        }
                        else
                            break;
                    }
                    
                }

            }
        }
        return allTerms.size ();
    }*/

    public int getPolynomialsNumber () {
        return polynomials.size ();
    }

    /**
     * @return all variables in inner polynomials
     */
    public char[] getVars () {
        throw new UnsupportedOperationException ( "Not yet implemented" );
    }

    public void simplify () {
        for ( int i = 0; i < polynomials.size (); i ++ ) {
            polynomials.elementAt ( i ).simplify ();
        }
    }
    
    //    public void bruteForceSolve()
    
   public void solveGaussJordan()
   {
       //separateVariables();
       
       Matrix m = new Matrix ( this ); //TODO Implement self solving On Matrix
       System.out.println ( "The matrix is=>"+m);
       m.solveByGaussJordan ();
   }
   
   

    @Override
    public String toString () { 
        String polys = "";
        for ( int i = 0; i < polynomials.size (); i ++ ) {
            polys += polynomials.elementAt ( i ) + "\n";
        }
        return polys;
    }
}