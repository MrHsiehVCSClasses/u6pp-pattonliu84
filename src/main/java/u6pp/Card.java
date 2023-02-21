package u6pp;

public class Card {

    public static final String RED = "red";
    public static final String BLUE = "blue";
    public static final String YELLOW = "yellow";
    public static final String GREEN = "green";
    public static final String ZERO = "0";
    public static final String ONE = "1";
    public static final String TWO = "2";
    public static final String THREE = "3";
    public static final String FOUR = "4";
    public static final String FIVE = "5";
    public static final String SIX = "6";
    public static final String SEVEN = "7";
    public static final String EIGHT = "8";
    public static final String NINE = "9";

    public static String DRAW_2 = "DRAW_2";
    public static String REVERSE = "REVERSE";
    public static String SKIP = "SKIP";
    public static String WILD = "WILD";
    public static String WILD_DRAW_4 = "WILD_DRAW_4";

    public static String[] COLORS = {RED, GREEN, BLUE, YELLOW, WILD}; 
    public static String[] VALUES = {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, 
        DRAW_2, REVERSE, SKIP, WILD, WILD_DRAW_4};

    private String color;
    private String value;

    public Card(String color, String value) {
        this.color = color;
        this.value = value;
    }

    //Get methods. 
    public String getColor() {
        return color;
    }

    public String getValue() {
        return value;
    }

    public boolean canPlayOn(Card other) {
        if (other == null) {
            return false;
        }
        if (color.equals(other.getColor())) {
            return true;
        }
        if (value == other.getValue()) {
            return true;
        }
        if (WILD.equals(color) || WILD_DRAW_4 == value) {
            return true;
        }
        return false;
    }

    //Checks if the color is null, then sets the correct color. 
    public boolean trySetColor(String color) {
        if (color == null) {
            return false;
        }
        if (!WILD.equals(this.color)) {
            return false;
        }
        if (color.equals(RED) || color.equals(BLUE) || color.equals(YELLOW) || color.equals(GREEN)) {
            this.color = color;
            return true;
        }
        return false;
    }

}
