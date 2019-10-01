/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arboles;

/**
 *
 * @author hca
 */
public class ArbolAVL<T extends Comparable<T>> extends LinkedBT<T> implements BSTADT<T> {

    NodoAVL<T> raiz;
    int cont;

    public ArbolAVL() {
        raiz = null;
        cont = 0;
    }

    public void inserta(T elem) {
        NodoAVL<T> actual = new NodoAVL(elem);
        NodoAVL<T> papa = actual.getPapa();
        boolean termine = false;
        while (!termine && papa != null) {
            if (actual == actual.getPapa().getIzq()) {
                actual.getPapa().fe -= 1;
            } else {
                actual.getPapa().fe += 1;
            }
            if (actual.getPapa().fe == 2) {
                actual = rotacion(actual.getPapa());
                termine = true;
            } else if (actual.getPapa().fe == 0) {
                termine = true;
            }
            actual = actual.getPapa();
        }
    }

    private NodoAVL<T> buscar(T elem) {
        boolean termine = false;
        NodoAVL<T> actual = new NodoAVL<T>(elem);
        while (!termine && actual.papa != null) {
            if (actual == actual.getPapa().getIzq()) {
                actual.getPapa().fe -= 1;
            } else {
                actual.getPapa().fe += 1;
            }
            if (actual.getPapa().fe == 2) {
                actual = rotacion(actual.getPapa());
                termine = true;
            } else if (actual.getPapa().fe == 0) {
                termine = true;
            }
            actual = actual.getPapa();
        }
    }

    public void borrar(T elem) {
        NodoAVL<T> actual = new NodoAVL(elem);
        NodoAVL<T> papa = actual.getPapa();
        boolean termine = false;
        while (!termine && papa != null) {
            if (actual == actual.getPapa().getIzq()) {
                actual.getPapa().fe -= 1;
            } else {
                actual.getPapa().fe += 1;
            }
            if (actual.getPapa().fe == 2) {
                actual = rotacion(actual.getPapa());
                termine = true;
            } else if (actual.getPapa().fe == 0) {
                termine = true;
            }
            actual = actual.getPapa();
        }
    }

    private NodoAVL<T> rotacion(NodoAVL<T> n) {
        NodoAVL<T> alfa, papa, beta, gamma, A, B, C, D;
        // izq-izq
        if (n.fe == -2 && (n.izq.fe == -1 || n.izq.fe == 0)) {
            alfa = n;
            papa = n.getPapa();
            beta = alfa.getIzq();
            gamma = beta.getIzq();
            A = gamma.getIzq();
            B = gamma.getDer();
            C = beta.getDer();
            D = alfa.getDer();

            gamma.cuelga(A);
            gamma.cuelga(B);
            alfa.cuelga(C);
            alfa.cuelga(D);
            beta.cuelga(alfa);
            beta.cuelga(gamma);

            if (papa != null) {
                papa.cuelga(beta);
            } else {
                beta.setPapa(null);
                raiz = beta;
            }

            alfa.setFe(altura(alfa.getDer()) - altura(alfa.getIzq()));
            beta.setFe(altura(beta.getDer()) - altura(beta.getIzq()));
        } else {
            // izq-der
            if (n.fe == -2 && n.izq.fe == 1) {
                alfa = n;
                papa = n.getPapa();
                beta = alfa.getIzq();
                gamma = beta.getDer();
                A = beta.getIzq();
                B = gamma.getIzq();
                C = gamma.getDer();
                D = alfa.getDer();

                gamma.cuelga(beta);
                gamma.cuelga(alfa);
                alfa.cuelga(C);
                alfa.cuelga(D);
                beta.cuelga(A);
                beta.cuelga(B);

                if (papa != null) {
                    papa.cuelga(gamma);
                } else {
                    gamma.setPapa(null);
                    raiz = gamma;
                }

                alfa.setFe(altura(D) - altura(C));
                beta.setFe(altura(B) - altura(A));
                gamma.setFe(altura(alfa)-altura(beta));
            }
        }
        return beta;
    }
    
    private int altura(NodoAVL<T> nodo) {
        
    }
    
    
    // acabar los otros casos y hacer el método de altura
}
