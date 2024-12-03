package com.homeworkcollect.service;

import com.homeworkcollect.entity.vo.ReportAttachmentVO;

import java.util.List;

/**
 * @author: xbronze
 * @date: 2024-12-03 16:12
 * @description: TODO
 */
public interface IReportAttachmentService {

    public List<ReportAttachmentVO> queryReportAttachmentVOList(Integer reportId);

}
