package es.educastur.ikerfm.tienda;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author alu06d
 */
public class MetodosAuxTest {
    
    public MetodosAuxTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }


    
    @Test
    public void testEsInt() {
        
        assertAll(
            () -> assertTrue(MetodosAux.esInt("8"),"El 8 es int"),
            () -> assertTrue(MetodosAux.esInt("46"),"El 46 es int"),
            () -> assertTrue(MetodosAux.esInt("55"),"El 55 es int"),
            () -> assertFalse(MetodosAux.esInt("8.8"),"8.8 NO es int"),
            () -> assertFalse(MetodosAux.esInt("-55.5"),"-55.5 NO es int"),
            () -> assertFalse(MetodosAux.esInt("HOLA"),"HOLA NO es int")  
        );
        
      
    }

    
    @Test
    public void testEsDouble() {
        assertAll(
            () -> assertTrue(MetodosAux.esDouble("8"),"El 8 es double"),
            () -> assertTrue(MetodosAux.esDouble("55"),"El 55 es double"),
            () -> assertTrue(MetodosAux.esDouble("8.8"),"El 8.8 es double"),
            () -> assertTrue(MetodosAux.esDouble("-55.5"),"-55.5 NO es double"),
            () -> assertFalse(MetodosAux.esDouble("HOLA"),"HOLA NO es double"),
            () -> assertFalse(MetodosAux.esDouble("E"),"E NO es double")
        );
    }
    
    
    @Test
    public void testValidarDNI() {
        assertAll(
           () -> assertTrue(MetodosAux.validarDNI("90015161S"), "90015161S DNI válido" ),
           () -> assertTrue(MetodosAux.validarDNI("90463229C"), "90463229C DNI válido"),
           () -> assertFalse(MetodosAux.validarDNI("72825528R"),"72825528 NO es DNI válido"),
           () -> assertFalse(MetodosAux.validarDNI("90463229X"),"90463229X no es DNI válido")
        );
    }
    
}
