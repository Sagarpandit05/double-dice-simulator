import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class RollingDiceGui extends JFrame {
    public RollingDiceGui(){
        super("Rolling Double Dice");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 700));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        addGuiComponents();
    }
    private void addGuiComponents(){
     JPanel jPanel = new JPanel();
     jPanel.setLayout(null);

     //1.   banner
//        JLabel bannerImg = ImgService.loadImage("Dice/banner.png");
//        bannerImg.setBounds(45,25,600,100);
//        jPanel.add(bannerImg);
        JLabel bannerImg = ImgService.loadImage("Dice/banner.png");
        if (bannerImg != null) {
            bannerImg.setBounds(45, 25, 600, 100);
            jPanel.add(bannerImg);
        } else {
            System.out.println("Banner image not loaded.");
        }



        //2.Dices
//        JLabel diceOneImg = ImgService.loadImage("Dice/dice1.png");
//        diceOneImg.setBounds(100,200,200,200);
//        jPanel.add(diceOneImg);

        JLabel diceOneImg = ImgService.loadImage("Dice/dice1.png");
        if (diceOneImg != null) {
            diceOneImg.setBounds(100, 200, 200, 200);
            jPanel.add(diceOneImg);
        } else {
            System.out.println("Dice one image not loaded.");
        }


//        JLabel diceTwoImg = ImgService.loadImage("Dice/dice2.png");
//        diceTwoImg.setBounds(390,200,200,200);
//        jPanel.add(diceTwoImg);

        JLabel diceTwoImg = ImgService.loadImage("Dice/dice2.png");
        if (diceTwoImg != null) {
            diceTwoImg.setBounds(390, 200, 200, 200);
            jPanel.add(diceTwoImg);
        } else {
            System.out.println("Dice two image not loaded.");
        }


        //3.Roll Button
        Random rand = new Random();
        JButton rollButton = new JButton("roll");
        rollButton.setBounds(250,550,200,50);
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollButton.setEnabled(false);

                //long for 3 seconds
                long startTime = System.currentTimeMillis();
                Thread rollThread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                       long andTime = System.currentTimeMillis();
                       try{

                           while((andTime - startTime)/1000f < 3){
                              //roll dice
                              int diceOne = rand.nextInt(6)+1;
                              int diceTwo = rand.nextInt(6)+1;

                              //update dice image
                              ImgService.updateImage(diceOneImg,"Dice/dice" + diceOne + ".png");
                              ImgService.updateImage(diceTwoImg,"Dice/dice" + diceTwo + ".png");

                              repaint();
                              revalidate();

                              //sleep thread
                              Thread.sleep(60);
                              andTime = System.currentTimeMillis();
                          }
                          rollButton.setEnabled(true);
                       }catch (InterruptedException e){
                           System.out.println("Threading Error: "+e);
                       }
                    }
                });
                rollThread.start();
            }
        });
        jPanel.add(rollButton);

        this.getContentPane().add(jPanel);


    }
}
