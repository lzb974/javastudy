package cn.sinjinsong.eshop.common.domain.entity.message;

import cn.sinjinsong.eshop.common.enumeration.message.MessageStatus;
import cn.sinjinsong.eshop.common.properties.DateTimeProperties;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProducerTransactionMessageDO implements Serializable{
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column producer_transaction_message.msgId
     *
     * @mbggenerated
     */
    private Long id;
    
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column producer_transaction_message.message_status
     *
     * @mbggenerated
     */
    private MessageStatus messageStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column producer_transaction_message.update_time
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = DateTimeProperties.LOCAL_DATE_TIME_PATTERN)
    private LocalDateTime updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column producer_transaction_message.create_time
     *
     * @mbggenerated
     */
    @JsonFormat(pattern = DateTimeProperties.LOCAL_DATE_TIME_PATTERN)
    private LocalDateTime createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column producer_transaction_message.send_times
     *
     * @mbggenerated
     */
    private Integer sendTimes;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column producer_transaction_message.topic
     *
     * @mbggenerated
     */
    private String topic;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column producer_transaction_message.body
     *
     * @mbggenerated
     */
    private byte[] body;

}