package com.example.bo.domain;

import java.io.Serializable;
import java.util.function.Supplier;

public interface BaseFunction<E extends BaseEntity> extends Serializable {

    Supplier<E> identity();
    
    E clone(E e);

    E destroy(E e);

    boolean validate(E e);

    void doSynchronizerRelationData();
}
