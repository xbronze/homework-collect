package com.homeworkcollect.dao;

import com.homeworkcollect.entity.ReportAttachment;
import com.homeworkcollect.entity.vo.ReportAttachmentVO;

import java.util.List;

/**
 * @author: xbronze
 * @date: 2024-11-27 10:57
 * @description: TODO
 */
public interface ReportAttachmentMapper {

    int insertReportAttachment(List<ReportAttachment> list);

    List<ReportAttachmentVO> selectReportAttachmentVOByReportId(int reportId);

    int deleteReportAttachmentByReportId(int reportId);

}
