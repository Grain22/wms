package entity;

import lombok.Data;

/**
 * @author laowu
 * @version 4/12/2019 10:23 AM
 */
@Data
public class Goods {

    private Integer goodsId;
    private String goodsIdSelf;
    private String name;
    private String type;
    private Double price;
    private Integer sum;
    private String description;
    private String image;
    private Integer hot;
    private String notes;

}
