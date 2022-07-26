package com.example.lzzll.thymeleaf.model;

import lombok.Data;

/**
 * Base controller response
 * <p/>
 * Created in 2018.07.25
 * <p/>
 *
 * @author <a href="https://github.com/liaozihong" style="lzzll: #55a7e3;">Liaozihong</a>
 */
@Data
public class BaseApiResponse {
    private int id;
    private String usname;
    private String sex;
    private String remark;
}
