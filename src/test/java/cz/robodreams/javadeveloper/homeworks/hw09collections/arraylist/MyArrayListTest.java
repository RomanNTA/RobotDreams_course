package cz.robodreams.javadeveloper.homeworks.hw09collections.arraylist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class MyArrayListTest {

    @Test
    void testBasicInsertAndRemoval() {
        List<Integer> referral = new ArrayList<>(2);
        referral.add(1);
        referral.add(2);
        referral.add(3);
        referral.add(4);

        MyList myList = createNewList();

        assertThat(myList.isEmpty()).isTrue();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);

        referral.remove(1);
        myList.remove(1);

        assertThat(myList.get(1)).isEqualTo(referral.get(1));

        for (int i = 0; i < 100; i++) {
            myList.add(i);
            referral.add(i);
            assertCapacity(myList, referral);
        }

        for (int i = 0; i < 90; i++) {
            Integer myRemoval = myList.remove(0);
            Integer referralRemoval = referral.remove(0);
            assertThat(myRemoval).isEqualTo(referralRemoval);
            assertCapacity(myList, referral);

        }

    }

    @Test
    void increaseTest() {
        MyList myList = createNewList();
        for (int i = 0; i < 10; i++) {
            myList.add(i);
        }
        myList.add(1);
        assertThat(((InnerAccessToArrayList) myList).getAllocatedCapacity()).isEqualTo(12);
    }

    @Test
    void testContains() {
        MyList myList = createNewList();
        List<Integer> referral = new ArrayList<>();
        addFewElementsToBothList(myList, referral);

        assertThat(myList.indexOf(5)).isEqualTo(referral.indexOf(5));

        assertThat(myList.contains(4)).isEqualTo(referral.contains(4));

    }

    @Test
    void addAll() {
        List<Integer> referral = new ArrayList<>(2);
        MyList myList = createNewList();

        addFewElementsToBothList(myList, referral);

        List<Integer> integers = Arrays.asList(1, 5, 2, 6, 8, 5, 2, 14, 5, 45, 2, 5);
        referral.addAll(integers);
        myList.addAll(integers);

        assertThat(myList.get(15)).isEqualTo(referral.get(15));
    }

    private static void addFewElementsToBothList(MyList myList, List<Integer> referral) {
        for (int i = 1; i < 10; i++) {
            myList.add(i);
            referral.add(i);
            assertCapacity(myList, referral);
        }
    }

    private static void assertCapacity(MyList myList, List<Integer> referral) {
        assertThat(((InnerAccessToArrayList) myList).getAllocatedCapacity())
                .as("Expected allocated memory, elements %d, allocated size %d", referral.size(), myList)
                .isBetween(referral.size(), referral.size() * 4);
    }

    private static MyList createNewList() {
        return MyArrayListFactory.createMyArrayListInstance();
    }
}
