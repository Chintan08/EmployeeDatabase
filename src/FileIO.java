import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {

    public EmpSet<EmpRecord> set = new EmpSet<>();
    public BSTree<EmpRecord, Node> tree = new BSTree<>();

    public int read(String file) throws FileNotFoundException {

        String fileName = file + ".dat";
        File realFile = new File(fileName);
        String lastName;
        String firstName;
        String position;
        String site;
        String employeeID;

        if(realFile.exists()){
            Scanner scanner = new Scanner(realFile);

            while(scanner.hasNext()){
                String field = scanner.nextLine();
                String[] fields = field.split(" ");
                lastName = fields[0];
                firstName = fields[1];
                position = fields[2];
                site = fields[3];
                if(fields.length == 5){ site = fields[3] + " " + fields[4]; }

                EmpRecord emp = new EmpRecord(lastName, firstName, position, site);

                int record = set.add(emp);

                // Generate ID //
                employeeID = site.charAt(0) + "-" + lastName.substring(0, 3).toUpperCase() + firstName.charAt(0) + "-" + set.countOccurrences(emp);
                emp.setEmpID(employeeID);

                tree.add(emp, record);
            }

            scanner.close();
            return 0;
        }

        else{
            // Error Code; File does not exist.
            return -1;
        }

    }

    public void write(String[] content) throws IOException {

        String fileName = "empUpdate.dat";
        FileWriter writer = new FileWriter(fileName, true);

        for (String s : content) {
            writer.append(s);
        }

        System.out.println("File has been written.");
    }
}
