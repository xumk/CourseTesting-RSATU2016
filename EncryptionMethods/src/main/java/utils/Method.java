package utils;

/**
 * Created by Алексей on 24.12.2016.
 */
public class Method {
    private String methodName;
    private String methodCaption;

    public Method(String methodName, String methodCaption) {
        this.methodName = methodName;
        this.methodCaption = methodCaption;
    }

    public String getMethodName() {
        return methodName;
    }

    public Method setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public String getMethodCaption() {
        return methodCaption;
    }

    public Method setMethodCaption(String methodCaption) {
        this.methodCaption = methodCaption;
        return this;
    }

    @Override
    public String toString() {
        return methodCaption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Method method = (Method) o;

        if (methodName != null ? !methodName.equals(method.methodName) : method.methodName != null) return false;
        return methodCaption != null ? methodCaption.equals(method.methodCaption) : method.methodCaption == null;

    }

    @Override
    public int hashCode() {
        int result = methodName != null ? methodName.hashCode() : 0;
        result = 31 * result + (methodCaption != null ? methodCaption.hashCode() : 0);
        return result;
    }
}
