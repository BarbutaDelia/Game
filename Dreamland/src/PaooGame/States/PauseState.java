package PaooGame.States;

// The pause GameState can only be activated
// by calling GameStateManager#setPaused(true).


import PaooGame.RefLinks;

import java.awt.*;

public class PauseState extends State {
    boolean paused;
    public PauseState(RefLinks refLink) {

        super(refLink);
        paused = true;
    }

    public void Update() {
        if(paused)
            handleInput(refLink);
    }

    public void Draw(Graphics g) {
        if(paused) {
            g.drawString("paused", 40, 30);

            g.drawString("arrow", 12, 76);
            g.drawString("keys", 16, 84);
            g.drawString(": move", 52, 80);

            g.drawString("space", 12, 96);
            g.drawString(": action", 52, 96);

            g.drawString("F1:", 36, 112);
            g.drawString("return", 68, 108);
            g.drawString("to menu", 68, 116);
        }

    }
    public void handleInput(RefLinks refLink) {
        if(refLink.GetKeyManager().escape) {
            paused = false;
        }
        }
    }


