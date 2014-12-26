/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.hardcodeando.ui;
import java.io.File;  
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author estefi
 */
public class SaveFile {
    
    private static SaveFile instance = null;
    
    private SaveFile(){

    }

    public static SaveFile getInstance() {
        if (instance == null) {
            instance = new SaveFile();
        }
        return instance;
    }
    
    @SuppressWarnings("ConvertToTryWithResources")
    public void saveToFile(String filename, String path, String json){
    
        File file=new File(path);        
        try {  
            file.createNewFile();    
                                   
            FileWriter fileWriter = new FileWriter(file);  
            fileWriter.write(json);  
            fileWriter.flush();  
            fileWriter.close();            
        } catch (IOException ex) {
            Logger.getLogger(SaveFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}