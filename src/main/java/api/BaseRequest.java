package api;

import io.restassured.specification.ResponseSpecification;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(fluent = true)
public class BaseRequest<T> {
    private Object body;
    private String path;
    private Map<String, Object> params;
    private Class<T> responseClass;
    private ResponseSpecification responseSpecification;

//    public void body(Object requestBody) {
//        this.body = requestBody;
//    }
//
//    public Object getBody() {
//        if (this.body != null) {
//            return this.body;
//        } else return null;
//    }
//
//    public void path(String path) {
//        this.path = path;
//    }
//
//    public BaseRequest<T> params(Map<String, Object> params) {
//        this.params = params;
//        return this;
//    }
//
//    public void responseClass(Class<T> responseClass) {
//        this.responseClass = responseClass;
//    }
}
