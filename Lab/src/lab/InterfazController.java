/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import lab.ListaDoble.ListaDobleEnlazada;
import lab.ListaDoble.NodoDoble;
import lab.ListaSimple.ListaSimple;
import lab.ListaSimple.NodoSimple;
import lab.Multilista.ListaFila;
import lab.Multilista.NodoColumna;
import lab.Multilista.NodoFila;

public class InterfazController implements Initializable {

    List<File> files = new ArrayList<>();
    List<ListaSimple> ls = new ArrayList<>();//Listas simple
    List<ListaDobleEnlazada> ld = new ArrayList<>();//Listas doblemente enlazada
    List<ListaFila> ml = new ArrayList<>();//Multilistas
    Scanner sc = new Scanner(System.in);

    @FXML
    private Button btnFileChooser,btnOp,btnMultEsc;

    @FXML
    private TextField textM1, textM2,textM3,textE;

    @FXML
    private ComboBox<String> operacionM;

    @FXML
    void fileChooser(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("CSV,TXT,XLS FILES", "*.csv", "*.txt", "*.xls"));
        List<File> filesChoose = fc.showOpenMultipleDialog(null);
        this.files.addAll(filesChoose);
        for (File f : filesChoose) {
            leerArchivo(this.sc, f, new ListaSimple(), new ListaDobleEnlazada(), new ListaFila());
        }
    }

    @FXML
    void operacion() {
        String operacion = operacionM.getValue();
        ListaSimple l1 = ls.get(Integer.parseInt(textM1.getText()));
        ListaSimple l2 = ls.get(Integer.parseInt(textM2.getText()));
        ListaDobleEnlazada result = sumMatrices(l1, l2);

        switch (operacion) {
            case "Sumar":
                result = sumMatrices(l1, l2);
                break;
            case "Restar":
                result = restMatrices(l1, l2);
                break;
            case "Multiplicar":

                break;
            default:
                throw new AssertionError();
        }
    }
    @FXML
    void mult(){
        ListaDobleEnlazada l1 = ld.get(Integer.parseInt(textM3.getText()));
        multEscalar(Integer.parseInt(textE.getText()),l1);
    }
    void leerArchivo(Scanner sc, File file, ListaSimple ls, ListaDobleEnlazada ld, ListaFila ml) {

        int row = 0, column = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
            String line = null;
            String separador;
            if (file.getName().contains(".csv")) {
                separador = ",";
            } else {
                separador = "\t";
            }
            while ((line = br.readLine()) != null) {
                String temp[] = line.split(separador);
                NodoFila f = new NodoFila(row);
                for (String t : temp) {
                    if (Integer.parseInt(t) != 0) {
                        NodoSimple p = new NodoSimple(row, column, Integer.parseInt(t));
                        ls.addNodo(p);
                        NodoDoble q = new NodoDoble(row, column, Integer.parseInt(t));
                        ld.addNodo(q);
                        NodoColumna c = new NodoColumna(column, Integer.parseInt(t));
                        f.columnas.addNodo(c);
                        column++;
                        ml.addNodo(f);
                    }
                }
                column = 0;
                row++;
            }

            br.close();

        } catch (IOException ex) {
            System.out.println("No se encontro archivo");

        }
        this.ls.add(ls);
        this.ld.add(ld);
        this.ml.add(ml);
    }

    ListaDobleEnlazada sumMatrices(ListaSimple m1, ListaSimple m2) {

        NodoSimple p = m1.ptr;
        NodoSimple q = m2.ptr;
        ListaDobleEnlazada l1 = new ListaDobleEnlazada();

        while (p != null) {
            int val;
            if (m2.obtener(p.row, p.column) != null) {
                val = p.data + m2.obtener(p.row, p.column).data;
            } else {
                val = p.data;
            }
            NodoDoble n = new NodoDoble(p.row, p.column, val);
            l1.addDimension(n, p.row, p.column);
            p = p.next;
        }

        while (q != null) {
            if (l1.obtener(q.row, q.column) == null) {
                int val;
                if (m1.obtener(q.row, q.column) != null) {
                    val = q.data + m1.obtener(p.row, p.column).data;
                } else {
                    val = q.data;
                }
                NodoDoble n = new NodoDoble(q.row, q.column, val);
                l1.addDimension(n, q.row, q.column);
            }
            q = q.next;
        }

        return l1;
    }

    ListaDobleEnlazada restMatrices(ListaSimple m1, ListaSimple m2) {

        NodoSimple p = m1.ptr;
        NodoSimple q = m2.ptr;
        ListaDobleEnlazada l1 = new ListaDobleEnlazada();

        while (p != null) {
            int val;
            if (m2.obtener(p.row, p.column) != null) {
                val = p.data - m2.obtener(p.row, p.column).data;
            } else {
                val = p.data;
            }
            NodoDoble n = new NodoDoble(p.row, p.column, val);
            l1.addDimension(n, p.row, p.column);
            p = p.next;
        }

        while (q != null) {
            if (l1.obtener(q.row, q.column) == null) {
                int val;
                if (m1.obtener(q.row, q.column) != null) {
                    val = m1.obtener(p.row, p.column).data - q.data;
                } else {
                    val = q.data;
                }
                NodoDoble n = new NodoDoble(q.row, q.column, val);
                l1.addDimension(n, q.row, q.column);
            }
            q = q.next;
        }

        return l1;
    }
    
    ListaDobleEnlazada multEscalar(int escalar,ListaDobleEnlazada lista){
        ListaDobleEnlazada l1=new ListaDobleEnlazada();
        
        NodoDoble p=lista.ptr;
        
        while(p!=null){
            NodoDoble n=new NodoDoble(p.row,p.column,p.data*escalar);
            l1.addNodo(n);
        }
        
        return l1;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> operaciones = new ArrayList<>();
        Collections.addAll(operaciones, new String[]{"Sumar", "Restar", "Multiplicar"});
        operacionM.getItems().addAll(operaciones);
    }

}
