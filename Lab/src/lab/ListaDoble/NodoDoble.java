package lab.ListaDoble;

public class NodoDoble {
    public int row;
    public int column;
    public int data;
    public NodoDoble next;
    public NodoDoble prev;

    public NodoDoble(int row,int column,int data) {
        this.row=row;
        this.column=column;
        this.data = data;
        this.next = null;
        this.prev=null;
    }
}
