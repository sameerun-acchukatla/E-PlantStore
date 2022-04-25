package be.intec.models;

import java.io.Serializable;

public enum BloomTime implements Serializable {

    SPRING,SUMMER,AUTUMN,WINTER,NEVER;

    public String getStatus(){
        return this.name();
    }
}
