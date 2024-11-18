package com.ashtensanchezcustomersupport.site;

import com.ashtensanchezcustomersupport.entities.UserPrincipal;

public interface UserPrincipalRepository extends GenericRepository<Long, UserPrincipal> {

    UserPrincipal getByUsername(String username);
}
