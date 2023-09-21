package com.example.meireles.banker.application.mapper;

import com.example.meireles.banker.application.dto.request.AuthenticationRequest;
import com.example.meireles.banker.domain.model.AuthenticationModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationMapper {

    AuthenticationModel toUser(AuthenticationRequest authenticationRequest);

}
