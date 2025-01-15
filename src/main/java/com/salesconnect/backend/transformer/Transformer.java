package com.salesconnect.backend.transformer;

import java.util.ArrayList;
import java.util.List;

public abstract class Transformer<Entity, DTO> {

    public abstract Entity toEntity(DTO dto);

    public abstract DTO toDTO(Entity entity);

    public List<Entity> toEntityList(Iterable<DTO> dtoList) {
        List<Entity> entities = new ArrayList<>();
        if (dtoList == null) {
            return entities;  // Retourner une liste vide si dtoList est null
        }
        for (DTO dto : dtoList) {
            entities.add(toEntity(dto));
        }
        return entities;
    }


    public List<DTO> toDTOList(Iterable<Entity> entityList) {
        List<DTO> dtos = new ArrayList<>();
        if (entityList == null) {
            return dtos;  // Retourner une liste vide si entityList est null
        }
        for (Entity entity : entityList) {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }



}
