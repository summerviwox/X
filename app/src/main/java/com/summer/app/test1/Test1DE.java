package com.summer.app.test1;

import android.os.Build;

import com.blankj.utilcode.util.LogUtils;
import com.summer.app.model.A;
import com.summer.app.model.B;
import com.summer.x.app.XApp;
import com.summer.x.base.ui.DE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * tangjie 2023/3/1 15:39
 **/
public class Test1DE extends DE<Test1DA> {

    public Test1DE(Test1DA da) {
        super(da);


    List<A> as = new ArrayList<>(List.of(
            new A(new B(1)),
            new A(new B(2)),
            new A(new B(3)),
            new A(new B(4)),
            new A(new B(5))
    ));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            List<Integer> as1 =  as.stream().filter(new Predicate<A>() {
                @Override
                public boolean test(A a) {
                    return a.b.x>2;
                }
            }).map(new Function<A, Integer>() {
                @Override
                public Integer apply(A a) {
                    a.b.x = a.b.x*a.b.x;
                    return a.b.x;
                }
            }).collect(Collectors.toList());
            LogUtils.e(as1);


        }


    }


}
