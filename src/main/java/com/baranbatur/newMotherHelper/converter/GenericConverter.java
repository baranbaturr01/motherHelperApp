package com.baranbatur.newMotherHelper.converter;

import java.util.function.Function;

public class GenericConverter<E, D> implements Converter<E, D> {
    private final Function<E, D> toDtoFunction;
    private final Function<D, E> toEntityFunction;

    public GenericConverter(Function<E, D> toDtoFunction, Function<D, E> toEntityFunction) {
        this.toDtoFunction = toDtoFunction;
        this.toEntityFunction = toEntityFunction;
    }

    @Override
    public D convertToDto(E entity) {
        return toDtoFunction.apply(entity);
    }

    @Override
    public E convertToEntity(D dto) {
        return toEntityFunction.apply(dto);
    }
}
