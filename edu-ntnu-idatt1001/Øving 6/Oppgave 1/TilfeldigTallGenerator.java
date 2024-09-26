import java.util.Random;
/**
 * TilfeldigTallGenerator
 */
public class TilfeldigTallGenerator {
    
    Random random = new Random();

    public int tilfeldigTall(){
        int tall = random.nextInt(10);
        return tall;
    }
}
