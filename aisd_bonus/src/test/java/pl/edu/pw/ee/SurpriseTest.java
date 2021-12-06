package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SurpriseTest 
{
    @Test
    public void shouldAnswerWithTrue() {
        int[] points = {1, 2, 3};

        Surprise sur = new Surprise();

        int result = sur.countMaxSumPoints(points);

        assertEquals(6, result);
    }
}
