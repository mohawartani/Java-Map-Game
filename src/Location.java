import java.util.LinkedHashMap;
import java.util.Map;

public class Location {

    /** TODO D
     * ddeclare private final locationId, description, exits
     */
    private final int locationId;
    private final String description;
    private final  Map<String, Integer> exits;



    public Location(int locationId, String description, Map<String, Integer> exits) {
        /** TODO D
         * dset the locationId and the description
         */
        this.locationId = locationId;
        this.description = description;

        /** TODO D
         * dif exits are not null, set the exit
         * dNote that exits should be of type LinkedHashMap to maintain the insertion order
         * dotherwise, set the exits LinkedHashMap to (Q,0)
         */
        LinkedHashMap<String, Integer> exit = new LinkedHashMap<>(exits);
        if(exit.isEmpty()) {
            exits.put("Q",0);
            this.exits = exit;}
        else{
            this.exits = exit;}
    }

    protected void addExit(String direction, int location) {
        /** TODO D
         * dput the direction and the location in the exits LinkedHashMap
         */
        this.exits.put(direction, location);
    }

    public int getLocationId() {
        /** TODO D
         * dcomplete getter to return the location id
         */
        return locationId;
    }

    public String getDescription() {
        /** TODO D
         * dcomplete getter to return the description
         */
        return description;

    }

    public Map<String, Integer> getExits() {
        /** TODO D
         * dcomplete getter to return a copy of exits
         * d(preventing modification of exits from outside the Location instance)
         */
        LinkedHashMap<String, Integer> exit = new LinkedHashMap<>(exits);
        return exit;
    }
}
