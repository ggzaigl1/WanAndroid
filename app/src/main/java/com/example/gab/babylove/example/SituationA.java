package com.example.gab.babylove.example;

/**
 * Created by 初夏小溪 on 2018/3/23 0023.
 */

public class SituationA {

    public void requestSupport(String situation){
        System.out.println(getClass().getSimpleName() + ": 这里是A,请求" + situation + "帮助");
    }

    class SituationB{

        public void requestSupport(String situation){
            System.out.println(getClass().getSimpleName() + ": 这里是B,请求" + situation + "帮助");
        }

        public void support(boolean isSupport){
            if (isSupport){
                System.out.println(getClass().getSimpleName() + ": 这里是A,请求" + "帮助");
            }
        }
    }
}
