package org.beru.dreammer.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class MessageEntity {
    private String from;
    private String text;
}
