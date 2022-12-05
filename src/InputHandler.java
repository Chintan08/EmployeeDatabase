import java.io.IOException;
import java.util.Scanner;

public class InputHandler {

    private BSTree<EmpRecord, Node> tree;
    private EmpSet<EmpRecord> set;
    private final FileIO reader = new FileIO();

    public void start() throws IOException {

        Scanner scanner = new Scanner(System.in);
        boolean incomplete = true;

        while(incomplete) {
            System.out.println("What file would you like to open?\n" +
                    "Please make sure the file is spelled properly and in an accessible place. The program assumes this is a .dat file, so do not put .dat.");
            String input = scanner.next();
            int code = reader.read(input);

            if(code == -1){
                System.out.println("Your file path was incorrect.");
            }

            else{
                incomplete = false;
            }
        }

        System.out.println("Your file was read properly.");
        tree = reader.tree;
        set = reader.set;
        main();
    }

    @SuppressWarnings("all")
    public void main() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        do{

            System.out.println("\nHere are a list of commands:\n" +
                    "L: Lists employees out in ascending order.\n" +
                    "F: Finds an employee based on the ID you give it.\n" +
                    "S: Finds an employee based on the Site you give it.\n" +
                    "P: Finds an employee based on the Position you give it.\n" +
                    "I: Adds an employee to the database.\n" +
                    "M: Merges other files into the database.\n" +
                    "D: Deletes an employee by finding the ID you give.\n" +
                    "E: Exits and saves the database to a file.\n" +
                    "Q: Exits the application without saving.\n");

            System.out.println("What would you like to do?");
            input = scanner.next().toUpperCase();

            // LIST //
            if(input.equals("L")){
                tree.list();
            }

            // FIND //
            // Merges all find options into one. Gives you all employees with that certain search queurey.
            if(input.equals("F") || input.equals("S") || input.equals("P")){
                System.out.println("Enter a token:\n");
                boolean found = set.find(scanner.next());
                if(!found){
                    System.out.println("We could not find anyone with that token.");
                }
                System.out.println("\n");
            }

            // INSERT //
            if(input.equals("I")){
                String last = "";
                String first = "";
                String pos = "";
                String site = "";
                String id = "";

                System.out.println("Enter a Last Name:\n");
                last = scanner.next();

                System.out.println("Enter a First Name:\n");
                first = scanner.next();

                System.out.println("Enter a Position:\n");
                pos = scanner.next();

                System.out.println("Enter a Site:\n");
                site = scanner.next();

                EmpRecord emp = new EmpRecord(last, first, pos, site);

                int record = set.add(emp);

                // Generate ID //
                id = site.charAt(0) + "-" + last.substring(0, 3).toUpperCase() + first.charAt(0) + "-" + set.countOccurrences(emp);
                emp.setEmpID(id);

                tree.add(emp, record);

                System.out.println(last + " has been added. They have record number: " + record + " and ID: " + id + ".");
            }

            // MERGE //
            if(input.equals("M")){
                boolean incomplete = true;

                while(incomplete) {
                    System.out.println("What file would you like to open?\n" +
                            "Please make sure the file is spelled properly and in an accessible place. The program assumes this is a .dat file, so do not put .dat.");
                    int code = reader.read(scanner.next());

                    if(code == -1){
                        System.out.println("Your file path was incorrect.");
                    }

                    else{
                        incomplete = false;
                    }
                }

                System.out.println("Files have been merged properly.");
            }

            // DELETE //
            if(input.equals("D")){

                System.out.println("Type in an ID to delete that employee.");
                String in = scanner.next();

                while(true) {
                    System.out.println("Are you sure you'd like to remove this user? This data will be unrecoverable. (Y/N)");
                    String yn = scanner.next();

                    if(yn.toUpperCase().equals("Y")){
                        set.remove(in);
                        tree.remove(in);
                        break;
                    }
                    else if(yn.toUpperCase().equals("N")){
                        break;
                    }
                    else{
                        System.out.println("Sorry, we didn't get that.");
                    }
                }

            }

            // EXIT //
            if(input.equals("E") || input.equals("Q")){ break; }

        }while(true);

        // SAVE AND QUIT //
        if(input.equals("E")){
            String[] list = set.compile();
            reader.write(list);
            System.exit(0);
        }

        // QUIT //
        if(input.equals("Q")){
            System.exit(0);
        }
    }

}
