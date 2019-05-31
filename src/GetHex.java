import java.awt.*;
/**
 * Returns Hex code
 * Create, set and get a hexadecimal code based on the colour chosen by the user.
 */
public class GetHex {



    public String returnHex(Color col){
        int red, green, blue;
        red = col.getRed();
        blue = col.getBlue();
        green = col.getGreen();

        String hex = String.format("#%02X%02X%02X", red, green, blue);
        return hex;
    }
}
