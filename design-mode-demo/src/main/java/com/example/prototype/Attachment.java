package com.example.prototype;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author zhangjw54
 */
@Data
public class Attachment implements Serializable {

    @Serial
    private static final long serialVersionUID = -8560317180101354870L;

    private String name;

    public void download() {
        System.out.println("下载附件，文件名为" + name);
    }
}
