package psbankActions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PSBankObjects {

	Properties p = new Properties();
    public Properties getObjectRepository() throws IOException{
        //Read object repository file
        InputStream stream = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\psbankObjects\\PSBankCalcObjects.properties"));
        //InputStream stream = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\psbankObjects\\PSBankOnlineObjects.properties"));
        //load all objects
        p.load(stream);
         return p;
    }//end of method getObjectRepository
}//end of class Objects
