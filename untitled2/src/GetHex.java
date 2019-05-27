import java.awt.*;

public class GetHex {



    public String returnHex(Color col){
        int red, green, blue;
        red = col.getRed();
        blue = col.getBlue();
        green = col.getGreen();

        String hex = String.format("#%02x%02x%02x", red, green, blue);
        return hex;
    }
}
