/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uatx.algebra.utils;

import java.util.Vector;
import mx.uatx.algebra.EquationSystem;
import mx.uatx.algebra.Polynomial;
import mx.uatx.algebra.SimpleTerm;

/**
 *
 * @author javatlacati
 */
public class Matrix {

    double matr[][];
    EquationSystem es;

    /**
     * @param equationSystem 
     */
    public Matrix ( EquationSystem equationSystem ) {
        es = equationSystem;
        es.simplify (); //simplify to avoid problems with constants
        matr = new double[ es.getPolynomialsNumber () ][ es.getTermsNumber () + 1 ]; //matriz de polinomios (términos+constantes)
        fillMatrix ();
    }

    /**
     * @return A Matrix determinant or NAN
     * @see  Double.#NAN
     */
    public double getDeterminant () {
        double det = Double.NaN; //error there is no det
        if (  ! zeroRow_Col () ) {
            if (  ! equalRows_Cols () ) {
                for ( int i = 0; i < matr.length; i ++ ) {
                    for ( int j = 0; j < matr[i].length; j ++ ) {
                        det = 0;
                    }
                }
            }
            else {
                det = 0;
            }
        }
        return det;
    }
    
     public double[] getRowByNumber ( double[] row, double n ) {
        double[] row1=row.clone ();
                for ( int i = 0; i < row1.length; i ++ ) {
            row1[i] *= n;
        }
        return row1;
    }

    private double[] changeRowSigns ( double[] d ) {
        double[] x = d.clone ();
        for ( int i = 0; i < x.length; i ++ ) {
            x[i] = d[i] * -1;
        }
        return x;
    }

    /***/
    private boolean equalRows_Cols () {
        for ( int i = 0; i < matr.length; i ++ ) {
            double[] ds = matr[i];
        }
        return false;
    }

    /***/
    private void fillAddededMatrix ( double[][] matIdentidad, double[][] maAumentada ) {
        int origMatrSiz = matr[0].length;
        for ( int i = 0; i < matr.length; i ++ ) {
            double[] ds = matr[i];
            double[] ds1 = matIdentidad[i];
            for ( int j = 0; j < ds.length; j ++ ) {
                maAumentada[i][j] = ds[j];
                maAumentada[i][j + origMatrSiz] = ds1[j];
            }
        }
    }

    /***/
    private void fillIdentityMatrix ( double[][] matIdentidad ) {
        int alto = 0, ancho = 0;
        alto = matIdentidad.length;
        if ( alto != 0 ) {
            ancho = matIdentidad[0].length;
            if ( ancho == alto )//llenamos la matriz
            {
                for ( int i = 0; i < matIdentidad.length; i ++ ) {
                    double[] ds = matIdentidad[i];
                    for ( int j = 0; j < ds.length; j ++ ) {
                        if ( i == j )//mismo numero de fila y columna
                        {
                            ds[j] = 1;
                        }
                    }
                }
            }
        }
        else {
            System.out.println ( "The matrix size " + alto + "x" + ancho + " is not square matrix!!!" );
        }
    }

    /**Method that fills the matrix with the equation data*/
    private void fillMatrix () {
        Vector<Polynomial> polynomials = es.polynomials;
        Polynomial diffTerms = new Polynomial ( es.getAllTerms () );
        diffTerms.simplify ();
        //System.out.println ( diffTerms ); centinel to show all terms on a new Polynomial already simplified
        Polynomial p;

        for ( int i = 0; i < polynomials.size (); i ++ ) {
            p = polynomials.elementAt ( i );
            for ( int j = 0; j < p.terms.size (); j ++ ) {
                SimpleTerm st = p.terms.elementAt ( j );
                for ( int k = 0; k < diffTerms.terms.size (); k ++ ) {
                    SimpleTerm st1 = diffTerms.terms.elementAt ( k );
                    if ( st.getVariable () == st1.getVariable () && st.getExponent () == st1.getExponent () ) {
                        matr[i][j] = st.getCoef (); //was ik but now it's ok
                    }
                // else {
                //       matr[i][k] = 0;
                //}
                }


            }
            //constants here
            for ( int j = 0; j < polynomials.size (); j ++ ) {
                Polynomial polynomial = polynomials.elementAt ( j );
                for ( int k = 0; k < polynomial.constants.size (); k ++ ) { //TODO Set a constant 0 if there aren't constants, could be at constructor
                    double constant = polynomial.constants.elementAt ( k );
                    matr[j][k + es.getTermsNumber ()] = constant;
                }
            }
        }

    }

    @Override
    public String toString () {
        String cadena = "";
        cadena += "[ ";
        for ( int i = 0; i < matr.length; i ++ ) {
            cadena += "[";
            for ( int j = 0; j < matr[i].length; j ++ ) {
                cadena = cadena + matr[i][j] + " "; //TODO separate elements by commas
            }
            cadena += "]";
        }
        cadena += " ]";
        return cadena;
    }

    /**
     * Switch two rows of a bidimensional matrix. The implementation asumes that the matrix had equal cols number.
     * @param d A matrix row
     * @param d0 A matrix row
     */
    public void switchRows ( double[] d, double[] d0 ) {
        for ( int i = 0; i < d.length; i ++ ) {
            double aux;
            aux = d[i];
            d[i] = d0[i];
            d0[i] = aux;
        }
    }

    private double[] getCol ( double[][] matIdentidad, int j ) {
        double[] col= new double[matIdentidad[0].length];
        for ( int i = 0; i < matIdentidad.length; i ++ ) {
            for ( int k = 0; k < matIdentidad[0].length; k ++ ) {
                if(k==j)
                col[i]=matIdentidad[i][k];
            }
        }
        return col;
    }

    private double[][] getInverseMatrix ( double[][] maAumentada ) {
        double[][] maInversa= new double[ matr.length ][ matr[0].length ];
        for ( int i = 0; i < maAumentada.length; i ++ ) {
            for ( int j = 0; j < maInversa[0].length; j ++ ) {
            maInversa[i][j]=maAumentada[i][j+(matr[0].length)];
               // System.out.println ( "maIdentidad[i][j]:"+maAumentada[i][j+(matr[0].length-1)] );
            }
        }
        return maInversa;
    }

    private double[][] getMultipliedMatrix ( double[][] InverseMatrix, double[][] matIdentidad ) {
        double[][] multipliedMatrix= new double[InverseMatrix.length][matIdentidad[0].length];
        if(InverseMatrix.length==matIdentidad[0].length)
        {
        for ( int i = 0; i < InverseMatrix.length; i ++ ) {
            for ( int j = 0; j < InverseMatrix.length; j ++ ) {
                multipliedMatrix[i][j]=multiplyRowByCol(InverseMatrix[i],getCol(matIdentidad,j));
            }
        }
        }
        return multipliedMatrix;
    }

    private double multiplyRowByCol ( double[] d, double[] col ) {
        double multiplication=0;
        for ( int i = 0; i < col.length; i ++ ) {
            multiplication += d[i] * col[i];
            
        }
        return multiplication;
    }

   

    /***/
    private void printMatrix ( double[][] maAumentada ) {
        String cadena = "[ ";
        for ( int i = 0; i < maAumentada.length; i ++ ) {
            cadena += "[ ";
            for ( int j = 0; j < maAumentada[i].length; j ++ ) {
                cadena += maAumentada[i][j] + " ";
            }
            cadena += " ]";
        }
        cadena += " ]";
        System.out.println ( cadena );
    }

    /**Utilitary method that quickly determines if matrix has some 
     *zero row or col that makes the determinant 0 and the matrix withot inverse*/
    private boolean zeroRow_Col () {
        int zeroCount = 0;
        for ( int i = 0; i < matr.length; i ++ ) {
            //if(matr[i]==0.0)
            //zeroCount++;
        }
        return false;
    }

    /***/
    public void solveByGaussJordan () {
        System.out.println ( "Wait solving by Gauss-Jordan Method" );
        //convertir el primer elemento (0)(0) a 1
        for ( int i = 0; i < matr.length; i ++ ) {
            for ( int j = i + 1; j < matr.length; j ++ ) {
                for ( int k = 0; k < matr[0].length - 1; k ++ ) { //don't work with the last col
                    if ( matr[i][k] != 0.0 ) {
                        System.out.println ( "R"+i+"*"+ 1/matr[i][k]);
                        rowByNumber ( matr[i], 1 / matr[i][k] );
                        printMatrix ( matr );
                    }
                    if ( matr[j][k] != 0.0 ) {
                        System.out.println ( "R"+j+"*"+ 1/matr[j][k]);
                        rowByNumber ( matr[j], 1 / matr[j][k] );
                        //mcm
                        //matr
                        printMatrix ( matr );
                        if ( matr[i][k] != 0.0 ) {
                            if ( matr[i][k] == matr[j][k] ) {
                                rowPlusRow ( matr[j], changeRowSigns ( matr[i] ) );
                                System.out.println ( "R"+j+"-R"+i);
                                printMatrix ( matr );
                            }
                            else if ( matr[i][k] > matr[j][k] ) {
                                System.out.println ( i + "st row is greater than " + j + "st" );
                            }
                            else {
                                System.out.println ( j + "st row is greater than " + i + "st" );
                            }
                        }
                    }
                }
            }

        }

        System.out.println ( "<= that is the solution or all the solutions. (Hope)" );
    }

    /***/
    public void InverseByGaussJordan () {
        //Los renglones se reducen a un primo
        int cols=es.getTermsNumber () + 1;
        double[][] matIdentidad = new double[ es.getPolynomialsNumber () ][ cols ];
        double[][] maAumentada = new double[ es.getPolynomialsNumber () ][ cols * 2 ];
        fillIdentityMatrix ( matIdentidad );
        fillAddededMatrix ( matIdentidad, maAumentada );
        System.out.println ( "The addeded matrix is:" );
        printMatrix ( maAumentada );
        
        //Gaussian elimination without sustitution
         for ( int i = 0; i < maAumentada.length; i ++ ) {
            for ( int j = i + 1; j < maAumentada.length; j ++ ) {
                for ( int k = 0; k < cols ; k ++ ) {
                    if ( i==k&&maAumentada[i][k] != 1.0 &&  maAumentada[i][k] != 0.0) {
                        System.out.println ( "R"+i+"*"+ 1/maAumentada[i][k]);
                        System.out.println ( "i:"+i+"k:"+k );
                        rowByNumber ( maAumentada[i], 1 / maAumentada[i][k] );
                        printMatrix ( maAumentada );
                    }
                    
                    if ( j==k&&maAumentada[j][k] != 1.0 &&  maAumentada[j][k] != 0.0) {
                        if(k-1>=0){
                            if(maAumentada[j][k-1]==1.0)
                            break;
                        }
                        System.out.println ( "R"+j+"*"+ 1/maAumentada[j][k]);
                        System.out.println ( "i:"+j+"k:"+k );
                        rowByNumber ( maAumentada[j], 1 / maAumentada[j][k] );
                        printMatrix ( maAumentada );
                    }
                    
                    if ( maAumentada[j][k] != 0.0) {
                        if(k-1>=0){
                            if(maAumentada[j][k-1]==1.0)
                            break;
                        }
                        System.out.println ( "R"+j+"*"+ 1/maAumentada[j][k]);
                        rowByNumber ( maAumentada[j], 1 / maAumentada[j][k] );
                        printMatrix ( maAumentada );
                        if ( maAumentada[i][k] != 0.0 ) {
                            if ( maAumentada[i][k] == maAumentada[j][k] ) { //add condition to avoid to erase the last
                                System.out.println ( "i:"+1+"j:"+j+"k:"+k );
                                rowPlusRow ( maAumentada[j], changeRowSigns ( maAumentada[i] ) );
                                System.out.println ( "R"+j+"-R"+i);
                                printMatrix ( maAumentada );
                            }
                            else if ( maAumentada[i][k] > maAumentada[j][k] ) {
                                System.out.println ( i + "st row is greater than " + j + "st" );
                            }
                            else {
                                System.out.println ( j + "st row is greater than " + i + "st" );
                            }
                        }
                    }   
                }
            }
         }
        //Jordan
        for ( int i = maAumentada.length-1; i >=0 ; i -- ) {
            for ( int j = maAumentada.length-2; j >=0; j -- ) {
                for ( int k = cols-1; k >=0 ; k -- ) {
                    //System.out.println ( "maAumentada["+i+"]["+k+"]"+maAumentada[i][k] );
                    //System.out.println ("maAumentada["+j+"]["+k+"]"+ maAumentada[j][k] );
                    if(i==k&&j!=k){
                        System.out.println ( "R"+j+"-("+maAumentada[j][k]+"*R"+i+")" );
                    rowPlusRow ( maAumentada[j], changeRowSigns ( getRowByNumber ( maAumentada[i], maAumentada[j][k])));//mcm
                    printMatrix ( maAumentada );
                    }
                }
            }
        }
        
        System.out.println ( "Finally the inverse Matrix is:" );
        double[][] InverseMatrix=getInverseMatrix(maAumentada);
        printMatrix (InverseMatrix );
        System.out.println ( "Let's comprobate by multiplying ");
        printMatrix ( InverseMatrix);
                System.out.println("\nby ");
                printMatrix ( matr);
        printMatrix ( getMultipliedMatrix(InverseMatrix,matr));
        
    }

    /**
     * @param row
     * @param n double presition number wich multiply row
     */
    public void rowByNumber ( double[] row, double n ) {
        for ( int i = 0; i < row.length; i ++ ) {
            row[i] *= n;
        }
    }

    /**
     * @param row1
     * @param row2 
     */
    public void rowPlusRow ( double[] row1, double[] row2 ) {
        for ( int col = 0; col < row1.length; col ++ ) {
            row1[col] += row2[col];
        }
    }

    /**
     * @param Arr
     * @param col 
     */
    public void rowToUnit ( double[] Arr, int col ) {
        if ( Arr.length == 0 | col > Arr.length ) {
            return;
        }
        double divisor = Arr[col];
        while ( col < Arr.length ) {
            Arr[col] /= divisor;
            col ++;
        }
    }
}