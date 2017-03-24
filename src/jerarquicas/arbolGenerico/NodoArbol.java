package jerarquicas.arbolGenerico;

public class NodoArbol<tipoElem> {

    private tipoElem elem;
    private NodoArbol<tipoElem> hijoIzq;
    private NodoArbol<tipoElem> hermanoDer;

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

    public NodoArbol<tipoElem> getHijoIzq() {
        return hijoIzq;
    }

    public void setHijoIzq(NodoArbol<tipoElem> hijoIzq) {
        this.hijoIzq = hijoIzq;
    }

    public NodoArbol<tipoElem> getHermanoDer() {
        return hermanoDer;
    }

    public void setHermanoDer(NodoArbol<tipoElem> hermanoDer) {
        this.hermanoDer = hermanoDer;
    }
}
