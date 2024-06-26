import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import org.slf4j.MDC;

public class LoggingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String accessId = UUID.randomUUID().toString();
        String endpointUrl = request.getRequestURI();
        String accessId = request.getHeader("accessId");

        // Apache（特にApache HTTP Server）が出力するリクエストIDに相当するもの
        String requestId = request.getHeader("X-Request-ID");
        if (requestId == null || requestId.isEmpty()) {
            requestId = request.getHeader("X-B3-TraceId");
        }

        // RequestContextHolderを使用して、リクエストスコープにアクセスIDとエンドポイントURLを設定
        RequestContext.setAccessId(accessId);
        RequestContext.setEndpointUrl(endpointUrl);

        //
        MDC.put("accessId", accessId);
        MDC.put("endpointUrl", endpointUrl);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        MDC.clear(); // リクエスト処理後にMDCをクリア
    }
}