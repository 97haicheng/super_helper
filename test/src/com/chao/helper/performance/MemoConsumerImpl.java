package com.chao.helper.performance;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by think on 2017/4/6.
 */
public class MemoConsumerImpl implements MemoConsumer {

    ArrayList arr=null;
    HashMap hash=null;

    public void creatArray() {
        arr=new ArrayList(1000);
    }

    public void creatHashMap() {
        hash=new HashMap(1000);
    }

}
