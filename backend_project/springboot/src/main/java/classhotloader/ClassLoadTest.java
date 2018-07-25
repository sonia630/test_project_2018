package classhotloader;

/**
 * 测试java类的热加载
 */
public class ClassLoadTest {
    public static void main(String[] args) {
        new Thread(new MsgHandler()).start();
    }
}
