public class Human implements IBlockAndHuman{

    private final int HumanY=640;
    private final int HeadY=605;
    
    private int HumanX;
    
    private int HeadExtent;
    
    private int HeadR;
    private int HeadG;
    private int HeadB;
    
    public Human(int humanX,int HeadExtent, int r, int g, int b) {
        HumanX = humanX;
        this.HeadExtent = HeadExtent;
        HeadR = r;
        HeadG = g;
        HeadG = b;
    }
    
    public int getHumanY() {
        return HumanY;
    }

    public int getHeadY() {
        return HeadY;
    }
    
    public int getHumanX() {
        return HumanX;
    }

    public void setHumanX(int humanX) {
        HumanX = humanX;
    }

    public int getHeadExtent() {
        return HeadExtent;
    }

    public void setHeadExtent(int HeadExtent) {
        this.HeadExtent = HeadExtent;
    }

    public int getHeadR() {
        return HeadR;
    }

    
    public int getHeadG() {
        return HeadG;
    }
    
    public int getHeadB() {
        return HeadB;
    }
    
    public void setHeadRGB(int r,int g,int b) {
        HeadR = r;
        HeadG = g;
        HeadB = b;
    }

    @Override
    public void showObject() {
        
        //head
        Main.processing.noStroke();
        Main.processing.fill(HeadG,HeadG,HeadB);
        Main.processing.circle(HumanX, HeadY, HeadExtent);
        Main.processing.fill(0);
        //eyes
        Main.processing.noStroke();
        Main.processing.rect(HumanX+3, 602, 5, 4);
        Main.processing.noStroke();
        Main.processing.rect(HumanX-7, 602, 5, 4);
        Main.processing.stroke(0);
        Main.processing.line(HumanX-10, 603, HumanX+10, 603);
        //mouth
        Main.processing.line(HumanX-2, 609, HumanX+3, 609);
        //body
        Main.processing.fill(255);
        Main.processing.rect(HumanX-10, 615, 20, 25);
        //pocket
        Main.processing.fill(0);
        Main.processing.line(HumanX-5, 630, HumanX+5, 630);
        Main.processing.line(HumanX-5, 638, HumanX+5, 638);
        Main.processing.line(HumanX-5, 630, HumanX-5, 637);
        Main.processing.line(HumanX+5, 630, HumanX+5, 637);
        //legs
        Main.processing.stroke(255);
        Main.processing.line(HumanX-5, 640, HumanX-5, 650);
        Main.processing.line(HumanX+5, 640, HumanX+5, 650);
        //right hand
        Main.processing.line(HumanX+10, 615, HumanX+15, 625);
        Main.processing.line(HumanX+15, 625, HumanX+10, 635);
        //left hand
        Main.processing.line(HumanX-10, 615, HumanX-15, 625);
        Main.processing.line(HumanX-15, 625, HumanX-10, 635);
        //bands
        Main.processing.stroke(0);
        Main.processing.fill(0);
        Main.processing.line(HumanX-3, 615, HumanX-3, 622);
        Main.processing.circle(HumanX-3, 622, 2);
        Main.processing.line(HumanX+3, 615, HumanX+2, 622);
        Main.processing.circle(HumanX+3, 622, 2);
    }
    
}
