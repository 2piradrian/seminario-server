package com.group3.notifications.presentation.service;

import com.group3.entity.*;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.notifications.config.helpers.SecretKeyHelper;
import com.group3.notifications.domain.dto.notification.mapper.NotificationMapper;
import com.group3.notifications.domain.dto.notification.request.*;
import com.group3.notifications.domain.dto.notification.response.GetLatestUncheckNotificationRes;
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
    public void create(CreateNotificationReq dto) {
        if (!this.secretKeyHelper.isValid(dto.getSecret())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        Notification existingNotification = notificationRepository.findBy(dto.getTargetId(), dto.getCarriedOutById(), dto.getContent());

        if (existingNotification != null) {
            notificationRepository.delete(dto.getTargetId(), dto.getCarriedOutById(), dto.getContent());
            return;
        }

        if (dto.getContent() == NotificationContent.UPVOTE) {
            notificationRepository.delete(dto.getTargetId(), dto.getCarriedOutById(), NotificationContent.DOWNVOTE);
        }
        else if (dto.getContent() == NotificationContent.DOWNVOTE) {
            notificationRepository.delete(dto.getTargetId(), dto.getCarriedOutById(), NotificationContent.UPVOTE);
        }

        Notification notification = new Notification();
        notification.setSourceId(dto.getSourceId());
        notification.setTargetId(dto.getTargetId());
        notification.setCarriedOutBy(User.builder().id(dto.getCarriedOutById()).build());
        notification.setContent(dto.getContent());
        notification.setIsRead(false);

        LocalDateTime now = LocalDateTime.now();

        notification.setCreatedAt(now);
        notification.setUpdatedAt(now);

        notificationRepository.save(notification);
    }

    @Override
    public GetNotificationPageRes getNotificationsByTarget(GetNotificationPageReq dto) {

        User user = userRepository.auth(dto.getToken());

        if (user == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        PageContent<Notification> notifications = this.notificationRepository.getByTargetId(dto.getTargetId(), dto.getPage(), dto.getSize());
        
        notifications.getContent().forEach(notification -> {
            User carriedOutBy = this.userRepository.getById(notification.getCarriedOutBy().getId(), dto.getToken());
            notification.setCarriedOutBy(carriedOutBy);
        });

        return NotificationMapper.getPage().toResponse(notifications);
    }

    @Override
    public GetLatestUncheckNotificationRes getLatestUncheckNotification(GetLatestUncheckNotificationReq dto) {

        if (!this.secretKeyHelper.isValid(dto.getSecret())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Notification notification = this.notificationRepository.getLatestUncheck(dto.getSourceId(), dto.getTargetId(), NotificationContent.PAGE_INVITATION);
        if (notification == null) throw new ErrorHandler(ErrorType.NOTIFICATION_NOT_FOUND);

        User userCarrier = this.userRepository.getById(notification.getCarriedOutBy().getId(), dto.getToken());
        if (userCarrier == null) throw new ErrorHandler(ErrorType.USER_NOT_FOUND);

        notification.setCarriedOutBy(userCarrier);

        return NotificationMapper.getLatestUncheck().toResponse(notification);
    }

    @Override
    public void checkInvitation(CheckInvitationReq dto) {

        if (!this.secretKeyHelper.isValid(dto.getSecret())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        User user = this.userRepository.auth(dto.getToken());
        if (user == null) throw new ErrorHandler(ErrorType.UNAUTHORIZED);

        Notification notification = this.notificationRepository.getById(dto.getNotificationId());
        if (notification == null) throw new ErrorHandler(ErrorType.NOTIFICATION_NOT_FOUND);

        if (!notification.getContent().equals(NotificationContent.PAGE_INVITATION)){
            throw new ErrorHandler(ErrorType.NOTIFICATION_NOT_FOUND);
        }

        notification.setIsRead(true);
        notification.setUpdatedAt(LocalDateTime.now());

        this.notificationRepository.update(notification);

        this.notificationRepository.delete(notification.getTargetId(), notification.getCarriedOutBy().getId(), notification.getContent());
    }

    @Override
    public void markAsRead(MarkAsReadReq dto){
        User user = this.userRepository.auth(dto.getToken());
        if (user == null) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        Notification notification = this.notificationRepository.getById(dto.getNotificationId());
        if (notification == null) throw new ErrorHandler(ErrorType.NOTIFICATION_NOT_FOUND);

        if (!notification.getTargetId().equals(user.getId())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        if (!notification.getIsRead()) {
            notification.setIsRead(true);
            notification.setUpdatedAt(LocalDateTime.now());

            this.notificationRepository.update(notification);
        }
    }

    @Override
    public void deleteBySourceId(DeleteBySourceIdReq dto) {
        if (!this.secretKeyHelper.isValid(dto.getSecret())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        this.notificationRepository.deleteBySourceId(dto.getSourceId());
    }

    @Override
    public void deleteAllByUserId(DeleteAllByUserIdReq dto) {
        if (!this.secretKeyHelper.isValid(dto.getSecret())) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        this.notificationRepository.deleteAllByUserId(dto.getUserId());
    }
    
}
