package classhotloader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ManagerFactory {
    //记录加载类的加载信息
    public static final Map<String,LoadInfo> loadTimeMap = new HashMap<>();
    //要加载的类的classpath
    public static final String CLASS_PATH = "User/yongtali/Project/classloader/bin/";
    //实现热加载的类的全名称(包名+类名)
    public static final String MY_MANAGER = "/Users/yongtali/Project/test_project/backend_project/springboot/src/main/java/classhotloader/MyManager";

    public static BaseManager getManager(String className){
        File loadFile = new File(CLASS_PATH + className.replaceAll("//","/")+".class");
        long lastModified = loadFile.lastModified();
        //loadTimeMap 不包含className为key的loadinfo信息,证明这个类没有被加载,那么需要加载
        if(loadTimeMap.get(className) == null){
            load(className,lastModified);
        }
        //加载类的时间戳变化了,我们同样要重新加载这个类到JVM
        else if(loadTimeMap.get(className).getLoadTime() != lastModified){
            load(className,lastModified);
        }
        return loadTimeMap.get(className).getManager();
    }

    private static void  load(String className,long lastModified){
        MyClassLoader myClassLoader = new MyClassLoader((CLASS_PATH));
        Class<?> loadClass = null;
        try{
            loadClass = myClassLoader.loadClass(className);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        BaseManager manager = newInstanced(loadClass);
        LoadInfo loadInfo = new LoadInfo(myClassLoader,lastModified);
        loadInfo.setManager(manager);
        loadTimeMap.put(className,loadInfo);

    }

    //以反射的方式创建basemanager 子类对象
    private static BaseManager newInstanced(Class<?> loadClass){
        try {
            return (BaseManager) loadClass.getConstructor(new Class[]{}).newInstance(new Object(){});
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

}
