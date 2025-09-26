package com.group3.users.domain.repository;

public interface ProfileRepositoryI {

    void create(String id, String email, String name, String surname, String secret);

}
