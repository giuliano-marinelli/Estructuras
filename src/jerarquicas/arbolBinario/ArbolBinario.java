package jerarquicas.arbolBinario;

import lineales.lista.Lista;

public class ArbolBinario<tipoElem> {

    private NodoArbol<tipoElem> raiz;

    public boolean insertar(tipoElem elem, tipoElem elemPadre, char lugar) {
        boolean res = true;
        if (raiz == null) {
            raiz = new NodoArbol(elem);
        } else {
            NodoArbol<tipoElem> padre = buscarNodo(raiz, elemPadre);
            if (padre != null) {
                if ((lugar == 'I') && (padre.getIzquierdo() == null)) {
                    padre.setIzquierdo(new NodoArbol(elem));
                } else if ((lugar == 'D') && (padre.getDerecho() == null)) {
                    padre.setDerecho(new NodoArbol(elem));
                } else {
                    res = false;
                }
            } else {
                res = false;
            }
        }
        return res;
    }

    public boolean eliminar(tipoElem elem) {
        boolean res;
        NodoArbol<tipoElem> aux = padreAux(raiz, elem);
        if (aux != null) {
            if (aux.getDerecho().getElem().equals(elem)) {
                aux.setDerecho(null);
            } else {
                aux.setIzquierdo(null);
            }
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    public boolean esVacio() {
        return (raiz == null);
    }

    private NodoArbol<tipoElem> buscarNodo(NodoArbol<tipoElem> ini, tipoElem elem) {
        NodoArbol res;
        if ((ini == null) || (ini.getElem().equals(elem))) {
            res = ini;
        } else {
            res = buscarNodo(ini.getIzquierdo(), elem);
            if (res == null) {
                res = buscarNodo(ini.getDerecho(), elem);
            }
        }
        return res;
    }

    public tipoElem padre(tipoElem elem) {
        tipoElem res;
        NodoArbol<tipoElem> padre = padreAux(raiz, elem);
        if (padre != null) {
            res = padre.getElem();
        } else {
            res = null;
        }
        return res;
    }

    private NodoArbol<tipoElem> padreAux(NodoArbol<tipoElem> nodo, tipoElem elem) {
        NodoArbol<tipoElem> aux = null;
        if (nodo != null) {
            if (nodo.getIzquierdo() != null) {
                if (!nodo.getIzquierdo().getElem().equals(elem)) {
                    aux = padreAux(nodo.getIzquierdo(), elem);
                } else {
                    aux = nodo;
                }
            }
            if (aux == null) {
                if (nodo.getDerecho() != null) {
                    if (!nodo.getDerecho().getElem().equals(elem)) {
                        aux = padreAux(nodo.getDerecho(), elem);
                    } else {
                        aux = nodo;
                    }
                }
            }
        }
        return aux;
    }

    public int altura() {
        int res = -1;
        if (raiz != null) {
            res = alturaAux(raiz);
        }
        return res;
    }

    private int alturaAux(NodoArbol<tipoElem> nodo) {
        int suma;
        if (nodo == null) {
            suma = -1;
        } else {
            suma = 1 + Math.max(alturaAux(nodo.getIzquierdo()),
                    alturaAux(nodo.getDerecho()));
        }
        return suma;
    }

    public int nivel(tipoElem elem) {
        int res;
        if (raiz == null) {
            res = -1;
        } else {
            res = nivelAux(raiz, elem);
        }
        return res;
    }

    private int nivelAux(NodoArbol<tipoElem> ini, tipoElem elem) {
        int res = 0;
        if (ini == null) {
            res = 0;
        } else if (raiz.getElem().equals(elem)) {
            res = 0;
        } else if (ini.getElem().equals(elem)) {
            res = 1;
        } else {
            res += nivelAux(ini.getIzquierdo(), elem);
            if (res < 1) {
                res += nivelAux(ini.getDerecho(), elem);
            }
            if (res >= 1 && !ini.getElem().equals(raiz.getElem())) {
                res += 1;
            }
        }
        return res;
    }

    public Lista listarPreorden() {
        Lista res = new Lista();
        if (raiz != null) {
            res = new Lista();
            listarPreordenAux(raiz, res);
        }
        return res;
    }

    private void listarPreordenAux(NodoArbol<tipoElem> nodo, Lista res) {
        if (nodo != null) {
            res.insertar(nodo.getElem(), res.longitud() + 1);
            listarPreordenAux(nodo.getIzquierdo(), res);
            listarPreordenAux(nodo.getDerecho(), res);
        }
    }

    public Lista listarPosorden() {
        Lista res = new Lista();
        if (raiz != null) {
            res = new Lista();
            listarPosordenAux(raiz, res);
        }
        return res;
    }

    private void listarPosordenAux(NodoArbol<tipoElem> nodo, Lista res) {
        if (nodo != null) {
            listarPosordenAux(nodo.getIzquierdo(), res);
            listarPosordenAux(nodo.getDerecho(), res);
            res.insertar(nodo.getElem(), res.longitud() + 1);
        }
    }

    public Lista listarInorden() {
        Lista res = new Lista();
        if (raiz != null) {
            res = new Lista();
            listarInordenAux(raiz, res);
        }
        return res;
    }

    private void listarInordenAux(NodoArbol<tipoElem> nodo, Lista res) {
        if (nodo != null) {
            listarInordenAux(nodo.getIzquierdo(), res);
            res.insertar(nodo.getElem(), res.longitud() + 1);
            listarInordenAux(nodo.getDerecho(), res);
        }
    }

    public void vaciar() {
        raiz = null;
    }

    public ArbolBinario<tipoElem> clonar() {
        ArbolBinario<tipoElem> clon = new ArbolBinario();
        if (raiz != null) {
            clon.raiz = new NodoArbol(raiz.getElem());
            clonarAux(raiz, clon.raiz);
        }
        return clon;
    }

    private void clonarAux(NodoArbol<tipoElem> aux, NodoArbol<tipoElem> nuevo) {
        if (aux.getIzquierdo() != null) {
            nuevo.setIzquierdo(new NodoArbol(aux.getIzquierdo().getElem()));
            clonarAux(aux.getIzquierdo(), nuevo.getIzquierdo());
        }
        if (aux.getDerecho() != null) {
            nuevo.setDerecho(new NodoArbol(aux.getDerecho().getElem()));
            clonarAux(aux.getDerecho(), nuevo.getDerecho());
        }
    }

    @Override
    public String toString() {
        String res = "";
        if (raiz != null) {
            res += "[";
            res += toStringAux(raiz);
            res += "]";
        }
        return res;
    }

    private String toStringAux(NodoArbol<tipoElem> aux) {
        String res = aux.getElem().toString();
        res += "(";
        if (aux.getIzquierdo() != null) {
            res += aux.getIzquierdo().getElem().toString();
        } else {
            res += "null";
        }
        res += ", ";
        if (aux.getDerecho() != null) {
            res += aux.getDerecho().getElem().toString();
        } else {
            res += "null";
        }
        res += ")";
        if (aux.getIzquierdo() != null) {
            res += ", " + toStringAux(aux.getIzquierdo());
        }
        if (aux.getDerecho() != null) {
            res += ", " + toStringAux(aux.getDerecho());
        }
        return res;
    }

}
