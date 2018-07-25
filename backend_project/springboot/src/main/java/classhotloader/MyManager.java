package classhotloader;

/**
 * BaseManager的子类,此类需要实现java类的热加载功能
 */
public class MyManager implements BaseManager {

    @Override
    public void logic() {
        System.out.println("I am in imooc,实现一个如何实现一个java类的热加载实例");
    }
}
