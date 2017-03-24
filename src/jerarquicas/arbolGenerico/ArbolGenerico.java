package jerarquicas.arbolGenerico;

import lineales.lista.Lista;

public class ArbolGenerico<tipoElem> {

    private NodoArbol<tipoElem> raiz;

    public boolean insertar(tipoElem elem, tipoElem padre) {
        boolean res = true;
        if (raiz == null) {
            raiz = new NodoArbol(elem);
        } else {
            NodoArbol<tipoElem> nodoPadre = obtenerNodo(raiz, padre);
            if (nodoPadre != null) {
                NodoArbol<tipoElem> aux = new NodoArbol(elem);
                aux.setHermanoDer(nodoPadre.getHijoIzq());
                nodoPadre.setHijoIzq(aux);
            } else {
                res = false;
            }
        }
        return res;
    }

    public boolean pertenece(tipoElem elem) {
        return (obtenerNodo(raiz, elem) != null);
    }

    private NodoArbol<tipoElem> obtenerNodo(NodoArbol<tipoElem> ini, tipoElem elem) {
        NodoArbol res = null;
        if (ini != null) {
            if (ini.getElem().equals(elem)) {
                res = ini;
            } else {
                res = obtenerNodo(ini.getHermanoDer(), elem);
                if (res == null) {
                    res = obtenerNodo(ini.getHijoIzq(), elem);
                }
            }
        }
        return res;
    }

    public tipoElem padre(tipoElem elem) {
        tipoElem res = null;
        if (raiz != null) {
            if (!raiz.getElem().equals(elem)) {
                NodoArbol<tipoElem> padre = padreAux(raiz, elem);
                if (padre != null) {
                    res = padre.getElem();
                }
            }
        }
        return res;
    }

    private NodoArbol<tipoElem> padreAux(NodoArbol<tipoElem> ini, tipoElem elem) {
        NodoArbol<tipoElem> res = null;
        NodoArbol<tipoElem> aux = ini.getHijoIzq();
        while (aux != null && res == null) {
            if (aux.getElem().equals(elem)) {
                res = ini;
            } else {
                aux = aux.getHermanoDer();
            }
        }
        if (res == null) {
            aux = ini.getHijoIzq();
            while (aux != null && res == null) {
                res = padreAux(aux, elem);
                aux = aux.getHermanoDer();
            }
        }
        return res;
    }

    public boolean esVacio() {
        return (raiz == null);
    }

    public Lista ancestros(tipoElem elem) {
        Lista res = new Lista();
        if (raiz != null) {
            ancestrosAux(res, raiz, elem);
        }
        return res;
    }

    private void ancestrosAux(Lista res, NodoArbol<tipoElem> aux, tipoElem elem) {
        if (elem.equals(aux.getElem())) {
            res.insertar(aux.getElem(), 1);
        } else {
            if (aux.getHermanoDer() != null) {
                ancestrosAux(res, aux.getHermanoDer(), elem);
            }
            if (res.esVacia()) {
                if (aux.getHijoIzq() != null) {
                    ancestrosAux(res, aux.getHijoIzq(), elem);
                    if (!res.esVacia()) {
                        res.insertar(aux.getElem(), 1);
                    }
                }
            }
        }
    }

    public int altura() {
        int res = -1;
        if (raiz != null) {
            res = alturaAux(raiz, 0);
        }
        return res;
    }

    private int alturaAux(NodoArbol<tipoElem> aux, int suma) {
        int res = 0;
        if (aux.getHijoIzq() == null && aux.getHermanoDer() == null) {
            res = suma;
        } else {
            if (aux.getHermanoDer() != null) {
                res = alturaAux(aux.getHermanoDer(), suma);
            }
            if (aux.getHijoIzq() != null) {
                res = Math.max(res, alturaAux(aux.getHijoIzq(), suma + 1));
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

    private void listarPreordenAux(NodoArbol<tipoElem> aux, Lista res) {
        if (aux != null) {
            res.insertar(aux.getElem(), res.longitud() + 1);
            NodoArbol<tipoElem> temp = aux.getHijoIzq();
            while (temp != null) {
                listarPreordenAux(temp, res);
                temp = temp.getHermanoDer();
            }
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

    private void listarPosordenAux(NodoArbol<tipoElem> aux, Lista res) {
        if (aux != null) {
            NodoArbol<tipoElem> temp = aux.getHijoIzq();
            while (temp != null) {
                listarPosordenAux(temp, res);
                temp = temp.getHermanoDer();
            }
            res.insertar(aux.getElem(), res.longitud() + 1);
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

    private void listarInordenAux(NodoArbol<tipoElem> aux, Lista res) {
        if (aux != null) {
            NodoArbol<tipoElem> temp = aux.getHijoIzq();
            if (temp != null) {
                listarInordenAux(temp, res);
                temp = temp.getHermanoDer();
            }
            res.insertar(aux.getElem(), res.longitud() + 1);
            while (temp != null) {
                listarInordenAux(temp, res);
                temp = temp.getHermanoDer();
            }

        }
    }

    public void vaciar() {
        raiz = null;
    }
}
