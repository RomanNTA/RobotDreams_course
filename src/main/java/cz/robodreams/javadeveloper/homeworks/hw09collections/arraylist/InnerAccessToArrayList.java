package cz.robodreams.javadeveloper.homeworks.hw09collections.arraylist;

/**
 * Do not change these constants value
 *
 * Use it for your implementation
 */
public interface InnerAccessToArrayList {

     int INIT_CAPACITY = 2;
     int INCREASE_CAPACITY_LOAD_FACTOR = 20;
     int DECREASE_CAPACITY_DECISION_FACTOR = 25;
     int DECREASE_CAPACITY_FACTOR = 50;

    /**
     *
     * @return size of pre-allocated array for arrayList
     */
    int getAllocatedCapacity();

}
