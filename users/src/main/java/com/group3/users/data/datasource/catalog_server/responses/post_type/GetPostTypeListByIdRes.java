package com.group3.users.data.datasource.catalog_server.responses.post_type;

import com.group3.entity.PostType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetPostTypeListByIdRes {

    private List<PostType> postTypes;

}
