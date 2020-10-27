package grain.configs;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author wulifu
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ResultInfo {
    String code;
    String desc;


    public static final ResultInfo SUCCESS = new ResultInfo(Msg.SUCCESS);
    public static final ResultInfo UNKNOWN = new ResultInfo(Msg.UNKNOWN);

    public ResultInfo(Msg success) {
        this.code = success.code;
        this.desc = success.description;
    }

      public static String system(String desc) {
        return UNKNOWN.copy().setDesc(desc).json();
    }

    public String json() {
        return JSON.toJSONString(this);
    }

    public ResultInfo copy() {
        return new ResultInfo(this.code, this.desc);
    }

}

