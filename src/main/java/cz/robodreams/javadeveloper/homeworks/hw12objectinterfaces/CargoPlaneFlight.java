package cz.robodreams.javadeveloper.homeworks.hw12objectinterfaces;

public class CargoPlaneFlight extends Plane implements Flight {

    private String goodsOnBoard;
    public CargoPlaneFlight() {
        super(PLANE_CARGO);
        goodsOnBoard = GOODS[UsefulProc.getRandomId(0,COUNT_OF_GOODS)];
    }
   public String getGoodsOnBoard(){
        return goodsOnBoard;
   }



}
