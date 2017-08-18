package util.stepmapping;

import util.handle.AbstractHandle;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StepMapping {

    private static Map<String, Mapping> stepMappingMap = new HashMap<>();
    private static Map<String, Mapping> commandMappingMap = new HashMap<>();

    public static void initializeMapping() throws Exception {

        Reflections reflections = new Reflections("handling");
        Set<Class<? extends AbstractHandle>> classes = reflections.getSubTypesOf(AbstractHandle.class);

        for (Class clazz : classes){

            Method[] methods = clazz.getMethods();
            for (Method method : methods) {

                if (method.isAnnotationPresent(Step.class)) {


                    Step step = method.getAnnotation(Step.class);
                    String stepName = step.value();
                    String commandText = step.commandText();

                    Mapping mapping = new Mapping();
                    mapping.setHandleClassName(clazz.getSimpleName());
                    mapping.setHandleMethod(method.getName());
                    mapping.setStep(stepName);
                    mapping.setCommandText(commandText);

                    if (!stepMappingMap.containsKey(stepName)){
                        stepMappingMap.put(stepName, mapping);
                    }
                    else {
                        throw new Exception(
                                "Error when reading a step \"" + stepName + "\" in class "
                                + clazz.getSimpleName()
                                + "; Step already exists in class "
                                + stepMappingMap.get(stepName).getHandleClassName()
                        );
                    }

                    if (!commandText.equals("")){
                        if (!commandMappingMap.containsKey(commandText)){
                            commandMappingMap.put(commandText, mapping);
                        }
                        else {
                            throw new Exception(
                                    "Error when reading a commandText \"" + commandText + "\" in class "
                                            + clazz.getSimpleName()
                                            + "; CommandText already exists in class "
                                            + commandMappingMap.get(commandText).getHandleClassName()
                            );
                        }
                    }

                }
            }
        }
    }


    public static boolean containsStep(String step){
        return stepMappingMap.containsKey(step);
    }

    public static Mapping getMappingByStep(String step){
        return stepMappingMap.get(step);
    }


    public static boolean containsCommandText(String step){
        return commandMappingMap.containsKey(step);
    }

    public static Mapping getMappingByCommandText(String commandText){
        return commandMappingMap.get(commandText);
    }
}
