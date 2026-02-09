package com.group3.catalog.domain.dto.pagetype.mapper;

import com.group3.catalog.domain.dto.pagetype.mapper.implementation.*;

public class PageTypeMapper {

    public static GetPageTypeByIdMapper getById(){ return new GetPageTypeByIdMapper(); }

    public static GetPageTypeListByIdMapper getListById() { return new GetPageTypeListByIdMapper(); }

    public static GetAllPageTypeMapper getAll() { return new GetAllPageTypeMapper(); }

    public static CreateMapper create() { return new CreateMapper(); }

    public static EditMapper edit() { return new EditMapper(); }

    public static DeleteMapper delete() { return new DeleteMapper(); }
}
