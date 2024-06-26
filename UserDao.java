import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDao {
    public User findById(Long id) {
        Map<String, Object> logInfo = new HashMap<>();
        logInfo.put("userId", id);
        LogUtil.logInfo(this.getClass().getName(), "Fetching user from database", logInfo);
        
        // データベース操作のシミュレーション
        return new User(id, "John Doe");
    }
}