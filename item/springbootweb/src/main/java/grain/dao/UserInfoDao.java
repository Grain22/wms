package grain.dao;

import grain.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author wulifu
 */
@Repository
public interface UserInfoDao extends JpaRepository<UserInfo,Integer> {
    /**
     * find all by id
     * @param begin primary key
     * @param end primary key
     * @return grain.dao.entity.UserInfo
     */
    Iterable<UserInfo> findAllByIdBetweenOrderById(Integer begin,Integer end);
}
