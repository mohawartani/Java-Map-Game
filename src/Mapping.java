import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mapping {

    public static final int INITIAL_LOCATION = 95;

    /** TODO D
     * dcreate a static LocationMap object
     */
    static LocationMap locationMap = new LocationMap();

    /** TODO D
     * dcreate a vocabulary HashMap to store all directions a user can go
     */
    HashMap<String, String> vocabulary = new HashMap<>();

    /** TODO D
     * dcreate a FileLogger object
     */
    FileLogger fileLogger = new FileLogger();

    /** TODO D
     * dcreate a ConsoleLogger object
     */
    ConsoleLogger consoleLogger = new ConsoleLogger();


    public Mapping() {
        //vocabulary.put("QUIT", "Q"); //example
        /** TODO D
         * dcomplete the vocabulary HashMap <Key, Value> with all directions.
         * duse the directions.txt file and crosscheck with the ExpectedInput and ExpectedOutput files to find the keys and the values
         */
        vocabulary.put("NORTH","N");
        vocabulary.put("SOUTH","S");
        vocabulary.put("EAST","E");
        vocabulary.put("WEST", "W");

        vocabulary.put("NORTHEAST","NE");
        vocabulary.put("NORTHWEST","NW");
        vocabulary.put("SOUTHWEST","SW");
        vocabulary.put("SOUTHEAST","SE");

        vocabulary.put("UP","U");
        vocabulary.put("DOWN","D");

        vocabulary.put("QUIT","Q");

    }

    public void mapping() {

        /** TODO D
         * dcreate a Scanner object
         */
        Scanner scan = new Scanner(System.in);

        /** TODO D
         * dinitialise a location variable with the INITIAL_LOCATION
         */
        int location = INITIAL_LOCATION;



        while (true) {

            /** TODO D
             * dget the location and print its description to both console and file
             * duse the FileLogger and ConsoleLogger objects
             */
            String descrip = locationMap.get(location).getDescription();
            consoleLogger.log(descrip + "\n");
            fileLogger.log(descrip);


            /** TODO D
             * dverify if the location is exit
             */
            if (location == 0){
                //consoleLogger.log("\n");
                //fileLogger.log("\n");
                break;
            }

            /** TODO D
             * dget a map of the exits for the location
             */
            Map<String, Integer> exits;
            exits = new HashMap<>();
            exits = locationMap.get(location).getExits();

            /** TODO D
             * dprint the available exits (to both console and file)
             * dcrosscheck with the ExpectedOutput files
             * dHint: you can use a StringBuilder to append the exits
             */
            StringBuilder availableExits = new StringBuilder();
            availableExits.append("Available exits are ");
            for (String a: exits.keySet()) {
                availableExits.append(a).append(", ");
            }
            fileLogger.log(availableExits.toString());
            consoleLogger.log(availableExits.toString() + "\n");

            /** TODO D
             * dinput a direction
             * densure that the input is converted to uppercase
             */
            String in = scan.nextLine().toUpperCase();

            /** TODO D
             * dare we dealing with a letter / word for the direction to go to?
             * davailable inputs are: a letter(the HashMap value), a word (the HashMap key), a string of words that contains the key
             * dcrosscheck with the ExpectedInput and ExpectedOutput files for examples of inputs
             * dif the input contains multiple words, extract each word
             * dfind the direction to go to using the vocabulary mapping
             * dif multiple viable directions are specified in the input, choose the last one
             */
            String nextDirect = "";
            int len = in.length();
            if (len < 3){
                if (!in.equals("UP")) {
                    if (vocabulary.containsValue(in)){
                        nextDirect = in;
                    }
                } else {
                    nextDirect = "U";
                }
            } else{
                String[] newInArray = in.split(" ");
                for (String a: newInArray){
                    if(vocabulary.containsKey(a)){
                        nextDirect = vocabulary.get(a);
                    }
                }
            }

            /** TODO D
             * dif user can go in that direction, then set the location to that direction
             * dotherwise print an error message (to both console and file)
             * dcheck the ExpectedOutput files
             */
            if(exits.containsKey((nextDirect))) {
                location = exits.get(nextDirect);
            }
            else{
                consoleLogger.log("You cannot go in that direction\n");
                fileLogger.log("You cannot go in that direction\n");
            }
        }
    }

    public static void main(String[] args) {
        /**TODO D
         * drun the program from here
         * dcreate a Mapping object
         * dstart the game
         */
        Mapping runinGame = new Mapping();

        runinGame.mapping();
    }

}