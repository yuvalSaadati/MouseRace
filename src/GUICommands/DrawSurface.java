package GUICommands;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DrawSurface {
    private List<Command> commands = new ArrayList();

    public DrawSurface() {
    }

    public void setColor(Color color) {
            this.commands.add(new SetColor(color));
    }


    public void fillRectangle(int x, int y, int width, int height) {
        this.commands.add(new FillRectangle(x, y, width, height));
    }

    public void fillCircle(int x, int y, int r) {
        this.commands.add(new FillOval(x - r, y - r, r * 2, r * 2));
    }

    public void drawText(int x, int y, String text, int fontSize) {
        this.commands.add(new DrawText(x, y, text, fontSize));
    }

    public void paint(Graphics g) {
        Iterator itr = this.commands.iterator();

        while(itr.hasNext()) {
            Command cmd = (Command)itr.next();
            cmd.draw(g);
        }
    }
}
