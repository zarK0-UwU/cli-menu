import java.util.ArrayList;
import java.util.Scanner;

/**
 * inputReader
 */
public class cliInputReader {
    ArrayList<Entry> entries = new ArrayList<Entry>();
    String menuMessage;
    boolean vertical;// will default to vertical
    boolean leftToRight;// will default to L->R

    boolean allowEmpty;// if allow empty
    Entry defaultEntry;// then

    String noMatchError;// * this will be required allways
    String multyMatchError;// * this will be required allways

    void InputReader() {

    }

    String read() {
        Scanner sc = new Scanner(System.in);
        String out = sc.nextLine();
        sc.close();
        return out;
    }

    /**
     * safe read
     * 
     * @return user input String
     * 
     *         if no valid input for menu
     * 
     * @return null
     */
    String safeRead() {
        String out = read();
        ArrayList<Integer> validFor = new ArrayList<Integer>();
        for (Entry entry : entries) {
            if (entry.validateFor(out)) {
                validFor.add(entries.indexOf(entry));
            }
        }
        if (validFor.size() > 1) {
            System.out.flush();
            System.out.println(multyMatchError);
            return null;
        } else if (validFor.size() < 1) {
            if (allowEmpty && defaultEntry != null) {
                return defaultEntry.name;
            } else {
                System.out.flush();
                System.out.println(noMatchError);
                return null;
            }
        } else {
            return out;
        }

    }

    /**
     * persistant safe read
     * 
     * @return user input String (no matter what)
     */
    String pSafeRead() {
        String out = read();
        ArrayList<Integer> validFor = new ArrayList<Integer>();
        for (entry entry : entries) {
            if (entry.validateFor(out)) {
                validFor.add(entries.indexOf(entry));
            }
        }
        if (validFor.size() > 1) {
            System.out.flush();
            System.out.println(multyMatchError);
            return pSafeRead();
        } else if (validFor.size() < 1) {
            if (allowEmpty && defaultEntry != null) {
                return defaultEntry.name;
            } else {
                System.out.flush();
                System.out.println(noMatchError);
                return pSafeRead();
            }
        } else {
            return out;
        }

    }

    @Override
    public String toString() {

        return;
    }

}