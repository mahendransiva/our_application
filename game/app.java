import java.awt.Image;
import javax.swing.*;

class app
{
    public static void main(String[] args) throws Exception
    {
        int boardwidth=360;
        int boardheight=640;

       
        JFrame frame=new JFrame("floppy bird");
       // frame.setVisible(true);
        frame.setSize(boardwidth,boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable((false));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FlappyBird flappybird =new FlappyBird();
        frame.add(flappybird);
        frame.pack();
        flappybird.requestFocus();
        frame.setVisible(true);
    }
}