package com.pandora.backend.dto;

import com.pandora.backend.domain.PermissionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Integer id;
    private String username;
    private String email;
    private String name;
    private String lastName;
    private PermissionType permissionType;
    private boolean hasToSetPassword = true;
    private boolean deleted;
}
