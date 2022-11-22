package lab.Multilista;

public class NodoFila {

    public int row;
    public ListaColumna columnas;
    public NodoFila next;

    public NodoFila(int data) {
        this.row = data;
        this.columnas=new ListaColumna();
        this.next = null;
    }
    
}
