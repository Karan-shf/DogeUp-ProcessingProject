public class Human implements IBlockAndHuman{

    private final int HumanY=640;
    private int HumanX;

    private int extent;

    private int R;
    private int G;
    private int B;

    public Human(int humanX,int extent, int r, int g, int b) {
        HumanX = humanX;
        this.extent = extent;
        R = r;
        G = g;
        B = b;
    }

    public int getHumanY() {
        return HumanY;
    }

    public int getHumanX() {
        return HumanX;
    }

    public void setHumanX(int humanX) {
        HumanX = humanX;
    }

    public int getExtent() {
        return extent;
    }

    public void setExtent(int extent) {
        this.extent = extent;
    }

    public int getR() {
        return R;
    }

    
    public int getG() {
        return G;
    }
    
    public int getB() {
        return B;
    }
    
    public void setRGB(int r,int g,int b) {
        R = r;
        G = g;
        B = b;
    }

    @Override
    public void showObject() {
        Main.processing.fill(R, G, B);
        Main.processing.noStroke();
        Main.processing.ellipse(HumanX, HumanY, extent, extent);
    }
    
}
