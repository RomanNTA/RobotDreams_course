package cz.robodreams.javadeveloper.project.balist;

/**
 * Do not change these constants value
 * Use it for your implementation
 */
public interface IArrayCalculator {

    int ALIGN = 8;   //8 byte = 64bit
    int INIT_CAPACITY = ALIGN;
    int INCREASE_CAPACITY_LOAD_FACTOR = 25;
    int DECREASE_CAPACITY_DECISION_FACTOR = 40;
    int DECREASE_CAPACITY_FACTOR = 50;


    /* value * 1,25 => 125%  */
    double ICLF = (double) (INCREASE_CAPACITY_LOAD_FACTOR + 100) / 100;

    /* value * 0,25 => 25%   */
    double DCDF = (double) DECREASE_CAPACITY_DECISION_FACTOR / 100;

    /* value * 0,5  => 50%   */
    double DCF = (double) DECREASE_CAPACITY_FACTOR / 100;


    /**
     *
     * @return size of pre-allocated array for arrayList
     */
    int getAllocatedCapacity();

    void clear();

}
