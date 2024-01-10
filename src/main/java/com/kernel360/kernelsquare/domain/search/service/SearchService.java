package com.kernel360.kernelsquare.domain.search.service;

import com.kernel360.kernelsquare.domain.question.dto.FindQuestionResponse;
import com.kernel360.kernelsquare.domain.question.entity.Question;
import com.kernel360.kernelsquare.domain.search.repository.CustomQuestionRepository;
import com.kernel360.kernelsquare.global.common_response.error.code.QuestionErrorCode;
import com.kernel360.kernelsquare.global.common_response.error.exception.BusinessException;
import com.kernel360.kernelsquare.global.dto.PageResponse;
import com.kernel360.kernelsquare.global.dto.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final CustomQuestionRepository customQuestionRepository;

    public PageResponse<FindQuestionResponse> searchQuestions(Pageable pageable, String keyword) {

        Integer currentPage = pageable.getPageNumber()+1;

        Page<Question> pages = customQuestionRepository.searchByKeyword(pageable, keyword);

        Integer totalPages = pages.getTotalPages();

        if (totalPages == 0) totalPages+=1;

        if (currentPage > totalPages) {
            throw new BusinessException(QuestionErrorCode.PAGE_NOT_FOUND);
        }

        Pagination pagination = Pagination.toEntity(totalPages, pages.getSize(), currentPage.equals(totalPages));

        List<FindQuestionResponse> responsePages = pages.getContent().stream()
            .map(question -> FindQuestionResponse.of(question.getMember(), question, question.getMember().getLevel()))
            .toList();

        return PageResponse.of(pagination, responsePages);
    }
}
