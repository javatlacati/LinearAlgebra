package mx.uatx.algebra;

import java.util.Vector;
import mx.uatx.algebra.numbers.Complex;
import mx.uatx.algebra.numbers.Rational;

/**@version 0.9 - 18/09/2008 21:03:42*/
public class ComplexPolynomial{
    public Vector<ComplexTerm> terms;

    public  ComplexPolynomial() {
        terms=new Vector<ComplexTerm>();
    }

    public ComplexPolynomial add(ComplexPolynomial p)
    {
        ComplexPolynomial polynomial=new ComplexPolynomial();
        for ( int i = 0; i < terms.size(); i++ ) {
            ComplexTerm complexTerm = terms.elementAt(i);
            polynomial.addTerm(complexTerm);
        }
        for ( int j = 0; j < p.terms.size(); j++ ) {
                ComplexTerm complexTerm1 = p.terms.elementAt(j);
                polynomial.addTerm(complexTerm1);
            }
        polynomial.simplify();
        return polynomial;
    }

    public void addTerm( ComplexTerm c ) {
        if(terms.size()==0)//there are no elements
         terms.add(c);
        else
        {
            char var=c.getVariable();
        for ( int i = 0; i < terms.size(); i++ ) {
            if(terms.elementAt(i).getVariable()>=var)
            {
                terms.add(i, c);
                return;
            }
            else if(i+1<terms.size())
            {
                    if(terms.elementAt(i+1).getVariable()>=var)
                    {
                        terms.add(i+1, c);
                        return;
                    }
            }
                    else if(i+1==terms.size())
                    {
                        terms.add(c);//insert at the end
                        return;
                    }
        }
    }
    }
    
public Complex getGrade()
{
    Complex max=new Complex();
     for ( int i = 0; i < terms.size(); i++ ) {
         if(terms.get(i).getExponent().getReal().toDouble()>max.getReal().toDouble())
                max=terms.get(i).getExponent();
        }
    return max;
}
    
public void simplify()
{
    for ( int i = 0; i < terms.size(); i++ ) {
        ComplexTerm complexTerm = terms.elementAt(i);
        for ( int j = i+1; j < terms.size(); j++ ) 
            if(complexTerm.getVariable()==terms.elementAt(j).getVariable()&&complexTerm.getExponent().equals(terms.elementAt(j).getExponent()))
            {
                ComplexTerm ct = terms.elementAt(j);
                terms.remove(i);
                terms.remove(j-1);
                addTerm(new ComplexTerm(ct.add(complexTerm)));
            }
    }

}

    @Override
    public
    String toString() {
        String cadena="";
         for ( int i = 0; i < terms.size(); i++ ) {
            if(i!=0)
            {
            if(terms.get(i).getCoef().getReal().toDouble()>0.0)
            cadena+="+"+terms.get(i);
            else
                cadena+="-"+terms.get(i);
            }
            else
                cadena+=terms.get(i);
        }
        
        return cadena;
    }
    
}