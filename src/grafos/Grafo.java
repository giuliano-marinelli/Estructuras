package grafos;

import lineales.cola.Cola;
import lineales.lista.Lista;

public class Grafo<tipoElem> {

    private NodoVert<tipoElem> inicio;

    public boolean insertarVertice(tipoElem elem) {
        boolean res = false;
        NodoVert<tipoElem> aux = ubicarVertice(elem);
        if (aux == null) {
            inicio = new NodoVert(elem, inicio);
            res = true;
        }
        return res;
    }

    public boolean eliminarVertice(tipoElem elem) {
        boolean res = false;
        if (inicio != null) {
            NodoAdy<tipoElem> auxAdy = null;
            NodoVert<tipoElem> aux = inicio;
            if (aux.getElem().equals(elem)) {
                auxAdy = aux.getPrimerAdy();
                inicio = inicio.getSigVertice();
                res = true;
            } else {
                while (!res && aux.getSigVertice() != null) {
                    if (aux.getSigVertice().getElem().equals(elem)) {
                        auxAdy = aux.getSigVertice().getPrimerAdy();
                        aux.setSigVertice(aux.getSigVertice().getSigVertice());
                        res = true;
                    }
                    aux = aux.getSigVertice();
                }
            }
            if (res) {
                while (auxAdy != null) {
                    eliminarArcoAux(auxAdy.getVertice(), elem);
                    auxAdy = auxAdy.getSigAdyacente();
                }
            }
        }
        return res;
    }

    private NodoVert<tipoElem> ubicarVertice(tipoElem buscado) {
        NodoVert<tipoElem> aux = inicio;
        while (aux != null && !(aux.getElem().equals(buscado))) {
            aux = aux.getSigVertice();
        }
        return aux;
    }

    public boolean existeVertice(tipoElem buscado) {
        boolean res = false;
        NodoVert<tipoElem> aux = inicio;
        while (!res && aux != null) {
            if (aux.getElem().equals(buscado)) {
                res = true;
            }
            aux = aux.getSigVertice();
        }
        return res;
    }

    public boolean insertarArco(tipoElem origen, tipoElem destino, double etiqueta) {
        boolean res = false;
        if (inicio != null) {
            NodoVert<tipoElem> nodoOrigen = null;
            NodoVert<tipoElem> nodoDestino = null;
            NodoVert<tipoElem> aux1 = inicio;
            boolean encontroOrigen = false;
            boolean encontroDestino = false;
            while (aux1 != null && (!encontroOrigen || !encontroDestino)) {
                if (aux1.getElem().equals(origen)) {
                    nodoOrigen = aux1;
                    encontroOrigen = true;
                }
                if (aux1.getElem().equals(destino)) {
                    nodoDestino = aux1;
                    encontroDestino = true;
                }
                aux1 = aux1.getSigVertice();
            }
            if (nodoOrigen != null && nodoDestino != null) {
                if (nodoOrigen.getPrimerAdy() == null) {
                    nodoOrigen.setPrimerAdy(new NodoAdy(nodoDestino, etiqueta));
                    res = true;
                } else {
                    NodoAdy<tipoElem> aux2 = nodoOrigen.getPrimerAdy();
                    while (aux2 != null && !(aux2.getVertice().getElem().equals(destino))) {
                        if (aux2.getSigAdyacente() == null) {
                            aux2.setSigAdyacente(new NodoAdy(nodoDestino, etiqueta));
                            res = true;
                        }
                        aux2 = aux2.getSigAdyacente();
                    }
                }
                if (res) {
                    NodoAdy<tipoElem> aux3 = new NodoAdy(nodoOrigen, etiqueta);
                    aux3.setSigAdyacente(nodoDestino.getPrimerAdy());
                    nodoDestino.setPrimerAdy(aux3);
                }
            }
        }
        return res;
    }

    public boolean eliminarArco(tipoElem origen, tipoElem destino) {
        boolean res = false;
        NodoVert<tipoElem> nodoOrigen = null;
        NodoVert<tipoElem> nodoDestino = null;
        NodoVert<tipoElem> aux = inicio;
        boolean encontroOrigen = false;
        boolean encontroDestino = false;
        while (aux != null && (!encontroOrigen || !encontroDestino)) {
            if (aux.getElem().equals(origen)) {
                nodoOrigen = aux;
                encontroOrigen = true;
            }
            if (aux.getElem().equals(destino)) {
                nodoDestino = aux;
                encontroDestino = true;
            }
            aux = aux.getSigVertice();
        }
        if (nodoOrigen != null && nodoDestino != null) {
            res = eliminarArcoAux(nodoOrigen, destino);
            if (res) {
                eliminarArcoAux(nodoDestino, origen);
            }
        }
        return res;
    }

    private boolean eliminarArcoAux(NodoVert<tipoElem> nodoOrigen, tipoElem elemDestino) {
        boolean res = false;
        NodoAdy<tipoElem> aux = nodoOrigen.getPrimerAdy();
        if (aux != null) {
            if (nodoOrigen.getPrimerAdy().getVertice().getElem().equals(elemDestino)) {
                nodoOrigen.setPrimerAdy(nodoOrigen.getPrimerAdy().getSigAdyacente());
                res = true;
            } else {
                while (!res && aux.getSigAdyacente() != null) {
                    if (aux.getSigAdyacente().getVertice().getElem().equals(elemDestino)) {
                        aux.setSigAdyacente(aux.getSigAdyacente().getSigAdyacente());
                        res = true;
                    }
                    aux = aux.getSigAdyacente();
                }
            }
        }
        return res;
    }

    public boolean existeArco(tipoElem origen, tipoElem destino) {
        boolean res = false;
        NodoVert<tipoElem> nodoOrigen = null;
        NodoVert<tipoElem> nodoDestino = null;
        NodoVert<tipoElem> aux = inicio;
        boolean encontroOrigen = false;
        boolean encontroDestino = false;
        while (aux != null && (!encontroOrigen || !encontroDestino)) {
            if (aux.getElem().equals(origen)) {
                nodoOrigen = aux;
                encontroOrigen = true;
            }
            if (aux.getElem().equals(destino)) {
                nodoDestino = aux;
                encontroDestino = true;
            }
            aux = aux.getSigVertice();
        }
        NodoAdy<tipoElem> auxAdy = nodoOrigen.getPrimerAdy();
        while (!res && auxAdy != null) {
            if (auxAdy.getVertice().equals(nodoDestino)) {
                res = true;
            }
            auxAdy = auxAdy.getSigAdyacente();
        }
        return res;
    }

    public boolean existeCamino(tipoElem origen, tipoElem destino) {
        boolean res = false;
        NodoVert<tipoElem> nodoOrigen = null;
        NodoVert<tipoElem> nodoDestino = null;
        NodoVert<tipoElem> aux = inicio;
        boolean encontroOrigen = false;
        boolean encontroDestino = false;
        while (aux != null && (!encontroOrigen || !encontroDestino)) {
            if (aux.getElem().equals(origen)) {
                nodoOrigen = aux;
                encontroOrigen = true;
            }
            if (aux.getElem().equals(destino)) {
                nodoDestino = aux;
                encontroDestino = true;
            }
            aux = aux.getSigVertice();
        }
        if (nodoOrigen != null && nodoDestino != null) {
            Lista visitados = new Lista();
            res = existeCaminoAux(nodoOrigen, destino, visitados);
        }
        return res;
    }

    public boolean existeCaminoAux(NodoVert<tipoElem> aux, tipoElem destino, Lista visitados) {
        boolean res = false;
        if (aux != null) {
            if (aux.getElem().equals(destino)) {
                res = true;
            } else {
                visitados.insertar(aux.getElem(), visitados.longitud() + 1);
                NodoAdy<tipoElem> auxAdy = aux.getPrimerAdy();
                while (!res && auxAdy != null) {
                    if (visitados.localizar(auxAdy.getVertice().getElem()) < 0) {
                        res = existeCaminoAux(auxAdy.getVertice(), destino, visitados);
                    }
                    auxAdy = auxAdy.getSigAdyacente();
                }
            }
        }
        return res;
    }

    public Lista listarArcos() {
        Lista<String[]> res = new Lista();
        Lista<tipoElem> visitados = new Lista();
        NodoVert<tipoElem> aux = inicio;
        while (aux != null) {
            visitados.insertar(aux.getElem(), visitados.longitud() + 1);
            NodoAdy<tipoElem> auxAdy = aux.getPrimerAdy();
            while (auxAdy != null) {
                if (visitados.localizar(auxAdy.getVertice().getElem()) < 0) {
                    String[] nuevoArco = {aux.getElem().toString(), auxAdy.getVertice().getElem().toString(), "" + auxAdy.getEtiqueta()};
                    res.insertar(nuevoArco, res.longitud() + 1);
                }
                auxAdy = auxAdy.getSigAdyacente();
            }
            aux = aux.getSigVertice();
        }
        return res;
    }

    public Lista listarEnProfundidad() {
        Lista visitados = new Lista();
        NodoVert<tipoElem> aux = inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 0) {
                listarEnProfundidadAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    private void listarEnProfundidadAux(NodoVert<tipoElem> aux, Lista visitados) {
        if (aux != null) {
            visitados.insertar(aux.getElem(), visitados.longitud() + 1);
            NodoAdy<tipoElem> auxAdy = aux.getPrimerAdy();
            while (auxAdy != null) {
                if (visitados.localizar(auxAdy.getVertice().getElem()) < 0) {
                    listarEnProfundidadAux(auxAdy.getVertice(), visitados);
                }
                auxAdy = auxAdy.getSigAdyacente();
            }
        }
    }

    public Lista listarEnAnchura() {
        Lista visitados = new Lista();
        NodoVert<tipoElem> aux = inicio;
        while (aux != null) {
            if (visitados.localizar(aux.getElem()) < 0) {
                listarEnAnchuraAux(aux, visitados);
            }
            aux = aux.getSigVertice();
        }
        return visitados;
    }

    private void listarEnAnchuraAux(NodoVert<tipoElem> ini, Lista visitados) {
        Cola<NodoVert<tipoElem>> orden = new Cola();
        orden.poner(ini);
        while (!orden.esVacia()) {
            NodoVert<tipoElem> aux = orden.obtenerFrente();
            orden.sacar();
            if (visitados.localizar(aux.getElem()) < 0) {
                visitados.insertar(aux.getElem(), visitados.longitud() + 1);
            }
            NodoAdy<tipoElem> auxAdy = aux.getPrimerAdy();
            while (auxAdy != null) {
                if (visitados.localizar(auxAdy.getVertice().getElem()) < 0) {
                    orden.poner(auxAdy.getVertice());
                }
                auxAdy = auxAdy.getSigAdyacente();
            }
        }
    }

    public Lista caminoNoPasaPor(tipoElem origen, tipoElem destino, tipoElem noPasa) {
        NodoVert<tipoElem> nodoOrigen = ubicarVertice(origen);
        Lista camino = new Lista();
        if (nodoOrigen != null && !origen.equals(noPasa) && !destino.equals(noPasa) && existeCamino(origen, destino)) {
            caminoNoPasaPorAux(nodoOrigen, destino, noPasa, camino, 0);
            if (camino.localizar(noPasa) > 0) {
                camino = new Lista();
            }
        }
        return camino;
    }

    private double caminoNoPasaPorAux(NodoVert<tipoElem> aux, tipoElem destino, tipoElem noPasa, Lista anchura, double suma) {
        if (aux != null) {
            if (aux.getElem().equals(destino)) {
                anchura.insertar(destino, anchura.longitud() + 1);
            } else if (anchura.localizar(aux.getElem()) < 0) {
                double sumaMinima;
                double nuevaSuma;
                Lista temp = new Lista();
                Lista cambio = new Lista();
                anchura.insertar(aux.getElem(), anchura.longitud() + 1);
                cambio.copiar(anchura);
                temp.copiar(anchura);
                NodoAdy<tipoElem> ady = aux.getPrimerAdy();
                sumaMinima = caminoNoPasaPorAux(ady.getVertice(), destino, noPasa, anchura, suma + 1);
                if (cambio.localizar(noPasa) < 0) {
                    ady = ady.getSigAdyacente();
                    while (ady != null) {
                        nuevaSuma = caminoNoPasaPorAux(ady.getVertice(), destino, noPasa, temp, suma + 1);
                        if (nuevaSuma > 0) {
                            if (sumaMinima == 0) {
                                sumaMinima = nuevaSuma;
                                anchura.copiar(temp);
                            } else {
                                if (nuevaSuma < sumaMinima) {
                                    sumaMinima = nuevaSuma;
                                    anchura.copiar(temp);
                                }
                            }
                        }
                        temp.copiar(cambio);
                        ady = ady.getSigAdyacente();
                    }
                    suma = sumaMinima;
                } else {
                    suma = Double.MAX_VALUE;
                }
            } else {
                suma = 0;
            }
        }
        return suma;
    }

    public Lista caminoMasCorto(tipoElem origen, tipoElem destino) {
        NodoVert<tipoElem> nodoOrigen = ubicarVertice(origen);
        Lista camino = new Lista();
        if (nodoOrigen != null && existeCamino(origen, destino)) {
            caminoMasCortoAux(nodoOrigen, destino, camino, 0);
        }
        return camino;
    }

    private double caminoMasCortoAux(NodoVert<tipoElem> aux, tipoElem destino, Lista anchura, double suma) {
        if (aux != null) {
            if (aux.getElem().equals(destino)) {
                anchura.insertar(destino, anchura.longitud() + 1);
            } else if (anchura.localizar(aux.getElem()) < 0) {
                double sumaMinima;
                double nuevaSuma;
                Lista temp = new Lista();
                Lista cambio = new Lista();
                anchura.insertar(aux.getElem(), anchura.longitud() + 1);
                cambio.copiar(anchura);
                temp.copiar(anchura);
                NodoAdy<tipoElem> ady = aux.getPrimerAdy();
                sumaMinima = caminoMasCortoAux(ady.getVertice(), destino, anchura, suma + 1);
                ady = ady.getSigAdyacente();
                while (ady != null) {
                    nuevaSuma = caminoMasCortoAux(ady.getVertice(), destino, temp, suma + 1);
                    if (nuevaSuma > 0) {
                        if (sumaMinima == 0) {
                            sumaMinima = nuevaSuma;
                            anchura.copiar(temp);
                        } else {
                            if (nuevaSuma < sumaMinima) {
                                sumaMinima = nuevaSuma;
                                anchura.copiar(temp);
                            }
                        }
                    }
                    temp.copiar(cambio);
                    ady = ady.getSigAdyacente();
                }
                suma = sumaMinima;
            } else {
                suma = 0;
            }
        }
        return suma;
    }

    public Lista caminoMasLargo(tipoElem origen, tipoElem destino) {
        NodoVert<tipoElem> nodoOrigen = ubicarVertice(origen);
        Lista camino = new Lista();
        if (nodoOrigen != null && existeCamino(origen, destino)) {
            caminoMasLargoAux(nodoOrigen, destino, camino, 0);
        }
        return camino;
    }

    private double caminoMasLargoAux(NodoVert<tipoElem> aux, tipoElem destino, Lista anchura, double suma) {
        if (aux != null) {
            if (aux.getElem().equals(destino)) {
                anchura.insertar(destino, anchura.longitud() + 1);
            } else if (anchura.localizar(aux.getElem()) < 0) {
                double sumaMaxima;
                double nuevaSuma;
                Lista temp = new Lista();
                Lista cambio = new Lista();
                anchura.insertar(aux.getElem(), anchura.longitud() + 1);
                cambio.copiar(anchura);
                temp.copiar(anchura);
                NodoAdy<tipoElem> ady = aux.getPrimerAdy();
                sumaMaxima = caminoMasLargoAux(ady.getVertice(), destino, anchura, suma + 1);
                ady = ady.getSigAdyacente();
                while (ady != null) {
                    nuevaSuma = caminoMasLargoAux(ady.getVertice(), destino, temp, suma + 1);
                    if (nuevaSuma > 0) {
                        if (sumaMaxima == 0) {
                            sumaMaxima = nuevaSuma;
                            anchura.copiar(temp);
                        } else {
                            if (nuevaSuma > sumaMaxima) {
                                sumaMaxima = nuevaSuma;
                                anchura.copiar(temp);
                            }
                        }
                    }
                    temp.copiar(cambio);
                    ady = ady.getSigAdyacente();
                }
                suma = sumaMaxima;
            } else {
                suma = 0;
            }
        }
        return suma;
    }

    public double longitudCaminoMenorLongitud(tipoElem origen, tipoElem destino) {
        double longitud = -1;
        NodoVert<tipoElem> nodoOrigen = ubicarVertice(origen);
        Lista camino = new Lista();
        if (nodoOrigen != null && existeCamino(origen, destino)) {
            longitud = caminoMenorLongitudAux(nodoOrigen, destino, camino, 0);
        }
        return longitud;
    }

    public Lista caminoMenorLongitud(tipoElem origen, tipoElem destino) {
        NodoVert<tipoElem> nodoOrigen = ubicarVertice(origen);
        Lista camino = new Lista();
        if (nodoOrigen != null && existeCamino(origen, destino)) {
            caminoMenorLongitudAux(nodoOrigen, destino, camino, 0);
        }
        return camino;
    }

    private double caminoMenorLongitudAux(NodoVert<tipoElem> aux, tipoElem destino, Lista anchura, double suma) {
        if (aux != null) {
            if (aux.getElem().equals(destino)) {
                anchura.insertar(destino, anchura.longitud() + 1);
            } else if (anchura.localizar(aux.getElem()) < 0) {
                double sumaMinima;
                double nuevaSuma;
                Lista temp = new Lista();
                Lista cambio = new Lista();
                anchura.insertar(aux.getElem(), anchura.longitud() + 1);
                cambio.copiar(anchura);
                temp.copiar(anchura);
                NodoAdy<tipoElem> ady = aux.getPrimerAdy();
                sumaMinima = caminoMenorLongitudAux(ady.getVertice(), destino, anchura, suma + ady.getEtiqueta());
                ady = ady.getSigAdyacente();
                while (ady != null) {
                    nuevaSuma = caminoMenorLongitudAux(ady.getVertice(), destino, temp, suma + ady.getEtiqueta());
                    if (nuevaSuma > 0) {
                        if (sumaMinima == 0) {
                            sumaMinima = nuevaSuma;
                            anchura.copiar(temp);
                        } else {
                            if (nuevaSuma < sumaMinima) {
                                sumaMinima = nuevaSuma;
                                anchura.copiar(temp);
                            }
                        }
                    }
                    temp.copiar(cambio);
                    ady = ady.getSigAdyacente();
                }
                suma = sumaMinima;
            } else {
                suma = 0;
            }
        }
        return suma;
    }

    public double longitudCaminoMayorLongitud(tipoElem origen, tipoElem destino) {
        double longitud = -1;
        NodoVert<tipoElem> nodoOrigen = ubicarVertice(origen);
        Lista camino = new Lista();
        if (nodoOrigen != null && existeCamino(origen, destino)) {
            longitud = caminoMayorLongitudAux(nodoOrigen, destino, camino, 0);
        }
        return longitud;
    }

    public Lista caminoMayorLongitud(tipoElem origen, tipoElem destino) {
        NodoVert<tipoElem> nodoOrigen = ubicarVertice(origen);
        Lista camino = new Lista();
        if (nodoOrigen != null && existeCamino(origen, destino)) {
            caminoMayorLongitudAux(nodoOrigen, destino, camino, 0);
        }
        return camino;
    }

    private double caminoMayorLongitudAux(NodoVert<tipoElem> aux, tipoElem destino, Lista anchura, double suma) {
        if (aux != null) {
            if (aux.getElem().equals(destino)) {
                anchura.insertar(destino, anchura.longitud() + 1);
            } else if (anchura.localizar(aux.getElem()) < 0) {
                double sumaMaxima;
                double nuevaSuma;
                Lista temp = new Lista();
                Lista cambio = new Lista();
                anchura.insertar(aux.getElem(), anchura.longitud() + 1);
                cambio.copiar(anchura);
                temp.copiar(anchura);
                NodoAdy<tipoElem> ady = aux.getPrimerAdy();
                sumaMaxima = caminoMayorLongitudAux(ady.getVertice(), destino, anchura, suma + ady.getEtiqueta());
                ady = ady.getSigAdyacente();
                while (ady != null) {
                    nuevaSuma = caminoMayorLongitudAux(ady.getVertice(), destino, temp, suma + ady.getEtiqueta());
                    if (nuevaSuma > 0) {
                        if (sumaMaxima == 0) {
                            sumaMaxima = nuevaSuma;
                            anchura.copiar(temp);
                        } else {
                            if (nuevaSuma > sumaMaxima) {
                                sumaMaxima = nuevaSuma;
                                anchura.copiar(temp);
                            }
                        }
                    }
                    temp.copiar(cambio);
                    ady = ady.getSigAdyacente();
                }
                suma = sumaMaxima;
            } else {
                suma = 0;
            }
        }
        return suma;
    }

    public boolean esVacio() {
        return (inicio == null);
    }

    public void vaciar() {
        inicio = null;
    }

    public Grafo<tipoElem> clonar() {
        Grafo<tipoElem> clon = new Grafo();
        if (inicio != null) {
            clon.inicio = new NodoVert(inicio.getElem());
            NodoVert<tipoElem> aux = inicio;
            NodoVert<tipoElem> auxClon = clon.inicio;
            while (aux.getSigVertice() != null) {
                aux = aux.getSigVertice();
                auxClon.setSigVertice(new NodoVert(aux.getElem()));
                auxClon = auxClon.getSigVertice();
            }
            aux = inicio;
            auxClon = clon.inicio;
            while (aux != null) {
                NodoAdy<tipoElem> auxAdy = aux.getPrimerAdy();
                auxClon.setPrimerAdy(new NodoAdy(clon.ubicarVertice(auxAdy.getVertice().getElem()), auxAdy.getEtiqueta()));
                NodoAdy<tipoElem> auxAdyClon = auxClon.getPrimerAdy();
                while (auxAdy.getSigAdyacente() != null) {
                    auxAdy = auxAdy.getSigAdyacente();
                    auxAdyClon.setSigAdyacente(new NodoAdy(clon.ubicarVertice(auxAdy.getVertice().getElem()), auxAdy.getEtiqueta()));
                    auxAdyClon = auxAdyClon.getSigAdyacente();
                }
                aux = aux.getSigVertice();
                auxClon = auxClon.getSigVertice();
            }
        }
        return clon;
    }

    @Override
    public String toString() {
        String res = "";
        if (inicio != null) {
            Lista visitados = new Lista();
            NodoVert<tipoElem> aux = inicio;
            res += "[";
            while (aux != null) {
                if (visitados.localizar(aux.getElem()) < 0) {
                    res += toStringAux(aux, visitados);
                }
                aux = aux.getSigVertice();
                res += ")";
                if (aux != null) {
                    res += ", ";
                }
            }
            res += "]";
        }
        return res;
    }

    private String toStringAux(NodoVert<tipoElem> aux, Lista visitados) {
        String res = "";
        if (aux != null) {
            visitados.insertar(aux.getElem(), visitados.longitud() + 1);
            res += aux.getElem().toString() + "(";
            NodoAdy<tipoElem> ady = aux.getPrimerAdy();
            while (ady != null) {
                res += ady.getVertice().getElem().toString() + "(" + ady.getEtiqueta() + ")";
                ady = ady.getSigAdyacente();
                if (ady != null) {
                    res += ", ";
                }
            }
        }
        return res;
    }

}
