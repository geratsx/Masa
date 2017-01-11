import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class XBiggestArray {


    public static void main(final String[] args) {


        final List<Integer> list = Arrays.asList(3, 44, -34, 0, 7, 1, 22, 7);
        final int maxNums = 3;

        System.out.println(getMaxNums(list, maxNums));
    }

    private static List<Integer> getMaxNums(final List<Integer> numbers, final int maxNums) {

        final List<Integer> results = new ArrayList<>();

        // Fast return
        if (numbers == null || numbers.size() == 0) {
            return results;
        } else if (maxNums <= 0) {
            return results;
        } else {
            for (final Integer num : numbers) {
                tryAddMax(results, num, maxNums);
            }

            return results;
        }
    }

    private static void tryAddMax(final List<Integer> results, final Integer num, final int maxNums) {
        if (results.size() == 0) {
            results.add(num);
        } else {
            boolean added = false;
            for (int i = 0; i < results.size(); i++) {
                if (num > results.get(i)) {
                    results.add(i, num);
                    added = true;
                    break;
                }
            }

            // Smallest number yet, but still have place for it
            if (!added && results.size() < maxNums) {
                results.add(num);
            }
            // Remove smallest number
            else if (results.size() > maxNums) {
                results.remove(results.size() - 1);
            }
        }
        System.out.println(results);
    }
}
