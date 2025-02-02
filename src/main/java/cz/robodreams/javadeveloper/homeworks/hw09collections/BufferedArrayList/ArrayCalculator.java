package cz.robodreams.javadeveloper.project.balist;


public class ArrayCalculator implements IArrayCalculator {

    protected Boolean needTmpArray;
    protected int capacity;
    protected int size;

    public ArrayCalculator() {
        this.capacity = IArrayCalculator.INIT_CAPACITY;
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
            capacity = INIT_CAPACITY;
            return;
        }

        int sizeMin = (int) Math.ceil((double) capacity * DCDF);
        if (sizeMin > newSize) {  // je to mimo povolenou oblast - vyfukujeme 50% volné oblasti

            int alocDcf = newSize + (int) Math.ceil((double) (capacity - newSize) * DCF);
            alocDcf = ((alocDcf / ALIGN)) * ALIGN;
            capacity = Math.max(INIT_CAPACITY, alocDcf);
            //alocDcf < INIT_CAPACITY ? INIT_CAPACITY : alocDcf;
            needTmpArray = true;
        }
//        else {  // je to v povolené oblasti
//            newSize = newSize < INIT_CAPACITY ? INIT_CAPACITY : newSize;
//        }
    }

    public void resizeCapacity(int newSize) {

        needTmpArray = false;

        if (newSize == size) {
            return;
        }

        if (newSize > capacity) {
            realocatePlus(newSize);
        } else {
            realocateMinus(newSize);
        }

        if (needTmpArray) {
            reCopyWithNewSize(capacity);
        }
    }

    protected void reCopyWithNewSize(int newSize) {}

    @Override
    public int getAllocatedCapacity() {
        return 0;
    }

    @Override
    public void clear() {
        size = 0;
        capacity = INIT_CAPACITY;
        needTmpArray = false;
    }


}
