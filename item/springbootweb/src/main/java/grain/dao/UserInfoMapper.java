package grain.dao;

import grain.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * @author wulifu
 */
public interface UserInfoMapper extends CrudRepository<UserInfo,String> {
}
