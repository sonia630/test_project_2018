package classhotloader;

import java.io.*;

/**
 * 自定义java类加载器来实现java类的热加载
 */
public class MyClassLoader extends ClassLoader {
    //要加载的java类的classpath路径
    private String classPath;

    public MyClassLoader(String classPath) {
        super(ClassLoader.getSystemClassLoader());
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = null;

        return this.defineClass(name,data,0,data.length);

    }

    /**
     * 加载 class文件中的内容
     * @param name
     * @return
     */

    private byte[] loadClassData(String name){
        FileInputStream is = null;
        try{
            name = name.replace(".","//");
            //构建一个文件,把文件读入到 inputstream
            is = new FileInputStream(new File(classPath + name + ".class"));
            //输出流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b = 0;
            while ((b = is.read()) != -1){
                baos.write(b);
            }
            is.close();

            return baos.toByteArray();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
