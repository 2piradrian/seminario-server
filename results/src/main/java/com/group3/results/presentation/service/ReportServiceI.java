package com.group3.results.presentation.service;

import com.group3.results.domain.dto.request.GetReportReq;
import com.group3.results.domain.dto.response.GetReportRes;

public interface ReportServiceI {

    GetReportRes getReport(GetReportReq dto);

}
