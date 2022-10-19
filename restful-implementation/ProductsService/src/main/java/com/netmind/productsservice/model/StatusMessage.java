package com.netmind.productsservice.model;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@XmlRootElement
public class StatusMessage {

    private Integer status;
    private String message;

}