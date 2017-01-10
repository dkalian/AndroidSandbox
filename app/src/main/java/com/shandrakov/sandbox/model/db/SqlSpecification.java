package com.shandrakov.sandbox.model.db;

import com.shandrakov.sandbox.model.repository.Specification;

public interface SqlSpecification extends Specification {
    String query();
}
