package com.shandrakov.cleanarchitecture.db;

import com.shandrakov.cleanarchitecture.repository.Specification;

public interface SqlSpecification extends Specification {
    String query();
}
