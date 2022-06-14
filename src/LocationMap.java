import java.io.*;
import java.util.*;

public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME = "locations.txt";
    private static final String DIRECTIONS_FILE_NAME = "directions.txt";

    /**
     * TODO D
     * dcreate a static locations HashMap
     */
    static Map<Integer, Location> locations = new LinkedHashMap<Integer, Location>();

    static {
        /** TODO D
         * dcreate a FileLogger object
         */
        FileLogger fileLogger = new FileLogger();

        /** TODO D
         * dcreate a ConsoleLogger object
         */

        ConsoleLogger consoleLogger = new ConsoleLogger();

        /** TODO D
         * dRead from LOCATIONS_FILE_NAME so that a user can navigate from one location to another
         * duse try-with-resources/catch block for the FileReader
         * dextract the location and the description on each line
         * dprint all locations and descriptions to both console and file
         * dcheck the ExpectedOutput files
         * dput each location in the locations HashMap using temporary empty hashmaps for exits
         */

        try (BufferedReader br = new BufferedReader(new FileReader(LOCATIONS_FILE_NAME))) {
            ArrayList<String> AASents = new ArrayList<>();
            ArrayList<String> AANumbs = new ArrayList<>();

            StringBuilder toLogger = new StringBuilder();
            String sAA;

            while ((sAA = br.readLine()) != null) {
                int i = 0;
                int ind = 0;
                String numHolder = "";
                for (int x = 0; x < sAA.length(); x++) {
                    if (sAA.charAt(x) != ',') {
                        numHolder += sAA.charAt(x);
                        ind++;
                    } else {
                        break;
                    }
                }
                AASents.add(sAA.substring(ind + 1));
                AANumbs.add(numHolder);
                i++;
            }

            toLogger.append("Available locations:\n");
            for (int i = 0; i < AANumbs.size(); i++) {
                toLogger.append(AANumbs.get(i)).append(": ").append(AASents.get(i)).append("\n");
            }
            toLogger.setLength(toLogger.length() - 1);
            fileLogger.log(toLogger.toString());
            consoleLogger.log(toLogger.toString());
            int[] numspar = new int[AANumbs.size()];
            for (int i = 0; i < AANumbs.size(); i++) {
                numspar[i] = Integer.parseInt(AANumbs.get(i));
            }

            for (int i = 0; i < AANumbs.size(); i++) {
                locations.put(numspar[i], new Location(numspar[i], AASents.get(i), new LinkedHashMap<String, Integer>()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        /** TODO D
         * dRead from DIRECTIONS_FILE_NAME so that a user can move from A to B, i.e. current location to next location
         * duse try-with-resources/catch block for the FileReader
         * dextract the 3 elements  on each line: location, direction, destination
         * dprint all locations, directions and destinations to both console and file
         * dcheck the ExpectedOutput files
         * dfor each location, create a new location object and add its exit
         */

        try (BufferedReader br = new BufferedReader(new FileReader(DIRECTIONS_FILE_NAME))) {
            int rows = 0;
            String twod;
            while ((twod = br.readLine()) != null) {
                rows++;
            }
            String[][] directions = new String[rows][3];
            StringBuilder toLogger = new StringBuilder();
            ArrayList<Integer> loc = new ArrayList<>();
            ArrayList<String> dir = new ArrayList<>();
            ArrayList<Integer> destinationss = new ArrayList<>();

            try (BufferedReader buffedread = new BufferedReader(new FileReader(DIRECTIONS_FILE_NAME))) {
                int index = 0;
                String s;
                while ((s = buffedread.readLine()) != null) {
                    directions[index] = s.split(",");
                    index++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            toLogger.append("\nAvailable directions:\n");
            int k = 1;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < 3; j++) {
                    toLogger.append(directions[i][j]).append(": ");
                    if (j == 0) {
                        loc.add(Integer.parseInt(directions[i][j]));
                    } else if (j == 1) {
                        dir.add(directions[i][j]);
                    } else {
                        destinationss.add(Integer.parseInt(directions[i][j]));
                    }
                    if (k % 3 == 0) {
                        toLogger.setLength(toLogger.length() - 2);
                        if(i== rows-1 && j==2 )
                            toLogger.append("");
                        else
                            toLogger.append("\n");
                    }
                    k++;
                }
            }
            toLogger.append("\n");
            fileLogger.log(toLogger.toString());
            consoleLogger.log(toLogger.toString());
            for (Map.Entry<Integer, Location> entry : locations.entrySet()) {
                if (entry.getKey() < locations.size()) {
                    for (int j = 0; j < loc.size(); j++) {
                        if (Objects.equals(entry.getKey(), loc.get(j))) {
                            entry.getValue().addExit(dir.get(j), destinationss.get(j));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    /**
     * TODO D
     * dimplement all methods for Map
     * @return
     */
    @Override
    public int size() {

        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {

        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {

        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        //TODO D

        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        //TODO xxx
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        //TODO D
        locations.putAll(m);
    }

    @Override
    public void clear() {
        //TODO D
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        //TODO D
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        //TODO xxx
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        //TODO D
        return locations.entrySet();
    }
}