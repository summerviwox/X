package com.summer.x.base.ui;

@lombok.Data
public class Ope<A extends UI,B extends DE,C extends DA>{

  private A UI;

  private B DE;

  private C VA;

    public Ope(A UI, B DE, C VA) {
        this.UI = UI;
        this.DE = DE;
        this.VA = VA;
    }

    public A getUI() {
        return UI;
    }

    public B getDE() {
        return DE;
    }

    public C getVA() {
        return VA;
    }

    public void setUI(A UI) {
        this.UI = UI;
    }

    public void setDE(B DE) {
        this.DE = DE;
    }

    public void setVA(C VA) {
        this.VA = VA;
    }
}
