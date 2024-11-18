package com.ashtensanchezcustomersupport.site;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class GenericBaseRepository<I extends Serializable, E
        extends Serializable> implements GenericRepository<I, E> {

    protected final Class<I> idClass;
    protected final Class<E> entityClass;

    public GenericBaseRepository() {

        Type genericSuperclass = this.getClass().getGenericSuperclass();

        while (!(genericSuperclass instanceof ParameterizedType)) {

            if (!(genericSuperclass instanceof ParameterizedType)) {

                throw new IllegalStateException("Unable to determine type arguments because generic" +
                        " superclass neighter parameterized type nor class.");
            }

            if (genericSuperclass == GenericBaseRepository.class) {

                throw new IllegalStateException("Unable to determine type arguments because" +
                        " no parameterized generic superclass found.");
            }

            genericSuperclass = ((Class)genericSuperclass).getGenericSuperclass();
        }

        ParameterizedType type = (ParameterizedType) genericSuperclass;

        Type[] arguments = type.getActualTypeArguments();
            idClass = (Class<I>)arguments[0];
            entityClass = (Class<E>)arguments[1];
    }
}
