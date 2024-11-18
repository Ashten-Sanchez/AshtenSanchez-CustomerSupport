package com.ashtensanchezcustomersupport.site;

import com.ashtensanchezcustomersupport.entities.UserPrincipal;

public interface AuthenticationService {
    UserPrincipal authenticate(String username, String password);
    void saveUser(UserPrincipal principal, String newPassword);
}
