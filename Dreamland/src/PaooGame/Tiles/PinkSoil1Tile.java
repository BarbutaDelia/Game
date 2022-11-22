package PaooGame.Tiles;
import PaooGame.Graphics.Assets;

/*! \class public class PinkSoil1Tile extends Tile
    \brief Abstractizeaza notiunea de dala de tip sol roz 1.
 */
public class PinkSoil1Tile extends Tile
{
    /*! \fn public GrassTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public PinkSoil1Tile(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.pinkSoil1, id);
    }
}
