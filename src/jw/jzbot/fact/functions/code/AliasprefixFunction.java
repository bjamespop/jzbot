package jw.jzbot.fact.functions.code;

import jw.jzbot.fact.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by aboyd on 2015-01-02.
 */
public class AliasprefixFunction extends Function {
    @Override
    public void evaluate(Sink sink, ArgumentList arguments, FactContext context) {
        String originalPrefix = arguments.resolveString(0);
        String newPrefix = arguments.resolveString(1);
        // First, build up a dictionary of the aliases we're going to put in place. This prevents us from causing
        // problems by steamrollering over a function we needed to alias (say we ahve functions named "aab" and "ab"
        // and we're aliasing "a" to "").
        Map<String, Function> map = new HashMap<String, Function>();
        for (String functionName: context.getFunctionNames(originalPrefix, null)) {
            map.put(newPrefix + functionName.substring(originalPrefix.length()), context.getFunction(functionName, null));
        }
        // Then actually install the aliases.
        context.getLocalFunctions().putAll(map);
    }

    @Override
    public String getHelp(String topic) {
        return "TBD";
    }
}
