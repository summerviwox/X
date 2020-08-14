package com.summer.x.base.ui;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Ope<A extends UI,B extends DE,C extends VA>{

  private A UI;

  private B DE;

  private C VA;

    public Ope(A UI, B DE, C VA) {
        this.UI = UI;
        this.DE = DE;
        this.VA = VA;
    }
}
