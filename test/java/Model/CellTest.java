package Model;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Antony on 23.09.2016.
 */
public class CellTest extends TestCase {

    Cell c;

    public void setUp() {
        c = new Cell(0, 0);
    }

    /*
    @Test
    public void testCellObject() throws  Exception {
        assertNotNull(c);
    }

    @Test
    public void testnextStateFlaged() throws Exception {
        c.nextState();
        assertEquals(CellStatus.Flagged, c.getStatus());
    }

    @Test
    public void testnextStateQuestioned() throws Exception {

        int n=2;
        for (int i = 0; i < n; i++)
        {
            c.nextState();
        }
        assertEquals(CellStatus.Questioned, c.getStatus());
    }

    @Test
    public void testNextClosed() throws Exception {

        int n=3;
        for (int i = 0; i < n; i++)
        {
            c.nextState();
        }
        assertEquals(CellStatus.Closed, c.getStatus());
    }


    @Test
    public void testnextStateClosed() throws Exception {
        Cell c = new Cell(0,0);
        int n=4;
        for (int i = 0; i < n; i++)
        {
            c.nextState();
        }
        assertEquals(CellStatus.Flagged, c.getStatus());
    }
    */
}