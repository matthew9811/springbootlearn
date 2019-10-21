package com.shengxi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import lombok.ToString;

/**
 * @author yan
 * 推送类
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WiselyResponse implements Serializable {
    private String responseMsg;
}
