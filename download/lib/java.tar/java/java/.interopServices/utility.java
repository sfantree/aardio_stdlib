package aardio.interopServices;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
public class utility{ 
	static public boolean fieldExists(Object[] args) {
    	try {
    		Class ownerClass = args[0].getClass();
    		if( ownerClass.equals( Class.class ) ) ownerClass = (Class)args[0];
        	Field field = ownerClass.getField((String)args[1]); 
        	if(field!=null) return true;
        } catch (Exception e) {
        
        }  
        return false;
    } 
    static public Object getFieldValue(Object[] args) {
    	try {
    		Class ownerClass = args[0].getClass();
    		if( ownerClass.equals( Class.class ) ) ownerClass = (Class)args[0];
        	Field field = ownerClass.getField((String)args[1]); 
        	if(field!=null) return field.get(args[0]);
        	return null;
        } catch (Exception e) {
            return null;
        }  
    }  
    static public void setFieldValue(Object[] args) {
    	Field field = null;
    	try {
    		Class ownerClass = args[0].getClass();
    		if( ownerClass.equals( Class.class ) ) ownerClass = (Class)args[0];
        	field = ownerClass.getField((String)args[1]);
        	if(field==null) return;
        	field.set(args[0],args[2]); 
        } catch (IllegalArgumentException ea ){
        
        	if( args[2] instanceof Double ) {
        		double v = ((Double)(args[2])).doubleValue();
        		
        		try {
        			field.set(args[0],((Float)(float)v));
        			return;
        		} 
        		catch (Exception ea2 ){
        		
        		}
        		
        		try {
        			field.set(args[0],((Character)(char)v));
        			return;
        		} 
        		catch (Exception ea3 ){
        		}
        		
        		try {
        			field.set(args[0],((Short)(short)v));
        			return;
        		} 
        		catch (Exception ea4 ){
        		
        		}
        		
        		try {
        			field.set(args[0],((Integer)(int)v));
        			return;
        		} 
        		catch (Exception ea1 ){
        		
        		}
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        } 
    } 
	public static Object invokeMethod(Object[] args) throws Exception {   
     	Class ownerClass = args[0].getClass();
     	Class[] argsClass = new Class[args.length-2];  
     	Object[] args2 = new Object[args.length-2]; 
     	boolean numberTest = false;
     	Method method = null;
     	
     	if( ownerClass.equals( Class.class ) ) ownerClass = (Class)args[0];
  
  		try{
     		for (int i = 2, j = args.length; i < j; i++) {   
		
         		argsClass[i-2] = args[i].getClass(); 
         		args2[i-2] = args[i];  
          		if( argsClass[i-2].equals(Integer.class) ){
					argsClass[i-2] = int.class;
				}
				else if( argsClass[i-2].equals(Double.class) ){
					argsClass[i-2] = double.class;
					numberTest = true;
				}
				else if( argsClass[i-2].equals(Float.class) ){
					argsClass[i-2] = float.class;
				}
				else if( argsClass[i-2].equals(Long.class) ){
					argsClass[i-2] = long.class;
				}
				else if( argsClass[i-2].equals(Character.class) ){
					argsClass[i-2] = char.class;
				}
				else if( argsClass[i-2].equals(Short.class) ){
					argsClass[i-2] = short.class;
				}
				else if( argsClass[i-2].equals(Boolean.class) ){
					argsClass[i-2] = boolean.class;
				}
     		} 
     		
     		method = ownerClass.getMethod((String)args[1],argsClass);
     	}catch(NoSuchMethodException e1){ 
			if( numberTest){ 
				try{
					for (int i = 2, j = args.length; i < j; i++) {   
						if( args[i].getClass().equals(Double.class) ){
							argsClass[i-2] = int.class;
							args2[i-2] = (int)((double)(Double)(args[i]));  
						}
     				} 
     				method = ownerClass.getMethod((String)args[1],argsClass);	
				}
				catch(NoSuchMethodException e2){
					try{
						for (int i = 2, j = args.length; i < j; i++) {   
							if( args[i].getClass().equals(Double.class) ){
								argsClass[i-2] = float.class;
								args2[i-2] = (float)((double)(Double)(args[i])); 
							}
     					} 
     					method = ownerClass.getMethod((String)args[1],argsClass);	
					}
					catch(NoSuchMethodException e3){
						try{
           					for (int i = 2, j = args.length; i < j; i++) {   
								if( args[i].getClass().equals(Double.class) ){
									argsClass[i-2] = short.class;
									args2[i-2] = (short)((double)(Double)(args[i])); 
								}
     						} 
     						method = ownerClass.getMethod((String)args[1],argsClass);	
						}
						catch(NoSuchMethodException e4){
							try{
          						for (int i = 2, j = args.length; i < j; i++) {   
									if( args[i].getClass().equals(Double.class) ){
										argsClass[i-2] = char.class;
										args2[i-2] = (char)((double)(Double)(args[i])); 
									}
     							}
     							method = ownerClass.getMethod((String)args[1],argsClass);	
							}
							catch(NoSuchMethodException e5){
								throw e5;
							}	
						}
					}	
				}
			}
		}    
      	
     	return method.invoke(args[0], args2);
	}     
	public static Object createInstance(Object[] args) throws Exception {   
     	Class ownerClass = (Class)args[0];
     	Class[] argsClass = new Class[args.length-1];  
     	Object[] args1 = new Object[args.length-1]; 
     	boolean numberTest = false;
     	Constructor method = null; 
  
  		try{
     		for (int i = 1, j = args.length; i < j; i++) {   
		
         		argsClass[i-1] = args[i].getClass(); 
         		args1[i-1] = args[i];  
          		if( argsClass[i-1].equals(Integer.class) ){
					argsClass[i-1] = int.class;
				}
				else if( argsClass[i-1].equals(Double.class) ){
					argsClass[i-1] = double.class;
					numberTest = true;
				}
				else if( argsClass[i-1].equals(Float.class) ){
					argsClass[i-1] = float.class;
				}
				else if( argsClass[i-1].equals(Long.class) ){
					argsClass[i-1] = long.class;
				}
				else if( argsClass[i-1].equals(Character.class) ){
					argsClass[i-1] = char.class;
				}
				else if( argsClass[i-1].equals(Short.class) ){
					argsClass[i-1] = short.class;
				}
				else if( argsClass[i-1].equals(Boolean.class) ){
					argsClass[i-1] = boolean.class;
				}
     		} 
     		
     		method = ownerClass.getConstructor(argsClass);
     	}catch(NoSuchMethodException e1){ 
			if( numberTest){ 
				try{
					for (int i = 1, j = args.length; i < j; i++) {   
						if( args[i].getClass().equals(Double.class) ){
							argsClass[i-1] = int.class;
							args1[i-1] = (int)((double)(Double)(args[i]));  
						}
     				} 
     				method = ownerClass.getConstructor(argsClass);	
				}
				catch(NoSuchMethodException e2){
					try{
						for (int i = 1, j = args.length; i < j; i++) {   
							if( args[i].getClass().equals(Double.class) ){
								argsClass[i-1] = float.class;
								args1[i-1] = (float)((double)(Double)(args[i])); 
							}
     					} 
     					method = ownerClass.getConstructor(argsClass);	
					}
					catch(NoSuchMethodException e3){
						try{
           					for (int i = 1, j = args.length; i < j; i++) {   
								if( args[i].getClass().equals(Double.class) ){
									argsClass[i-1] = short.class;
									args1[i-1] = (short)((double)(Double)(args[i])); 
								}
     						} 
     						method = ownerClass.getConstructor(argsClass);	
						}
						catch(NoSuchMethodException e4){
							try{
          						for (int i = 1, j = args.length; i < j; i++) {   
									if( args[i].getClass().equals(Double.class) ){
										argsClass[i-1] = char.class;
										args1[i-1] = (char)((double)(Double)(args[i])); 
									}
     							}
     							method = ownerClass.getConstructor(argsClass);	
							}
							catch(NoSuchMethodException e5){
								throw e5;
							}	
						}
					}	
				}
			}
		}    
      	
     	return method.newInstance(args1);
	}    
	public static boolean objectIsNumber(Object v){
		if( v instanceof Integer ) return true;
		if( v instanceof Double ) return true;
		if( v instanceof Float ) return true;
		if( v instanceof Short ) return true;
		if( v instanceof Character ) return true;
		if( v instanceof Long ) return true;
		return false;
	} 
	public static boolean objectIsString(Object v){
		return v instanceof String;
	} 
	public static String object2String(Object v){
		return (String)v;
	} 
	public static double object2Number(Object v){
		if( v instanceof Integer ) return ((Integer)v).doubleValue();
		if( v instanceof Double ) return ((Double)v).doubleValue();
		if( v instanceof Float ) return ((Float)v).doubleValue();
		if( v instanceof Short ) return  ((Short)v).doubleValue();
		if( v instanceof Character ) return (double)(char)((Character)v);
		if( v instanceof Long ) ((Long)v).doubleValue();
		return (double)( (Double)(v) );
	} 
}  