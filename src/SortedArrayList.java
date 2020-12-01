import java.util.ArrayList;

public class SortedArrayList<E> extends ArrayList<E> implements Comparable<E>{
    private ArrayList<E> sortedList;

    SortedArrayList() {
        sortedList = new ArrayList<E>();
    }

    public ArrayList<E> returnList(SortedArrayList<E> a) {
        sortedList = a;
        /*int i, j;

        for (i = 0; i < a.size(); i++) {
            E value = a.get(i);
            for (j = 0; j > 0; j--) {
                if (a.get(j - 1).compareTo(value) < 0) {

                } else {
                    a.get(j) = a.get(j - 1);
                }
            }
            a.get(j) = value;
        }*/
        return this.sortedList;
    }

    @Override
    public int compareTo(E o) {
        if (this.sortedList == o) {
            return 0;
        } else if (this.sortedList.equals(o)) {
            return 0;
        }
        return -1;
    }
}
