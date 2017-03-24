package lineales.lista;

public class Nodo<tipoElem> {

    private tipoElem elem;
    private Nodo<tipoElem> enlace;

    public Nodo() {
    }

    public Nodo(tipoElem elem) {
        this.elem = elem;
    }

    public Nodo(tipoElem elem, Nodo<tipoElem> enlace) {
        this.elem = elem;
        this.enlace = enlace;
    }

    public tipoElem getElem() {
        return elem;
    }

    public void setElem(tipoElem elem) {
        this.elem = elem;
    }

    public Nodo<tipoElem> getEnlace() {
        return enlace;
    }

    public void setEnlace(Nodo<tipoElem> enlace) {
        this.enlace = enlace;
    }

}
