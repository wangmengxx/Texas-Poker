package test;
import main.poker;
import org.junit.Assert;
import org.junit.Test;

public class pokerTest {
    @Test
    public void test(){
        poker poker = new poker();
        String str1 = poker.begin("Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C AH");
        String str2 = poker.begin("Black: 2H 4S 4C 2D 4H White: 2S 8S AS QS 3S");
        String str3 = poker.begin("Black: 2H 3D 5S 9C KD White: 2C 3H 4S 8C KH");
        String str4 = poker.begin("Black: 2H 3D 5S 9C KD White: 2D 3H 5C 9S KH");
        Assert.assertEquals("White wins", str1);
        Assert.assertEquals("Black wins", str2);
        Assert.assertEquals("Black wins", str3);
        Assert.assertEquals("Tie", str4);
    }
}
