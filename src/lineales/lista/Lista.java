package lineales.lista;

public class Lista<tipoElem> {

    private Nodo<tipoElem> cab;

    public boolean insertar(tipoElem elem, int pos) {
        Nodo<tipoElem> nuevo = new Nodo(elem);
        boolean res = true;
        if (cab == null) {
            cab = nuevo;
        } else if ((pos <= this.longitud() + 1) && (pos > 0)) {
            if (pos == 1) {
                nuevo.setEnlace(cab);
                cab = nuevo;
            } else {
                Nodo<tipoElem> aux = cab;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                nuevo.setEnlace(aux.getEnlace());
                aux.setEnlace(nuevo);
            }
        } else {
            res = false;
        }

        return res;
    }

    public boolean eliminar(int pos) {
        boolean res;
        if ((cab != null) && (pos <= this.longitud()) && (pos > 0)) {
            if (pos == 1) {
                cab = cab.getEnlace();
            } else {
                Nodo<tipoElem> aux = cab;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    public tipoElem recuperar(int pos) {
        tipoElem res = null;
        if ((cab != null) && (pos <= this.longitud()) && (pos > 0)) {
            Nodo<tipoElem> aux = cab;
            int i = 1;
            while (i < pos) {
                aux = aux.getEnlace();
                i++;
            }
            res = aux.getElem();
        }
        return res;
    }

    public int localizar(tipoElem elem) {
        int res = -1;
        boolean flag = false;
        if (cab != null) {
            Nodo<tipoElem> aux = cab;
            int i = 1;
            do {
                if (elem.equals(aux.getElem())) {
                    res = i;
                    flag = true;
                }
                aux = aux.getEnlace();
                i++;
            } while ((aux != null) && (!flag));
        }
        return res;
    }

    public int longitud() {
        int i = 0;
        if (cab != null) {
            Nodo<tipoElem> aux = cab;
            do {
                aux = aux.getEnlace();
                i++;
            } while (aux != null);
        }
        return i;
    }

    public boolean esVacia() {
        return (cab == null);
    }

    public void vaciar() {
        cab = null;
    }

    public Lista<tipoElem> clonar() {
        Lista<tipoElem> clon = new Lista();
        clon.cab = new Nodo(cab.getElem());
        Nodo<tipoElem> original = cab.getEnlace();
        Nodo<tipoElem> aux = clon.cab;
        while (original != null) {
            aux.setEnlace(new Nodo(original.getElem()));
            aux = aux.getEnlace();
            original = original.getEnlace();
        }
        return clon;
    }

    public void copiar(Lista<tipoElem> lista) {
        if (lista.cab != null) {
            this.cab = new Nodo(lista.cab.getElem());
            Nodo aux = cab;
            Nodo auxCopia = lista.cab.getEnlace();
            while (auxCopia != null) {
                aux.setEnlace(new Nodo(auxCopia.getElem()));
                aux = aux.getEnlace();
                auxCopia = auxCopia.getEnlace();
            }
        }
    }

    @Override
    public String toString() {
        String res = "";
        if (cab != null) {
            Nodo<tipoElem> aux = cab;
            int i = 1;
            res = "[";
            do {
                res += aux.getElem().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    res += ", ";
                }
                i++;
            } while (aux != null);
            res += "]";
        }
        return res;
    }

}
