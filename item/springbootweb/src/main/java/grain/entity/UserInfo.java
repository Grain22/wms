package grain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author wulifu
 */
@Data
@Entity
@Table(name = "user_info")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserInfo {
    /**
     * TABLE：使用一个特定的数据库表格来保存主键。
     * SEQUENCE：根据底层数据库的序列来生成主键，条件是数据库支持序列。
     * IDENTITY：主键由数据库自动生成（主要是自动增长型）
     * AUTO：主键由程序控制(也是默认的,在指定主键时，如果不指定主键生成策略，默认为AUTO)
     *
     * --@Column(name = "id", unique = true, nullable = false)
     * name 属性表示字段名称
     * unique 属性表示该字段是否唯一 true : 唯一 false : 不唯一 注意： 非主键的唯一字段会生成一个唯一索引，命名规格为 UK_随机字符串
     * nullable 属性表示该字段是否可空 true ：可以为空
     * length 属性表示该字段的长度
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "pwd")
    private String pwd;
}
