package com.group3.results.presentation.service;

import com.group3.entity.Role;
import com.group3.entity.TimeReportContent;
import com.group3.entity.User;
import com.group3.error.ErrorHandler;
import com.group3.error.ErrorType;
import com.group3.results.config.helpers.SecretKeyHelper;
import com.group3.results.data.repository.EventRepository;
import com.group3.results.data.repository.PageProfileRepository;
import com.group3.results.data.repository.PostRepository;
import com.group3.results.data.repository.UserRepository;
import com.group3.results.domain.dto.mapper.ResultsMapper;
import com.group3.results.domain.dto.request.GetReportReq;
import com.group3.results.domain.dto.response.GetReportRes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReportService implements ReportServiceI {

    private final SecretKeyHelper secretKeyHelper;

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final EventRepository eventRepository;

    private final PageProfileRepository pageRepository;

    public GetReportRes getReport(GetReportReq dto) {
        User user = this.userRepository.auth(dto.getToken());
        if (user == null || user.getRole() != Role.ADMIN) {
            throw new ErrorHandler(ErrorType.UNAUTHORIZED);
        }

        String secret = this.secretKeyHelper.getSecret();

        TimeReportContent usersReport =
            this.userRepository.getGrowthReport(dto.getToken(), secret);
        TimeReportContent postsReport =
            this.postRepository.getGrowthReport(dto.getToken(), secret);
        TimeReportContent eventsReport =
            this.eventRepository.getGrowthReport(dto.getToken(), secret);
        TimeReportContent pagesReport =
            this.pageRepository.getGrowthReport(dto.getToken(), secret);

        return ResultsMapper.getReport().toResponse(
            usersReport,
            postsReport,
            eventsReport,
            pagesReport
        );
    }
}
