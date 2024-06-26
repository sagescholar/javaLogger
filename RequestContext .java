import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestContext {
    public static void setAccessId(String id) {
        getRequest().setAttribute("accessId", id);
    }

    public static String getAccessId() {
        return (String) getRequest().getAttribute("accessId");
    }

    public static void setEndpointUrl(String url) {
        getRequest().setAttribute("endpointUrl", url);
    }

    public static String getEndpointUrl() {
        return (String) getRequest().getAttribute("endpointUrl");
    }

    private static ServletRequestAttributes getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes());
    }
}