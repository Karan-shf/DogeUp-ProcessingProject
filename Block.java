import java.util.concurrent.ThreadLocalRandom;

public class Block implements IBlockAndHuman{

    private static int BlockWidth = 30;
    private static int BlockHeight = 60;

    public static int speedY = -80; 

    private int BlockX;
    private int BlockY;

    private int R;
    private int G;
    private int B;


    public boolean scored;
    public boolean killed;

    public Block(int blockX, int blockY,int r, int g,int b) {
        BlockX = blockX;
        BlockY = blockY;
        scored = false;
        killed = false;
        R = r;
        G = g;
        B = b;
    }

    public static void CraeteBlocks(){
        for (int i=0;i<100;i++) {
            switch (ThreadLocalRandom.current().nextInt(1,5)) {
                case 1:
                    Main.GameBlocks.add(new Block(ThreadLocalRandom.current().nextInt(0,100), speedY,84, 12, 19));
                    speedY -= 80;
                    Main.GameBlocks.add(new Block(ThreadLocalRandom.current().nextInt(100,200), speedY,250,0,0));
                    speedY += 40;
                    Main.GameBlocks.add(new Block(ThreadLocalRandom.current().nextInt(200,300), speedY,250,0,0));
                    speedY -= 30;
                    Main.GameBlocks.add(new Block(ThreadLocalRandom.current().nextInt(300,400), speedY,250,0,0));
                    speedY -= 80;
                    break;
                case 2:
                    Main.GameBlocks.add(new Block(ThreadLocalRandom.current().nextInt(0,100), speedY,250,0,0));
                    speedY -= 80;
                    Main.GameBlocks.add(new Block(ThreadLocalRandom.current().nextInt(100,200), speedY,84, 12, 19));
                    speedY += 40;
                    Main.GameBlocks.add(new Block(ThreadLocalRandom.current().nextInt(200,300), speedY,250,0,0));
                    speedY -= 30;
                    Main.GameBlocks.add(new Block(ThreadLocalRandom.current().nextInt(300,400), speedY,250,0,0));
                    speedY -= 80;
                    break;
                case 3:
                    Main.GameBlocks.add(new Block(ThreadLocalRandom.current().nextInt(0,100), speedY,250,0,0));
                    speedY -= 80;
                    Main.GameBlocks.add(new Block(ThreadLocalRandom.current().nextInt(100,200), speedY,250,0,0));
                    speedY += 40;
                    Main.GameBlocks.add(new Block(ThreadLocalRandom.current().nextInt(200,300), speedY,84, 12, 19));
                    speedY -= 30;
                    Main.GameBlocks.add(new Block(ThreadLocalRandom.current().nextInt(300,400), speedY,250,0,0));
                    speedY -= 80;
                    break;
                case 4:
                    Main.GameBlocks.add(new Block(ThreadLocalRandom.current().nextInt(0,100), speedY,250,0,0));
                    speedY -= 80;
                    Main.GameBlocks.add(new Block(ThreadLocalRandom.current().nextInt(100,200), speedY,250,0,0));
                    speedY += 40;
                    Main.GameBlocks.add(new Block(ThreadLocalRandom.current().nextInt(200,300), speedY,250,0,0));
                    speedY -= 30;
                    Main.GameBlocks.add(new Block(ThreadLocalRandom.current().nextInt(300,400), speedY,84, 12, 19));
                    speedY -= 80;
                    break;
            }

        }
    }

    public static void setDangerousBlocks() {
        for (int i=0;i<Main.GameBlocks.size();i++){
            // if (ThreadLocalRandom.current().nextInt(4)) {
                
            // }
        }
    }
    
    public static int getBlockWidth() {
        return BlockWidth;
    }
    
    public static int getBlockHeight() {
        return BlockHeight;
    }
    
    public int getBlockX() {
        return BlockX;
    }
    
    public int getBlockY() {
        return BlockY;
    }

    public void setBlockY(int blockY) {
        BlockY = blockY;
    }

    public int getR() {
        return R;
    }

    public void setR(int r) {
        R = r;
    }

    public int getG() {
        return G;
    }

    public void setG(int g) {
        G = g;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    @Override
    public void showObject() {
        Main.processing.fill(R, G, B);
        Main.processing.noStroke();
        Main.processing.rect(BlockX,BlockY,Block.getBlockWidth(),Block.getBlockHeight());
    }
    
}
