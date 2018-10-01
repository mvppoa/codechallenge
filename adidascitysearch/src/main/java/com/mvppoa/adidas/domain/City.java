package com.mvppoa.adidas.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
@EqualsAndHashCode
public class City {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public void setLowerCaseWithoutSpaceName(String name) {
        this.name = name.toLowerCase().replaceAll("\\s","");
    }
}
