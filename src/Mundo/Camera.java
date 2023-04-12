package Mundo;

public class Camera {
    public static  int x,y;

    // serve para que a câmera fique focada no player
    public static int Clamp(int inicio, int minimo, int maximo){

        if (inicio < minimo){
            inicio = minimo;
        }
        if (inicio > maximo){
            inicio = maximo;
        }

        return inicio;
    }

}
