package lab.Multilista;

public class NodoColumna {

    public int Column;
    public int data;
    //public NodoColumna adyacente;
    public NodoColumna next;

    public NodoColumna(int column, int data) {
        this.Column = column;
        this.data = data;
        this.next = null;
        //this.adyacente=null;
    }
}
