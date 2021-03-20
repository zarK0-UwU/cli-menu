import java.util.ArrayList;

public class Entry {
    String name;
    ArrayList<String> aliases;

    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param aliases the aliases to set
     */
    public void setAliases(ArrayList<String> aliases) {
        this.aliases = aliases;
    }

    /**
     * @return the aliases
     */
    public ArrayList<String> getAliases() {
        return aliases;
    }

    /**
     * add entry to
     * 
     * @param aliases
     */
    void addAlias(String alias) {
        aliases.add(alias);
    }

    boolean validateFor(String in) {
        return aliases.contains(in);
    }
}
