import java.util.*;
import java.util.stream.Collectors;

public class RangeImpl implements Range {
    List<Long> list;
    int size;
    long lowerBound;
    long upperBound;


    public RangeImpl(long start, long end) {
        checkBounds(start, end);
        size = (int) (end - start + 1);
        lowerBound = start;
        upperBound = end;
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
        return lowerBound;
    }

    public long getUpperBound() {
        return upperBound;
    }

    public boolean contains(long value) {
        return value >= getLowerBound() && value <= getUpperBound();
    }

    public List<Long> asList() {
        if (list == null) {
            long[] range = new long[size];
            for (int i = 0; i < size; i++) {
                range[i] = this.getLowerBound() + i;
            }
            list = Arrays.stream(range).boxed().collect(Collectors.toList());
        }
        return list;
    }

    public Iterator<Long> asIterator() {
        return asList().iterator();
    }
}
