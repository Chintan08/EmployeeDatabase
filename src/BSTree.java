@SuppressWarnings("unchecked")
public class BSTree<E extends EmpRecord, T extends Node>{

    private T root;
    private int manyItems;

    public BSTree(){
        root = null;
        manyItems = 0;
    }

    /**
     * Adds an employee to the tree. Only checks first condition; goes to recursive Add if first condition passes.
     * @param emp - Employee to be added
     * @param record - What is their set index?
     */
    public void add(E emp, int record){

        if(root == null){
            root = (T) new Node<>(emp, record);
            manyItems++;
        }
        else{
            add(root, emp, record);
        }
    }

    /**
     * Add method is recursive. The first one is only for the base root case.
     * @param cursor - how we iterate through the tree.
     * @param emp - employee information
     * @param record - what index are we at in the set?
     */
    public void add(T cursor, E emp, int record){

        if(compare(emp, cursor) == -1){
            if(cursor.getLeft() == null){
                cursor.setLeft(new Node<>(emp, record));
                manyItems++;
            }
            else{
                add((T) cursor.getLeft(), emp, record);
            }
        }

        else if(compare(emp, cursor) == 1){
            if(cursor.getRight() == null){
                cursor.setRight(new Node<>(emp, record));
                manyItems++;
            }
            else{
                add((T) cursor.getRight(), emp, record);
            }
        }
    }

    /**
     * A method that routes directly into a recursive version that checks all cases.
     * @param empID - The Employee to remove
     */
    public void remove(String empID){

        T cursor = root;
        remove(cursor, empID);

    }

    /**
     * A method that removes a node recursively.
     * Finds three cases, keeps the ADT in balance.
     * @param cursor - The tree cursor
     * @param empID - The ID we are trying to find and remove
     */
    private void remove(T cursor, String empID) {

        T precursor = cursor;

        while (cursor != null && !(cursor.data.getEmpID()).equals(empID)) {

            precursor = cursor;

            if (stringCompare(empID, cursor) == -1) {
                cursor = (T) cursor.getLeft();
            } else {
                cursor = (T) cursor.getRight();
            }
        }

        if(cursor == null){ System.out.println("Your ID was unable to be found."); }

        // Case 1: Leaf nodes //
        else if(cursor.getLeft() == null && cursor.getRight() == null){

            // If this is not the root node
            if(cursor != root){
                if(precursor.getLeft() == cursor){ precursor.setLeft(null); }
                else{ precursor.setRight(null); }
            }

            // Delete the entire tree if the root needs to be deleted
            else{ root = null; }

        }

        // Case 2: The node has two children //
        else if(cursor.getLeft() != null && cursor.getRight() != null){

            T temp = getLeftMost((T) cursor.getRight());

            remove(root, temp.data.getEmpID());

            cursor.data = temp.data;
        }

        // Case 3: Node only has one child //
        else{

            // fancy schmancy tertiary statement
            T child = (T) ((cursor.left != null)? cursor.left: cursor.right);

            if(cursor != root){

                if(cursor == precursor.getLeft()){
                    precursor.setLeft(child);
                }
                else{
                    precursor.setRight(child);
                }
            }

            else{
                root = child;
            }
        }

    }

    /**
     * A method that takes itself to a recursive list.
     * Prints out formatted lists.
     */
    public void list(){

        System.out.println(
                "----------------------------------------------------------------------\n" +
                        "Record#\t EmployeeID\t Last Name\t First Name\t Position\t Site\n" +
                        "----------------------------------------------------------------------"
        );

        list(root);
        System.out.println("\n");
    }

    /**
     * The greatest method I have ever made in my life
     * @param cursor - The traversing node
     */
    public void list(T cursor){

        if(cursor.getLeft() != null){
            list((T) cursor.getLeft());
        }

        System.out.println(cursor);

        if(cursor.getRight() != null){
            list((T) cursor.getRight());
        }
    }

    /////////////////////
    // UTILITY METHODS //
    /////////////////////

    private int compare(E emp, T cursor){
        String empID = emp.getEmpID();
        String cursID = cursor.data.getEmpID();

        for(int i = 0; i < empID.length(); i++){
            if(empID.charAt(i) > cursID.charAt(i)){
                return 1;
            }

            else if(empID.charAt(i) < cursID.charAt(i)){
                return -1;
            }
        }

        return 0;
    }

    private int stringCompare(String ID, T cursor){
        String cursID = cursor.data.getEmpID();

        for(int i = 0; i < ID.length(); i++){
            if(ID.charAt(i) > cursID.charAt(i)){
                return 1;
            }

            else if(ID.charAt(i) < cursID.charAt(i)){
                return -1;
            }
        }
        return 0;
    }

    public T getLeftMost(T node){
        T cursor = node;

        while(cursor.getLeft() != null){
            cursor = (T) cursor.getLeft();
        }

        return cursor;
    }

    public T getRightMost(T node){
        T cursor = node;

        while(cursor.getRight() != null){
            cursor = (T) cursor.getRight();
        }

        return cursor;
    }

    public T deleteRightMost(T node){
        if(node.getRight() == null){ return (T) node.getLeft(); }
        else{
            return deleteRightMost((T) node.getRight());
        }
    }

}
