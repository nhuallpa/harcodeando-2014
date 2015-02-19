/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.hardcodeando.ui.utils;


import ar.com.hardcodeando.dto.HillDTO;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nestor
 */
public class SaveFileTest {
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {

    }    
    
    @Test
    public void getTypeRigth() throws IOException {
        HillDTO hillDTO = new HillDTO();
        hillDTO.setCurrentStep(1);
        hillDTO.setValueScreen1("Hola");
        

        
        AlgorithmStateStorage.saveHill("./hill.txt", hillDTO);
        
        String type = AlgorithmStateStorage.getType("./hill.txt");
        
        assertEquals(AlgorithmStateStorage.ALGO_HILL, type);
        
    }
    
    
    @Test
    public void saveAndLoadHill() throws IOException {
        HillDTO hillDTO = new HillDTO();
        HillDTO hillDTOLoaded = null;
        hillDTO.setCurrentStep(1);
        hillDTO.setValueScreen1("Hola");
        
        
        
        AlgorithmStateStorage.saveHill("./hill.txt", hillDTO);
        

        hillDTOLoaded = AlgorithmStateStorage.loadHill("./hill.txt");
        
        assertEquals(1, hillDTOLoaded.getCurrentStep());
        assertEquals("Hola", hillDTOLoaded.getValueScreen1());
        
    }
    

}
