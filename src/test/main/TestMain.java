package test.main;

import lineales.pila.Pila;
import lineales.cola.Cola;
import lineales.lista.Lista;
import jerarquicas.arbolBinario.ArbolBinario;
import jerarquicas.arbolGenerico.ArbolGenerico;
import conjuntistas.arbolBinarioBusqueda.ArbolBinarioBusqueda;
import conjuntistas.avl.AVL;
import grafos.Grafo;
import test.arbolBinarioPrinter.ArbolBinarioPrinter;

public class TestMain {

    public static void main(String[] args) {
        //TEST ARBOL GENERICO
        /*ArbolGenerico<String> a = new ArbolGenerico();
         System.out.println("Inserto perro: "+a.insertar("perro", null));
         System.out.println("Inserto gato en perro: "+a.insertar("gato", "perro"));
         System.out.println("Inserto raton en perro: "+a.insertar("raton", "perro"));
         System.out.println("La altura es: "+a.altura());
         System.out.println("Lista preorden: "+a.listarPreorden().toString());
         System.out.println("Lista posorden: "+a.listarPosorden().toString());
         System.out.println("Lista inorden: "+a.listarInorden().toString());
         System.out.println("Perro pertenece al arbol: "+a.pertenece("perro"));
         System.out.println("Raton pertenece al arbol: "+a.pertenece("raton"));
         System.out.println("Vaca pertenece al arbol: "+a.pertenece("vaca"));
         System.out.println("El arbol es vacio: "+a.esVacio());
         a.vaciar();
         System.out.println("Lo vacio.");
         System.out.println("El arbol es vacio: "+a.esVacio());
         System.out.println("Inserto perro: "+a.insertar("perro", null));
         System.out.println("Inserto gato en perro: "+a.insertar("gato", "perro"));
         System.out.println("Inserto raton en perro: "+a.insertar("raton", "perro"));
         System.out.println("El padre de gato es: "+a.padre("gato").toString());
         System.out.println("El padre de raton es: "+a.padre("raton").toString());
         System.out.println("El padre de perro es: "+a.padre("perro"));
         System.out.println("Los ancestros de gato: "+a.ancestros("gato"));*/
        //TEST GRAFO
        /*Grafo<Integer> g = new Grafo();
         System.out.println(g.esVacio());
         System.out.println("Agrego vertice 5: " + g.insertarVertice(5));
         System.out.println("Agrego vertice 4: " + g.insertarVertice(4));
         System.out.println("Agrego vertice 3: " + g.insertarVertice(3));
         System.out.println("Agrego vertice 2: " + g.insertarVertice(2));
         System.out.println("Agrego vertice 1: " + g.insertarVertice(1));
         System.out.println("Agrego arco A de 1-2: " + g.insertarArco(1, 2, 50));
         System.out.println("Agrego arco B de 1-4: " + g.insertarArco(1, 4, 100));
         System.out.println("Agrego arco C de 2-3: " + g.insertarArco(2, 3, 50));
         System.out.println("Agrego arco D de 3-5: " + g.insertarArco(3, 5, 50));
         System.out.println("Agrego arco E de 4-5: " + g.insertarArco(4, 5, 100));
         System.out.println("Elimina vertice 1: "+g.eliminarVertice(1)); 
         System.out.println("Elimino arco A de 2-1: "+g.eliminarArco(2, 1));
         System.out.println("Elimino arco A de 1-2: "+g.eliminarArco(1, 2));
         System.out.println(g.toString());
         System.out.println("Existe camino de 1-2: " + g.existeCamino(1, 2));
         System.out.println("Existe camino de 2-1: " + g.existeCamino(2, 1));
         System.out.println("Existe camino de 1-3: " + g.existeCamino(1, 3));
         System.out.println("Existe camino de 3-1: " + g.existeCamino(3, 1));
         System.out.println("Existe camino de 2-3: " + g.existeCamino(2, 3));
         System.out.println("Existe camino de 2-3: " + g.existeCamino(3, 2));
         System.out.println(g.listarEnProfundidad().toString());
         System.out.println(g.listarEnAnchura().toString());
         Grafo g2 = g.clonar();
         System.out.println(g.toString());
         System.out.println(g2.toString());
         System.out.println("camino nodos");
         System.out.println(g.caminoMasCorto(1, 5));
         System.out.println(g.caminoMasLargo(1, 5));
         System.out.println(g.caminoMasCorto(4, 2));
         System.out.println(g.caminoMasLargo(4, 2));
         System.out.println("camino longitud");
         System.out.println(g.caminoMenorLongitud(1, 5) + "" + g.longitudCaminoMenorLongitud(1, 5));
         System.out.println(g.caminoMayorLongitud(1, 5) + "" + g.longitudCaminoMayorLongitud(1, 5));
         System.out.println(g.caminoMenorLongitud(4, 2) + "" + g.longitudCaminoMenorLongitud(4, 2));
         System.out.println(g.caminoMayorLongitud(4, 2) + "" + g.longitudCaminoMayorLongitud(4, 2));
         System.out.println("camino sin uno");
         System.out.println(g.caminoNoPasaPor(1, 5, 2));
         System.out.println(g.caminoNoPasaPor(1, 5, 3));
         System.out.println(g.caminoNoPasaPor(1, 5, 4));
         System.out.println(g.caminoNoPasaPor(2, 4, 1));
         System.out.println(g.caminoNoPasaPor(2, 4, 3));
         System.out.println(g.caminoNoPasaPor(2, 4, 5));
         System.out.println(g.caminoNoPasaPor(3, 1, 2));
         System.out.println(g.caminoNoPasaPor(3, 1, 4));
         System.out.println(g.caminoNoPasaPor(3, 1, 5));
         System.out.println(g.caminoNoPasaPor(4, 2, 1));
         System.out.println(g.caminoNoPasaPor(4, 2, 3));
         System.out.println(g.caminoNoPasaPor(4, 2, 5));*/
        //TEST AVL
        /*AVL<String> p = new AVL();
        p.insertar("P0");
        p.insertar("P1");
        p.insertar("P2");
        p.insertar("P3");
        p.insertar("P4");
        p.insertar("P5");
        p.insertar("P6");
        p.insertar("P7");
        p.insertar("P8");
        p.insertar("P9");
        p.insertar("P10");
        p.insertar("P11");
        p.insertar("P12");
        p.insertar("P13");
        p.insertar("P14");
        p.print();*/
        /*AVL<Integer> a = new AVL();
         System.out.println("Insertar nodo 8: " + a.insertar(8));
         System.out.println("Altura del arbol: " + a.altura());
         System.out.println("Insertar nodo 5: " + a.insertar(5));
         System.out.println("Altura del arbol: " + a.altura());
         System.out.println("Insertar nodo 10: " + a.insertar(10));
         a.print();
         System.out.println("Elimino 8:" + a.eliminar(8));
         a.print();
         System.out.println("Elimino 5:" + a.eliminar(5));
         a.print();
         System.out.println("Insertar nodo 4: " + a.insertar(4));
         a.print();
         System.out.println("Insertar nodo 15: " + a.insertar(15));
         System.out.println("Insertar nodo 13: " + a.insertar(13));
         System.out.println("Insertar nodo 20: " + a.insertar(20));
         System.out.println("Insertar nodo 29: " + a.insertar(29));
         a.print();
         System.out.println(a.toString());
         System.out.println("Elimino 20:" + a.eliminar(20));
         a.print();
         System.out.println("Elimino 10:" + a.eliminar(10));
         a.print();
         System.out.println("Elimino 4:" + a.eliminar(4));
         a.print();
         System.out.println("Pertence al arbol el nodo 5: "+a.pertenece(5));
         System.out.println("Pertence al arbol el nodo 15: "+a.pertenece(15));
         System.out.println("Altura del arbol: "+a.altura());
         a.vaciar();
         System.out.println("Vacio el arbol");
         a.print();
         System.out.println("Pertence al arbol el nodo 15: "+a.pertenece(15));
         System.out.println("Altura del arbol: "+a.altura());*/
    }

}
