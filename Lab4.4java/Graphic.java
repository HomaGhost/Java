import javax.swing.*;
import java.awt.*;


// a*ln(b*x*x+1)+c*e^(1-d*x*x)
public class Graphic extends Canvas {
    private int begin;
    private int end;
    private int a;
    private int b;
    private int c;
    private int d;

    Graphic(int begin,int end,int a,int b,int c,int d) {

        setBounds(0,0,1000,650);

        this.begin = begin;
        this.end = end;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.drawLine(this.getWidth()/2,0,this.getWidth()/2,this.getHeight());
        g.drawLine(0,this.getHeight()/2,this.getWidth(),this.getHeight()/2);

        g.setColor(Color.blue);
        for(int i=getWidth()/2+begin;i<getWidth()/2+end;i++) {

            g.drawLine(i,(-((int) foo(i-getWidth()/2,a,b,c,d))+getHeight()/2),
                    i+1,(-((int) foo(i+1-getWidth()/2,a,b,c,d))+getHeight()/2));
        }
    }

    private static double foo(int x, int a,int b,int c,int d) {
        double log = c * Math.log(d*x*x+1);
        double exp = a*Math.cos(b*x);
        return log+exp;
    }
}
