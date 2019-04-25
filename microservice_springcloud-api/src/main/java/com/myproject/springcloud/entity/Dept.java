package com.myproject.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain=true)
public class Dept implements Serializable {

    private long dno;
    private String dName;
    private String dbSource;

}
