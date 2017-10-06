package mx.uatx.algebra.numbers.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComplexWritter{
    File destination;
    Formatter formatter;

    public
    ComplexWritter( File destination ) {
        try {
            this.destination = destination;
            formatter = new Formatter(destination);
        }
        catch ( FileNotFoundException ex ) {
            Logger.getLogger(ComplexWritter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}