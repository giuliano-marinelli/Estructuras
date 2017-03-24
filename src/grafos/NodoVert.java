package grafos;

public class NodoVert<tipoElem> {

    private tipoElem elem;
    private NodoVert<tipoElem> sigVertice;
    private NodoAdy<tipoElem> primerAdy;

    public NodoVert(tipoElem elem) {
        this.elem = elem;
    }

    public NodoVert(tipoElem elem, NodoVert<tipoElem> sigVertice) {
        this.elem = elem;
        this.sigVertice = sigVertice;
    }

    public tipoElem getElem() {
        return elem;
    }

    public void setElem(tipoElem elem) {
        this.elem = elem;
    }

    public NodoVert<tipoElem> getSigVertice() {
        return sigVertice;
    }

    public void setSigVertice(NodoVert<tipoElem> sigVertice) {
        this.sigVertice = sigVertice;
    }

    public NodoAdy<tipoElem> getPrimerAdy() {
        return primerAdy;
    }

    public void setPrimerAdy(NodoAdy<tipoElem> primerAdy) {
        this.primerAdy = primerAdy;
    }

}
