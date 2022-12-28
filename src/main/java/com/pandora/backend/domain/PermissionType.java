package com.pandora.backend.domain;

import static com.google.common.collect.ImmutableList.of;
import static java.util.Collections.emptyList;
import java.util.List;

public enum PermissionType {
    SUPER("SUPER", of("NONE", "EMPLOYEE", "MANAGER", "SUPER")),
    MANAGER("MANAGER", of("NONE", "EMPLOYEE", "MANAGER")),
    EMPLOYEE("EMPLOYEE", of("NONE", "EMPLOYEE")),
    NONE("NONE", emptyList());

    private final String permissionId;
    private final List<String> permissions;

    PermissionType(String permissionId, List<String> permissions) {
        this.permissionId = permissionId;
        this.permissions = permissions;
    }

    public boolean hasPermission(PermissionType permisssion) {
        return this.permissions
                .stream()
                .anyMatch(p -> p.equals(permisssion.permissionId));
    }
}