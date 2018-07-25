package classhotloader;

/**
 * 封闭加载类的信息
 */

public class LoadInfo {
    //自定义的类加载
    private MyClassLoader myClassLoader;
    //记录要加载的类的时间戳
    private long loadTime;
    private BaseManager manager;

    public LoadInfo(MyClassLoader myClassLoader, long loadTime) {
        super();
        this.myClassLoader = myClassLoader;
        this.loadTime = loadTime;
    }

    public BaseManager getManager() {
        return manager;
    }

    public void setManager(BaseManager manager) {
        this.manager = manager;
    }

    public Long getLoadTime() {
        return loadTime;
    }
}
