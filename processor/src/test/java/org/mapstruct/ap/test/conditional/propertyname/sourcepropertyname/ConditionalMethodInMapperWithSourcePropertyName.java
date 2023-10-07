/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.test.conditional.propertyname.sourcepropertyname;

import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SourcePropertyName;
import org.mapstruct.ap.test.conditional.propertyname.Employee;
import org.mapstruct.ap.test.conditional.propertyname.EmployeeDto;
import org.mapstruct.factory.Mappers;

/**
 * @author Filip Hrisafov
 * @author Nikola Ivačič
 * @author Mohammad Al Zouabi
 */
@Mapper
public interface ConditionalMethodInMapperWithSourcePropertyName {

    ConditionalMethodInMapperWithSourcePropertyName INSTANCE
        = Mappers.getMapper( ConditionalMethodInMapperWithSourcePropertyName.class );

    @Mapping(target = "country", source = "originCountry")
    Employee map(EmployeeDto employee);

    @Condition
    default boolean isNotBlank(String value, @SourcePropertyName String propName) {
        if ( propName.equalsIgnoreCase( "lastName" ) ) {
            return false;
        }
        return value != null && !value.trim().isEmpty();
    }
}
