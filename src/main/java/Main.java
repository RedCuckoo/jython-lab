import org.python.core.PyInstance;
import org.python.core.PyInteger;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

public class Main {
    public static void main(String[] args) {
        try(PythonInterpreter pyInterp = new PythonInterpreter()) {
            pyInterp.exec("import re");
            pyInterp.set("filename", new PyString("python-variant/text.txt"));
            pyInterp.set("n", new PyInteger(1));
            pyInterp.exec("file = open(filename, \"r\")");
            pyInterp.exec("text = file.read()");
            pyInterp.exec("text = text.lower()");
            pyInterp.exec("text = re.sub(\"[^\\w\\s]\", \"\", text)");
            pyInterp.exec("word_arr = text.split()");
            pyInterp.exec("dictionary = dict.fromkeys(word_arr, 0)");

            // add amount for each word
            pyInterp.exec("for v in  word_arr:\n" +
                    "\tdictionary[v] = dictionary[v] + 1\n");

            // sort by value in reversed order
            pyInterp.exec("sorted_dictionary = sorted(dictionary.items(), key=lambda kv: kv[1], reverse=True)\n");
            pyInterp.exec("result_dictionary = dict()\n");

            // create a new dictionary to return
            pyInterp.exec("for i in range(n):\n" +
                    "\tresult_dictionary[sorted_dictionary[i][0]] = sorted_dictionary[i][1]\n");
            pyInterp.exec("print(result_dictionary)");
        }
    }
}
