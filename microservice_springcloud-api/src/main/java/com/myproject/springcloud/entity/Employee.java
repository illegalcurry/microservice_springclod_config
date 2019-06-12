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
public class Employee implements Serializable {

    //@JestId
    private long eno;
    private String eName;
    private String dbSource;

}
