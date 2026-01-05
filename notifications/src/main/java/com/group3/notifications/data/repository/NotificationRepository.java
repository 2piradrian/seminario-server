package com.group3.notifications.data.repository;

import com.group3.entity.*;
import com.group3.notifications.data.datasource.postgres.mapper.NotificationEntityMapper;
import com.group3.notifications.data.datasource.postgres.model.NotificationModel;
import com.group3.notifications.data.datasource.postgres.repository.PostgresNotificationRepositoryI;
import com.group3.notifications.domain.repository.NotificationRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class NotificationRepository implements NotificationRepositoryI {

    private final PostgresNotificationRepositoryI repository;

    private int normalizePage(Integer page) {
        return (page != null && page > 0) ? page - 1 : 0;
    }

    @Override
    public PageContent<Notification> getByTargetId(String targetId, Integer page, Integer size) {
        int pageIndex = normalizePage(page);

        Page<NotificationModel> notificationModels = repository.findByTargetId(
                targetId,
                PageRequest.of(pageIndex, size)
        );

        return new PageContent<>(
                notificationModels.getContent().stream()
                        .map(NotificationEntityMapper::toDomain)
                        .collect(Collectors.toList()),
                notificationModels.getNumber() + 1,
                notificationModels.hasNext() ? notificationModels.getNumber() + 2 : null
        );
    }

    @Override
    public Notification getById(String notificationId) {
        NotificationModel notificationModel = this.repository.findById(notificationId).orElse(null);

        if (notificationModel == null) return null;

        return NotificationEntityMapper.toDomain(notificationModel);
    }

    @Override
    public Notification save(Notification notification) {
        NotificationModel notificationModel = NotificationEntityMapper.toModel(notification);
        NotificationModel saved = this.repository.save(notificationModel);
        return NotificationEntityMapper.toDomain(saved);
    }

    @Override
    public Notification update(Notification notification) {
        NotificationModel notificationModel = NotificationEntityMapper.toModel(notification);
        NotificationModel updated = this.repository.save(notificationModel);
        return NotificationEntityMapper.toDomain(updated);
    }

    @Override
    public Notification findBy(String targetId, String carriedOutById, NotificationContent content) {
        NotificationModel notificationModel = this.repository.findByTargetIdAndCarriedOutByIdAndContent(targetId, carriedOutById, content);
        if (notificationModel == null) {
            return null;
        }
        return NotificationEntityMapper.toDomain(notificationModel);
    }

    @Override
    public void delete(String targetId, String carriedOutById, NotificationContent content) {
        this.repository.deleteByTargetIdAndCarriedOutByIdAndContent(targetId, carriedOutById, content);
    }

    @Override
    public void deleteBySourceId(String sourceId) {
        this.repository.deleteBySourceId(sourceId);
    }

    @Override
    public Notification getLatestUncheck(String sourceId, String targetId, NotificationContent content) {

        List<NotificationModel> result = this.repository.findLatestUncheckNotifications(
            sourceId,
            targetId,
            content,
            PageRequest.of(0, 1)
        );

        if(result.isEmpty()){
            return null;
        }

        return NotificationEntityMapper.toDomain(result.getFirst());
    }

}
