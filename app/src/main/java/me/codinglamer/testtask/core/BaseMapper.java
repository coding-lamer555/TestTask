package me.codinglamer.testtask.core;

public interface BaseMapper<ApiModelType, EntityType, LocalModelType> {

//    EntityType mapApiToEntity(ApiModelType apiModelType);

    LocalModelType mapApiToLocal(ApiModelType apiModelType);

    EntityType mapLocalToEntity(LocalModelType localModelType);
}
