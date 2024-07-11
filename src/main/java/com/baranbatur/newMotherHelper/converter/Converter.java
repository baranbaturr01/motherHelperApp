package com.baranbatur.newMotherHelper.converter;

public interface Converter<E, D> {
    D convertToDto(E entity);

    E convertToEntity(D dto);
}
