package lineales.pila;

public class Pila<tipoElem> {

    private Nodo<tipoElem> tope = null;

    public boolean apilar(tipoElem elem) {
        Nodo<tipoElem> aux = new Nodo(elem, this.tope);
        tope = aux;
        return true;
    }

    public boolean desapilar() {
        boolean res;
        if (tope != null) {
            tope = tope.getEnlace();
            res = true;
        } else {
            res = false;
        }

        return res;
    }

    public tipoElem obtenerTope() {
        tipoElem res = null;
        if (tope != null) {
            res = tope.getElem();
        }
        return res;
    }

    public boolean esVacia() {
        return (tope == null);
    }

    public void vaciar() {
        tope = null;
    }

    public Pila<tipoElem> clonar() {
        Pila<tipoElem> clon = new Pila();
        if (tope != null) {
            clon.tope = new Nodo(tope.getElem());
            Nodo<tipoElem> original = tope.getEnlace();
            Nodo<tipoElem> aux = clon.tope;
            while (original != null) {
                aux.setEnlace(new Nodo(original.getElem()));
                aux = aux.getEnlace();
                original = original.getEnlace();
            }
        }
        return clon;
    }

    @Override
    public String toString() {
        String res = "";
        if (tope != null) {
            res = "[";
            Nodo<tipoElem> aux = this.tope;
            while (aux != null) {
                res += aux.getElem().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    res += ", ";
                }
            }
            res += "]";
        }
        return res;
    }

}
