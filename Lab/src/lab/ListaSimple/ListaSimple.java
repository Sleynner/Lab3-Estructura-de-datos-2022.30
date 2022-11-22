package lab.ListaSimple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListaSimple implements Iterable<NodoSimple> {

    public NodoSimple ptr;
    public NodoSimple ult;

    public ListaSimple() {
        this.ptr = null;
        this.ult = null;
    }

    public void addNodo(NodoSimple p) {
        if (this.ptr == null) {
            this.ptr = this.ult = p;
        } else {
            this.ult.next = p;
            this.ult = this.ult.next;
        }
    }

    public int size() {
        NodoSimple P = this.ptr;
        int count = 0;
        while (P != null) {
            count++;
            P = P.next;
        }
        return count;
    }

    public List<Integer> valueList() {
        List<Integer> lista_values = new ArrayList<>();
        for (NodoSimple dato : this) {
            lista_values.add(dato.data);
        }
        return lista_values;
    }
    
    public NodoSimple obtener(int row,int colum){
        
        NodoSimple p=this.ptr;
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
    
    @Override
    public Iterator<NodoSimple> iterator() {
        List<NodoSimple> lista = new ArrayList<NodoSimple>();
        NodoSimple P = this.ptr;
        while (P != null) {
            lista.add(P);
            P = P.next;
        }
        return lista.iterator();
    }

}
