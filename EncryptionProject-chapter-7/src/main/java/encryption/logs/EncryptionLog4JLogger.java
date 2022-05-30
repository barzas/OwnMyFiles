package encryption.logs;
import org.apache.logging.log4j.*;


public class EncryptionLog4JLogger {
	
	private static Logger logger = LogManager.getLogger(EncryptionLog4JLogger.class.getName());
	
	public static void info(String log, Class<?> origClass) {
		logger.info(String.format("in class: %s, %s", origClass.getName(), log));
	}
	
	public static void error(String log, Class<?> origClass) {
		logger.error(String.format("in class: %s, %s", origClass.getName(), log));
	}
	
	public static void fatal(String log, Class<?> origClass) {
		logger.error(String.format("in class: %s, %s", origClass.getName(), log));
	}
	
	public static void debug(String log, Class<?> origClass) {
		logger.debug(String.format("in class: %s, %s", origClass.getName(), log));
	}
	
	public static void warn(String log, Class<?> origClass) {
		logger.warn(String.format("in class: %s, %s", origClass.getName(), log));
	}
}