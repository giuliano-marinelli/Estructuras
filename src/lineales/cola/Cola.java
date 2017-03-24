package lineales.cola;

public class Cola<tipoElem> {

    private Nodo<tipoElem> frente;
    private Nodo<tipoElem> fin;

    public boolean poner(tipoElem elem) {
        Nodo<tipoElem> aux = new Nodo(elem);
        if (frente == null) {
            frente = aux;
            fin = aux;
        } else {
            fin.setEnlace(aux);
            fin = aux;
        }
        return true;
    }

    public boolean sacar() {
        boolean res;
        if (frente != null) {
            frente = frente.getEnlace();
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    public tipoElem obtenerFrente() {
        tipoElem res = null;
        if (frente != null) {
            res = frente.getElem();
        }
        return res;
    }

    public boolean esVacia() {
        return (frente == null);
    }

    public void vaciar() {
        frente = null;
        fin = null;
    }

    public Cola<tipoElem> clonar() {
        Cola<tipoElem> clon = new Cola();
        if (frente != null) {
            Nodo<tipoElem> original = frente.getEnlace();
            Nodo<tipoElem> aux = new Nodo(frente.getElem());
            clon.frente = aux;
            clon.fin = aux;
            while (original != null) {
                aux.setEnlace(new Nodo(original.getElem()));
                aux = aux.getEnlace();
                if (original.getEnlace() == null) {
                    clon.fin = aux;
                }
                original = original.getEnlace();
            }
        }
        return clon;
    }

    @Override
    public String toString() {
        String res = "";
        if (frente != null) {
            Nodo<tipoElem> aux = frente;
            res = "[";
            do {
                res += aux.getElem().toString();
                if (aux.getEnlace() != null) {
                    res += ", ";
                }
                aux = aux.getEnlace();
            } while (aux != null);
            res += "]";
        }
        return res;

    }
}
