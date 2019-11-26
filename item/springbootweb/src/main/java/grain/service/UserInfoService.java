package grain.service;

import grain.entity.UserInfo;

import java.util.List;

/**
 * @author wulifu
 */
public interface UserInfoService {

    /**
     * @return all user info
     */
    List<UserInfo> getAll();

}
