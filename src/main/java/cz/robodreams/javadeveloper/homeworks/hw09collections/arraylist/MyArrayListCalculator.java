package cz.robodreams.javadeveloper.homeworks.hw09collections.arraylist;

public class MyArrayListCalculator implements InnerAccessToArrayList {

    /* 1,2  => 120% */
    private final Double iclf = (double) (INCREASE_CAPACITY_LOAD_FACTOR + 100) / 100;
    /* 0,25 => 25%  */
    private final Double dcdf = (double) DECREASE_CAPACITY_DECISION_FACTOR / 100;
    /* 0,5  => 50%  */
    private final Double dcf = (double) DECREASE_CAPACITY_FACTOR / 100;


    private MyArrayListEx ale;

    public Boolean needTmpArray;

    public int capacity;


    /**
     * @param MyArrayListEx - zpětný ukazatel
     */
    public MyArrayListCalculator(MyArrayListEx le) {
        this.ale = le;
        this.capacity = InnerAccessToArrayList.INIT_CAPACITY;
        this.needTmpArray = false;
    }

    private void realocatePlus(int newSize) {

        if (newSize <= capacity /* && capacity == (int) Math.ceil((double) newSize * iclf) */ ) {
            needTmpArray = false;
        } else {
            needTmpArray = true;
            // Nafouknutí kapacity
            capacity = (int) Math.ceil((double) newSize * iclf);
        }
    }

    private void realocateMinus(int newSize) {

        int sizeMin = (int) Math.ceil((double) capacity * dcdf);
        if (sizeMin >= newSize) {  // je to mimo povolenou oblast - vyfukujeme 50% volné oblasti

            int alocDcf = newSize + (int) Math.ceil((double) (capacity - newSize) * dcf);
            if (alocDcf < INIT_CAPACITY) {
                capacity = INIT_CAPACITY;
            } else {
                capacity = alocDcf;
            }
            needTmpArray = true;

        } else {  // je to v povolené oblasti

            if (newSize <= INIT_CAPACITY) {
                capacity = INIT_CAPACITY;
            }
        }
    }

    public Boolean realocate(int newSize) {

        ale.setPosition(ale.size);
        needTmpArray = false;
        capacity = ale.getAllocatedCapacity();

        if (newSize > ale.size) {
            realocatePlus(newSize);
        } else if (newSize < ale.size) {
            realocateMinus(newSize);
        }
        ale.setSize(newSize);

        return needTmpArray;
    }


    @Override
    public int getAllocatedCapacity() {
        return 0;
    }

}
