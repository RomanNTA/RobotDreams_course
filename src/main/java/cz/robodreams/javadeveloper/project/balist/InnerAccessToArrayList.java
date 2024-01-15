package cz.robodreams.javadeveloper.project.balist;

/**
 * Do not change these constants value
 *
 * Use it for your implementation
 */
public interface InnerAccessToArrayList {

    int ALIGN = 8;   //8 byte = 64bit
    int INIT_CAPACITY = ALIGN;
    int INCREASE_CAPACITY_LOAD_FACTOR = 25;
    int DECREASE_CAPACITY_DECISION_FACTOR = 40;
    int DECREASE_CAPACITY_FACTOR = 50;

    /**
     *
     * @return size of pre-allocated array for arrayList
     */
    int getAllocatedCapacity();

}
