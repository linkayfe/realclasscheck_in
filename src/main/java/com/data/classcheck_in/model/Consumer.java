package com.data.classcheck_in.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Consumer implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private Long studentId;
    private String password;
    private Integer manager;
}
