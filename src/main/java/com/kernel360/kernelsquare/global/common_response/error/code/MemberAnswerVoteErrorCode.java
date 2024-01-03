package com.kernel360.kernelsquare.global.common_response.error.code;

import com.kernel360.kernelsquare.global.common_response.service.code.MemberAnswerVoteStatus;
import com.kernel360.kernelsquare.global.common_response.service.code.ServiceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum MemberAnswerVoteErrorCode implements ErrorCode {
    MEMBER_ANSWER_VOTE_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            MemberAnswerVoteStatus.MEMBER_ANSWER_VOTE_NOT_FOUND,
            "해당 투표는 존재하지 않습니다."
    );

    private final HttpStatus code;
    private final ServiceStatus serviceStatus;
    private final String msg;

    @Override
    public HttpStatus getStatus() {
        return code;
    }

    @Override
    public Integer getCode() {
        return serviceStatus.getServiceStatus();
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
