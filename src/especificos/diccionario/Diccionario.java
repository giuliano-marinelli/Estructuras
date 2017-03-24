package especificos.diccionario;

import lineales.lista.Lista;
import conjuntistas.avl.AVL;
import java.util.HashMap;

public class Diccionario<tipoClave extends Comparable, tipoDato> {

    private AVL<tipoClave> arbolClaves;
    private HashMap<tipoClave, tipoDato> tablaDatos;

    public Diccionario() {
        arbolClaves = new AVL();
        tablaDatos = new HashMap();
    }

    public boolean insertar(tipoClave clave, tipoDato dato) {
        boolean res;
        res = arbolClaves.insertar(clave);
        if (res) {
            tablaDatos.put(clave, dato);
        }
        return res;
    }

    public boolean eliminar(tipoClave clave) {
        boolean res;
        res = arbolClaves.eliminar(clave);
        if (res) {
            tablaDatos.remove(clave);
        }
        return res;
    }

    public boolean existeClave(tipoClave clave) {
        return arbolClaves.pertenece(clave);
    }

    public tipoDato obtenerDato(tipoClave clave) {
        return tablaDatos.get(clave);
    }

    public Lista listarClaves() {
        return arbolClaves.listar();
    }

    public Lista listarDatos() {
        Lista<tipoClave> listaClaves = arbolClaves.listar();
        Lista<tipoDato> listaDatos = new Lista();
        for (int i = 1; i <= listaClaves.longitud(); i++) {
            listaDatos.insertar(tablaDatos.get(listaClaves.recuperar(i)), i);
        }
        return listaDatos;
    }

    public Lista listarClavesRango(tipoClave ini, tipoClave fin) {
        return arbolClaves.listarRango(ini, fin);
    }

    public boolean esVacio() {
        return arbolClaves.esVacio();
    }
    
    public void print() {
        arbolClaves.print();
    }

}
