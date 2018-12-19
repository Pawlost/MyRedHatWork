package myProjects.Redhat.JUnitTest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class MatchesTest {

    private Matches matches;

    public MatchesTest(){

    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        matches = new Matches(10, "A");
    }

    @After
    public void tearDown() {
    }
}
