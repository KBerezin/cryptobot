package com.bootybanger.cryptobot.catalog.core.mapper;

import java.util.List;

public interface CommonEntityDtoMapper<D, E> {
    D toDto(E e);

    List<D> toDto(List<? extends E> eList);

    E toEntity(D d);

    List<E> toEntity(List<? extends D> dList);
}
