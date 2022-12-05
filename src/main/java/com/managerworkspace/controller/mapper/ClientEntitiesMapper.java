package com.managerworkspace.controller.mapper;

import com.managerworkspace.dto.response.ClientResponse;
import com.managerworkspace.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ClientEntitiesMapper {

  @Mappings({
      @Mapping(target = "id", source = "client.id"),
      @Mapping(target = "firstName", source = "client.firstName"),
      @Mapping(target = "middleName", source = "client.middleName"),
      @Mapping(target = "lastName", source = "client.lastName"),
      @Mapping(target = "passportId", source = "client.passportId"),
      @Mapping(target = "placeOfResidence", source = "client.placeOfResidence"),
      @Mapping(target = "phoneNumber", source = "client.phoneNumber"),
      @Mapping(target = "from", source = "client.from"),
      @Mapping(target = "to", source = "client.to"),
      @Mapping(target = "position", source = "client.position"),
      @Mapping(target = "companyName", source = "client.companyName"),
  })
  ClientResponse toResponse(Client client);
}
