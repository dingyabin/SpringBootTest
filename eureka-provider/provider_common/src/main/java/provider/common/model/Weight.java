package provider.common.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by MrDing
 * Date: 2017/3/18.
 * Time:19:18
 */
@Setter
@Getter
@ToString
public class Weight implements Serializable {

    private static final long serialVersionUID = 7138382515512564740L;

    private Long id;

    private double weight;

    private double waist;

    private Date createTime;


    public Weight() {
    }

    public Weight(double weight, double waist, Date createTime) {
        this.weight = weight;
        this.waist = waist;
        this.createTime = createTime;
    }


}
