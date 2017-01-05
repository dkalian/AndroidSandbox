package com.shandrakov.sandbox.db;

import com.shandrakov.sandbox.repository.Specification;

public interface SqlSpecification extends Specification {
    String query();
}
