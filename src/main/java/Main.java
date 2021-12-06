import org.python.antlr.ast.Str;
import org.python.core.*;
import org.python.modules._hashlib;
import org.python.util.PythonInterpreter;

import javax.swing.event.InternalFrameListener;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

public class Main {
    public static HashMap<String, Integer> HashMapFromDictionary(PyDictionary dict){
        ConcurrentMap<PyObject, PyObject> map =  dict.getMap();
        HashMap<String, Integer> result = new HashMap<>();

        for (Map.Entry<PyObject, PyObject> i : map.entrySet()){
            result.put(i.getKey().toString(), i.getValue().asInt());
        }

        return result;
    }

    public static HashMap<String, Integer> pythonFunc(String filename, Integer amount) {
        HashMap<String, Integer> result = new HashMap<>();

        PythonInterpreter.initialize(System.getProperties(), new Properties(), new String[]{filename, amount.toString()});
        try (PythonInterpreter pyInterp = new PythonInterpreter()) {
            pyInterp.execfile("python-variant/main.py");

            PyFunction function = (PyFunction) pyInterp.get("task5", PyFunction.class);
            PyDictionary pyResult = (PyDictionary) function.__call__(new PyString(filename), new PyInteger(amount));

//            System.out.println(pyResult);
            result = HashMapFromDictionary(pyResult);

        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(pythonFunc("python-variant/main.py", 3));
    }
}
