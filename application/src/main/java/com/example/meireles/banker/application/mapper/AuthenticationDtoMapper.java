package com.example.meireles.banker.application.mapper;

import com.example.meireles.banker.application.dto.request.AuthenticationRequest;
import com.example.meireles.banker.application.dto.response.AuthenticationResponse;
import com.example.meireles.banker.domain.model.AuthenticationModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationDtoMapper {

    AuthenticationModel toUser(AuthenticationRequest authenticationRequest);

    AuthenticationResponse toAuthenticationResponse(String token);

}
