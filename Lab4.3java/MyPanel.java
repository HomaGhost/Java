import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    MyPanel() {
        setBackground(Color.white);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(0,0,this.getWidth(),this.getHeight());

        g.setColor(new Color(34, 168, 224));
        g.fillRect(20,20,110,200);

        g.setColor(Color.white);
        g.fillRect(50,50,80,140);


        //C is done

        g.setColor(new Color(210, 225, 29));
        int[] xPointsA = new int[] {140,180,220,240,280,320,250,210,140};
        int[] yPointsA = new int[] {220,220,140,140,220,220,20,20,220};
        g.fillPolygon(xPointsA,yPointsA,xPointsA.length);

        g.setColor(Color.white);
        g.fillPolygon(new int[]{200,260,230,200},new int[] {120,120,50,120},4);

        g.setColor(new Color(158, 20, 220));
        g.fillRect(340,20,140,200); // N

        g.setColor(Color.white);
        g.fillRect(370,50,80,170);

        int[] xPointsN = new int[] {460,480,480,460};
        int[] yPointsN = new int[] {20,20,40,20};
        g.fillPolygon(xPointsN,yPointsN,xPointsN.length);

        g.fillPolygon(new int[]{340,360,340,340},new int[]{20,20,40,20},4);


        g.setColor(new Color(33, 226, 162));
        g.fillPolygon(new int[]{500,520,560,620,640,570,550,500},new int[]{20,20,200,20,20,220,220,20},8);

        g.setColor(new Color(224, 130, 34));
        int[] xPointsA1 = new int[] {620,660,700,720,760,800,730,690,620};
        int[] yPointsA1 = new int[] {220,220,140,140,220,220,20,20,220};
        g.fillPolygon(xPointsA1,yPointsA1,xPointsA1.length);


        g.setColor(Color.white);
        g.fillPolygon(new int[]{680,740,710,680},new int[] {120,120,50,120},4);

        g.setColor(new Color(224, 46, 29));
        g.fillRect(820,20,140,200);

        g.setColor(Color.white);
        g.fillRect(850,50,110,140);

        g.setColor(new Color(0, 0, 29));
        g.fillOval(1000,20,140,200);
    }
}
