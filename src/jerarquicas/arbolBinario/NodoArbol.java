package jerarquicas.arbolBinario;

public class NodoArbol<tipoElem> {

    private tipoElem elem;
    private NodoArbol<tipoElem> izquierdo;
    private NodoArbol<tipoElem> derecho;

    public NodoArbol() {
    }

    public NodoArbol(tipoElem elem) {
        this.elem = elem;
    }

    public tipoElem getElem() {
        return elem;
    }

    public void setElem(tipoElem elem) {
        this.elem = elem;
    }

    public NodoArbol<tipoElem> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbol<tipoElem> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoArbol<tipoElem> getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbol<tipoElem> derecho) {
        this.derecho = derecho;
    }

}
