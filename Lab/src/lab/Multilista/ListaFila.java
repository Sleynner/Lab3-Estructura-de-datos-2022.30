package lab.Multilista;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListaFila implements Iterable<NodoFila>{
    NodoFila ptr;
    NodoFila ult;

    public ListaFila() {
        this.ptr = null;
        this.ult = null;
    }

    public void addNodo(NodoFila p) {
        if (this.ptr == null) {
            this.ptr = this.ult = p;
        } else {
            this.ult.next = p;
            this.ult = this.ult.next;
        }
    }

    public int size() {
        NodoFila P = this.ptr;
        int count = 0;
        while (P != null) {
            count++;
            P = P.next;
        }
        return count;
    }

    public List<ListaColumna> valueList() {
        List<ListaColumna> lista_values = new ArrayList<>();
        for (NodoFila dato : this) {
            lista_values.add(dato.columnas);
        }
        return lista_values;
    }

    @Override
    public Iterator<NodoFila> iterator() {
        List<NodoFila> lista = new ArrayList<NodoFila>();
        NodoFila P = this.ptr;
        while (P != null) {
            lista.add(P);
            P = P.next;
        }
        return lista.iterator();
    }
}
