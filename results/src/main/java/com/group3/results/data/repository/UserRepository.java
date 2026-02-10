package com.group3.results.data.repository;

import com.group3.entity.Follow;
import com.group3.entity.TimeReportContent;
import com.group3.entity.User;
import com.group3.entity.UserProfile;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.results.data.datasource.users_server.repository.UsersServerRepositoryI;
import com.group3.results.data.datasource.users_server.responses.*;
import com.group3.results.domain.repository.UserRepositoryI;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class UserRepository implements UserRepositoryI {

    private final UsersServerRepositoryI repository;

    // ======== Authentication ========

    @Override
    public User auth(String token) {
        AuthUserRes response = this.repository.auth(token);

        User user = new User();
        user.setId(response.getId());
        user.setEmail(response.getEmail());
        user.setRole(response.getRole());
        user.setStatus(response.getStatus());

        UserProfile profile = new UserProfile();
        profile.setName(response.getProfile().getName());
        profile.setSurname(response.getProfile().getSurname());
        profile.setMemberSince(response.getProfile().getMemberSince());
        profile.setPortraitImage(response.getProfile().getPortraitImage());
        profile.setProfileImage(response.getProfile().getProfileImage());
        profile.setShortDescription(response.getProfile().getShortDescription());
        profile.setLongDescription(response.getProfile().getLongDescription());
        profile.setStyles(response.getProfile().getStyles());
        profile.setInstruments(response.getProfile().getInstruments());

        user.setProfile(profile);

        return user;
    }

    public List<User> getUserFilteredPage(String token, String fullname, List<String> styles, List<String> instruments, Integer page, Integer size, String secret){

        GetUserPageFilteredRes response = this.repository.getUserFiltered(token, secret , page, size, fullname, styles, instruments);

        return response.getUsers();
    }

    // ======== Single User Retrieval ========

    @Override
    public User getById(String userId, String token) {
        try {
            GetUserByIdRes response = this.repository.getById(token, userId);

            User user = new User();
            user.setId(response.getId());
            user.setEmail(response.getEmail());
            user.setRole(response.getRole());
            user.setStatus(response.getStatus());

            UserProfile profile = new UserProfile();
            profile.setName(response.getProfile().getName());
            profile.setSurname(response.getProfile().getSurname());
            profile.setMemberSince(response.getProfile().getMemberSince());
            profile.setPortraitImage(response.getProfile().getPortraitImage());
            profile.setProfileImage(response.getProfile().getProfileImage());
            profile.setShortDescription(response.getProfile().getShortDescription());
            profile.setLongDescription(response.getProfile().getLongDescription());
            profile.setStyles(response.getProfile().getStyles());
            profile.setInstruments(response.getProfile().getInstruments());

            user.setProfile(profile);

            return user;

        } catch (FeignException e) {
            return null;
        }
    }

    @Override
    public List<Follow> getAllFollowers(String token, String id, String secret){
        return this.repository.getAllFollowers(token, id, secret).getFollowers();
    }

    @Override
    public List<User> getByListOfIds(String token, String secret, Integer page, Integer size, List<String> ids) {
        GetByListOfIdsPageRes response = this.repository.getByListOfIds(token, secret, page, size, ids);
        List<User> users = new ArrayList<>();

        if (response != null && response.getUsers() != null) {
            for (User r : response.getUsers()) {
                User user = new User();
                user.setId(r.getId());
                user.setEmail(r.getEmail());
                user.setRole(r.getRole());
                user.setStatus(r.getStatus());

                if (r.getProfile() != null) {
                    UserProfile profile = new UserProfile();
                    profile.setName(r.getProfile().getName());
                    profile.setSurname(r.getProfile().getSurname());
                    profile.setMemberSince(r.getProfile().getMemberSince());
                    profile.setPortraitImage(r.getProfile().getPortraitImage());
                    profile.setProfileImage(r.getProfile().getProfileImage());
                    profile.setShortDescription(r.getProfile().getShortDescription());
                    profile.setLongDescription(r.getProfile().getLongDescription());
                    profile.setStyles(r.getProfile().getStyles());
                    profile.setInstruments(r.getProfile().getInstruments());
                    user.setProfile(profile);
                }
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public TimeReportContent getGrowthReport(String token, String secret) {
        GetUserGrowthReportRes response = this.repository.getGrowthReport(token, secret);

        return TimeReportContent.builder()
                .yearlyReport(response.getYearlyRegisteredUsers())
                .monthlyReport(response.getMonthlyRegisteredUsers())
                .weeklyReport(response.getWeeklyRegisteredUsers())
                .build();
    }

}
