/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.hardcodeando.ui.utils;


import ar.com.hardcodeando.dto.HillDTO;
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
    public void saveHillStepOne() {
        HillDTO hillDTO = new HillDTO();
        HillDTO hillDTOLoaded = null;
        hillDTO.setCurrentStep(1);
        hillDTO.setValueScreen1("Hola");
        SaveFile.getInstance().saveStep("./hill.txt", hillDTO);
        
        hillDTOLoaded = SaveFile.getInstance().loadHillState("./hill.txt");
        
        assertEquals(1, hillDTOLoaded.getCurrentStep());
        assertEquals(2, hillDTOLoaded.getValueScreen1());
        
    }
    

}
