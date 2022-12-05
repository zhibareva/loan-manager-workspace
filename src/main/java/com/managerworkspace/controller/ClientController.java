package com.managerworkspace.controller;

import com.managerworkspace.controller.annotation.APIController;
import com.managerworkspace.controller.mapper.ClientEntitiesMapper;
import com.managerworkspace.dto.response.ClientResponse;
import com.managerworkspace.service.ClientService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@APIController
@AllArgsConstructor
@Slf4j
public class ClientController {

  @Autowired
  private final ClientService clientService;
  private final ClientEntitiesMapper clientEntitiesMapper;

  @GetMapping(path = "/clients")
  @ResponseStatus(code = HttpStatus.OK)
  public List<ClientResponse> getClientByFilter(
      @RequestParam(value = "phoneNumber", required = false)
          String phoneNumber,
      @RequestParam(value = "lastName", required = false)
          String lastName,
      @RequestParam(value = "passportId", required = false)
          String passportId) {

    return clientService.getClientByFilter(phoneNumber, lastName, passportId).stream()
        .map(clientEntitiesMapper::toResponse)
        .collect(Collectors.toList());
  }
}
