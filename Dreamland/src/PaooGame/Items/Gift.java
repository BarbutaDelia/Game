package PaooGame.Items;

import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Gift extends Item{
    private BufferedImage image;





    public Gift (RefLinks refLink, int x, int y){
        super(refLink, x,y, 32, 32);
        image = Assets.gift;
        setPickedUp(false);

        normalBounds.x = x*Tile.TILE_WIDTH;
        normalBounds.y = y*Tile.TILE_HEIGHT;
        normalBounds.width = 32;
        normalBounds.height = 32;

    }

    public void Update(){
    }

    public void Draw(Graphics g)
    {
        g.drawImage(image, (int)x* Tile.TILE_WIDTH, (int)y*Tile.TILE_HEIGHT, 32, 32, null);
    }
}


