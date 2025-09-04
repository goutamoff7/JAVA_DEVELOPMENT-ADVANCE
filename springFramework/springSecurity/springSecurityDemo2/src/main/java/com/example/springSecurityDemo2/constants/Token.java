package com.example.springSecurityDemo2.constants;

import lombok.Getter;

@Getter
public enum Token {
    ACCESS_TOKEN(36_00_000L); // 1Hr

    final long expiration;

    Token(long expiration) {
        this.expiration = expiration;
    }

}
