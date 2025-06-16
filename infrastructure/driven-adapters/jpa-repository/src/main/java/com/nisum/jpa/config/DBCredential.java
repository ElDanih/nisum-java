package com.nisum.jpa.config;

import lombok.Builder;
import lombok.Getter;
@Builder
@Getter
public class DBCredential {
    private final String url;
    private final String username;
    private final String password;

}
