package PaooGame.Exception;
//clasa pentru tratarea erorii de tile nedefinit
public class UnknownTileException extends Exception{
    public UnknownTileException(String msg){
        super(msg);
    }
}
