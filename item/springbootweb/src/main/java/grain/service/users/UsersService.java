package grain.service.users;

import grain.dao.RingtoneVideoUserMapper;
import grain.entity.RingtoneVideoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

//    @Autowired
//    RingtoneVideoUserMapper mapper;

    public String login(String username) {
//        List<RingtoneVideoUser> select = mapper.select(new RingtoneVideoUser().setName(username));
//        return select.get(0).getPhoneNumber();
        return "";
    }

}
