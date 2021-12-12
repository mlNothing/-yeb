package com.example.pojo;

/**
 * 发送消息常量
 * @author mlNothing
 * @date 2021/12/12 11:39
 */
public class MailConstants {

//    消息投递中
    public static final Integer DELIVERING=0;
//    发送消息成功
    public  static final  Integer SUCCESS=1;
//    发送消息失败
    public static  final Integer FAILURE=2;
//    最大尝试次数
    public  static  final  Integer MAX_TRY_COUNT=3;
//   消息超时时间
    public  static  final Integer MSG_TIMEOUT=1;
//    队列
    public  static  final String MAIL_QUEUE_NAME="mail.welcome";
//    交换机
    public  static  final  String MAIL_EXCHANGE_NAME="mail.exchange";
//    路由键
    public  static  final  String MAIL_ROUTING_NAME="mial.routing.key";








}
