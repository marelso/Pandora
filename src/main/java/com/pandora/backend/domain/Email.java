package com.pandora.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Email {
    private String from;
    private String to;
    private String subject;
    private String body;
}
