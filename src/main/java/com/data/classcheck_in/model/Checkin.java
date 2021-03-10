package com.data.classcheck_in.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
public class Checkin implements Serializable {
    @TableId
    private Long checkinNo;
    private Long studentId;
    private Date datetime;
    private String address;
    private String subject;
    private String type;
}
