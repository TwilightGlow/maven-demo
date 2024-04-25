package com.example.function;

/**
 * @author zhangjw54
 */
public class FunctionUtil {

    public static ThrowExceptionFunction isTrue(boolean condition) {
        return (message) -> {
            if (!condition) {
                throw new RuntimeException(message);
            }
        };
    }

    public static BranchHandler isTrueOrFalse(boolean condition) {
        return (trueHandler, falseHandler) -> {
            if (condition) {
                trueHandler.run();
            } else {
                falseHandler.run();
            }
        };
    }

    public static PresentOrElseHandler<?> isPresent(String obj) {
        return (presentHandler, absentHandler) -> {
            if (obj != null) {
                presentHandler.accept(obj);
            } else {
                absentHandler.run();
            }
        };
    }
}
