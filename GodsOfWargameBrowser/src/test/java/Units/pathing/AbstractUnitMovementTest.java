/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units.pathing;

import com.godsofwargame.backend.GodsofWargame;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author brenn
 */
public class AbstractUnitMovementTest {
    
    public AbstractUnitMovementTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    /**
     * Test of move method, of class AbstractUnitMovement.
     */
    @Test
    public void testMove() {
        System.out.println("move");
        GodsofWargame gameState = null;
        Routing movePath = null;
        String ID = "";
        AbstractUnitMovement instance = new AbstractUnitMovementImpl();
        instance.move(gameState, movePath, ID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of beginMovement method, of class AbstractUnitMovement.
     */
    @Test
    public void testBeginMovement() {
        System.out.println("beginMovement");
        AbstractUnitMovement instance = new AbstractUnitMovementImpl();
        instance.beginMovement();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AbstractUnitMovementImpl extends AbstractUnitMovement {

        public void move(GodsofWargame gameState, Routing movePath, String ID) {
        }

        public void beginMovement() {
        }
    }
    
}
