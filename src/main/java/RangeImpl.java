import java.util.*;
import java.util.stream.Collectors;

public class RangeImpl implements Range {
    long[] range;
    int size;


    public RangeImpl(long start, long end) {
        checkBounds(start, end);

        size = (int) (end - start + 1);
        range = new long[size];
        for (int i = 0; i < size; i++) {
            range[i] = start + i;
        }
    }

    private void checkBounds(long start, long end) {
        if (start > end) {
            throw new IllegalArgumentException("start is greater than end");
        }
    }

    public boolean isBefore(Range otherRange) {
        return this.getUpperBound() < otherRange.getLowerBound();
    }

    public boolean isAfter(Range otherRange) {
        return this.getLowerBound() > otherRange.getUpperBound();
    }

    public boolean isConcurrent(Range otherRange) {
        return !this.isAfter(otherRange) && !this.isBefore(otherRange);
    }

    public long getLowerBound() {
        return range[0];
    }

    public long getUpperBound() {
        return range[size - 1];
    }

    public boolean contains(long value) {
        return value >= getLowerBound() && value <= getUpperBound();
    }

    public List<Long> asList() {
        return Arrays.stream(range).boxed().collect(Collectors.toList());
    }

    public Iterator<Long> asIterator() {
        return asList().iterator();
    }
}
