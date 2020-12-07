import java.util.ArrayList;

public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E> implements Comparable<E> {
    private ArrayList<E> sortedList;

    SortedArrayList() {
        sortedList = new ArrayList<E>();
    }

    //When librarian inserts a value, that value will always have been sorted upon
    //returning, as each getArrayList method runs through the below method before being output.
    public void sortAdd(E obj) {
        //if array has nothing in it, simply add first element
        if (this.size() == 0) {
            this.add(obj);
        }
        //then if there are others, go through the array and compare each value
        for (int i = 0; i < this.size(); i++) { //for the length of the array
            if (obj.compareTo(this.get(i)) < 0) {
                this.add(i, obj);
                return;
            }
            if (obj.compareTo(this.get(i)) == 0) {
                return;
            }
        }
        this.add(obj);
    }

    @Override
    public int compareTo(E o) {
        return -1;
    }
}
