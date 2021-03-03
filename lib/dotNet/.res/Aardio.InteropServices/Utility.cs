using System;
using System.CodeDom.Compiler;
using System.Collections;
using System.Reflection;
using System.Runtime.InteropServices;

namespace Aardio.InteropServices
{
	[ClassInterface(ClassInterfaceType.AutoDispatch), ComVisible(true)]
	public class Utility
	{
		public CCodeCompiler CreateCompiler(string provideType)
		{
			object obj = this.loadAssembly("System").CreateInstance(provideType);
			if (obj == null)
			{
				return null;
			}
			return new CCodeCompiler(obj as CodeDomProvider);
		}

		public object InvokeMember(object assemblyName, string typeName, string methodName, int invokeAttr, object args, object target)
		{
			Assembly assembly = assemblyName as Assembly;
			if (assembly == null)
			{
				assembly = this.loadAssembly(assemblyName as string);
			}
			if (assembly != null)
			{
                
                Type t = assembly.GetType(typeName);
                if ( ((BindingFlags)invokeAttr & BindingFlags.InvokeMethod) == BindingFlags.InvokeMethod)
                {
                    Type tClass = assembly.GetType(typeName + "." + methodName);
                    if (tClass != null && tClass.IsClass && ( (invokeAttr & (16 | 8 | 256)) == (16 | 8 | 256)) )
                    {
                        return tClass.InvokeMember("", (BindingFlags)BindingFlags.CreateInstance | BindingFlags.IgnoreReturn, null, target, (args as ArrayList).ToArray());
                    }

                    ArrayList arr =(args as ArrayList);
                    Type []types = new Type[arr.Count];
                    for (int i = 0; i < arr.Count; i++) 
                    {
                        types[i] = arr[i].GetType();
                    }

                    MethodInfo m = t.GetMethod(methodName, (BindingFlags)invokeAttr | BindingFlags.IgnoreReturn, null, types,null);
                    if (m!=null)
                    {
                        return m.Invoke(target, (args as ArrayList).ToArray());
                    }

                    MethodInfo []ms = t.GetMethods( (BindingFlags)invokeAttr | BindingFlags.IgnoreReturn );
                    for (int i = 0; i < ms.Length; i++)
                    {
                        if(ms[i].Name == methodName && ms[i].GetParameters().Length == arr.Count)
                        {
                            return ms[i].Invoke(target, (args as ArrayList).ToArray());
                        }
                    }
                }
                

                return t.InvokeMember(methodName, (BindingFlags)invokeAttr | BindingFlags.IgnoreReturn, null, target, (args as ArrayList).ToArray());
			}
			return null;
		}

        public object InvokeObjectMember(object target, string methodName, int invokeAttr, object args)
		{
			if (target == null)
			{
				return null;
			}

            Type t = target.GetType();
            if (((BindingFlags)invokeAttr & BindingFlags.InvokeMethod) == BindingFlags.InvokeMethod)
            {
                ArrayList arr = (args as ArrayList);
                Type[] types = new Type[arr.Count];
                for (int i = 0; i < arr.Count; i++)
                {
                    types[i] = arr[i].GetType();
                }

                MethodInfo m = t.GetMethod(methodName, (BindingFlags)invokeAttr | BindingFlags.IgnoreReturn, null, types, null);
                if (m != null)
                {
                    return m.Invoke(target, (args as ArrayList).ToArray());
                }

                MethodInfo[] ms = t.GetMethods((BindingFlags)invokeAttr | BindingFlags.IgnoreReturn);
                for (int i = 0; i < ms.Length; i++)
                {
                    if (ms[i].Name == methodName && ms[i].GetParameters().Length == arr.Count)
                    {
                        return ms[i].Invoke(target, (args as ArrayList).ToArray());
                    }
                }
            }

            return t.InvokeMember(methodName, (BindingFlags)invokeAttr | BindingFlags.IgnoreReturn, null, target, (args as ArrayList).ToArray());
		}

        public Object ParseEnum(object assemblyName, string typeName, string methodName)
        {
            Assembly assembly = assemblyName as Assembly;
            if (assembly == null)
            {
                assembly = this.loadAssembly(assemblyName as string);
            }
            if (assembly != null)
            {
                Type t = assembly.GetType(typeName);
                if (t.IsEnum)
                {
                    return System.Enum.Parse(t, methodName);
                }
            }
            return null;
        }

        public Assembly loadAssembly(string assemblyName)
		{
			try
			{
				Assembly assembly = Assembly.LoadWithPartialName(assemblyName);
				Assembly result = assembly;
				return result;
			}
			catch (SystemException)
			{
			}
			try
			{
				Assembly assembly2 = Assembly.Load(AssemblyName.GetAssemblyName(assemblyName));
				Assembly result = assembly2;
				return result;
			}
			catch (SystemException)
			{
			}
			try
			{
				Assembly assembly3 = Assembly.LoadFrom(assemblyName);
				Assembly result = assembly3;
				return result;
			}
			catch (SystemException)
			{
			}
			try
			{
				Assembly assembly4 = Assembly.LoadFile(assemblyName);
				Assembly result = assembly4;
				return result;
			}
			catch (SystemException)
			{
			}
			return null;
		}
	}
}
