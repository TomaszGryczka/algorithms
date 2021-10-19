package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pw.ee.services.HeapInterface;

public class Heap<T extends Comparable<T>> implements HeapInterface<T> {

    private List<T> items;

    public Heap() {
        items = new ArrayList<>();
    }

    @Override
    public void put(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        items.add(item);
        heapUp();
    }

    @Override
    public T pop() {
        if (items.isEmpty() == true) {
            throw new IllegalStateException("Items array is empty");
        }

        int firstItemId = 0;
        int lasItemId = items.size() - 1;

        T resultValue = items.get(firstItemId);

        swapItems(firstItemId, lasItemId);
        items.remove(lasItemId);

        heapDown();

        return resultValue;
    }

    private void heapUp() {
        int n = items.size();
        int childId = n - 1;
        int parentId = (childId - 1) / 2;

        while (parentId >= 0) {
            if (isChildBiggerThanParent(childId, parentId)) {
                swapItems(childId, parentId);
                childId = parentId;
                parentId = (childId - 1) / 2;
            } else {
                break;
            }
        }
    }

    private void heapDown() {
        int parentId = 0;
        int childId = 1;

        int n = items.size();

        while (childId < n) {
            if (isRightChildBiggerThanLeft(childId)) {
                childId++;
            }
            swapItems(childId, parentId);
            parentId = childId;
            childId = 2 * parentId + 1;
        }
    }

    private boolean isChildBiggerThanParent(int childId, int parentId) {
        T childValue = items.get(childId);
        T parentValue = items.get(parentId);

        boolean result = childValue.compareTo(parentValue) > 0;

        return result;
    }

    private boolean isRightChildBiggerThanLeft(int leftChildId) {
        int n = items.size();

        boolean result;

        if (leftChildId + 1 < n) {
            int rightChildId = leftChildId + 1;
            T leftChildValue = items.get(leftChildId);
            T rightChildValue = items.get(rightChildId);

            result = rightChildValue.compareTo(leftChildValue) > 0;
        } else {
            result = false;
        }

        return result;
    }

    private void swapItems(int childId, int parentId) {
        T childValue = items.get(childId);
        T parentValue = items.get(parentId);

        items.set(childId, parentValue);
        items.set(parentId, childValue);
    }

}
