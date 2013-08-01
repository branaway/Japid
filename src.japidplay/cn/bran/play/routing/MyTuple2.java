/**
 * 
 */
package cn.bran.play.routing;

public  class  MyTuple2 <A, B>{
    private  A a;
    private B b;

    public MyTuple2(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A _1() {

        return a;

    }

    public B _2() {
        return b;
    }
}
