package com.homeworkcollect.service.impl;

import com.homeworkcollect.dao.ReportAttachmentMapper;
import com.homeworkcollect.entity.vo.ReportAttachmentVO;
import com.homeworkcollect.service.IReportAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xbronze
 * @date: 2024-12-03 16:14
 * @description: TODO
 */
@Service
public class ReportAttachmentServiceImpl implements IReportAttachmentService {

    @Autowired
    private ReportAttachmentMapper reportAttachmentMapper;

    @Override
    public List<ReportAttachmentVO> queryReportAttachmentVOList(Integer reportId) {
        List<ReportAttachmentVO> reportAttachmentVOList = reportAttachmentMapper.selectReportAttachmentVOByReportId(reportId);
        if (reportAttachmentVOList != null && reportAttachmentVOList.size() > 0) {
            reportAttachmentVOList.forEach(reportAttachmentVO -> {
                String address = reportAttachmentVO.getAddress();
                reportAttachmentVO.setFileName(address.substring(address.indexOf("@") + 1));
                // TODO 这里的minio地址直接定义，后续再使用配置文件定义
                reportAttachmentVO.setDownloadUrl("http://117.78.2.154:9100/homework-collect/" + address);
            });
            return reportAttachmentVOList;
        }
        return new ArrayList<>();
    }


}
