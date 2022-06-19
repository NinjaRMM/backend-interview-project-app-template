package com.ninjaone.backendinterviewproject.model;

import java.io.Serializable;

/**
 * AbstractEntity
 */

public interface AbstractEntity<K extends Serializable> extends Serializable {
    abstract K getId();
}
