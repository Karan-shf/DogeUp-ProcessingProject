import processing.core.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Main extends PApplet {

    public static int speedX=5;
    public static int GameCondition = 0;
    public static float GameSpeed = 5;
    public static int GameScore = 0;
    public static int Lifes = 5;
    public static PApplet processing;
    public static ArrayList<Block> GameBlocks = new ArrayList<Block>();
    public static Human human;
    public static boolean savedInDatabase = false;
   

    public static void main(String[] args) {
        PApplet.main("Main", args);
    }

    @Override
    public void setup() {
        processing = this;
        Block.CraeteBlocks();
        human = new Human(mouseX,20,0,255,0);
    }
    
    @Override
    public void settings() {
        size(400,700);
    }
    
    @Override
    public void draw() {
        switch (GameCondition) {
            case 0:
                background(0,0,0);
                stroke(209,59,187);
                line(0,human.getHumanY()+(human.getExtent()/2),400,human.getHumanY()+(human.getExtent()/2));
                for (Block block : GameBlocks) {
                    // ShowBlock(block.getBlockX(),block.getBlockY(),block.getR(),block.getG(),block.getB());
                    block.showObject();
                    if (block.getBlockY()>human.getHumanY()+(human.getExtent()/2) && !block.scored) {
                        if (GameSpeed>7.5) {
                            GameScore+=4;
                        } else if (GameSpeed>2.5) {
                            GameScore+=2;
                        } else {
                            GameScore++;
                        }
                        block.scored = true;
                    }
                }

                fill(26, 9, 176);
                textSize(30);
                text("Score: "+GameScore,30,40);
                MoveBlocks();
                human.setHumanX(mouseX);
                human.showObject();
                // ShowHuman(human.getHumanY(), human.getExtent(), human.getR(), human.getG(), human.getB());
                checkMousePress();
                CheckGameCondition();
                fill(26, 9, 176);
                textSize(30);
                text("Lifes: "+Lifes,30,70);
                PrintGameSpeed();
                changeHumanColor();
                // System.out.println(GameScore);
                break;
            case -1:
                // System.out.println("you lost for some reason");
                GameOver();
                break;
            case 1:
                // System.out.println("you won!!!");
                GameWon();
                break;    
        }
    }

    public void MoveBlocks() {
        for (Block block : GameBlocks) {
            block.setBlockY(block.getBlockY()+(int)GameSpeed);
        }
    }

    // public void ShowBlock(int x,int y,int R, int G,int B){
    //     fill(R, G, B);
    //     noStroke();
    //     rect(x,y,Block.getBlockWidth(),Block.getBlockHeight());
    // }

    // public void ShowHuman(int y,int extent,int R, int G,int B) {
    //     fill(R, G, B);
    //     noStroke();
    //     // circle(mouseX, y, extent);
    //     ellipse(mouseX, y, extent, extent);
    // }

    public void CheckGameCondition(){
        boolean checkGameBlocks = true;
        for (Block block : GameBlocks) {

            if (mouseX+(human.getExtent()/2)>block.getBlockX() && mouseX-(human.getExtent()/2)<block.getBlockX()+Block.getBlockWidth()
             && human.getHumanY()+(human.getExtent()/2)>block.getBlockY() && human.getHumanY()-(human.getExtent()/2)<block.getBlockY()+Block.getBlockHeight()) {
                if (!block.killed) { 
                    if (block.getR()==250) {
                        Lifes--;
                        background(250,0,0);
                        GameScore -= 50;
                    } else {
                        Lifes -= 2;
                        background(250,0,0);
                        GameScore -= 75;
                    }

                    block.killed=true;
                }
                if (Lifes<=0) {
                    GameCondition = -1;
                }
            }

            if (!block.scored) {
                checkGameBlocks=false;
            }

        }

        if (checkGameBlocks) {
            GameCondition=1;
        }
        
    }

    public void checkMousePress(){
        if (mousePressed) {

            if (mouseButton==LEFT) {
                if (GameSpeed<8) {
                //     if (GameSpeed==2.5) {
                //         GameSpeed=5;
                //     } else if (GameSpeed==5) {
                //         GameSpeed=(float) 7.5;
                //     }
                    GameSpeed+=0.5;
                }
            }

            if (mouseButton==RIGHT){
                if (GameSpeed>2.5) {
                    
                    GameSpeed-=0.5;
                }
            }
        }
    }

    public void GameOver(){
        background(0,0,0);
        fill(250,0,0);
        textSize(70);
        text("You Lost! :(",30,250);
        textSize(40);
        text("Your Score: "+GameScore,40,350);
    }

    public void GameWon() {

        ArrayList<Integer> highscores = CheckHighScore();
        // highscores.sort(null);
        // for (int i:highscores) {
        //     System.out.println(i);
        // }
        // System.out.println("----------------");

        
        background(0,0,0);
        if (GameScore>=highscores.get(0)){
            fill(181, 24, 31);
            textSize(40);
            text("NEW HIGH SCORE!!!",30,120);
        }
        fill(19, 110, 43);
        textSize(50);
        text("Congratulations",30,200);
        text("You Won!!",30,250);
        textSize(40);
        text("Your Final Score: "+GameScore,30,350);
        text("Remaining Lives: "+Lifes,30,420);
        
        
        if (!savedInDatabase) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DogeUp",
                "root", "root1234");
                String sql = "INSERT INTO Scores VALUES (?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, GameScore);
                preparedStatement.executeUpdate();
                connection.close();
                // System.out.println("table updated succesfully");
                savedInDatabase = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
        highscores = CheckHighScore();
        fill(126, 18, 130);
        text("Top 5 Scores:",30,475);
        text("1. "+highscores.get(0),30,520);
        text("2. "+highscores.get(1),30,560);
        text("3. "+highscores.get(2),30,600);
        text("4. "+highscores.get(3),30,640);
        text("5. "+highscores.get(4),30,680);

    }

    public void PrintGameSpeed() {
        if (GameSpeed<=2.5) {
            textSize(20);
            text("Game Speed: Slow",30,100);
        } else if (GameSpeed<=7.5) {
            textSize(20);
            text("Game Speed: Normal",30,100);
        } else {
            textSize(20);
            text("Game Speed: Fast",30,100);
        }
    }

    public ArrayList<Integer> CheckHighScore() {
        ArrayList<Integer> HighScores = new ArrayList<Integer>();
        String sql = "SELECT Score FROM Scores ORDER BY Score DESC LIMIT 5" ;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DogeUp",
            "root", "root1234");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                HighScores.add(resultSet.getInt(1));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return HighScores;
    }

    public void changeHumanColor(){

        if (Lifes==5) {
            human.setRGB(79, 245, 2);
        } else if (Lifes==4) {
            human.setRGB(65, 186, 9);
        } else if (Lifes==3) {
            human.setRGB(52, 138, 12);
        } else if (Lifes==2) {
            human.setRGB(34, 92, 6);
        } else if (Lifes==1) {
            human.setRGB(19, 54, 2);
        } 
            
    }
    
}
