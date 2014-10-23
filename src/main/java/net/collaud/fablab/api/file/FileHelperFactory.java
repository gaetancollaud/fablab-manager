package net.collaud.fablab.api.file;

import java.io.File;
import net.collaud.fablab.common.file.AbstractFileHelperFactory;
import net.collaud.fablab.common.file.FileHelper;
import net.collaud.fablab.common.file.FileHelperException;

/**
 *
 * @author gaetan
 */
public class FileHelperFactory extends AbstractFileHelperFactory {

	public static final String DIR_CONFIG = "config/";
	public static final String FILE_CONFIG = "fablab_config.properties";
	public static final int ID_CONFIG = 1;

	private static FileHelperFactory instance;

	protected static FileHelperFactory getInstance() {
		if (instance == null) {
			instance = new FileHelperFactory();
		}
		return instance;
	}

	private FileHelperFactory() {
	}

	public static FileHelper<ConfigFileHelper> getConfig() {
		return getInstance().get(ID_CONFIG);
	}

	@Override
	protected FileHelper create(Integer key) throws FileHelperException {
		String f = null;
		switch (key) {
			case ID_CONFIG:
				f = FILE_CONFIG;
				break;
			default:
				throw getUnknowFileHelperException(key);
		}
		if (new File(f).exists()) {
			return createPropertiesFileHelper(f);
		} else {
			return createPropertiesFileHelper(DIR_CONFIG + "/" + f);
		}
	}

}
