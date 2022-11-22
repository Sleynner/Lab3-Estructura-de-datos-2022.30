package lab.ListaSimple;

public class NodoSimple {

    public int row;
    public int column;
    public int data;
    public NodoSimple next;

    public NodoSimple(int row,int column,int data) {
        this.column=column;
        this.row=row;
        this.data = data;
        this.next = null;
    }
}
