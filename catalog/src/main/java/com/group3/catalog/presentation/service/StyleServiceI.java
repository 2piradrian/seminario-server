package com.group3.catalog.presentation.service;

import com.group3.catalog.domain.dto.style.request.*;
import com.group3.catalog.domain.dto.style.response.*;

public interface StyleServiceI {

    GetAllStyleRes getAll();

    GetStyleByIdRes getById(GetStyleByIdReq dto);

    GetStyleListByIdRes getListById(GetStyleListByIdReq dto);

    CreateStyleRes create(CreateStyleReq dto);

    EditStyleRes edit(EditStyleReq dto);

    void delete(DeleteStyleReq dto);

}
