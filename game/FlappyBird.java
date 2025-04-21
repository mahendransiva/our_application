// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.*;

// public class FlappyBird extends JPanel implements ActionListener, KeyListener{
//     int boardwidth=360;
//     int boardheight=640;

//     //images
//     Image flappybird;
//     Image flappybirdpipe1;
//     Image flappybirdpipe2;
//     Image background;

//     //bird
//     int birdx=boardwidth/8;
//     int birdy=boardheight/2;
//     int birdwidth=34;
//     int birdheight=24;

//     class Bird {
//         int x=birdx;
//         int y=birdy;
//         int width=birdwidth;
//         int height=birdheight;
//         Image img;

//         Bird( Image img){
//             this.img=img;
//         }
//     }

//     int pipeX=boardheight;
//     int pipeY=0;
//     int pipewidth=64;
//     int pipeHeight=512;

//     class Pipe{
//         int x=pipeX;  
//         int y=pipeY;
//         int width=pipewidth;
//         int height=pipeHeight;
//         Image img;
//         boolean passed =false;

//         pipe (Image flappybirdpipe1Image)
//         {
//             this.img=img;   
//         }
//     }

//     Bird bird;
//     int velocityX=-4;
//     int velocityY=0;
//     int gravity=1;

//     ArrayList<pipe> pipes;
//     Random random=new Random();

//     Timer gamerLoop;
//     Timer placepipesTimer;
//     boolean gameOver=false;
//     double score=0;

//     FlappyBird(){
//         setPreferredSize(new Dimension(boardwidth,boardheight));
//         setBackground(Color.BLUE);
//         setFocusable(true);
//         addKeyListener(this);

//         //load images
//         background =new ImageIcon(getClass().getResource("./background.png")).getImage();
//         flappybird=new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
//         flappybirdpipe1=new ImageIcon(getClass().getResource("./flappybirdpipe1.png")).getImage();
//         flappybirdpipe2=new ImageIcon(getClass().getResource("./flappybirdpipe2.png")).getImage();

//         bird =new Bird(flappybird);
//         pipes=new ArrayList<pipe>();

//         placepipesTimer=new Timer(1500, new ActionListener(){
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 public void actionPerformed(ActionEvent e) {
//                     placePipes();
//                 }
//             }
//         });

//         placepipesTimer.start();

//         gamerLoop=new Timer(1000/60,this);
//         gamerLoop.start();
//     }

//     public void placePipes()
//     {
//         int randomPipeY=(int) (pipeY-pipeHeight/4- Math.random()*(pipeHeight/2));
//         int openingSpace=boardheight/4;
//         pipe flappybirdpipe1=new pipe(flappybirdpipe1);
//         topPipe.y=randomPipeY;
//         pipes.add(flappybirdpipe1);

//         pipe bottomPipe new Pipe(flappybirdpipe2);
//         flappybirdpipe2.y=topPipe.y+pipeHeight+openingSpace;
//     }



//     public void paintComponent(Graphics g){
//         super.paintComponent(g);
//         draw(g);
//     }

//     public void draw(Graphics g)
//     {
//         g.drawImage(background,0,0,boardwidth,boardheight,null);
//         g.drawImage(bird.img,bird.x,bird.y,bird.width,bird.height,null);

//         for(int i=0;i<pipes.size();i++) {
//           Pipe = pipes.get(i);
//           g.drawImage(Pipe.img,Pipe.x,Pipe.y,pipe.width,pipe.height,null);
//         }

//         g.setColor(Color.WHITE);
//         g.setFont(new Font("arial", Font.PLAIN, 32));
//         if(gameOver){
//             g.drawString("game over "+String.valueOf((int) score),10,35);
//         }
//         else
//         {
//             g.drawString(String.valueOf((int)score),10,35);
//         }
//     }

//     public void move()
//     {

//         velocityY += gravity;
//         bird.y += velocityY;
//         bird.y = (int)Math.pow(bird.y,0);

//         for (int i = 0; i < pipes.size(); i++) {
//             Pipe pipe=pipes.get(i); 
//             pipe.x+=velocityX;

//             if(!pipe.passed && bird.x > pipe.x + pipe.width){
//                 pipe.passed=true;
//                 score+=0.5;
//             }

//             if(collision(flappybird, pipe)){
//                 gameOver=true;
//             }
//         }

//         if(bird.y > boardheight)
//         {
//             gameOver=true;
//         }
//     }

//     public boolean collision(Bird a,Pipe b){
//         return a.x<b.x+b.width && 
//                a.x+a.width>b.x &&
//                a.y<b.y+b.height &&
//                a.y+a.height>b.y;
//     }

//     @Override
//     public void actionPerformed(ActionEvent e) {
//         move();
//        repaint();
//        if(gameOver){
//         placepipesTimer.stop();
//         gamerLoop.stop();
//        }
//     }

//     @Override
//     public void keyPressed(KeyEvent e) {
//        if(e.getKeyCode() == KeyEvent.VK_SPACE)
//        {
//         velocityY=-9;
//         if(gameOver)
//         {
//             bird.y=birdY;
//             velocityY=0;
//             pipes.clear();
//             score=0;
//             gameOver=false;
//             gamerLoop.start();
//             placepipesTimer.start();
//         }
//        }
//     }

//     @Override
//     public void keyTyped(KeyEvent e) {}

//     @Override
//     public void keyReleased(KeyEvent e) {
//     }
// }







import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    int boardWidth = 360;
    int boardHeight = 640;

    // Images
    Image flappyBirdImg;
    Image pipeTopImg;
    Image pipeBottomImg;
    Image backgroundImg;

    // Bird
    int birdX = boardWidth / 8;
    int birdY = boardHeight / 2;
    int birdWidth = 34;
    int birdHeight = 24;

    class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image img) {
            this.img = img;
        }
    }

    class Pipe {
        int x;
        int y;
        int width;
        int height;
        Image img;
        boolean passed = false;

        Pipe(int x, int y, int width, int height, Image img) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.img = img;
        }
    }

    Bird bird;
    int velocityX = -4;
    int velocityY = 0;
    int gravity = 1;

    ArrayList<Pipe> pipes;
    Random random = new Random();

    Timer gameLoop;
    Timer placePipesTimer;
    boolean gameOver = false;
    double score = 0;

    public FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.BLUE);
        setFocusable(true);
        addKeyListener(this);

        // Load images
        backgroundImg = new ImageIcon(getClass().getResource("background.png")).getImage();
        flappyBirdImg = new ImageIcon(getClass().getResource("flappybird.png")).getImage();
        pipeTopImg = new ImageIcon(getClass().getResource("flappybirdpipe1.png")).getImage();
        pipeBottomImg = new ImageIcon(getClass().getResource("flappybirdpipe2.png")).getImage();

        bird = new Bird(flappyBirdImg);
        pipes = new ArrayList<>();

        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipesTimer.start();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    public void placePipes() {
        int pipeWidth = 64;
        int pipeHeight = 512;
        int gap = boardHeight / 4;

        int randomY = -pipeHeight / 2 + random.nextInt(pipeHeight / 2);

        Pipe topPipe = new Pipe(boardWidth, randomY, pipeWidth, pipeHeight, pipeTopImg);
        Pipe bottomPipe = new Pipe(boardWidth, randomY + pipeHeight + gap, pipeWidth, pipeHeight, pipeBottomImg);

        pipes.add(topPipe);
        pipes.add(bottomPipe);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);
        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

        for (Pipe pipe : pipes) {
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameOver) {
            g.drawString("Game Over " + (int) score, 10, 35);
        } else {
            g.drawString("" + (int) score, 10, 35);
        }
    }

    public void move() {
        velocityY += gravity;
        bird.y += velocityY;

        ArrayList<Pipe> toRemove = new ArrayList<>();
        for (Pipe pipe : pipes) {
            pipe.x += velocityX;

            if (!pipe.passed && pipe.x + pipe.width < bird.x) {
                pipe.passed = true;
                score += 0.5;
            }

            if (collision(bird, pipe)) {
                gameOver = true;
            }

            if (pipe.x + pipe.width < 0) {
                toRemove.add(pipe);
            }
        }

        pipes.removeAll(toRemove);

        if (bird.y > boardHeight || bird.y < 0) {
            gameOver = true;
        }
    }

    public boolean collision(Bird a, Pipe b) {
        return a.x < b.x + b.width &&
               a.x + a.width > b.x &&
               a.y < b.y + b.height &&
               a.y + a.height > b.y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
            repaint();
        } else {
            gameLoop.stop();
            placePipesTimer.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (gameOver) {
                // Reset game
                bird.y = birdY;
                velocityY = 0;
                pipes.clear();
                score = 0;
                gameOver = false;
                gameLoop.start();
                placePipesTimer.start();
            } else {
                velocityY = -9;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        FlappyBird game = new FlappyBird();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

