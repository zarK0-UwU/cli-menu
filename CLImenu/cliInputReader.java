package CLImenu;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * inputReader
 */
public class cliInputReader {
    ArrayList<Entry> entries = new ArrayList<Entry>();
    String title;
    boolean vertical;// will default to vertical
    boolean leftToRight;// when vertical menu will define what side to align the content, default to
                        // L->R

    boolean allowEmpty;// if allow empty
    Entry defaultEntry;// then

    String noMatchError;// * this will be required allways
    String multyMatchError;// * this will be required allways

    void InputReader(ArrayList<Entry> entries, String title, String noMatchError, String multyMatchError,
            Boolean allowEmpty, Entry defaultEntry, boolean vertical, boolean leftToRight) {

    }

    void InputReader(ArrayList<Entry> entries, String title, String noMatchError, String multyMatchError,
            Boolean allowEmpty, Entry defaultEntry) {

    }

    void InputReader(ArrayList<Entry> entries, String title, String noMatchError, String multyMatchError) {

    }

    void InputReader(ArrayList<Entry> entries, String title) {

    }

    void InputReader(String title) {

    }

    void InputReader() {

    }

    /*
     * Setters and configurarion
     */
    /**
     * @param allowEmpty the allowEmpty to set
     */
    public void allowEmpty() {
        this.allowEmpty = true;
    }

    public void denyEmpty() {
        this.allowEmpty = true;
    }

    /**
     * @param entries the entries to set
     */
    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    public void addEntry(String name, ArrayList<String> aliases) {
        Entry entry = new Entry(name, aliases);
        this.entries.add(e)
    }

    public void addEntry(String name) {

    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param defaultEntry the defaultEntry to set
     */
    public void setDefaultEntry(Entry defaultEntry) {
        this.defaultEntry = defaultEntry;
    }

    /**
     * @param noMatchError the noMatchError to set
     */
    public void setNoMatchError(String noMatchError) {
        this.noMatchError = noMatchError;
    }

    /**
     * @param multyMatchError the multyMatchError to set
     */
    public void setMultyMatchError(String multyMatchError) {
        this.multyMatchError = multyMatchError;
    }

    /**
     * @param vertical the vertical to set
     */
    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    /**
     * @param leftToRight the leftToRight to set
     */
    public void setLeftToRight(boolean leftToRight) {
        this.leftToRight = leftToRight;
    }

    /*
     * Getters
     */
    /**
     * @return the entries
     */
    public ArrayList<Entry> getEntries() {
        return entries;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the defaultEntry
     */
    public Entry getDefaultEntry() {
        return defaultEntry;
    }

    /**
     * @return the multyMatchError
     */
    public String getMultyMatchError() {
        return multyMatchError;
    }

    /**
     * @return the noMatchError
     */
    public String getNoMatchError() {
        return noMatchError;
    }

    /*
     * Reader handlers
     */
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
            System.out.println(toString());
            System.out.println(multyMatchError);
            return pSafeRead();
        } else if (validFor.size() < 1) {
            if (allowEmpty && defaultEntry != null) {
                return defaultEntry.name;
            } else {
                System.out.flush();
                System.out.println(toString());
                System.out.println(noMatchError);
                return pSafeRead();
            }
        } else {
            return out;
        }

    }

    /*
     * Output handlers
     */
    int maxMenuWidth() {
        int max = 0;
        int current;
        for (Entry entry : entries) {
            current = entry.name.length();
            if (current > max) {
                max = current;
            }
        }
        current = title.length();
        if (current > max) {
            max = current;
        }
        return max;
    }

    String frameBorder() {
        int max = maxMenuWidth();
        String out = "+";
        for (int i = 0; i < max + 2; i++) {
            out += "-";
        }
        out += "+\n";
        return out;
    }

    String frame(char filler) {
        int max = maxMenuWidth();
        String out = "|";
        for (int i = 0; i < max + 2; i++) {
            out += filler;
        }
        out += "|\n";
        return out;
    }

    String frame(String text) {
        int max = maxMenuWidth();
        String out = "| ";
        if (this.leftToRight) {
            out += text;
            for (int i = 0; i < max - text.length(); i++) {
                out += " ";
            }
            out += " |\n";
            return out;
        } else {
            for (int i = 0; i < max - text.length(); i++) {
                out += " ";
            }
            out += text;
            out += " |\n";
            return out;
        }
    }

    String verticalMenu() {
        String out = frameBorder();
        out += frame(title);
        out += frameBorder();
        if (leftToRight) {
            for (int i = 0; i < entries.size(); i++) {
                out += frame(i + ". " + entries.get(i).name);
                out += frame(" ");
            }
        } else {
            for (int i = 0; i < entries.size(); i++) {
                out += frame(entries.get(i).name + " ." + i);
                out += frame(" ");
            }
        }
        out += frameBorder();
        return out;
    }

    String horizontalmenu() {
        String out = "+" + title + " (";
        for (Entry entry : entries) {
            if (defaultEntry == entry) {
                out += entry.name.toUpperCase();
            } else {
                out += entry.name;
            }
        }
        return out;
    }

    @Override
    public String toString() {
        if (vertical) {
            return verticalMenu();
        } else {
            return horizontalmenu();
        }

    }

}