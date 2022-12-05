public class EmpSet<E>{

    E[] set;
    int manyItems;
    int capacity = 100;

    @SuppressWarnings("unchecked")
    public EmpSet(){
        set = (E[]) new Object[capacity];
        manyItems = 0;
    }

    /**
     * Add adds an EmpRecord to the set.
     * Add does not need to check for duplicates.
     * This is because no employee can be a duplicate due to their IDs.
     * @param emp - The employee to be added.
     */
    public int add(E emp){
        int index = 0;
        if(manyItems >= capacity){
            ensureCapacity();
        }

        for(int i = 0; i < set.length; i++){
            if(set[i] == null){
                set[i] = emp;
                manyItems++;
                index = i;
                break;
            }
        }

        return index;
    }

    /**
     * Removes an EmpRecord by comparing EmpIDs.
     * @param empID - What employee ID needs to be removed.
     */
    public void remove(String empID){

        for(E element : set){
            if(element instanceof EmpRecord){
                if(((EmpRecord) element).getEmpID().equals(empID)){
                    ((EmpRecord) element).setDelete();
                    manyItems--;
                    return; // break the method off
                }
            }
        }
    }

    /**
     * Finds the amount of times an employee is found, compared by the first two parts of their ID.
     * This helps generate an ID.
     * @param emp - What employee are we comparing?
     * @return - How many times we have found someone. Formatted specifically for IDs.
     */
    @SuppressWarnings("all")
    public String countOccurrences(E emp){

        int amount = 0;
        String strAmount = "";

        if(emp instanceof EmpRecord) {
            for (int i = 0; i < manyItems; i++) {
                if (set[i] != null && equals(set[i], emp)) {
                    amount += 1;
                }
            }
        }

        strAmount = ""+amount;

        if(amount < 10){
            strAmount = "0"+strAmount;
        }

        return strAmount;
    }

    /**
     * Compares two employees by taking the first two parts of their ID.
     * @param emp1 - First employee to compare
     * @param emp2 - Second employee to compare
     * @return - If both are equal, true. Else, false.
     */
    public boolean equals(E emp1, E emp2){
        if(emp1 instanceof EmpRecord) {
            String compiled1 = ((EmpRecord) emp1).getSite().charAt(0) + ((EmpRecord) emp1).getLastName().substring(0, 3) + ((EmpRecord) emp1).getFirstName().charAt(0);
            String compiled2 = ((EmpRecord) emp2).getSite().charAt(0) + ((EmpRecord) emp2).getLastName().substring(0, 3) + ((EmpRecord) emp2).getFirstName().charAt(0);;
            return compiled1.equals(compiled2);
        }
        else{
            return false;
        }
    }

    /**
     * Finds an Employee by finding a string in the compiled version of their information.
     * Uses indexOf to find similarities. You can find them by their ID, site, or by a singular character.
     * Returns a large list of them if that is what needs to happen.
     * @param token - What are we finding them by?
     * @return - If found, returns true. Else, false.
     */
    public boolean find(String token){

        boolean flag = false;

        for(int i = 0; i < manyItems; i++){
            if(set[i] instanceof EmpRecord) {
                if (((EmpRecord) set[i]).getCompiled().contains(token)){
                    System.out.println(i + "\t" + set[i]);
                    flag = true;
                }
            }
        }

        return flag;
    }

    /**
     * Compiles a list of all employees who are not set for deletion.
     * @return - A list of all employees not being deleted.
     */
    public String[] compile(){

        String[] list = new String[manyItems];
        int index = 0;

        for(E element : set){
            if(element instanceof EmpRecord){
                if(!((EmpRecord) element).getDelete()){
                    list[index] = ((EmpRecord) element).getLastName() + " " + ((EmpRecord) element).getFirstName() + " " + ((EmpRecord) element).getPosition() + " " + ((EmpRecord) element).getSite();
                    index++;
                }
            }
        }

        return list;
    }

    /**
     * Doubles the size of an array.
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity(){
        E[] newSet = (E[]) new Object[manyItems * 2 + 1];
        System.arraycopy(set, 0, newSet, 0, manyItems);
        set = newSet;
    }
}
