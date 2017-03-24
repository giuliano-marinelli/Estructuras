package grafos;

public class NodoAdy<tipoElem> {

    private NodoVert<tipoElem> vertice;
    private NodoAdy<tipoElem> sigAdyacente;
    private double etiqueta;

    public NodoAdy(NodoVert<tipoElem> vertice, double etiqueta) {
        this.vertice = vertice;
        this.etiqueta = etiqueta;
    }

    public NodoVert<tipoElem> getVertice() {
        return vertice;
    }

    public void setVertice(NodoVert<tipoElem> vertice) {
        this.vertice = vertice;
    }

    public NodoAdy<tipoElem> getSigAdyacente() {
        return sigAdyacente;
    }

    public void setSigAdyacente(NodoAdy<tipoElem> sigAdyacente) {
        this.sigAdyacente = sigAdyacente;
    }

    public double getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(double etiqueta) {
        this.etiqueta = etiqueta;
    }

}
