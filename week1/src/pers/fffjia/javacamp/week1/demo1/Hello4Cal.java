package pers.fffjia.javacamp.week1.demo1;

public class Hello4Cal {

    public static void main(String[] args) {
        int a = 2000;
        int b = 2;
        int c;
        c = (a + b - b) * b / b;
        for(int i=0;i<c;i++){
            if(i<b)
                a=i;
        }
    }
}