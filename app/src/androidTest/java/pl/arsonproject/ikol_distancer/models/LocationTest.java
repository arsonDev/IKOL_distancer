package pl.arsonproject.ikol_distancer.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LocationTest {

    private Location location;

    @Before
    public void setUp() throws Exception {
        location = new Location();
    }

    @Test
    public void checkPoints() {
        Assert.assertEquals(true, location.checkPoints("54.45312,-32.43214"));
    }

    @Test
    public void checkPointIncorrectAll() {
        Assert.assertEquals(false, location.checkPoints("254.45312,-s32.43214"));
    }

    @Test
    public void checkPointIncorrectFirst() {
        Assert.assertEquals(false, location.checkPoints("254.45312,-32.43214"));
    }

    @Test
    public void checkPointIncorrectSecond() {
        Assert.assertEquals(false, location.checkPoints("4.45312,-s32.43214"));
    }

    @Test
    public void checkPointWithNames() {
        Assert.assertEquals(true, location.checkPoints("Warszawa"));
    }

    @Test
    public void checkPointWithNamesWithCoord() {
        Assert.assertEquals(false, location.checkPoints("Warszawa,-43.3423"));
    }

    @Test
    public void checkPointCorrectCoordWithSpaces() {
        Assert.assertEquals(true, location.checkPoints("41.40338, 2.17403"));
    }

    @Test
    public void checkPointInCorrectCoordWithSpaces() {
        Assert.assertEquals(false, location.checkPoints("41.40338, 222.17403"));
    }

    @Test
    public void checkPointEmpty() {
        Assert.assertEquals(false, location.checkPoints(""));
    }

    @Test
    public void checkPointNull() {
        Assert.assertEquals(false, location.checkPoints(null));
    }
}