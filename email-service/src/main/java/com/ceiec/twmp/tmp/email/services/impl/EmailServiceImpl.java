package com.ceiec.twmp.tmp.email.services.impl;


import com.ceiec.twmp.tmp.email.services.IEmailService;
import com.ceiec.twmp.tmp.email.vo.email.AttachedImage;
import com.ceiec.twmp.tmp.email.vo.email.AttendedFiles;
import com.ceiec.twmp.tmp.email.vo.email.EmailSendRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * CreateDate：2018/4/24
 * Author：wenliang
 * Description: 该类主要用于提供邮件发送服务
 **/

@Component
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSenderImpl mailSender;
    /** 日志对象 */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment env;

    /**
     * 发送邮件
     *
     * @param emailSendRequest the email send request
     * @throws Exception the exception
     */
    @Override
    public boolean sendMail(EmailSendRequest emailSendRequest) {
        boolean sendFlag = false;
        String sender = env.getProperty("spring.mail.username");
        try {
            if (emailSendRequest.getHtmlFlag() == true) {
                sendComplexFormatMail(sender ,emailSendRequest);
            } else {
                sendTxtMail(emailSendRequest.getRecipients(), sender, emailSendRequest.getSubject(), emailSendRequest.getText());
            }
            sendFlag = true;
        } catch(Exception e){
            logger.error("send email is error ,ErrorMessage is ="+e.getMessage());
            e.printStackTrace();
        }
        return  sendFlag;
    }

    /**
     * 发送包含简单文本的邮件
     *
     * @param recipients the recipients
     * @param sender     the sender
     * @param subject    the subject
     * @param text       the text
     * @throws MailException the mail exception
     */
    private void sendTxtMail(String[] recipients ,String sender ,String subject ,String text) throws MailException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人
        simpleMailMessage.setTo(recipients);
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        // 发送邮件
        mailSender.send(simpleMailMessage);
    }

    /**
     * 发送包含图片和附件的邮件
     *
     * @param sender           the sender
     * @param emailSendRequest the email send request
     * @throws Exception the exception
     */
    private void sendComplexFormatMail(String sender ,EmailSendRequest emailSendRequest ) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // multipart模式
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        mimeMessageHelper.setTo(emailSendRequest.getRecipients());
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setSubject(emailSendRequest.getSubject());
        // 启用html
        mimeMessageHelper.setText(emailSendRequest.getText(), true);
        //设置图片
        List<AttachedImage> attachedImages = emailSendRequest.getAttachedImages();
        if(null != attachedImages && attachedImages.size()>0){
            for (AttachedImage attachedImage: attachedImages) {
                mimeMessageHelper.addInline(attachedImage.getContentId(),  new ByteArrayResource(attachedImage.getImageBytes()), attachedImage.getContentType());
            }
        }
        // 设置附件
        List<AttendedFiles> attendedFiles = emailSendRequest.getAttendedFiles();
        if(null != attendedFiles && attendedFiles.size()>0){
            for (AttendedFiles attendedFile: attendedFiles) {
                mimeMessageHelper.addAttachment(attendedFile.getAttachmentFilename(), new ByteArrayResource(attendedFile.getFileBytes()));
            }
        }
        // 发送邮件
        mailSender.send(mimeMessage);
    }

    /**
     * 根据地址获得数据的字节流
     * @param strUrl 网络连接地址
     * @return
     */
    private static InputStream getImageFromNetByUrl(String strUrl){
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
          //  byte[] btImg = readInputStream(inStream);//得到图片的二进制数据
            return inStream;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

