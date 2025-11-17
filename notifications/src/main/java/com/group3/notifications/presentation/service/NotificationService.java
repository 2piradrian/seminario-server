package com.group3.notifications.presentation.service;

import com.group3.entity.Notification;
import com.group3.entity.PageContent;
import com.group3.entity.User;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.notifications.config.helpers.SecretKeyHelper;
import com.group3.notifications.domain.dto.notification.mapper.NotificationMapper;
import com.group3.notifications.domain.dto.notification.request.CreateNotificationReq;
import com.group3.notifications.domain.dto.notification.request.GetNotificationPageReq;
import com.group3.notifications.domain.dto.notification.response.CreateNotificationRes;
import com.group3.notifications.domain.dto.notification.response.GetNotificationPageRes;
import com.group3.notifications.domain.repository.NotificationRepositoryI;
import com.group3.notifications.domain.repository.UserRepositoryI;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@Transactional
@AllArgsConstructor
public class NotificationService implements NotificationServiceI {

    private final SecretKeyHelper secretKeyHelper;

    private final NotificationRepositoryI notificationRepository;

    private final UserRepositoryI userRepository;

    @Override
    public CreateNotificationRes create(CreateNotificationReq dto) {
        if (!this.secretKeyHelper.isValid(dto.getSecret())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        Notification notification = new Notification();
        notification.setSourceId(dto.getSourceId());
        notification.setTargetId(dto.getTargetId());
        notification.setContent(dto.getContent());
        notification.setCreatedAt(LocalDateTime.now());
        notification.setUpdatedAt(LocalDateTime.now());

        Notification saved = this.notificationRepository.save(notification);
        return NotificationMapper.create().toResponse(saved);
    }

    @Override
    public GetNotificationPageRes getNotificationsByTarget(GetNotificationPageReq dto) {
        User user = userRepository.auth(dto.getToken());
        if (user == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        PageContent<Notification> notifications = this.notificationRepository.getByTargetId(dto.getTargetId(), dto.getPage(), dto.getSize());
        return NotificationMapper.getPage().toResponse(notifications);
    }

}
