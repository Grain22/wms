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
@Table(name = "ringtone_video_user")
public class RingtoneVideoUser {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ringtone_video_user.id
     *
     * @mbg.generated Thu Nov 28 12:16:46 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ringtone_video_user.name
     *
     * @mbg.generated Thu Nov 28 12:16:46 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ringtone_video_user.phone_number
     *
     * @mbg.generated Thu Nov 28 12:16:46 CST 2019
     */
    private String phoneNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ringtone_video_user.user_type
     *
     * @mbg.generated Thu Nov 28 12:16:46 CST 2019
     */
    private String userType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ringtone_video_user.pay_type
     *
     * @mbg.generated Thu Nov 28 12:16:46 CST 2019
     */
    private String payType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ringtone_video_user.enroll_date
     *
     * @mbg.generated Thu Nov 28 12:16:46 CST 2019
     */
    private String enrollDate;
}