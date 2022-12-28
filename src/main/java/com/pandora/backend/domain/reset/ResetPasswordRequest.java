package com.pandora.backend.domain.reset;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequest {
    private String name;
    private String lastName;
    private String username;
}
