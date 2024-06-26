import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser(Long id) {
        Map<String, Object> logInfo = new HashMap<>();
        logInfo.put("userId", id);
        LogUtil.logInfo(this.getClass().getName(), "Fetching user from service", logInfo);
        
        return userDao.findById(id);
    }
}