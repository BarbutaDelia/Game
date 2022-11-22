package PaooGame.Observers;

import PaooGame.RefLinks;

import java.awt.*;

public class ToothbrushObserver implements Observer{
    int nrToothbrushes;
//observers pt toothbrushes
    public ToothbrushObserver(){
        super();
        nrToothbrushes = 0;
    }
    public void Update(RefLinks refLink){
        nrToothbrushes = refLink.getHero().getNrToothbrushes();
    }
    public void Draw(Graphics g, RefLinks refLink){
        g.drawString(refLink.getHero().getNrToothbrushes()+"", 100, 312);
    }
}
