package lab.ListaDoble;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListaDobleEnlazada implements Iterable<NodoDoble> {

    public NodoDoble ptr;
    public NodoDoble ult;

    public ListaDobleEnlazada() {
        this.ptr = null;
        this.ult = null;
    }

    public void addNodo(NodoDoble p) {
        if (this.ptr == null) {
            this.ptr = this.ult = p;
        } else {
            this.ult.next = p;
            this.ult.next.prev = this.ult;
            this.ult = this.ult.next;
        }
    }

    public int size() {
        NodoDoble P = this.ptr;
        int count = 0;
        while (P != null) {
            count++;
            P = P.next;
        }
        return count;
    }

    public void addDimension(NodoDoble d, int row, int column) {
        NodoDoble p = this.ptr;
        int sw = 0;
        if (ptr == null) {
            ptr = d;
        } else {

            while (p != null && sw == 0) {
                if (p.row == row) {
                    if (p.column > column) {
                        p.prev.next = d;
                        d.next = p;
                        d.prev = p.prev;
                        p.prev = d;
                        sw = 1;
                    } else if (p.column < column && (p.next.column > column || p.next.row > row)) {
                        p.next.prev = d;
                        d.prev = p;
                        d.next = p.next;
                        p.next = d;
                        sw = 1;
                    }
                } else {

                    if (p.row > row) {
                        if (p == ptr) {
                            d.next = p;
                            ptr = d;
                            p.prev = d;
                            sw = 1;
                        }
                    } else if (p.row < row && p.next.row > row) {
                        p.next.prev = d;
                        d.prev = p;
                        d.next = p.next;
                        p.next = d;
                        sw = 1;
                    } else if (p.row < row) {
                        if (p == ult) {
                            ult.next = d;
                            d.prev = ult;
                            ult = d;
                            sw = 1;
                        }
                    }
                }
                p = p.next;
            }
        }
    }
    
     public NodoDoble obtener(int row,int colum){
        
        NodoDoble p=this.ptr;
        int sw=0;
        
        while(p!=null&&sw==0){
            if(p.row==row&&p.column==colum){
                sw=1;
                return p;
            }
            p=p.next;
        }
        
        return null;
    }

    public List<Integer> valueList() {
        List<Integer> lista_values = new ArrayList<>();
        for (NodoDoble dato : this) {
            lista_values.add(dato.data);
        }
        return lista_values;
    }

    @Override
    public Iterator<NodoDoble> iterator() {
        List<NodoDoble> lista = new ArrayList<NodoDoble>();
        NodoDoble P = this.ptr;
        while (P != null) {
            lista.add(P);
            P = P.next;
        }
        return lista.iterator();
    }
}
