package lab.Multilista;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListaColumna implements Iterable<NodoColumna>{
    NodoColumna ptr;
    NodoColumna ult;

    public ListaColumna() {
        this.ptr = null;
        this.ult = null;
    }

    public void addNodo(NodoColumna p) {
        if (this.ptr == null) {
            this.ptr = this.ult = p;
        } else {
            this.ult.next = p;
            this.ult = this.ult.next;
        }
    }

    public int size() {
        NodoColumna P = this.ptr;
        int count = 0;
        while (P != null) {
            count++;
            P = P.next;
        }
        return count;
    }

    public List<Integer> valueList() {
        List<Integer> lista_values = new ArrayList<>();
        for (NodoColumna dato : this) {
            lista_values.add(dato.data);
        }
        return lista_values;
    }

    @Override
    public Iterator<NodoColumna> iterator() {
        List<NodoColumna> lista = new ArrayList<NodoColumna>();
        NodoColumna P = this.ptr;
        while (P != null) {
            lista.add(P);
            P = P.next;
        }
        return lista.iterator();
    }
}
