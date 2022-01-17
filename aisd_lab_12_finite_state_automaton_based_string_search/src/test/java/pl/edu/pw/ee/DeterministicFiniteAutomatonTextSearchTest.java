package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.io.TempDir;

public class DeterministicFiniteAutomatonTextSearchTest {

    private DeterministicFiniteAutomatonTextSearch dSearch;

    @Before
    public void setUp() {
        
    }

    @Test
    public void should_ThrowException_When_PatternIsNull() {
        //given
        String pattern = null;
        String text = "BAEGH";

        //when
        dSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int result = dSearch.findFirst(text);

        //then
        System.out.println(result);
    }

    @Test
    public void should_() {
        //given
        String pattern = null;
        String text = "BAEGH";

        //when
        dSearch = new DeterministicFiniteAutomatonTextSearch(pattern);
        int result = dSearch.findFirst(text);

        //then
        System.out.println(result);
    }
}
