package conjuntistas.arbolBinarioBusqueda;

import lineales.lista.Lista;
import test.arbolBinarioPrinter.ArbolBinarioPrinter;

public class ArbolBinarioBusqueda<tipoElem extends Comparable> {

    protected NodoArbol<tipoElem> raiz;

    public boolean insertar(tipoElem elem) {
        boolean res = true;
        if (raiz == null) {
            raiz = new NodoArbol(elem);
        } else {
            res = insertarAux(raiz, elem);
        }
        return res;
    }

    protected boolean insertarAux(NodoArbol<tipoElem> aux, tipoElem elem) {
        boolean res = true;
        if (elem.equals(aux.getElem())) {
            res = false;
        } else {
            if (elem.compareTo(aux.getElem()) < 0) {
                if (aux.getIzquierdo() != null) {
                    res = insertarAux(aux.getIzquierdo(), elem);
                } else {
                    aux.setIzquierdo(new NodoArbol(elem));
                }
            } else {
                if (aux.getDerecho() != null) {
                    res = insertarAux(aux.getDerecho(), elem);
                } else {
                    aux.setDerecho(new NodoArbol(elem));
                }
            }
        }
        return res;
    }

    public boolean eliminar(tipoElem elem) {
        boolean res = false;
        if (raiz != null) {
            if (raiz.getElem().equals(elem)) {
                if (esHoja(raiz)) {
                    raiz = null;
                } else if (soloHijoIzq(raiz)) {
                    raiz = raiz.getIzquierdo();
                } else if (soloHijoDer(raiz)) {
                    raiz = raiz.getDerecho();
                } else {
                    raiz = tercerCaso(raiz);
                }
                res = true;
            } else {
                res = eliminarAux(raiz, elem);
            }
        }
        return res;
    }

    protected boolean eliminarAux(NodoArbol<tipoElem> aux, tipoElem elem) {
        boolean res = false;
        if (aux != null) {
            if ((aux.getIzquierdo() != null) && (aux.getIzquierdo().getElem().equals(elem))) {
                if (esHoja(aux.getIzquierdo())) {
                    aux.setIzquierdo(null);
                } else if (soloHijoIzq(aux.getIzquierdo())) {
                    aux.setIzquierdo(aux.getIzquierdo().getIzquierdo());
                } else if (soloHijoDer(aux.getIzquierdo())) {
                    aux.setIzquierdo(aux.getIzquierdo().getDerecho());
                } else {
                    aux.setIzquierdo(tercerCaso(aux.getIzquierdo()));
                }
                res = true;
            } else if ((aux.getDerecho() != null) && (aux.getDerecho().getElem() == elem)) {
                if (esHoja(aux.getDerecho())) {
                    aux.setDerecho(null);
                } else if (soloHijoIzq(aux.getDerecho())) {
                    aux.setDerecho(aux.getDerecho().getIzquierdo());
                } else if (soloHijoDer(aux.getDerecho())) {
                    aux.setDerecho(aux.getDerecho().getDerecho());
                } else {
                    aux.setDerecho(tercerCaso(aux.getDerecho()));
                }
                res = true;
            } else {
                res = eliminarAux(aux.getIzquierdo(), elem);
                if (!res) {
                    res = eliminarAux(aux.getDerecho(), elem);
                }
            }
        }
        return res;
    }

    protected boolean esHoja(NodoArbol<tipoElem> aux) {
        return ((aux.getDerecho() == null) && (aux.getIzquierdo() == null));
    }

    protected boolean soloHijoDer(NodoArbol<tipoElem> aux) {
        return ((aux.getDerecho() != null) && (aux.getIzquierdo() == null));
    }

    protected boolean soloHijoIzq(NodoArbol<tipoElem> aux) {
        return ((aux.getDerecho() == null) && (aux.getIzquierdo() != null));
    }

    protected NodoArbol<tipoElem> tercerCaso(NodoArbol<tipoElem> aux) {
        NodoArbol<tipoElem> temp;
        temp = obtenerMinAux(aux.getDerecho());
        aux.setElem(temp.getElem());
        eliminarAux(aux, temp.getElem());
        return aux;
    }

    public boolean pertenece(tipoElem elem) {
        boolean res = false;
        if (raiz != null) {
            res = perteneceAux(raiz, elem);
        }
        return res;
    }

    protected boolean perteneceAux(NodoArbol<tipoElem> aux, tipoElem elem) {
        boolean res = false;
        if (aux != null) {
            if (elem == aux.getElem()) {
                res = true;
            } else {
                if (elem.compareTo(aux.getElem()) < 0) {
                    res = perteneceAux(aux.getIzquierdo(), elem);
                } else {
                    res = perteneceAux(aux.getDerecho(), elem);
                }
            }
        }
        return res;
    }

    public boolean esVacio() {
        return (raiz == null);
    }

    public tipoElem obtenerMin() {
        tipoElem res;
        if (raiz != null) {
            res = obtenerMinAux(raiz).getElem();
        } else {
            res = null;
        }
        return res;
    }

    protected NodoArbol<tipoElem> obtenerMinAux(NodoArbol<tipoElem> aux) {
        NodoArbol<tipoElem> res;
        if (aux.getIzquierdo() == null) {
            res = aux;
        } else {
            res = obtenerMinAux(aux.getIzquierdo());
        }
        return res;
    }

    public void eliminarMin() {
        if (raiz != null) {
            raiz = eliminarMinAux(raiz);
        }
    }

    protected NodoArbol<tipoElem> eliminarMinAux(NodoArbol<tipoElem> aux) {
        if (aux.getIzquierdo() == null) {
            aux = aux.getDerecho();
        } else {
            aux.setIzquierdo(eliminarMinAux(aux.getIzquierdo()));
        }
        return aux;
    }

    public tipoElem obtenerMax() {
        tipoElem res;
        if (raiz != null) {
            res = obtenerMaxAux(raiz).getElem();
        } else {
            res = null;
        }
        return res;
    }

    protected NodoArbol<tipoElem> obtenerMaxAux(NodoArbol<tipoElem> aux) {
        NodoArbol<tipoElem> res;
        if (aux.getDerecho() == null) {
            res = aux;
        } else {
            res = obtenerMaxAux(aux.getDerecho());
        }
        return res;
    }

    public void eliminarMax() {
        if (raiz != null) {
            raiz = eliminarMaxAux(raiz);
        }
    }

    protected NodoArbol<tipoElem> eliminarMaxAux(NodoArbol<tipoElem> aux) {
        if (aux.getDerecho() == null) {
            aux = aux.getIzquierdo();
        } else {
            aux.setDerecho(eliminarMaxAux(aux.getDerecho()));
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

    protected int alturaAux(NodoArbol<tipoElem> aux) {
        int suma;
        if (aux == null) {
            suma = -1;
        } else {
            suma = 1 + Math.max(alturaAux(aux.getIzquierdo()),
                    alturaAux(aux.getDerecho()));
        }
        return suma;
    }

    public Lista listar() {
        Lista res = new Lista();
        if (raiz != null) {
            res = new Lista();
            listarAux(res, raiz);
        }
        return res;
    }

    protected void listarAux(Lista res, NodoArbol<tipoElem> aux) {
        if (aux != null) {
            listarAux(res, aux.getIzquierdo());
            res.insertar(aux.getElem(), res.longitud() + 1);
            listarAux(res, aux.getDerecho());
        }
    }

    public Lista listarRango(tipoElem ini, tipoElem fin) {
        Lista res = new Lista();
        if (raiz != null) {
            res = new Lista();
            listarRangoAux(res, raiz, ini, fin);
        }
        return res;
    }

    protected void listarRangoAux(Lista res, NodoArbol<tipoElem> aux, tipoElem ini, tipoElem fin) {
        if (aux != null) {
            if (aux.getElem().compareTo(ini) > 0) {
                listarRangoAux(res, aux.getIzquierdo(), ini, fin);
            }
            if (aux.getElem().compareTo(ini) >= 0 && aux.getElem().compareTo(fin) <= 0) {
                res.insertar(aux.getElem(), res.longitud() + 1);
            }
            if (aux.getElem().compareTo(fin) < 0) {
                listarRangoAux(res, aux.getDerecho(), ini, fin);
            }
        }
    }

    public void vaciar() {
        raiz = null;
    }

    public ArbolBinarioBusqueda<tipoElem> clonar() {
        ArbolBinarioBusqueda<tipoElem> clon = new ArbolBinarioBusqueda();
        if (raiz != null) {
            clon.raiz = new NodoArbol(raiz.getElem());
            clonarAux(raiz, clon.raiz);
        }
        return clon;
    }

    protected void clonarAux(NodoArbol<tipoElem> aux, NodoArbol<tipoElem> nuevo) {
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

    protected String toStringAux(NodoArbol<tipoElem> aux) {
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

    //METODO TEMPORAL (ELIMINAR) Sirve para dibujar el arbol
    public void print() {
        ArbolBinarioPrinter.printNode(raiz);
    }

}
