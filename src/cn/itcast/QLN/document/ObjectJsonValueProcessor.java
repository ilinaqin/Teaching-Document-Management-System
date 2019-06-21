package cn.itcast.QLN.document;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public  class ObjectJsonValueProcessor  implements JsonValueProcessor{

	 /** 
     * 需要留下的字段数组 
     */  
    private String[] properties;  
      
    /** 
     * 需要做处理的复杂属性类型 
     */  
    private Class<?> clazz;  
      
    /** 
     * 构造方法,参数必须 
     * @param properties 
     * @param clazz 
     */  
    public ObjectJsonValueProcessor(String[] properties,Class<?> clazz){  
        this.properties = properties;  
        this.clazz =clazz;  
    }  
  
    public Object processArrayValue(Object value, JsonConfig arg1) {
             PropertyDescriptor pd = null;  
            Method method = null;  
            StringBuffer json = new StringBuffer("{");  
             try{  
                 for(int i=0;i<properties.length;i++){  
                     pd = new PropertyDescriptor(properties[i], clazz);  
                    method = pd.getReadMethod(); 
                     String v = String.valueOf(method.invoke(value));  
                     json.append("'"+properties[i]+"':'"+v+"'");  
                     json.append(i != properties.length-1?",":"");  
                 }  
                 json.append("}");  
             }catch (Exception e) {  
                 e.printStackTrace();  
             }  
             System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhh");
             return JSONObject.fromObject(json.toString());   
     }
    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {  
    	//key为实体关联字段，即外键
    	PropertyDescriptor pd = null;  
        Method method = null;  
        StringBuffer json = new StringBuffer("{");  
        try{  
            for(int i=0;i<properties.length;i++){  
                pd = new PropertyDescriptor(properties[i], clazz);  
               
              //反射：通过类PropertyDescriptor可以得到properties数组即相关联的实体中需要传递的属性，它的名称，类型以及getter,setter方法  
                method = pd.getReadMethod();//得到属性的读取方法，即getter()  
               
                if (value != null){
                String v = String.valueOf(method.invoke(value)); //执行getter(),当然也可以看出这里value
                                                               //即是一个实体类的字节码，在这里是Category.class,然后在组装JSON格式的字符串 
                json.append("'"+properties[i]+"':'"+v+"'");  
                json.append(i != properties.length-1?",":"");  
            }  
            }
            json.append("}");  
            System.out.println("json = "+json.toString());
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
        return JSONObject.fromObject(json.toString());  
        
    }  
}
