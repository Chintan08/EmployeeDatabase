public class Node<T extends EmpRecord> extends EmpRecord{

    T data;
    int record;

    T left;
    T right;

    public Node(T emp, int record){
        super();
        data = emp;
        this.record = record;
        left = null;
        right = null;
    }

    public Node getLeft(){
        return (Node)left;
    }

    public void setLeft(T node){
        left = node;
    }

    public T getRight(){
        return right;
    }

    public void setRight(T node){
        right = node;
    }

    public void setEmp(T emp){
        data = emp;
    }
    public String toString(){
        return record + "\t" + data.getEmpID() + "\t" + data.getLastName() + "\t" + data.getFirstName() + "\t" + data.getPosition() + "\t" + data.getSite();
    }
}
