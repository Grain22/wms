package grain.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "ringtone_video_category")
public class RingtoneVideoCategory {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ringtone_video_category.id
     *
     * @mbg.generated Thu Nov 28 12:15:36 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ringtone_video_category.type
     *
     * @mbg.generated Thu Nov 28 12:15:36 CST 2019
     */
    private String type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ringtone_video_category.name
     *
     * @mbg.generated Thu Nov 28 12:15:36 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ringtone_video_category.higher_up_id
     *
     * @mbg.generated Thu Nov 28 12:15:36 CST 2019
     */
    private Integer higherUpId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ringtone_video_category.create_time
     *
     * @mbg.generated Thu Nov 28 12:15:36 CST 2019
     */
    private String createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ringtone_video_category.dimentsion
     *
     * @mbg.generated Thu Nov 28 12:15:36 CST 2019
     */
    private String dimentsion;
}