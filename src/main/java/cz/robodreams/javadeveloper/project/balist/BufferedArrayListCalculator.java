package cz.robodreams.javadeveloper.project.balist;


public class BufferedArrayListCalculator implements InnerAccessToArrayList {

    /* 1,2  => 120% */
    private static final double ICLF = (double) (INCREASE_CAPACITY_LOAD_FACTOR + 100) / 100;
    /* 0,25 => 25%  */
    private static final double DCDF = (double) DECREASE_CAPACITY_DECISION_FACTOR / 100;
    /* 0,5  => 50%  */
    private static final double DCF = (double) DECREASE_CAPACITY_FACTOR / 100;

    private BufferedArrayList ale;

    public Boolean needTmpArray;

    public int capacity;


    /**
     * @param MyArrayListEx - zpětný ukazatel
     */
    public BufferedArrayListCalculator(BufferedArrayList le) {
        this.ale = le;
        this.capacity = InnerAccessToArrayList.INIT_CAPACITY;
        this.needTmpArray = false;
    }

    private void realocatePlus(int newSize) {

        if (newSize <= INIT_CAPACITY) {
            capacity = INIT_CAPACITY;
            needTmpArray = false;
            return;
        }

        if (newSize <= capacity /* && capacity == (int) Math.ceil((double) newSize * iclf) */) {
            needTmpArray = false;
        } else {
            needTmpArray = true;
            // Nafouknutí kapacity
            capacity = (int) Math.ceil((double) newSize * ICLF);
            capacity = ((capacity / ALIGN) + 1) * ALIGN;
        }
    }

    private void realocateMinus(int newSize) {

        if (newSize <= INIT_CAPACITY && capacity > INIT_CAPACITY) {
            capacity = INIT_CAPACITY;
            needTmpArray = true;
            return;
        }

        if (newSize <= INIT_CAPACITY) {
            return;
        }

        int sizeMin = (int) Math.ceil((double) capacity * DCDF);
        if (sizeMin > newSize) {  // je to mimo povolenou oblast - vyfukujeme 50% volné oblasti

            int alocDcf = newSize + (int) Math.ceil((double) (capacity - newSize) * DCF);
            alocDcf = ((alocDcf / ALIGN)) * ALIGN;
            capacity = alocDcf < INIT_CAPACITY ? INIT_CAPACITY : alocDcf;
            needTmpArray = true;
        } else {  // je to v povolené oblasti
            newSize = newSize < INIT_CAPACITY ? INIT_CAPACITY : newSize;
        }
    }

    public Boolean realocate(int newSize) {

        ale.setPosition(ale.size());
        needTmpArray = false;
        capacity = ale.getAllocatedCapacity();

        if (newSize > ale.size()) {
            realocatePlus(newSize);
        } else if (newSize < ale.size()) {
            realocateMinus(newSize);
        }
        ale.setSize(newSize);
        return needTmpArray;
    }


    public Boolean resize(int newSize) {

        //ale.setPosition(ale.size());
        needTmpArray = false;
        capacity = ale.getAllocatedCapacity();

        if (newSize > capacity) {
            realocatePlus(newSize);
        } else if (newSize < capacity) {
            realocateMinus(newSize);
        }

        if (needTmpArray) {
            ale.reCopyWithNewSize(capacity);
        }
//        ale.setSize(newSize);
        return needTmpArray;
    }

    @Override
    public int getAllocatedCapacity() {
        return 0;
    }

}
