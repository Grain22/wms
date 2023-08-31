package org.grain.tools.file;

import java.io.File;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author grain
 */
public class ClassLoadUtils {

    public static ArrayList<Class<?>> getAllClassByInterface(Class<?> clazz) {
        ArrayList<Class<?>> list = new ArrayList<>();
        // 判断是否是一个接口
        if (clazz.isInterface()) {
            try {
                ArrayList<Class<?>> allClass = getAllClass(clazz.getPackage().getName());
                /*
                 * 循环判断路径下的所有类是否实现了指定的接口 并且排除接口类自己
                 */
                for (Class<?> aClass : allClass) {
                    /*
                     * 判断是不是同一个接口
                     * isAssignableFrom:判定此 Class 对象所表示的类或接口与指定的 Class
                     * 参数所表示的类或接口是否相同，或是否是其超类或超接口
                     */
                    if (clazz.isAssignableFrom(aClass)) {
                        if (!clazz.equals(aClass)) {
                            /*
                             * 自身并不加进去
                             */
                            list.add(aClass);
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("出现异常" + e.getMessage());
            }
        }
        return list;
    }


    /**
     * 从一个指定路径下查找所有的类
     */
    private static ArrayList<Class<?>> getAllClass(String packagename) {
        List<String> classNameList = getClassName(packagename);
        ArrayList<Class<?>> list = new ArrayList<>();
        for (String className : classNameList) {
            try {
                list.add(Class.forName(className));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("load class from name failed:" + className + e.getMessage());
            }
        }
        return list;
    }

    /**
     * 获取某包下所有类
     *
     * @param packageName 包名
     * @return 类的完整名称
     */
    public static List<String> getClassName(String packageName) {
        List<String> fileNames = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String packagePath = packageName.replace(".", "/");
        URL url = loader.getResource(packagePath);
        if (url != null) {
            String type = url.getProtocol();
            if ("file".equals(type)) {
                String fileSearchPath = url.getPath();
                fileSearchPath = fileSearchPath.substring(0, fileSearchPath.indexOf("/classes"));
                fileNames = getClassNameByFile(fileSearchPath);
            } else if ("jar".equals(type)) {
                try {
                    JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                    JarFile jarFile = jarURLConnection.getJarFile();
                    fileNames = getClassNameByJar(jarFile);
                } catch (java.io.IOException e) {
                    throw new RuntimeException("open Package URL failed：" + e.getMessage());
                }

            } else {
                throw new RuntimeException("file system not support! cannot load MsgProcessor！");
            }
        }
        return fileNames;
    }

    /**
     * 从项目文件获取某包下所有类
     *
     * @param filePath 文件路径
     * @return 类的完整名称
     */
    private static List<String> getClassNameByFile(String filePath) {
        List<String> myClassName = new ArrayList<>();
        File file = new File(filePath);
        File[] childFiles = file.listFiles();
        if (childFiles == null) {
            return myClassName;
        }
        for (File childFile : childFiles) {
            if (childFile.isDirectory()) {
                myClassName.addAll(getClassNameByFile(childFile.getPath()));
            } else {
                String childFilePath = childFile.getPath();
                if (childFilePath.endsWith(".class")) {
                    childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9, childFilePath.lastIndexOf("."));
                    childFilePath = childFilePath.replace("\\", ".");
                    myClassName.add(childFilePath);
                }
            }
        }

        return myClassName;
    }

    /**
     * 从jar获取某包下所有类
     *
     * @return 类的完整名称
     */
    private static List<String> getClassNameByJar(JarFile jarFile) {
        List<String> myClassName = new ArrayList<>();
        try {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry jarEntry = entries.nextElement();
                String entryName = jarEntry.getName();
                if (entryName.endsWith(".class")) {
                    entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));
                    myClassName.add(entryName);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("发生异常:" + e.getMessage());
        }
        return myClassName;
    }
}
