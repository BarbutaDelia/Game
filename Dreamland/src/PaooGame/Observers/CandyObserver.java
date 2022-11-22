package PaooGame.Observers;

import PaooGame.RefLinks;

import java.awt.*;
//observers pt candies
public class CandyObserver implements Observer{
    int nrCandies;

    public CandyObserver(){
        super();
        nrCandies = 0;
    }
    public void Update(RefLinks refLink){
        nrCandies = refLink.getHero().getNrCandies();
    }
    public void Draw(Graphics g, RefLinks refLink){
        g.drawString(refLink.getHero().getNrCandies()+"", 32, 312);
    }
}
