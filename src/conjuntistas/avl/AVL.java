package conjuntistas.avl;

import conjuntistas.arbolBinarioBusqueda.*;

public class AVL<tipoElem extends Comparable> extends ArbolBinarioBusqueda<tipoElem> {

    @Override
    public boolean insertar(tipoElem elem) {
        boolean res = super.insertar(elem);
        if (res) {
            autobalancear(raiz);
        }
        return res;
    }

    @Override
    protected boolean insertarAux(NodoArbol<tipoElem> aux, tipoElem elem) {
        boolean res = super.insertarAux(aux, elem);
        if (res) {
            if (aux.getIzquierdo() != null) {
                aux.setIzquierdo(autobalancear(aux.getIzquierdo()));
            }
            if (aux.getDerecho() != null) {
                aux.setDerecho(autobalancear(aux.getDerecho()));
            }
        }
        return res;
    }

    @Override
    public boolean eliminar(tipoElem elem) {
        boolean res = super.eliminar(elem);
        if (res) {
            autobalancear(raiz);
        }
        return res;
    }

    @Override
    protected boolean eliminarAux(NodoArbol<tipoElem> aux, tipoElem elem) {
        boolean res = super.eliminarAux(aux, elem);
        if (res) {
            autobalancear(aux);
        }
        return res;
    }

    private NodoArbol<tipoElem> autobalancear(NodoArbol<tipoElem> n) {
        int balanceP;
        balanceP = alturaAux(n.getIzquierdo()) - alturaAux(n.getDerecho());
        if (balanceP < -1) {
            int balanceHD = alturaAux(n.getDerecho().getIzquierdo()) - alturaAux(n.getDerecho().getDerecho());
            if (balanceHD < 0) {
                rotacionSimpleIzq(n);
            } else {
                rotacionSimpleDer(n.getDerecho());
                rotacionSimpleIzq(n);
            }
        } else if (balanceP > 1) {
            int balanceHI = alturaAux(n.getIzquierdo().getIzquierdo()) - alturaAux(n.getIzquierdo().getDerecho());
            if (balanceHI > 0) {
                rotacionSimpleDer(n);
            } else {
                rotacionSimpleIzq(n.getIzquierdo());
                rotacionSimpleDer(n);
            }
        }
        return n;
    }

    private NodoArbol<tipoElem> rotacionSimpleIzq(NodoArbol<tipoElem> n) {
        tipoElem aux = n.getElem();
        n.setElem(n.getDerecho().getElem());
        NodoArbol<tipoElem> temp = n.getDerecho().getIzquierdo();
        n.setDerecho(n.getDerecho().getDerecho());
        if (n.getIzquierdo() != null) {
            NodoArbol<tipoElem> nuevoIzq = new NodoArbol(aux);
            nuevoIzq.setIzquierdo(n.getIzquierdo());
            n.setIzquierdo(nuevoIzq);
            n.getIzquierdo().setDerecho(temp);
        } else {
            n.setIzquierdo(new NodoArbol(aux));
            n.getIzquierdo().setDerecho(temp);
        }

        return n;
    }

    private NodoArbol<tipoElem> rotacionSimpleDer(NodoArbol<tipoElem> n) {
        tipoElem aux = n.getElem();
        n.setElem(n.getIzquierdo().getElem());
        NodoArbol<tipoElem> temp = n.getIzquierdo().getDerecho();
        n.setIzquierdo(n.getIzquierdo().getIzquierdo());
        if (n.getDerecho() != null) {
            NodoArbol<tipoElem> nuevoDer = new NodoArbol(aux);
            nuevoDer.setDerecho(n.getDerecho());
            n.setDerecho(nuevoDer);
            n.getDerecho().setIzquierdo(temp);
        } else {
            n.setDerecho(new NodoArbol(aux));
            n.getDerecho().setIzquierdo(temp);
        }
        return n;
    }

}
