package com.github.vbauer.houdini.exception.base;

import com.github.vbauer.houdini.util.CollectionUtils;

import java.util.Arrays;

/**
 * @author Vladislav Bauer
 */

public abstract class MethodObjectConverterException extends ObjectConverterException {

    private final Class<?>[] parameterTypes;
    private final Class<?> returnType;


    public MethodObjectConverterException(final Class<?> returnType, final Class<?>... parameterTypes) {
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
    }


    public Class<?>[] getParameterTypes() {
        return Arrays.copyOf(parameterTypes, CollectionUtils.size(parameterTypes));
    }

    public Class<?> getReturnType() {
        return returnType;
    }

}
