package com.shengxi.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author yan
 * 浏览器提交数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WiselyMessage implements Serializable {
    private String name;
}
